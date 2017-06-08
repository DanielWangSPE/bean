package com.wz.mode;
/** 
* 单例模式演示
* @author : wangzhi
* @data ：2017年6月8日 上午8:43:44 
* 
*/
public class SingleTon {
	
	private static int num;
	
	//私有化构造方法，阻止该对象在别的对象中被创建
	private SingleTon(){}
	
	//在类内部创建一个对象
	private static SingleTon s = new SingleTon();
	
	//提供一个公开对外访问的可以返回该类对象的方法
	public static SingleTon getInstance(){
		return s;
	}
	
	public static void set(int num){
		s.num = num;
	}
	
	public static int get(){
		return num;
	}
}
