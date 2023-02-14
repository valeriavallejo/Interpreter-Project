/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 *  The ArgsCode class creates a newFrame to make room for a certain number of
 * arguments, as specified by the value of its argument.
 * 
 */
public class ArgsCode extends ByteCode{
    int numArgs;
    ArrayList<String> args;
    
    public ArgsCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.numArgs = Integer.parseInt(args.get(0));
    }
    public void execute(VirtualMachine vm){
        vm.newFrameAt(numArgs);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "ARGS " + numArgs;
    }
}
