package client;

import java.awt.Color;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;

public class SelectRGB extends JDialog implements AdjustmentListener, ActionListener {
	
	JTextArea chatArea;
	
	JScrollBar scroll_r,scroll_g,scroll_b;
	JLabel label_r,label_g,label_b;
	JLabel sample;
	JButton btnOk,btnNo;
	
	Color initColor;
	
	SelectRGB(JTextArea chatArea) {
		
		this.chatArea=chatArea;
		
		initColor=chatArea.getBackground();
		
		JPanel p=new JPanel();
		add(p);
		p.setLayout(null);
		
		
		scroll_r=new JScrollBar(Scrollbar.HORIZONTAL, 127, 1, 0, 256);
		scroll_g=new JScrollBar(Scrollbar.HORIZONTAL, 127, 1, 0, 256);
		scroll_b=new JScrollBar(Scrollbar.HORIZONTAL, 127, 1, 0, 256);
		
		scroll_r.setBounds(10,40,300,20);
		scroll_g.setBounds(10,70,300,20);
		scroll_b.setBounds(10,100,300,20);
		
		p.add(scroll_r);
		p.add(scroll_g);
		p.add(scroll_b);
		
		label_r=new JLabel("RED");
		label_g=new JLabel("GREEN");
		label_b=new JLabel("BLUE");
		
		label_r.setBounds(320,40,50,20);
		label_g.setBounds(320,70,50,20);
		label_b.setBounds(320,100,50,20);
		
		p.add(label_r);
		p.add(label_g);
		p.add(label_b);
		
		sample=new JLabel("SAMPLE");
		sample.setBounds(400,40,80,80);
		sample.setOpaque(true);
		sample.setBackground(Color.black);
		p.add(sample);
		
		btnOk=new JButton("확인");
		btnNo=new JButton("취소");
		
		btnOk.setBounds(300,150,80,20);
		btnNo.setBounds(400,150,80,20);
		
		p.add(btnOk);
		p.add(btnNo);
		
		scroll_r.addAdjustmentListener(this);
		scroll_g.addAdjustmentListener(this);
		scroll_b.addAdjustmentListener(this);
		
		btnOk.addActionListener(this);
		btnNo.addActionListener(this);
		
		setTitle("RGB 값을 조절하여 배경색을 선택하세요");
		setSize(500,250);
		setModal(true);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==btnOk) {
			
			dispose();
			
		}
		else if(e.getSource()==btnNo) {
			
			chatArea.setBackground(initColor);
			dispose();
			
		}
		
	}

	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		
		if(e.getSource()==scroll_r) {
			int value=scroll_r.getValue();
			label_r.setText(Integer.toString(value));
		}
		else if(e.getSource()==scroll_g) {
			int value=scroll_g.getValue();
			label_g.setText(Integer.toString(value));
		}
		else if(e.getSource()==scroll_b) {
			int value=scroll_b.getValue();
			label_b.setText(Integer.toString(value));
		}
		
		Color color=new Color(scroll_r.getValue(), scroll_g.getValue(), scroll_b.getValue());
		
		sample.setBackground(color);
		chatArea.setBackground(color);
		
	}

}
