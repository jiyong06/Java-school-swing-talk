package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class Client extends JFrame implements ActionListener{
	
	String userId;
	
	JPanel jp_top, jp_chat, jp_bottom;
	
	JLabel userLabel;
	
	JTextArea chatArea;
	JScrollPane chatScroll;
	
	JTextArea txtSend;
	JButton btn_imo;
	
	//메뉴 만들기
	JMenuBar menubar;
	JMenu menu;
	JMenuItem[] menuItem;
	
	Client(String userId){
		
		this.userId = userId;
		
		createMenu();
		
		setLayout(null); //절댓값 레이아웃을 활용해서 컴포넌트를 배치
		
		draw_top_panel(); //화면 상단
		draw_chat_panel();
		draw_bottom_panel();
		
		setTitle("Robot Chat");
		setSize(315, 640);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		//창이 생성되자 마자 Listener 스레드를 호출
		Listener listener = new Listener(userId, chatArea);
		Thread thread = new Thread(listener);
		thread.start();
				
		
	}

	private void draw_bottom_panel() {
		
		jp_bottom = new JPanel();
		jp_bottom.setBounds(0, 480, 300, 100);
		jp_bottom.setBackground(Color.gray);
		
		jp_bottom.setLayout(null);
		
		txtSend = new JTextArea();
		txtSend.setLineWrap(true);
		txtSend.setBounds(4, 4, 215, 91);
		
		btn_imo = new JButton("♡");
		btn_imo.setBounds(230, 4, 60, 30);
		
		add(jp_bottom);
		jp_bottom.add(txtSend);
		jp_bottom.add(btn_imo);
		
		//키보드 이벤트를 나중에 달아 줄 예쩡
	}

	private void draw_chat_panel() {
		
		jp_chat = new JPanel();
		jp_chat.setBounds(0, 40,  300,  440);
		jp_chat.setLayout(new GridLayout(0, 1));
		
		chatArea = new JTextArea();
		chatArea.setBackground(Color.pink);
		
		chatScroll = new JScrollPane(chatArea);
		chatScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		add(jp_chat);
		jp_chat.add(chatScroll);
	}

	private void draw_top_panel() {
		
		jp_top = new JPanel();
		jp_top.setBounds(0, 0, 300, 40);
		jp_top.setBackground(Color.yellow);
		
		userLabel = new JLabel();
		userLabel.setText(userId);
		userLabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		
		add(jp_top);
		jp_top.add(userLabel);
		
	}

	private void createMenu() {
		
		menubar = new JMenuBar();
		menu = new JMenu("설정");
		
		menuItem = new JMenuItem[5];
		String[] menuTitle = {"이름변경", "배경변경"	,"폰트변경", "서버주소변경", "종료"};	
		
		for(int i = 0 ; i < menuItem.length ; i++) {
			
			menuItem[i] = new JMenuItem(menuTitle[i]);
			menuItem[i].addActionListener(this);
			menu.add(menuItem[i]);
			
			
		}
		
		menubar.add(menu);
		setJMenuBar(menubar);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==menuItem[0]) {
			
			userId=JOptionPane.showInputDialog("새 이름을 입력하세요");
			
			if(userId.trim().length() !=0) {
				userLabel.setText(userId);
			}
			
		}
		else if(e.getSource()==menuItem[1]) {
			
			SelectRGB selectRGB=new SelectRGB(chatArea);
			
		}
		else if(e.getSource()==menuItem[2]) {
			
			SelectFont selectFont=new SelectFont(chatArea, userLabel);
			
		}
		else if(e.getSource()==menuItem[3]) {
			
			
		}
		else if(e.getSource()==menuItem[4]) {
			
			
		}

		
		
	}
}
