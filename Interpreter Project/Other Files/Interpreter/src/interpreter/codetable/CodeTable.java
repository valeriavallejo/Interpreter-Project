/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.codetable;
import interpreter.bytecode.ByteCode;
import java.util.*;

/**
 *
 * @author veeva
 */
public class CodeTable extends Object{
    //when you construct code table (used in byte code loader), constructor could do all initializations
    private static HashMap<String, String> bytecode = new HashMap<>();
    
    public static String getClassName(String code){
        if(bytecode.containsKey(code)){
            return CodeTable.bytecode.get(code);
        }
        return "";
    }
    public static String getBCValue(String className){
        for(String str : bytecode.keySet()){
            if(str.equals(className)){
                return str;
            }
        }
        return "Value not found in";
    }
    public static void init(){
        bytecode.put("HALT", "HaltCode");
        bytecode.put("POP", "PopCode");
        bytecode.put("FALSEBRANCH", "FalseBranchCode");
        bytecode.put("GOTO", "GoToCode");
        bytecode.put("STORE", "StoreCode");
        bytecode.put("LOAD", "LoadCode");
        bytecode.put("LIT", "LitCode");
        bytecode.put("ARGS", "ArgsCode");
        bytecode.put("CALL", "CallCode");
        bytecode.put("RETURN", "ReturnCode");
        bytecode.put("BOP", "BopCode");
        bytecode.put("READ", "ReadCode");
        bytecode.put("WRITE", "WriteCode");
        bytecode.put("LABEL", "LabelCode");
        bytecode.put("DUMP", "DumpCode");
    }
}
