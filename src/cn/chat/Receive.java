package cn.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收数据线程
 * @author yuanzhixiangsuse
 *
 */
public class Receive implements Runnable {
	private DataInputStream dis;
	private boolean isRuning=true;
	public Receive() {
		
	}
    public Receive(Socket client) {
		try {
			dis=new DataInputStream(client.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		isRuning=false;
		CloseUtil.closeAll(dis);
		}
	}
    public String receive() {
    	String msg="";
		try {
			msg=dis.readUTF();
		} catch (IOException e) {
			isRuning=false;
			CloseUtil.closeAll(dis);
		}
		return msg;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (isRuning) {
			System.out.println(receive());
			
		}
	}

}
