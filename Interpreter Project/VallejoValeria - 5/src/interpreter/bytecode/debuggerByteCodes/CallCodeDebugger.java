package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.CallCode;
import interpreter.debugger.DebugVirtualMachine;

/**
 * The CallCodeDebugger class does the CallCode class' execute method, then it 
 * adds the current line number to the lineStack in the DebugVM so that it can
 * be referred to later when we return to where the call was made.
 */
public class CallCodeDebugger extends CallCode{
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.pushCurrLine(dvm.currLine);
    }
}
