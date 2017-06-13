package com.wz.common.tools;

/** 
* 计算贷款的工具类
* @author : wangzhi
* @data ：2017年6月7日 下午1:26:37 
* 
*/
public class LoanUtils {
	
	// 月利率
	public static double ylv_sd = 0.04655 / 12;
	public static double ylv_gjj = 0.0325 /12;
	public static double ylv_lc = 0.06 / 12;
	//贷款周期
	public static int zhouqi = 360;
	//还贷后收入
	public static double sr = 5000;
	//提前还款额
	public static double hk = 100000;
	
	/**
	 * 计算幂运算
	 * @param dishu
	 * @param zhishu
	 * @return
	 */
	public static double chengf(double dishu, int zhishu){
		double result = 1;
		for(int i = 0; i < zhishu; i++){
			result *= dishu;
		}
		return result;
	}
	
	/**
	 * 计算商贷等额本息的月还款额
	 * @return 结果保留2位有效数字
	 */
	public static double paySD(double je_sd){
		double pay = ((int)(chengf((1 + ylv_sd), zhouqi) * je_sd * ylv_sd / (chengf((1 + ylv_sd), zhouqi) - 1) * 100)) / 100.00;
		return pay;
	}
	
	public static double newPaySD(double je_sd,int zq){
		double pay = ((int)(chengf((1 + ylv_sd), zq) * je_sd * ylv_sd / (chengf((1 + ylv_sd), zq) - 1) * 100)) / 100.00;
		return pay;
	}
	
	/**
	 * 计算公积金等额本息的月还款额
	 * @return 结果保留2位有效数字
	 */
	public static double payGJJ(double je_gjj){
		double pay = ((int)(chengf((1 + ylv_gjj), zhouqi) * je_gjj * ylv_gjj / (chengf((1 + ylv_gjj), zhouqi) - 1) * 100)) / 100.00;
		return pay;
	}
	
	
	/**
	 * 逐月打印欠贷情况
	 */
	public static void printMeg(){
		
		// 贷款金额
		double je_sd = 270000;
		double je_gjj = 400000;
		
		double pay_sd = paySD(je_sd);
		double pay_gjj = payGJJ(je_gjj);
		
		System.out.println("每月还款为：" + (pay_sd + pay_gjj) + " RMB;");
		double caifu = 0;
		double qian_sd = je_sd;
		double qian_gjj = je_gjj;
		for(int i = 0; i <= zhouqi; i++){
			if(caifu > hk && qian_sd > hk){
				qian_sd -= hk;
				caifu -= hk;
				je_sd = qian_sd;
				sr += 544;
				pay_sd = newPaySD(je_sd, zhouqi - i);
				System.out.println("第" + i + "个月提前还款10W，财富降为：" + caifu + " ,商贷为：" + qian_sd);
			}
			System.out.println("第" + i + "个月后，还公积金：" + pay_gjj + "，还商贷:" + pay_sd + ",商贷欠款为：" + qian_sd + " RMB，公积金欠款为：" + qian_gjj + " RMB,财富为：" + caifu);
			caifu = caifu * (1 + ylv_lc) + sr;
			qian_sd = ((int)((qian_sd * (1 + ylv_sd) - pay_sd) * 100)) / 100.00;
			qian_gjj = ((int)((qian_gjj * (1 + ylv_gjj) - payGJJ(je_gjj)) * 100)) / 100.00;
		}
		
	}
}
