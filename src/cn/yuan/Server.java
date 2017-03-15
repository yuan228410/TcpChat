package cn.yuan;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket serverSocket=new ServerSocket(8888);
		Socket socket=serverSocket.accept();
		System.out.println("一个客户端一链接");
		String string="欢迎使用";
		//BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		//bufferedWriter.write(string);
		
		//bufferedWriter.flush();
		DataOutputStream dataOutputStream=new DataOutputStream(socket.getOutputStream());
		dataOutputStream.writeUTF(string);
		
	}

}
