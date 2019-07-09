package cn.com.xgh.rpn_calculator.calculator.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import cn.com.xgh.rpn_calculator.calculator.Calculator;
import cn.com.xgh.rpn_calculator.common.Constants;
import cn.com.xgh.rpn_calculator.common.Tools;

/**
 *rpn计算器
 *
 * @author xiaguanghua
 *
 * @createTime 2019年7月8日上午11:26:46
 *
 * @version 
 *
 */
public class RpnCalculator implements Calculator{
	/*操作数栈*/
	private Stack<Double> numbers = new Stack<Double>(); 
	/*操作数栈的日志栈,用来存放操作数栈的历史记录*/
	private Stack<List<Double>> numlogs = new Stack<>(); 

	
	/* 
	 * 一元运算符运算规则
	 */
	@Override
	public void unaryOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception {
		double num = stk1.pop();
		switch (operation) {
		case Constants.SQRT:
			stk1.push(sqrt(num));
			stk2.push(Tools.getStack(stk1));
			break;
		default:
			throw new Exception("ERROR");
		}
		
	}

	/* 
	 * 二元运算符运算规则
	 */
	@Override
	public void binaryOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception {
		/*取出操作数栈顶数值*/
		double num2 = stk1.pop();  
		/*取出操作数栈顶数值*/
		double num1 = stk1.pop();
		/*配对运算符 ，计算并将结果入栈，同时日志栈记录操作数栈的数据*/
		switch (operation) {
		case Constants.ADD:
			stk1.push(num1 + num2); 
			stk2.push(Tools.getStack(stk1));
			break;
		case Constants.SUBSTRACT:
			stk1.push(num1 - num2);
			stk2.push(Tools.getStack(stk1));
			break;
		case Constants.MULTIPLY:
			stk1.push(num1 * num2);
			stk2.push(Tools.getStack(stk1));
			break;
		case Constants.DIVIDE:
			stk1.push(divide(num1, num2));
			stk2.push(Tools.getStack(stk1));
			break;
		default:
			throw new Exception("ERROR");
		}
		
	}

	/* 
	 * 特殊运算符运算规则
	 */
	@Override
	public void specialOperationRules(Stack<Double> stk1, Stack<List<Double>> stk2, String operation) throws Exception {
		switch (operation) {
		/*undo*/
		case Constants.UNDO:
			while (!stk1.empty()) { 
				stk1.pop();
			}
			/*如果日志栈不为空，将栈顶数据弹出*/
			if (!stk2.empty()) { 
				stk2.pop();
				/*弹出数据后，日志栈不为空，将现在的栈顶数据压入操作数栈*/
				if (!stk2.empty()) {
					/*读取日志栈顶数据，并将其存放到list1集合中*/
					List<Double> list1 = stk2.peek();
					/*将现在的栈顶数据压入操作数栈*/
					for (int i = 0; i < list1.size(); i++) {
						if (list1.get(i) != null) {
							stk1.push(list1.get(i));
						}
					}
				}
			}
			break;
		/*clear*/
		case Constants.CLEAR:
			/*清空操作数栈*/
			while (!stk1.empty()) { 
				stk1.pop();
			}
			/*将null压入日志栈，以便执行undo时可以区别*/
			List<Double> list2 = new ArrayList<>();  
			list2.add(null);
			stk2.push(list2);
			break;
		default:
			throw new Exception("ERROR");
		}
		
	}

	/* 
	 * 除法运算
	 */
	@Override
	public double divide(double a, double b) throws Exception {
		if (b == 0) {
			throw new Exception("divisor cannot be 0!");
		}
		return a / b;
	}

	@Override
	public double sqrt(double f) throws Exception {
		if (f < 0) {
			throw new Exception("negative numbers cannot be squared");
		}
		double a = (double) Math.sqrt(f);
		return a;
	}
	
	/**
	 * 该方法对RPN表达式进行计算
	 * 
	 * @param rpn 为用户输入的RPN表达式
	 */
	public void calRpnExpression(String rpnData) throws Exception {
		/*将输入rpn数据以空格分隔成数组*/
		String[] rpnDataArray = rpnData.split(" ");
		int i = 0;
		/*获取数组的长度*/
		int rpnDataLength = rpnDataArray.length;
		while (i < rpnDataLength) {
			Calculator calculator = new RpnCalculator(); 
			/*获取栈的长度*/
			int n = numbers.size();
			if (Tools.strToDigit(rpnDataArray[i]) != null) { 
				/*字符串是操作数，则直接入栈*/
				numbers.push(Tools.strToDigit(rpnDataArray[i]));
				/*日志栈记录操作数栈的数据变化*/
				numlogs.push(Tools.getStack(numbers)); 
			} else { 
				/*字符串不是操作数，则要判断是哪种操作符*/
				String operation = rpnDataArray[i];
				if (operation.equals(Constants.UNDO) || operation.equals(Constants.CLEAR)) { 
					/*操作符是功能符undo或clear*/
					calculator.specialOperationRules(numbers, numlogs, operation);
				} else if (operation.equals(Constants.SQRT)) { // 操作符是一元运算符sqrt
					if (n > 0) { 
						/*如果栈中有操作数，则进行单目运算*/
						calculator.unaryOperationRules(numbers, numlogs, operation);
					} else { 
						/*栈中没有操作数，输出提示信息，并跳出循环*/
						System.out.print("operator" + operation + "(position:" + (2 * i - 1) + "):insufficient parameters ");
						break;
					}
 
				} 
				/*操作符是二元运算符*/
				else if (operation.equals(Constants.ADD) || operation.equals(Constants.SUBSTRACT) || operation.equals(Constants.MULTIPLY) || operation.equals(Constants.DIVIDE)) { 
					if (n > 1) {  
						/*栈的操作数大于等于2，则进行双目运算*/
						calculator.binaryOperationRules(numbers, numlogs, operation);
					} else { 
						/*栈中没有操作数，输出提示信息，并跳出循环*/
						System.out.print("operator" + operation + "(position:" + (2 * i + 1) + "):insufficient parameters ");
						break;
					}
 
				} else {
					throw new Exception("incorrect input");
				}
			}
			i++;
		}
		Tools.displayStack(numbers);
 
	}

	
}
