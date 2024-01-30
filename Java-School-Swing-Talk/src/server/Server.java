package server;

import java.awt.Color;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Server extends JFrame{
	
	//멤버변수 선언
	JTextArea serverText;
	JScrollPane serverScroll;
	
	//소켓 통신
	ServerSocket serverSocket;
	Socket socket;

	Server() {
		
		init();  //화면을 그려줘
		ready(); //통신 준비
		
		
	}

	private void init() {
		
		JPanel p = new JPanel();
		
		serverText = new JTextArea(23,38);
		serverText.setBackground(Color.yellow);
		
		serverScroll = new JScrollPane(serverText);
		serverScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(p);
		p.add(serverScroll);
		

		
		//기본세팅
		setTitle("Chat Server");
		setSize(400, 415);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
	}

	private void ready() {
		
		
		try {
			
			Log("Server", " 서버소켓 생성, 포트번호 2023");
			serverSocket = new ServerSocket(2023); //포트번호 2023
						
			while(true) {
				
				Log("Server", " 클라이언트의 연결 요청을 수신 대기 중입니다.");
				socket = serverSocket.accept(); //클라이언트의 요청이 있을 때 까지 계속 대기, 요청이 없으면 다음으로 진행 x
				
				Log("Server", " 클라이언트의 요청을 Accept");
				
				ServerReciever serverReciever = new ServerReciever(socket, serverText);
				Thread thread = new Thread(serverReciever);
				thread.start();
				
				Log("Server", "클라이언트의 소켓 스레드 생성");
			
			
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

	public static void main(String[] args) {
		
		new Server();

	}
	public void Log(String tag, String log) {
		String log_message = "[" + tag + "]" + log + "\n";
		serverText.append(log_message);
	}

}
