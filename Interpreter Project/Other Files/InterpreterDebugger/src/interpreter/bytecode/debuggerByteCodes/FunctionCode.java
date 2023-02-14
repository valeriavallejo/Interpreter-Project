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
 * The FunctionCode class has the name, start line, and end line of a function
 * as its member variables. Its execute method enters this data into the debugVM's
 * function environment record
 */
public class FunctionCode extends ByteCode{
    String name;
    int start, end;
    ArrayList<String> args;
    
    public FunctionCode(){
    }
    
    public void init(ArrayList<String> args){
        this.name = args.get(0);
        this.start = Integer.parseInt(args.get(1));
        this.end = Integer.parseInt(args.get(2));
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.newEnvironment(name, start, end);
        int numOfFormals = dvm.runStackSize()- dvm.framePtrPeek();
        if(dvm.isSteppingIn){
            dvm.executeStepIn(numOfFormals);
        }
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "FUNCTION " + name + " " + start + "" + end;
    }
}
