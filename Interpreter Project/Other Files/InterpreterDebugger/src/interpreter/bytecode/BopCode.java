/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;


/**
 * The BopCode class pops the top two values of the VM's runStack and performs a
 * certain operation, as specified by its argument. It then pushes the result of
 * the operation onto the top of the runStack.
 */
public class BopCode extends ByteCode{
    ArrayList<String> args;
    String operator;
    
    public BopCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.operator = args.get(0);
    }
    public void execute(VirtualMachine vm){
        int operand2 = vm.pop();
        int operand1 = vm.pop();
        int result;
        
        switch(operator){
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "==":
                if(operand1 == operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "!=":
                if(operand1 != operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "<=":
                if(operand1 <= operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case ">":
                if(operand1 > operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case ">=":
                if(operand1 >= operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "<":
                if(operand1 < operand2){
                    result = 1;
                }
                else{
                    result = 0;
                }
                break;
            case "|":
                
                result = operand1 | operand2;
            case "&":
                result = operand1 & operand2;
                break;
            default:
                result = Integer.MAX_VALUE;
        }
        
        vm.push(result);

    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "BOP " + operator;
    }
}
