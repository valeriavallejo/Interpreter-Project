/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;


/**
 * The LoadCode class copies the value that is an offset away from the bottom 
 * of the current frame and pushes it to the top of the runStack
 */
public class LoadCode extends ByteCode{
    String varName;
    int offset;
    ArrayList<String> args;
    
    public LoadCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.varName = args.get(1);
        this.offset = Integer.parseInt(args.get(0));
    }
    public void execute(VirtualMachine vm){
        vm.load(offset);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "LOAD " + offset + " " + varName + "   <load " + varName + ">"; 
    }
}
