package cn.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据线程
 * @author yuanzhixiangsuse
 *
 */
public class Send implements Runnable {
	//控制台输入流
	private BufferedReader console;
	//管道输出流
	private DataOutputStream dos;
	//控制线程标志
	private boolean isRuning=true;
	private String name;
	public  Send() {
		console=new BufferedReader(new InputStreamReader(System.in));
	}
    public	Send(Socket client,String name)
	{
		this();
		
		try {
			dos=new DataOutputStream(client.getOutputStream() );
			this.name=name;
			send(this.name);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			isRuning=false;
			CloseUtil.closeAll(dos,console);
		}
	}
    private String getMsgFromConsole() {
		try {
			return console.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
    /**
     * 从控制台发送接收数据
     * 发送数据
     */
    public void send(String msg) {
		if(msg!=null && !msg.equals(""))
		{
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				isRuning=false;
				CloseUtil.closeAll(dos,console);
			}
		}
	}
	@Override
	public void run() {
		// 线程体
		while (isRuning) {
			send(getMsgFromConsole());
			
		}
	}
}
