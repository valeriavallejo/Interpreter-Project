/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebugVirtualMachine;
import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * The FormalCode class holds the name and value of the Formal bytecode as its
 * member variables and enters them into the environment record.
 */
public class FormalCode extends ByteCode{
    String name;
    int value;
    ArrayList<String> args;
    
    public FormalCode(){
        
    }
    
    public void init(ArrayList<String> args){
        this.name = args.get(0);
        this.value = Integer.parseInt(args.get(1));
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.enter(name, value);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "FORMAL " + name + " " + value;
    }
}
