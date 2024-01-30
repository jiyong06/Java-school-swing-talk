package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JTextArea;

public class Listener implements Runnable{
	
	String userId;
	JTextArea clientText;
	
	Socket socket;
	
	BufferedReader br;
	BufferedWriter bw;
	
	public Listener(String userId, JTextArea clientText) {
		this.userId = userId;
		this.clientText = clientText;
		
		try {
			socket=new Socket("127.0.0.1",2023);
			
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			bw=new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void run() {
		
		try {
			
			bw.write("Hello Java\n");
			bw.flush();
			
			clientText.append(br.readLine()+"\n");
			clientText.append(br.readLine()+"\n");
			clientText.append(br.readLine()+"\n");
			
			bw.close();
			br.close();
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}