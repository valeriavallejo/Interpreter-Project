/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.PopCode;
import interpreter.debugger.DebugVirtualMachine;
import java.util.ArrayList;

/**
 * The PopCodeDebugger class' execute function differs from its superclass since
 * it pops off levels from the function  environment record
 */
public class PopCodeDebugger extends PopCode{
    public void init(ArrayList<String> args){
        this.levels = Integer.parseInt(args.get(0));
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.pop(levels);
    }

}
