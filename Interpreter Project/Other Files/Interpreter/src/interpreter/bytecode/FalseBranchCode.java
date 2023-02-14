/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The FalseBranchCode class looks for the address of the Label object that has 
 * the same argument as itself and sets the PC to that Label object's address if
 * the object at the top of the runStack is 0.
 */
public class FalseBranchCode extends ByteCode{
    String label;
    int currAddr;
    int targetAddr;
    ArrayList<String> args;
    
    public FalseBranchCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.label = args.get(0);
    }
    public void execute(VirtualMachine vm){
        int cond = vm.pop();
        if(cond == 0){
            this.currAddr = vm.getPC();
            this.targetAddr = vm.program.resolveAddress(currAddr, args);
            vm.setPC(targetAddr);
        }
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "FALSEBRANCH " + label;
    }
}
