/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.program;

import interpreter.bytecode.ByteCode;
import java.util.*;


/**
 * The Program class is used by the VM to retrieve information about ByteCodes
 * using the PC, which correspond to the addresses of the object within the file.
 * The Program class also resolves addresses for the bytecodes that need to jump
 * around in the code
 */

public class Program extends Object{
    private static ArrayList<ByteCode> bcObjectList = new ArrayList<ByteCode>();
    
    public int resolveAddress(int currAddr, ArrayList<String> args){

        for(int i = 0; i < bcObjectList.size(); i++){
            ByteCode bc = bcObjectList.get(i);
            String bcClass = bc.getClass().getName();
            if(bc.getArgs().size() > 0){
                if(bc.getArgs().get(0).equals(args.get(0)) && bcClass.equals("interpreter.bytecode.LabelCode")){
                    return i;
                }
            }
        }
        System.out.println("Address resolution failed");
        return Integer.MAX_VALUE;
    }
    public ByteCode getCode(int pc){
        return bcObjectList.get(pc);
    }
    public void setList(ArrayList<ByteCode> bcList){
        bcObjectList = (ArrayList<ByteCode>)bcList.clone();
    }
    
}
