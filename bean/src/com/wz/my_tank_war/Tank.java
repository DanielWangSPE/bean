package com.wz.my_tank_war;

import java.util.Vector;

public class Tank implements Runnable{
	boolean life=true;
	int x;
	int y;
	int direction;
	int speed=5;
	//�ӵ�
	Bullet bullet=null;
	Vector<Bullet> bullets=new Vector<Bullet>();
	
	//���캯��
	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Tank(int x, int y, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	
	
	public void run() {
		
	}

	public boolean isLife() {
		return life;
	}

	public void setLife(boolean life) {
		this.life = life;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public Vector<Bullet> getBullets() {
		return bullets;
	}

	public void setBullets(Vector<Bullet> bullets) {
		this.bullets = bullets;
	}
	
	
	
	
	
}
