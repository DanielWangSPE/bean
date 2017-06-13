package com.wz.test.swing;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/** 
* 
* @author : wangzhi
* @data ：2017年6月12日 下午5:14:26 
* 
*/
public class Test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame= new JFrame();  //创建JFrame
		frame.setLayout(null);  //设置布局为空，即不适用布局
		frame.setBounds(100, 100, 400, 200);  //设置JFrame的位置及宽高
		frame.setTitle("JFrame");
		frame.setResizable(true);  //设置是否允许调整窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //设置关闭按钮的相应动作，关闭窗口时退出程序
		frame.setVisible(true);
		
		JInternalFrame interFrame = new JInternalFrame("框架", true, true, true, true);
		interFrame.setBounds(0, 0, 100, 100);
		interFrame.setVisible(true);
		frame.add(interFrame);
	}

}
