/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The HaltCode class sets the VM's isRunning variable to false in order to 
 * stop the whole program
 */
public class HaltCode extends ByteCode {
    ArrayList<String> args;
    public HaltCode(){
        
    }
    public void init(ArrayList<String> args) {
        this.args = args;
    }

    public void execute(VirtualMachine vm) {
        vm.isRunning = false;
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "HALT";
    }
}