/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tariqpractice;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Tiger
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static boolean IsOperator(String exp) {
        if (exp.equals("+") || exp.equals("-") || exp.equals("*") || exp.equals("/") || exp.equals("^") || exp.equals("(") || exp.equals(")")) {
            return true;
        } else {
            return false;
        }
    }

    public static int OperatorWeight(String op) {
         if (op.equals("*") || op.equals("/")) {
            return 2;
        } else if (op.equals("+") || op.equals("-")) {
            return 1;
        } else if (op.equals("^")) {
            return 3;
        } else if (op.equals("(") || op.equals(")")) {
            return 4;
        }
        return 0;
    }

    public static boolean IsOpenParen(String op) {
        if (op.equals("(")) {
            return true;
        }
        return false;
    }

    public static boolean IsCloseParen(String op) {
        if (op.equals(")")) {
            return true;
        }
        return false;
    }

    public static String toPostfix(String expression) {
        Stack<String> stack = new Stack<>();
        String finalResult = "";
        String[] expArray = expression.split("");
        for (int i = 0; i < expArray.length; i++) {
            if (IsOperator(expArray[i]) == false) {
                finalResult = finalResult + expArray[i];
            } else {
                while (!stack.isEmpty() && !IsOpenParen(stack.lastElement())
                        && OperatorWeight(stack.lastElement()) >= OperatorWeight(expArray[i])) {

                    finalResult = finalResult + stack.lastElement();
                    stack.pop();
                }
                stack.push(expArray[i]);
            }
            if (IsOpenParen(expArray[i]) == true) {
                stack.push(expArray[i]);
            } else if (IsCloseParen(expArray[i])) {
                while (!stack.empty() && !IsOpenParen(stack.lastElement())) {
                    finalResult = finalResult + stack.lastElement();
                    stack.pop();
                }
                stack.pop();
            }
        }
        while (!stack.empty()) {
            finalResult = finalResult + stack.lastElement();
            stack.pop();
        }
        return finalResult.replace("(", "").replace(")", "");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Infix expression:");
        String userExpression = sc.nextLine();
        System.out.println("Postfix: " + toPostfix(userExpression));

    }
}
