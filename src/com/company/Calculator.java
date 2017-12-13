package com.company;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {
    private Stack<Character> operator = new Stack();
    private Stack<Double> operand  = new Stack();
    String[] operators;
    ArrayList<Double> operandList;
    private Ariphmetic ariphmetic= new Ariphmetic();
    private MathStringCleaner mathStringCleaner= new MathStringCleaner();
    public double calculate(String in){
        in=mathStringCleaner.checkString(in);
        parseString(in);
        if (operators.length==0) {
            return operandList.get(0);
        }

        for( int i=0;i<operandList.size();i++){
            pushOperator(operators[i]);
            operand.push(operandList.get(i));
        }

        if (operators.length>operandList.size()){
            pushOperator(operators[operandList.size()]);
        }

        while (!operator.empty()) {
            executeOperation();
        }

        return operand.peek();
    }
    private void parseString(String in) {
        operators=getOperators(in);
        operandList=getOperands(in);
        if ((operators.length>0)&&(operators[0].equals("-"))){
            operators[0]="";
            operandList.set(0,operandList.get(0)*(-1));
        }
        for (int i=1;i<operators.length;i++){
            if (operators[i].indexOf("(-")!=-1){
                operators[i]=operators[i].substring(0,operators[i].length()-1);
                operandList.set(i,operandList.get(i)*(-1));
            }
        }
    }
    private String[] getOperators(String in){
        Pattern pattern = Pattern.compile("\\d+\\.{0,1}\\d{0,}");
        return pattern.split(in);
    }
    private ArrayList<Double> getOperands(String in){
        Pattern pattern = Pattern.compile("(\\d+([.]\\d+){0,1})");
        Matcher m = pattern.matcher(in);
        ArrayList<Double> list = new ArrayList();
        while(m.find()){
            list.add(Double.parseDouble(m.group()));
        }
        return list;
    }
    private void pushOperator(String insertOperators){
        char oper;
        for (int j=0;j<insertOperators.length();j++) {
            oper = insertOperators.charAt(j);
            pushOperator(oper);
        }
    }
    private void pushOperator(char oper){
        if (operator.empty()) {
            operator.push(oper);
            return;
        }

        if (oper == '(') {
            operator.push(oper);
            return;
        }

        if (oper == ')') {
            while (!(operator.peek() == '(')) {
                executeOperation();
            }
            operator.pop();
            return;
        }

        while ((!operator.empty()) && operator.peek() != '(' && ((computeWeight(oper)) >= (computeWeight(operator.peek())))) {
            executeOperation();
        }
        operator.push(oper);
    }
    private void executeOperation(){
        double tmp;
        switch (operator.pop()){
            case '+':
                operand.push(ariphmetic.sum(operand.pop(),operand.pop()));
                break;
            case '-':
                tmp=operand.pop();
                operand.push(ariphmetic.difference(operand.pop(),tmp));
                break;
            case '*':
                operand.push(ariphmetic.product(operand.pop(),operand.pop()));
                break;
            case '/':
                tmp=operand.pop();
                operand.push(ariphmetic.qoutirent(operand.pop(),tmp));
                break;
            case '^':
                tmp=operand.pop();
                operand.push(ariphmetic.exponent(operand.pop(),tmp));
                break;
        }
    }
    private int computeWeight(char oper){
        switch (oper){
            case '+':
                 return 3;
            case '-':
                return 3;
            case '*':
                return 2;
            case '/':
                return 2;
            case '^':
                return 1;
        }
        return -1;
    }

}
