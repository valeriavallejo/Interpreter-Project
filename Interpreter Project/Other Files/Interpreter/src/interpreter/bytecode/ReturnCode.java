/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The ReturnCode Class pops the current frame, pushes the last value of that frame
 * onto the top of the stack, and sets the PC to the last address in the returnAddr
 * stack in the VM.
 * 
 */
public class ReturnCode extends ByteCode{
    ArrayList<String> args;
 
    public ReturnCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
    }
    public void execute(VirtualMachine vm){
       if(vm.getReturnAddrsSize() > 0){
            vm.popFrame(); 
            int callAddr = vm.popReturnAddr();
            vm.setPC(callAddr);
       }
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        if(args.size() > 0){
            String returnFunc = args.get(0);
            String returnName = "";
            int i = 0;
                if(returnFunc.contains("<")){
                    while(returnFunc.charAt(i) != '<'){
                        returnName += returnName.charAt(i);
                        i++;
                    }
                }
            if(vm.dumpStatus == "ON"){
                return "RETURN " + returnFunc + "   exit " + returnName + " :" + vm.peek(); 
            }
            return "RETURN " + returnFunc;
        }
        return "RETURN";
    }
}
