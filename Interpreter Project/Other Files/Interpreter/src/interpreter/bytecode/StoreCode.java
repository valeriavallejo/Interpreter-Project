/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The StoreCode class pops the top of the runStack and stores the value that 
 * is a certain number of places away from the bottom of the current frame 
 * (as specified by offset) with the value it popped.
 */
public class StoreCode extends ByteCode{
    String varName;
    int offset;
    ArrayList<String> args;
    
    public StoreCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.varName = args.get(1);
        this.offset = Integer.parseInt(args.get(0));
    }
    public void execute(VirtualMachine vm){
        vm.store(offset);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }

    public String dumpString(VirtualMachine vm){
        if(vm.dumpStatus == "ON"){
            return "STORE " + offset + " " + varName + "   " + varName + " = " + vm.peek();
        }
        return "STORE " + offset + " " + varName;
    }
}
