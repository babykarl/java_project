package cn.com.xgh.rpn_calculator;

import java.util.Scanner;

import cn.com.xgh.rpn_calculator.calculator.impl.RpnCalculator;

/**
 *测试入口
 *
 * @author xiaguanghua
 *
 * @createTime 2019年7月8日下午2:19:51
 *
 * @version 
 *
 */
public class Main {
	
	public static void main(String[] args) {
		RpnCalculator rpnCalculator = new RpnCalculator();
		try {
			while (true) {
				System.out.println("请输入逆波兰表达式 参数之间请以空格分隔：");
				Scanner scanner = new Scanner(System.in);
				String rpnData = scanner.nextLine();
				rpnCalculator.calRpnExpression(rpnData);
			}
 
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
}
