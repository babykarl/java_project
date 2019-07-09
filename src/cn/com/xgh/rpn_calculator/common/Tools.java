package cn.com.xgh.rpn_calculator.common;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *公共类
 *
 * @author xiaguanghua
 *
 * @createTime 2019年7月8日下午1:42:22
 *
 * @version 
 *
 */
public class Tools {
	
	/**
	 * 将字符串转为double类型
	 * 
	 * @param str
	 */
	public static Double strToDigit(String str) {
		try {
			double num = Double.valueOf(str);
			return num;
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 获取栈中数据，存储在list中
	 * 
	 * @param stk
	 */
	public static List<Double> getStack(Stack<Double> stk) {
		List<Double> getStk = new ArrayList<>();
		for (Double x : stk) {
			getStk.add(x);
		}
		return getStk;
	}
	
	/**
	 * 将底层的栈中数据显示出来
	 * 
	 * @param stk
	 */
	public static void displayStack(Stack<Double> stk) {
		if (stk.size() != 0) {
			System.out.print("stack:");
			for (Double x : stk) {
				System.out.print(outputFormat(x) + " ");
			}
		} else {
			System.out.println("stack:");
		}
		System.out.println();
	}
	
	/**
	 * 运算结果的显示格式化，最多10位精度
	 * 
	 * @param value  运算结果
	 */
	public static String outputFormat(double value) {
		DecimalFormat numformat = new DecimalFormat("##########.##########");
		String output = numformat.format(value);
		return output;
	}
 

}
