/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 *The DumpCode class sets the VM's dumpStatus to either ON or OFF so that the VM
 * knows what to print out in its dump() method
 */
public class DumpCode extends ByteCode{
    String status;
    ArrayList<String> args;
    
    public DumpCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.status = args.get(0);
    }
    public void execute(VirtualMachine vm){
            vm.dumpStatus = this.status;
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    public String dumpString(VirtualMachine vm){
        return "Don't print DUMP";
    }
}
