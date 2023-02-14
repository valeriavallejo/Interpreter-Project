/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The GoTo Class finds the Label in the program that has the same argument as itself
 * and sets the PC to that label's address 
 */
public class GoToCode extends ByteCode{
    ArrayList<String> args;
    int currAddr, targetAddr;
    
    public GoToCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        currAddr = vm.getPC();
        targetAddr = vm.program.resolveAddress(currAddr, args);
        vm.setPC(targetAddr);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "GOTO" + args.get(0);
    }
}
