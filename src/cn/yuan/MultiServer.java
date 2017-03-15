package cn.yuan;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=new ServerSocket(8888);
		while (true) {
			Socket socket=serverSocket.accept();
			System.out.println("一个客户端一链接");
			String string="欢迎使用";
			//BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			//bufferedWriter.write(string);
			
			//bufferedWriter.flush();
			DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
			dataOutputStream.writeUTF(string);
			dataOutputStream.flush();		
		}		
	}

}
