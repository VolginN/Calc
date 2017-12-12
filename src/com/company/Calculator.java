package com.company;

import javax.management.BadAttributeValueExpException;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Stack<Character> operator = new Stack();
    private Stack<Double> operand  = new Stack();

    public double calculate(String in)  {
        in=checkString(in);
        String[] operators=getOperators(in);
        String tmp;
        for (int i=0;i<operators.length;i++){
            tmp=in.substring(0,in.indexOf(operators[i]));
            if (tmp.length()>0) {
                operand.push(Double.parseDouble(tmp));
            }
            for (int j=0;j<operators[i].length();j++) {
                pushOperator(operators[i].charAt(j));
            }
            in=in.substring(in.indexOf(operators[i])+operators[i].length());
        }
        if (in.length()!=0) {
            operand.push(Double.parseDouble(in));
        }
        while (!operator.empty()) {
            executeOperation();
        }
        return operand.peek();

    }
    private String checkString(String in){
        in=in.replaceAll(",",".");
        in=in.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile("[^.()0-9+*/-]");
        Matcher m = pattern.matcher(in);
        if (m.find( )){
            throw new IllegalArgumentException("your expression contains letters");
        }
        return in;
    }
    private String[] getOperators(String in){
        Pattern pattern = Pattern.compile("\\d+\\.{0,1}\\d{0,}");
        return pattern.split(in);
    }
    private void pushOperator(char oper){
            if (operator.empty()){
                operator.push(oper);
                return;
            }

            if (oper=='(') {
                operator.push(oper);
                return;
            }

            if (oper==')'){
                while (!(operator.peek()=='(')){
                    executeOperation();
                }
                operator.pop();
                return;
            }

            while ((!operator.empty())&&operator.peek()!='('&&((computeWeight(oper))>=(computeWeight(operator.peek())))){
                executeOperation();
            }

            operator.push(oper);
        }
    private void executeOperation(){
        double tmp;
        switch (operator.pop()){
            case '+':
                operand.push(sum(operand.pop(),operand.pop()));
                break;
            case '-':
                tmp=operand.pop();
                operand.push(difference(operand.pop(),tmp));
                break;
            case '*':
                operand.push(product(operand.pop(),operand.pop()));
                break;
            case '/':
                tmp=operand.pop();
                operand.push(qoutirent(operand.pop(),tmp));
                break;
        }
    }
    private int computeWeight(char oper){
        switch (oper){
            case '+':
                 return 2;
            case '-':
                return 2;
            case '*':
                return 1;
            case '/':
                return 1;
            case '(':
                return 0;
        }
        return -1;
    }
    private double sum(double a,double b){
        return a+b;
    }
    private double difference(double a,double b){
        return a-b;
    }
    private double qoutirent(double a,double b){
        if (b==0){
            throw new IllegalArgumentException("Deviding on 0 is black magic");
        }
        return a/b;
    }
    private double product(double a,double b){
        return a*b;
    }
}
