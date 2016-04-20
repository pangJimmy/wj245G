package com.psw.wjcheck.rfid245G;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.Set;

import android.os.Handler;
import cn.pda.serialport.SerialPort;
import cn.pda.serialport.Tools;

import com.psw.wjcheck.util.Utils;

/**
 * 2.45G操作管理者
 * @author mac
 *
 */
public class RfidManager {

	private SerialPort mSerialPort ; //串口
	private InputStream mIn ;//串口输入流
	private OutputStream mOut ;//串口输出流
	private int port = 14 ;
	private int baudrate = 9600 ;
	
	private Handler mHandler ;
	
	private String tag = "RfidManager" ;
	private int ss ;
	
	//清除缓存  0a ff 02 40 b5
	//0a ff 03 42 01 b1 命令用于从缓存中获取标签ID号及可写区域内容
	//0a ff XX 50 00 00 00 ...CC命令用于写卡
	
	public RfidManager(){
		try {
			mSerialPort = new SerialPort(port, baudrate) ;//
			mIn = mSerialPort.getInputStream() ;
			mOut = mSerialPort.getOutputStream() ;
			
			mSerialPort.power_5Von() ;//打开电源
			new ReadThread().start() ;
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void start(){
		startFlag = true ;
	}
	
	public void stop(){
		startFlag = false ;
	}
	
	public void close(){
		running = false ;
	}
	
	//设置消息处理器
	public void setHandler(Handler handler){
		this.mHandler = handler ; 
	}
	
	//发送清空指令
	private void sendClear(){
		byte[] cmd = Tools.HexString2Bytes("0aff0240b5") ;
		if(mOut != null){
			try {
				mOut.write(cmd) ;
				mOut.flush() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void sendGetIdContent(){
		byte[] cmd = Tools.HexString2Bytes("0aff034201b1") ;
		if(mOut != null){
			try {
				mOut.write(cmd) ;
				mOut.flush() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//读取串口数据
	private byte[] recv(){
		byte[] buffer = new byte[512] ;
		byte[] recv = null ;
		int size = 0 ;
		if(mIn != null){
			try {
				if(mIn.available() > 0){
					Thread.sleep(50) ;
				}else{
					return recv;
				}
				size = mIn.read(buffer) ;
				if(size > 0){
					recv = new byte[size] ;
					Utils.LogE("RfidManager", Tools.Bytes2HexString(buffer, size)) ;
					System.arraycopy(buffer, 0, recv, 0, size) ;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return recv ;
	}
	
	private boolean running = true ; //线程控制开关
	private boolean startFlag = false ;
	
	
	
	/***
	 * 读数据线程
	 * @author admin
	 *
	 */
	private class ReadThread extends Thread{
		byte[] resp ;
		Map<String , byte[]> map ;//读取到的数据
		@Override
		public void run() {
			super.run();
			while(running){
				try {
				if(startFlag){
//					sendClear() ;
					sendGetIdContent() ;
					resp = recv() ;
					if(resp != null){
						map = resolve(resp, resp.length) ;
					}
					
				}
				
					Thread.sleep(30) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
	}
	
	//将读到数据回传给调用界面
	private void sendMesseage(String id, byte[] data){
		
	}
	
	//包头
	private byte HEAD = (byte) 0x0B ;//数据返回
	private byte ADDR = (byte) 0xFF ;//
	/**
	 * 解析数据
	 * @param buffer
	 * @param len
	 * @return  
	 * 	//写入成功后，读取返回的数据0BFF13000100814403BEA9413131313131000BEC3A4C
		//真实数据解析为GB2312:京A11111,000BEC--INT型-3052
	 */
	private Map<String, byte[]> resolve(byte[] buffer, int len){
		int index = 0 ;//每条数据包的起始位置
		int ps ; 
		int dataLen = 0 ; //数据内容长度
		Map<String, byte[]> map = null ;
		if(len > 3){
			//遍历接收的所有数据
			while(index < len ){
				//判断包头和地址
				if(buffer[index] == HEAD && buffer[index + 1] == ADDR){
					dataLen = buffer[index + 2]&0xff ;
					//长度没有越界
					if(dataLen + 3 <= len){
						
						if(dataLen == 0x13){
							byte[] id = new byte[4] ;
							byte[] content = new byte[12] ;
							System.arraycopy(buffer, index + 5, id, 0, id.length) ;
							System.arraycopy(buffer, index + 9, content, 0, content.length) ;
							Utils.LogE(tag, "ID:   "  + Tools.Bytes2HexString(id, id.length)) ;
							Utils.LogE(tag, "Content:   "  + Tools.Bytes2HexString(content, content.length)) ;
							map.put(Tools.Bytes2HexString(id, 4), content) ;
						}
						index = index + dataLen + 3 ;//指向下一个数据包
					}
				}
			}
		}
		return map ;
	}
	
	//计算校验位
	private  byte getCrc(byte[] recv, int recvLen){
		byte crc = 0 ;
		for(int i = 0 ; i < recvLen; i++){
			crc += recv[i];
		}
		crc = (byte) ((~crc) + 1);
		return crc ;
	}
}
