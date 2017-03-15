package cn.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
	private List<MyChannel> all=new ArrayList<MyChannel>();
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new Server().start();
	}

	public void start() throws IOException {
		ServerSocket serverSocket = new ServerSocket(8888);
		System.out.println("服务器已启动监听!");
		while (true) {
			Socket socket = serverSocket.accept();
			System.out.println("一个客户端一链接");
			MyChannel myChannel=new MyChannel(socket);
			new Thread(myChannel).start();
			all.add(myChannel);
		}
	}

	/**
	 * 一个客户端一条道路 输入流 输出流 接收数据 发送数据
	 * 
	 * @author yuanzhixiangsuse
	 *
	 */
	private class MyChannel implements Runnable {
		private DataOutputStream dos;
		private DataInputStream dis;
		private boolean isRuning = true;
		private String name;
		public MyChannel(Socket client) {
			// TODO Auto-generated constructor stub
			try {
				dos = new DataOutputStream(client.getOutputStream());
				dis = new DataInputStream(client.getInputStream());
				this.name=dis.readUTF();
				this.Send("欢迎进入聊天室");
				this.sendOthers(this.name+"进入聊天室");
				System.out.println(this.name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRuning = false;
				CloseUtil.closeAll(dis, dos);
			}
		}

		/**
		 * 读取数据
		 * 
		 * @return
		 */
		private String Receive() {
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				isRuning = false;
				CloseUtil.closeAll(dis);
				all.remove(this);
			}
			return msg;
		}

		private void Send(String msg) {
			if (msg != null && !msg.equals("")) {
				try {
					dos.writeUTF(msg);
					dos.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					isRuning = false;
					CloseUtil.closeAll(dos);
					all.remove(this);
				}

			}
		}
		/**
		 * 发送给其他客户端
		 */
		private  void sendOthers(String msg) {
			// TODO Auto-generated method stub
			for(MyChannel channel:all)
			{
				if(channel==this)
				{
					continue;
				}
				channel.Send(msg);
			}
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isRuning) {
				sendOthers(Receive());
			}
		}

	}
}
