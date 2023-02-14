/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode.debuggerByteCodes;

import interpreter.VirtualMachine;
import interpreter.bytecode.StoreCode;
import interpreter.debugger.DebugVirtualMachine;
import java.util.ArrayList;

/**
 *
 * @author veeva
 */
public class StoreCodeDebugger extends StoreCode{
    String varName;
    int offset;
    ArrayList<String> args;
    
    public StoreCodeDebugger(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.varName = args.get(1);
        this.offset = Integer.parseInt(args.get(0));
    }
    public void execute(VirtualMachine vm){
        vm.store(this.offset);
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.enter(varName, offset);
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
