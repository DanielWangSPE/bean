package com.wz.test.mode;
/** 
* 
* @author : wangzhi
* @data ：2017年6月8日 上午8:51:52 
* 
*/
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SingleTon s1 = SingleTon.getInstance();
		SingleTon s2 = SingleTon.getInstance();
		System.out.println(s1 == s2);
		s1.set(1);
		s2.set(2);
		System.out.println(s1.get());
		System.out.println(s2.get());
	}

}
