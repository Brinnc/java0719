package org.sp.app0719.client.view;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class MainFrame extends JFrame{
	Page[] pages; //이 프로그램에서 사용할 페이지들을 담게될 배열
	public static final int LOGIN=0;
	public static final int JOIN=1;
	
	LoginPage loginPage;
	
	public MainFrame() {
		pages=new Page[2];
		pages[0]=new LoginPage(this);
		pages[1]=new JoinPage(this);
		
		setLayout(new FlowLayout());
		
		for(int i=0; i<pages.length; i++) {
			add(pages[i]);
			
		}
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		
		showHide(LOGIN);
		
		
	}
	
	//화면 전환(페이지 전환)
	//pages[0]
	//pages[1]
	//호출방법: showHide(LOGIN); -> 로그인페이지만 보여짐
	//호출방법: showHide(JOIN); -> 회원가입페이지만 보여짐
	public void showHide(int n) {
		for(int i=0; i<pages.length; i++) {
			if(i==n) {
				pages[i].setVisible(true);
				
			}else {
				pages[i].setVisible(false);
				
			}
		}
		pack();
	}
	
	
	public static void main(String[] args) {
		new MainFrame();
	}
}
