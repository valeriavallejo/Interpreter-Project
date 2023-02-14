/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;


/**
 * The ByteCode class is parent class of all the ByteCode classes
 * It specifies that each ByteCode object must need init(), execute(), getArgs(),
 * and dumpString().
 * 
 */
public abstract class ByteCode {
    public ByteCode(){
    
    }
    public abstract void init(ArrayList<String> args);
    public abstract void execute(VirtualMachine vm);
    public abstract ArrayList<String> getArgs();
    public abstract String dumpString(VirtualMachine vm);
}
