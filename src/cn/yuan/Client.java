package cn.yuan;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket=new Socket("localhost", 8888);
		//BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//String string=bufferedReader.readLine();
		DataInputStream dataInputStream=new DataInputStream(socket.getInputStream());
		System.out.println(dataInputStream.readUTF());
	}
}
