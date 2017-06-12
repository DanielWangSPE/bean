package com.wz.my_tank_war;

public class Bullet implements Runnable{
	boolean life=true;
	int x;
	int y;
	int direction;
	int speed=10;

	//���캯��
	//ȷ���ӵ���λ�úͷ��򣬣�����̹�ˣ�
	public Bullet(int x, int y, int direction) {
		super();
		this.x = x;
		this.y = y;
		this.direction = direction;
	}

	//�������ӵ��̺߳��ӵ��᲻ͣ���˶�
	public void run() {
		//run������.start()֮��ͻ����У���Ϊ����������ѭ���������ӵ�һֱ���ƶ�
		//��Ϊ���̣߳������ӵ�����ʱ��̹�˿��Զ�
		while(true)//�����߳��������ѭ���ﶼҪ�и�˯�ߣ���Ȼռ����Դ̫��
		{

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			switch(direction)
			{
			case 0://��
				y-=speed;
				break;
			case 1://��
				x-=speed;
				break;
			case 2://��
				y+=speed;
				break;
			case 3://��
				x+=speed;
				break;
			}
			//����Ļ�����������Ϊfalse��Ȼ���ֱ��break
			if(x<0||x>400||y<0||y>300)
			{
				this.life=false;
				break;				
			}

		}
	}


}
