package com.wz.test.thread;
/** 
* 
* @author : wangzhi
* @data ：2017年6月13日 上午9:08:26 
* 
*/
public class ThreadTest implements Runnable{
	
	private Thread t;
	private String threadName;
	
	ThreadTest(String name){
		threadName = name;
		System.out.println("正在创建线程..." + threadName);
	}
	
	public void run(){
		System.out.println("正在运行线程..." + threadName);
		try {
			for(int i = 0; i < 4; i++){
				System.out.println("运行线程" + threadName + "," + i);
				Thread.sleep(100);
			} 
		}catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("线程" + threadName + "被迫中止");
		}
		System.out.println("线程" + threadName + "结束");
	}
	
	public void start(){
		System.out.println("正在启动线程" + threadName);
		if(t == null){
			t = new Thread(this, threadName);
			t.start();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*ThreadTest t1 = new ThreadTest("thread-1");
		t1.start();
		
		ThreadTest t2 = new ThreadTest("thread-2");
		t2.start();*/
		
		Chat m = new Chat();
		new T1(m);
		new T2(m);
	}

}

class Chat{
	boolean flag = false;
	
	public synchronized void Question(String msg){
		if(flag){
			try {
				Thread.sleep(1000);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(msg);
        flag = true;
        notify();
	}
	
	public synchronized void Answer(String msg){
		if(!flag){
			try {
				Thread.sleep(1000);
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(msg);
        flag = false;
        notify();
	}
}

class T1 implements Runnable{
	Chat m;
	String[] s1 = { "Hi", "How are you ?", "I am also doing fine!" };
	
	public T1(Chat m){
		this.m = m;
		new Thread(this, "Question").start();
	}
	
	public void run(){
		
		for (int i = 0; i < s1.length; i++) {
			m.Question(s1[i]);
	    }
	}
}

class T2 implements Runnable{
	Chat m;
	String[] s2 = { "Hi", "I am good, what about you?", "Great!" };
	
	public T2(Chat m){
		this.m = m;
		new Thread(this, "Answer").start();
	}
	
	public void run(){
		
		for (int i = 0; i < s2.length; i++) {
			m.Answer(s2[i]);
	    }
	}
}


