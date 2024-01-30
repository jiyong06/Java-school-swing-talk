package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Appmain extends JFrame implements ActionListener{
	
	JButton btn_login;
	JTextField txt_login;

	Appmain(){
		
		JPanel p = new JPanel();
		p.setBackground(Color.yellow);
		p.setLayout(null);
		
		btn_login = new JButton("확인");
		txt_login = new JTextField("20207 박지용");
		txt_login.setBounds(50, 400, 200, 30);
		btn_login.setBounds(50, 440, 200, 30);
		
		//이미지 열기
		ImageIcon image = new ImageIcon("img/robot.png");
		JLabel profile = new JLabel(image);
		
		profile.setBounds(70, 160, 160, 160);

		add(p);
		p.add(txt_login);
		p.add(btn_login);
		p.add(profile);
		
		btn_login.addActionListener(this);
		
		setTitle("RobotChat Login");
		setSize(320, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Appmain();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btn_login) {
			
			String userId = txt_login.getText().trim();
			
			if(userId.length()==0) {
				//메세지 창 띄우는 명령어 ( 암기 ) 
				JOptionPane.showMessageDialog(null, "아이디를 입력하세요");
			}else {
				new Client(userId);
				dispose();
			}		
		}
	}
}
