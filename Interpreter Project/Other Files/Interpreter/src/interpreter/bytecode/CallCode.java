/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The Call class puts the PC at the address of it's argument, which is a String
 * that corresponds to a Label somewhere in our program.
 * In order to return back once we are done with the call, this class pushes its
 * current address to the returnAddr variable in the VM so that its ready for when
 * we come across a RETURN
 */
public class CallCode extends ByteCode{
    String funcName;
    int funcAddr;
    int currAddr;
    ArrayList<String> args;
    
    public CallCode(){}
    
    public void init(ArrayList<String> args){
        this.args = args;
        this.funcName = args.get(0);
    }
    public void execute(VirtualMachine vm){
        currAddr = vm.getPC();
        vm.pushReturnAddr(currAddr);
        funcAddr = vm.program.resolveAddress(currAddr, args);
        vm.setPC(funcAddr);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    public String dumpString(VirtualMachine vm){
        String funcNameString = "";
        String params = "";
        int i = 0;
        
        if(funcName.contains("<")){
            while(funcName.charAt(i) != '<'){
            funcNameString += funcName.charAt(i);
            i++;
            }
        }
        else{
            funcNameString = funcName;
        }

        if(vm.framePtrPeek() > 0){
            for(i = vm.framePtrPeek(); i < vm.runStackSize(); i++){
                params = params + vm.getValAt(i) + ", ";
            }
            return "CALL " + funcName + "   " + funcNameString + "(" + params + ")";
        }
        return "CALL " + funcName;
    }
}
