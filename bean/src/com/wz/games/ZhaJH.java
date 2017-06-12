package com.wz.games;

import java.util.Scanner;

/** 
* 
* @author : wangzhi
* @data ：2017年6月8日 下午3:28:42 
* 
*/
public class ZhaJH {
	
	String[] pai_xing = {"baozi", "shunzi", "duizi", "putong"};
	
	/**
	 * 打印规则
	 */
	public static void printRules(){
		//有戏规则
		System.out.println("游戏规则：共52张普通牌，牌面为2,3,4,5,6,7,8,9,10,J,Q,K,A之一，大小递增，各四张； 每人抓三张牌。两人比较手中三张牌大小，大的人获胜。");
        System.out.println("对于牌型的规则如下：");
        System.out.println("1.三张牌一样即为豹子");
        System.out.println("2.三张牌相连为顺子（A23不算顺子）");
        System.out.println("3.有且仅有两张牌一样为对子 豹子>顺子>对子>普通牌型 在牌型一样时，比较牌型数值大小");
        System.out.println("谁输谁赢：1 --代表玩家1赢;0 --代表 平局   ;-1 --代表玩家2赢 ;-2 --代表不合法的输入");
	}
	
	/**
	 * 判断输入合法性
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static boolean isValid(String num1, String num2){
		String reg = "([2-9JQKA]|10){3}";// 正则匹配，只能出现2,3,4,5,6,7,8,9,10,J,Q,K,A，并且一共只能出现3次
        boolean a = num1.matches(reg);
        boolean b = num2.matches(reg);
        
        if(a == false || b == false){
        	return false;
        }else
        	return true;
	}
	
	/**
	 * 将字符串切分为字符数组
	 * @param num
	 * @return
	 */
	public static String[] getStrArray(String num){
		int length = num.length();
		String[] nums = new String[3];
		num.toUpperCase();
		if(length == 3){
			for(int i = 0; i < length; i++){
				nums[i] = num.substring(i, i + 1);
			}
		}else if(length == 4){
			int index = num.indexOf(1);
			if(index == 0){
				nums[0] = "10";
				nums[1] = num.substring(2, 3);
				nums[2] = num.substring(3);
			}else if(index == 1){
				nums[0] = num.substring(0, 1);
				nums[1] = "10";
				nums[2] = num.substring(3);
			}else if(index == 2){
				nums[0] = num.substring(0, 1);
				nums[1] = num.substring(1, 2);
				nums[2] = "10";
			}
		}else if(length == 5){
			int first = num.indexOf(1);
			int last = num.lastIndexOf(1);
			int dis = last - first;
			if(dis > 2){
				nums[0] = nums[2] = "10";
				nums[1] = num.substring(2, 3);
			}else{
				if(first == 0){
					nums[0] = nums[1] = "10";
					nums[2] = num.substring(2, 3);
				}else if(first == 2){
					nums[0] = num.substring(0, 1);
					nums[1] = nums[2] = "10";
				}
			}
		}else{
			for (int i = 0; i < nums.length; i++) {
                nums[i] = "10";
            }
		}
		return nums;
	}
	
	/**
	 * 将字符串数组转为整型数组
	 * @param nums
	 * @return
	 */
	public static int[] changeToNum(String[] nums){
		int[] result = new int[3];
		for(int i = 0; i < nums.length; i++){
			String num = nums[i];
			switch(num) {
			case "2":
				result[i] = 2;
			case "3":
				result[i] = 3;
			case "4":
				result[i] = 4;
			case "5":
				result[i] = 5;
			case "6":
				result[i] = 6;
			case "7":
				result[i] = 7;
			case "8":
				result[i] = 8;
			case "9":
				result[i] = 9;	
			case "10":
				result[i] = 10;
			case "J":
				result[i] = 11;
			case "Q":
				result[i] = 12;
			case "K":
				result[i] = 13;
			case "A":
				result[i] = 14;
			}
		}
		return 	result;
	}
	
	/**
	 * 数组从大到小排序
	 * @param num
	 * @return
	 */
	public static int[] paixu(int[] num){
		
		int biggest = num[0];
		int smallest = num[0];
		for(int i = 1; i < num.length; i++){
			if(num[i] > biggest){
				biggest = num[i];
			}else
				smallest = num[i];
		}
		int zhongjian = num[0] + num[1] + num[2] - biggest - smallest;
		int[] desc = {biggest, zhongjian, smallest};
		return desc;
	}
	
	/**
	 * 获取牌型
	 * @param num
	 * @return
	 */
	public static int getPaiXing(String[] num){
		int[] pai = paixu(changeToNum(num));
		if(pai[0] == pai[1] && pai[0] == pai[2]){
			return 0;
		}else if(pai[0] == pai[1] && pai[0] == pai[2]){
			return 2;
		}else if(pai[0]-pai[1] == 1 && pai[1]-pai[2] == 1){
			return 1;
		}else
			return 3;
	}
	
	/**
	 * 比大小
	 * @param num1
	 * @param num2
	 * @return
	 */
	public static int big_or_small(String[] num1, String[] num2){
		int paixing1 = getPaiXing(num1);
		int paixing2 = getPaiXing(num2);
		//牌型碾压
		if(paixing1 > paixing2){
			return 1;
		}else if(paixing1 < paixing2){
			return 2;
		//牌型相同
		}else{
			int big1 = paixu(changeToNum(num1))[0];
			int big2 = paixu(changeToNum(num2))[0];
			//牌型相同，比最大牌
			if(big1 > big2){
				return 1;
			}else if(big1 < big2){
				return 2;
			//牌型相同，最大牌相同
			}else{
				//比对几
				if(getPaiXing(num1) == 2){
					int last1 = paixu(changeToNum(num1))[2];
					int last2 = paixu(changeToNum(num2))[2];
					if(last1 > last2){
						return 1;
					}else if(last1 < last2){
						return 2;
					}else
						return 0;
				//顺子
				}else if(getPaiXing(num1) == 1){
					return 0;
				//单牌
				}else{
					int middle1 = paixu(changeToNum(num1))[1];
					int middle2 = paixu(changeToNum(num2))[1];
					if(middle1 > middle2){
						return 1;
					}else if(middle1 < middle2){
						return 2;
					//大牌、二牌都相同
					}else{
						int last1 = paixu(changeToNum(num1))[2];
						int last2 = paixu(changeToNum(num2))[2];
						if(last1 > last2){
							return 1;
						}else if(last1 < last2){
							return 2;
						}else
							return 0;
					}
				}
			}
		}
		
	}
	
	public static void main(String[] args){
		//宣读有戏规则
		printRules();
		boolean isContinue = true;
		Scanner scanner = new Scanner(System.in);
		while(isContinue){
			//2.分别出牌
			
	        System.out.println("请玩家1出牌：");
	        String num1 = scanner.next();
	        System.out.println("请玩家2出牌：");
	        String num2 = scanner.next();
	        
	        
	        //判断是否合法
	        boolean flag = isValid(num1, num2);
	        if(!flag){
	        	System.out.println("-2");
	        }else{
	        	String[] player1 = new String[3];
	        	String[] player2 = new String[3];
	        	player1 = getStrArray(num1);
	        	player2 = getStrArray(num2);
	        	
	        	//判断牌型大小
	        	int result = big_or_small(player1, player2);
	        	System.out.println(result);
	        }
		}
		
        //是否继续
        System.out.println("是否继续？输入N或n退出，其他任意键继续！");
        String string = scanner.next();
        string=string.toUpperCase();
        if("N".equals(string)){
            isContinue=false;
        }
	}
}
