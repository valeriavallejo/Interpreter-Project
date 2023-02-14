/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;


/**
 * The Call class puts the PC at the address of it's argument, which is a String
 * that corresponds to a Label somewhere in our program.
 * In order to return back once we are done with the call, this class pushes its
 * current address to the returnAddr variable in the VM so that its ready for when
 * we come across a RETURN
 */
public class CallCode extends ByteCode{
    protected String funcName;
    protected int funcAddr;
    protected int currAddr;
    protected ArrayList<String> args;
    protected String funcNameString;
    protected String params;
    
    public CallCode(){}
    
    public void init(ArrayList<String> args){
        this.args = args;
        this.funcName = args.get(0);
        
        this.funcNameString = "";
        this.params = "";
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
    }
    public void execute(VirtualMachine vm){
        if(vm.framePtrPeek() > 0){
            for(int i = vm.framePtrPeek(); i < vm.runStackSize(); i++){
                params = params + vm.getValAt(i) + ", ";
            }
        }
        
        currAddr = vm.getPC();
        vm.pushReturnAddr(currAddr);
        funcAddr = vm.program.resolveAddress(currAddr, args);
        vm.setPC(funcAddr);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    public String dumpString(VirtualMachine vm){
        if(vm.framePtrPeek() > 0){
            return "CALL " + funcName + "   " + funcNameString + "(" + params + ")";
        }
        return "CALL " + funcName;
    }
}
