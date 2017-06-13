package com.wz.project.games.my_tank_war;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;





public class MyPanel extends JPanel implements KeyListener,Runnable{
	MyTank myTank=null;
	//��Vectorװ�з�̹��
	Vector<EnemyTank> enemyTanks=new Vector<EnemyTank>();
	int enemyTanksNumber=3;

	public MyPanel(){
		myTank=new MyTank(140,232, 0);//0����������
		//�з�̹�˶���λ��
		for(int i=0;i<enemyTanksNumber;i++){
			EnemyTank enemyTank=new EnemyTank((i)*181+5,0,2);
			enemyTanks.add(enemyTank);
			Thread enemyTankThread=new Thread(enemyTank);
			enemyTankThread.start();
		}
	}


	//paint��������������̹�˺��ӵ��ģ���Ϊ�з����ҷ�̹�˺��ӵ�
	public void paint(Graphics g){
		super.paint(g);
		g.fillRect(0,0,400,300);

		//���Լ�̹��
		if (myTank.life) {
			drawTank(myTank.x, myTank.y, g, myTank.direction, 0);
		}
		//�����Լ��ӵ�
		for(int i=0;i<myTank.bullets.size();i++)
		{
			Bullet bullet=myTank.bullets.get(i);

			if(bullet!=null && bullet.life==true)
			{
				drawBullet(bullet.x, bullet.y, g, bullet.direction, 0);
			}

			if(bullet.life==false)
			{
				myTank.bullets.remove(bullet);
				i--;
			}
		}
		//���з�̹�˺��ӵ�
		for (int i = 0; i < enemyTanks.size(); i++) {
			EnemyTank enemyTank=enemyTanks.get(i);
			if (enemyTank.life) {
				drawTank(enemyTank.x, enemyTank.y, g, enemyTank.direction, 1);
				
				for (int j = 0; j < enemyTank.bullets.size(); j++) {
					Bullet bullet=enemyTank.bullets.get(j);
					if (bullet.life) {
						drawBullet(bullet.x, bullet.y, g, bullet.direction,0);
					}
					else {
						enemyTank.bullets.remove(bullet);
						j--;
					}
				}
			}
			else {
				enemyTanks.remove(enemyTank);
				i--;
			}
			
		}
		

	}

	//�ѻ���Graphics����������֤paint���������drawTank�õ�����һ������
	public void drawTank(int x,int y,Graphics g,int direction,int tankTpye)
	{
		switch(tankTpye)
		{
		case 0://�ҵ�̹��
			g.setColor(Color.yellow);
			break;
		case 1://���˵�̹��
			g.setColor(Color.green);
			break;
		}
		//���ݷ���̹�ˣ�Ҳ���ǲ�ͬ�����̹��
		switch(direction)
		{
		case 0://��
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y-3);
			break;
		case 1://��
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x-3, y+10);
			break;
		case 2://��
			g.fill3DRect(x, y, 5, 30,false);
			g.fill3DRect(x+15,y , 5, 30,false);
			g.fill3DRect(x+5,y+5 , 10, 20,false);
			g.fillOval(x+5, y+10, 10, 10);
			g.drawLine(x+10, y+15, x+10, y+33);
			break;
		case 3://��
			g.fill3DRect(x, y, 30, 5,false);
			g.fill3DRect(x, y+15, 30, 5, false);
			g.fill3DRect(x+5, y+5, 20, 10, false);
			g.fillOval(x+10, y+5, 10, 10);
			g.drawLine(x+15, y+10, x+33, y+10);
			break;			
		}
		this.repaint();//�����൱�ڵݹ�����ˣ�����̹�������ػ�

	}

	public void drawBullet(int x,int y,Graphics g,int direction,int bulletTpye) {
		switch(bulletTpye)
		{
		case 0://�ҵ�̹��
			g.setColor(Color.WHITE);
			break;
		case 1://���˵�̹��
			g.setColor(Color.green);
			break;
		}
		g.fill3DRect(x,y,3,3,false);
		this.repaint();//�����൱�ڵݹ�����ˣ�����̹�������ػ�
		
	}
	public void hit(Bullet bullet,Tank tank) {//����
		switch(tank.direction)
		{
		case 0://���Ϻͳ��º������Ǹ�д��һ��
		case 2:
			if(bullet.x>tank.x && bullet.x<tank.x+20 && bullet.y>tank.y && bullet.y<tank.y+30)//�ӵ�����̹�˾����ӵ���̹�˵ķ�Χ����
			{
				bullet.life=false;
				tank.life=false;
				//System.out.println("aaaa");//������δ�����ľ������
			}
			break;
		case 1://����ͳ���Ҳ�������Ǹ�д��һ��
		case 3:
			if(bullet.x>tank.x && bullet.x<tank.x+30 && bullet.y>tank.y && bullet.y<tank.y+20)
			{
				bullet.life=false;
				tank.life=false;
				//System.out.println("aaaa");//������δ�����ľ������
			}			
		}
	}
	
	public void myAttack() {//�ҷ������з�
		for (int i = 0; i < myTank.bullets.size(); i++) {
			Bullet bullet=myTank.bullets.get(i);
			if (bullet.life) {
				for (int j = 0; j < enemyTanks.size(); j++) {
					EnemyTank enemyTank=enemyTanks.get(j);
					if (enemyTank.life) {
						this.hit(bullet,enemyTank);
					}
				}
			}
		}
	} 
	
	public void enemyAttack() {//�з������ҷ�
		for (int i = 0; i < this.enemyTanks.size(); i++) {
			EnemyTank enemyTank=enemyTanks.get(i);
			if (enemyTank.life) {
				for (int j = 0; j < enemyTank.bullets.size(); j++) {
					Bullet bullet=enemyTank.bullets.get(j);
					if (bullet.life) {
						this.hit(bullet, myTank);
					}
				}
			}
		}
		
	}
	


	public void keyTyped(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_W)//��
		{
			this.myTank.move(0);

		}else if(e.getKeyCode()==KeyEvent.VK_A)
		{
			this.myTank.move(1);

		}else if(e.getKeyCode()==KeyEvent.VK_S)
		{
			this.myTank.move(2);

		}else if(e.getKeyCode()==KeyEvent.VK_D)
		{
			this.myTank.move(3);
		}	

		//���ڣ�ʵ��һ����һ�߿���
		if(e.getKeyCode()==KeyEvent.VK_J)
		{
			//this.mt.fszd();//�����ӵ�
			//�����ӵ���������������
			//bulletss��װ�ӵ���Vector
			if(this.myTank.bullets.size()<8)
			{
				this.myTank.fireBullets();
			}

		}
		this.repaint();
	}


	public void run() {
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.myAttack();
			this.enemyAttack();
			this.repaint();
			
		}
	}



}
