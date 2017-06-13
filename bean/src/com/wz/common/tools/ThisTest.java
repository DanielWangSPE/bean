package com.wz.common.tools;
/** 
* 
* @author : wangzhi
* @data ：2017年6月12日 上午10:09:06 
* 
*/
public class ThisTest extends F{

	ThisTest(){
		System.out.println("s");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisTest a = new ThisTest();
		
	}

}

class F {
	F(){
		System.out.println("f");
	}
}