/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;


/**
 * The LabelCode class holds names of functions in the program so that other 
 * ByteCodes can jump to its location
 */
public class LabelCode extends ByteCode{
    ArrayList<String> args;
    public LabelCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "LABEL " +  this.args.get(0);
    }
}
