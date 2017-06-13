package com.wz.project.games.my_tank_war;

public class MyTank extends Tank{

	public MyTank(int x, int y) {
		super(x, y);
	}
	public MyTank(int x, int y, int direction) {
		super(x, y, direction);
		this.x = x;
		this.y = y;
		this.direction = direction;
	}


	public void move(int direction) {
		this.direction=direction;
		switch(this.direction){
		case 0://����
			if (y>speed) {
				y-=speed;
			}
			break;
		case 1://����
			if (x>0) {
				x-=speed;
			}
			break;
		case 2://����
			if (y<265){
				y+=speed;
			}
			break;
		case 3://����
			if (x<365){
				x+=speed;
			}
			break;
		}
	}

	public void fireBullets() {
		if (this.life) {
			switch(this.direction){
			case 0://����
				bullet=new Bullet(x+10,y,0);
				bullets.add(bullet);
				break;
			case 1://����
				bullet=new Bullet(x,y+10,1);
				bullets.add(bullet);
				break;
			case 2://����
				bullet=new Bullet(x+10,y+30,2);
				bullets.add(bullet);
				break;
			case 3://����
				bullet=new Bullet(x+30,y+10,3);
				bullets.add(bullet);
				break;
			}
			//���������䣬ֻҪ�����ӵ����ӵ����������˶�
			Thread bulletThread=new Thread(bullet);
			bulletThread.start();
		}

	}

	public void run() {
		super.run();

	}

}
