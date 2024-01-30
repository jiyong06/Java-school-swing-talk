package client;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class SelectFont extends JDialog implements ListSelectionListener, ActionListener {
	
	JTextArea chatArea;
	JLabel userLabel;
	
	String[] name = {"굴림", "굴림체", "궁서", "나눔손글씨붓", "휴먼고딕"};
	String[] style = {"보통", "기울임", "굵게", "굵은 기울임"};
	String[] size = {"8", "10", "12", "16", "20", "24", "28", "32", "36", "40"};
	
	JList<String> fontName = new JList<String>(name);
	JList<String> fontStyle = new JList<String>(style);
	JList<String> fontSize = new JList<String>(size);
	
	
	JLabel sample = new JLabel("AaBbYyZz 한글처리");
	
	JButton btnOk = new JButton("확인");
	JButton btnNo = new JButton("취소");

	
	Font newFont;
	
	SelectFont(JTextArea chatArea, JLabel userLabel){
		
		this.chatArea=chatArea;
		this.userLabel=userLabel;
		
		JPanel p=new JPanel();
		add(p);
		p.setLayout(null);
		
		fontName.setBounds(10,10,100,160);
		fontStyle.setBounds(200,10,100,160);
		fontSize.setBounds(310,10,60,160);
		
		p.add(fontName);
		p.add(fontStyle);
		p.add(fontSize);
		
		fontName.setSelectedIndex(0);
		fontStyle.setSelectedIndex(0);
		fontSize.setSelectedIndex(0);
		
		sample.setBounds(200,180,170,100);
		Border border=BorderFactory.createLineBorder(Color.gray,1);
		sample.setBorder(border);
		p.add(sample);
		
		btnOk.setBounds(200,300,80,30);
		btnNo.setBounds(290,300,80,30);
		p.add(btnOk);
		p.add(btnNo);
		
		fontName.addListSelectionListener(this);
		fontStyle.addListSelectionListener(this);
		fontSize.addListSelectionListener(this);
		
		btnOk.addActionListener(this);
		btnNo.addActionListener(this);
		
		setTitle("폰트값을 변경해보세요");
		setSize(400,400);
		setModal(true);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnOk) {
			
			userLabel.setFont(newFont);
			dispose();
		}
		else if(e.getSource()==btnNo) {
			dispose();
		}
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		String name = fontName.getSelectedValue();
		
		int style = 0;
		switch(fontStyle.getSelectedIndex()) {
		
		case 0:
			style=Font.PLAIN;
			break;
		case 1:
			style=Font.ITALIC;
			break;
		case 2:
			style=Font.BOLD;
			break;
		case 3:
			style=Font.BOLD | Font.ITALIC;
			break;
		}
		
		int size=Integer.parseInt(fontSize.getSelectedValue());
		
		newFont=new Font(name,style,size);
		sample.setFont(newFont);
		
	}

}
