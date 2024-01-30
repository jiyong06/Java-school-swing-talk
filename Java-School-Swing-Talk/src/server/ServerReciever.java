package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class ServerReciever implements Runnable{
	
	Socket socket;
	JTextArea serverText;
	
	BufferedReader br;
	BufferedWriter bw;
	
	
	public ServerReciever(Socket socket, JTextArea serverText) {
		this.socket = socket;
		this.serverText = serverText;
		
		try {
			
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			Log("Server","Input, Output Stream 생성");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public void run() {
		
		String str;
		
		try {
			
			str=br.readLine();
			
			bw.write(str+"1\n");
			bw.flush();
			bw.write(str+"2\n");
			bw.flush();
			bw.write(str+"3\n");
			bw.flush();
			
			bw.close();
			br.close();
			socket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void Log(String tag, String log) {
		String log_message = "[" + tag + "]" + log + "\n";
		serverText.append(log_message);
	}

}