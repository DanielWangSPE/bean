package com.wz.project.games.my_tank_war;

import javax.swing.*;



import java.awt.*;


public class TankWarUI extends JFrame{

	MyPanel myPanel=null;
	
	public static void main(String[] args) {
		new TankWarUI();
	}
	
	public TankWarUI(){
		myPanel=new MyPanel();
		this.add(myPanel);
		this.addKeyListener(myPanel);
		Thread myPanelThread=new Thread(myPanel);
		myPanelThread.start();
		
		this.setSize(600,500);
		this.setLocation(270,170);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

}
