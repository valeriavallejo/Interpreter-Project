/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;


/**
 * The WriteCode class prints the value at the top of runStack to the console
 * 
 */
public class WriteCode extends ByteCode{
    ArrayList<String> args;
    
    public WriteCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        int topVal = vm.peek();
        System.out.println(topVal);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "WRITE";
    }
}
