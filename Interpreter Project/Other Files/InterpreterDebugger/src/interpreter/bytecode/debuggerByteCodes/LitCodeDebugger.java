/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.LitCode;
import interpreter.debugger.DebugVirtualMachine;
import java.util.ArrayList;
/**
 * The LitCodeDebugger class is an extension of the LitCode class
 * When executed in debugVM, it enters its member variables into the 
 * function environment record
 */
public class LitCodeDebugger extends LitCode{

    public void init(ArrayList<String> args){
        this.args = args;
        this.literal = new Integer(Integer.valueOf(args.get(0)));
        if(args.size() > 1){
            this.var = args.get(1);
        }
    }
    public void execute(VirtualMachine vm){
        super.execute(vm);
        if(var != null){
            DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
            int currOffset = dvm.runStackSize() - dvm.framePtrPeek() - 1;
            dvm.enter(var, currOffset);
        }
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
   
}
