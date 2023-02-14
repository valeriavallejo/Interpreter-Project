/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The PopCode class pops a certain amount of level off the top of the runStack,
 * as specified by its argument
 * 
 */
public class PopCode extends ByteCode{
    ArrayList<String> args;
    int levels;
    
    public PopCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.levels = Integer.parseInt(args.get(0));
    }
    public void execute(VirtualMachine vm){
        for(int i = 0; i < levels; i++){
            vm.pop();
        }
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "POP " + levels;
    }
}
