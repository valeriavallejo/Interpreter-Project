package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.ReturnCode;
import interpreter.debugger.DebugVirtualMachine;
import java.util.ArrayList;

/**
 * The ReturnCodeDebugger class does the ReturnCode class' execute method, then 
 * pops the DebugVM's current environment 
 */
public class ReturnCodeDebugger extends ReturnCode{
    ArrayList<String> args;
    
    public void init(ArrayList<String> args){
        this.args = args;
    }
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.popCurrEnvironment();
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
}
