package cn.com.xgh.rpn_calculator.calculator;

import java.util.List;
import java.util.Stack;

/**
 *具有的计算方法
 *
 * @author xiaguanghua
 *
 * @createTime 2019年7月8日上午11:18:09
 *
 * @version 
 *
 */
public interface Calculator {
	
	/**
	 * 一元运算符运算规则
	 * 
	 * @param stk1 操作符栈
	 * 
	 * @param stk2 日志栈
	 * 
	 * @param operation 操作符
	 * 
	 * @throws Exception
	 */
	void unaryOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception;
		
	/**
	 * 二元运算符规则
	 * 
	 * @param stk1操作符栈
	 * 
	 * @param stk2日志栈
	 * 
	 * @param operation操作符
	 * 
	 * @throws Exception
	 */
	void binaryOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception;
	
	/**
	 * undo与clear规则
	 * 
	 * @param stk1操作符栈
	 * 
	 * @param stk2日志栈
	 * 
	 * @param operation操作符
	 * 
	 * @throws Exception
	 */
	void specialOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception;
	
	/**
	 * 除法运算
	 * 
	 * @param a参数a
	 * 
	 * @param b参数b
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	 double divide(double a, double b) throws Exception;
	 
	 
	/**
	 * 开平方计算
	 * 
	 * @param f要开平方的是数
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	double sqrt(double f) throws Exception;
}
