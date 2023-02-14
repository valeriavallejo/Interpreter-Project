/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exprevaluator;

import java.util.*; 


//===================EVALUATOR====================

class Evaluator { 
    private Stack<Operand> opdStack;
    private Stack<Operator> oprStack;

    public Evaluator() {
        opdStack = new Stack<Operand>();
        oprStack = new Stack<Operator>();
    }

    public int eval(String expr) {
        String tok; 

	// init stack - necessary with operator priority schema;
	// the priority of any operator in the operator stack other then
	// the usual operators - "+-*/" - should be less than the priority
	// of the usual operators          
        oprStack.push(((Operator) Operator.operators.get("#")));          
        String delimiters = "+-*/# "; 
        StringTokenizer st = new StringTokenizer(expr,delimiters,true);
	// the 3rd arg is true to indicate to use the delimiters as tokens, too
	// but we'll filter out spaces

        while (st.hasMoreTokens()) {             
            if ( !(tok = st.nextToken()).equals(" ")) {        // filter out spaces
                if (Operand.check(tok)) {                      // check if tok is an operand                     
                    opdStack.push(new Operand(tok));                 
                } 
                else {                                         //else, tok is an operator or invalid               
                    if (!Operator.check(tok)) {                         
                            System.out.println("*****invalid token******");
                            System.exit(1);                     
                    } 

                    Operator newOpr = (Operator) Operator.operators.get(tok);         // POINT 1 
                    
                    while ( ((Operator)oprStack.peek()).priority() >= newOpr.priority()) {

                    // note that when we eval the expression 1 - 2 we will
                    // push the 1 then the 2 and then do the subtraction operation
                    // This means that the first number to be popped is the
                    // second operand, not the first operand - see the following code                         
                    Operator oldOpr = ((Operator)oprStack.pop());                         
                    Operand op2 = (Operand)opdStack.pop();                         
                    Operand op1 = (Operand)opdStack.pop();                         
                    opdStack.push(oldOpr.execute(op1,op2));
                    }
                    oprStack.push(newOpr);
                }               
            } 
        }
        Operator currOpr = ((Operator)oprStack.pop());
        while( !(currOpr.priority() == 1)){
            Operand op2 = (Operand)opdStack.pop();                         
            Operand op1 = (Operand)opdStack.pop();                         
            opdStack.push(currOpr.execute(op1,op2));
            currOpr = oprStack.pop();
        }
        return opdStack.pop().getValue();
    } 
}

//======================OPERAND=====================
    
class Operand {
    private String operand;
    private int value;
    
    Operand(String operand){
        this.operand = operand;
        this.value = Integer.valueOf(operand);
    }
    Operand(int value){
        this.value = value;
        this.operand = String.valueOf(value);
    }
    public int getValue(){
        return this.value;
    }
    
    public static boolean check(String tok){
        return Character.isDigit(tok.charAt(0));
    }
}

//=====================OPERATOR==========================

abstract class Operator {
    private String operator; 
    public static HashMap<String, Operator> operators = new HashMap<String, Operator>(){
        {
        put("+", new AdditionOperator());
        put("-", new SubtractionOperator());
        put("*", new MultiplicationOperator());
        put("/", new DivisionOperator());
        put("#", new PoundOperator());
        }
    };
   
    Operator(){}
    Operator(String oper){
        this.operator = oper;
    }
    abstract int priority();
    abstract Operand execute(Operand op1, Operand op2);
    
    static boolean check(String tok){ //use final so that operator class can have this function ???
        return tok.equals("*") ||
               tok.equals("/") ||
               tok.equals("+") ||
               tok.equals("-") ||
               tok.equals("#");
    }
}


//=================SUBCLASSES======================

class AdditionOperator extends Operator{
    private String add;
    
    int priority(){
        return 2;
    }
    
    Operand execute(Operand op1, Operand op2){
        String op3Val = String.valueOf(op1.getValue() + op2.getValue());
        Operand op3 = new Operand(op3Val);
        return op3;
    }
    
}

class SubtractionOperator extends Operator{
    private String sub;
    
    int priority(){
        return 2;
    }
    
    Operand execute(Operand op1, Operand op2){
        String op3Val = String.valueOf(op1.getValue() - op2.getValue());
        Operand op3 = new Operand(op3Val);
        return op3;
    }
}

class MultiplicationOperator extends Operator{
    private String mult;
    
    int priority(){
        return 3;
    }
    
    Operand execute(Operand op1, Operand op2){
        String op3Val = String.valueOf(op1.getValue() * op2.getValue());
        Operand op3 = new Operand(op3Val);
        return op3;
    }
}

class DivisionOperator extends Operator{
    private String div;
    
    int priority(){
        return 3;
    }
    
    Operand execute(Operand op1, Operand op2){
        String op3Val = String.valueOf(op1.getValue() / op2.getValue());
        Operand op3 = new Operand(op3Val);
        return op3;
    }
}

class PoundOperator extends Operator{
    private String pnd;
    
    int priority(){
        return 1;
    }
    
    Operand execute(Operand op1, Operand op2){
        String op3Val = String.valueOf(op1.getValue() / op2.getValue());
        Operand op3 = new Operand(op3Val);
        return op3;
    }
}

 
