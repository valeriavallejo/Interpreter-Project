package interpreter;
import interpreter.bytecode.ByteCode;
import java.util.*;

/**
 * The CodeTable class initializes differently depending on whether it is in 
 * debug mode or not
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
        System.out.println("Value not found in code table");
        return " ";
    }
    public static String getPackageClassName(String className){
        if(className.equals("LineCode") || className.equals("FunctionCode") || 
            className.equals("FormalCode") || className.equals("PopCodeDebugger") ||
            className.equals("LitCodeDebugger") || className.equals("ReturnCodeDebugger") ||
            className.equals("CallCodeDebugger") || className.equals("StoreCodeDebugger")){
            return "interpreter.bytecode.debuggerByteCodes." + className;
        }
        else{
            return "interpreter.bytecode." + className;
        }
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
        bytecode.put("LINE", "LineCode");
        bytecode.put("FUNCTION", "FunctionCode");
        bytecode.put("FORMAL", "FormalCode");
    }
    
    public static void debugInit(){
        bytecode.put("HALT", "HaltCode");
        bytecode.put("POP", "PopCodeDebugger");
        bytecode.put("FALSEBRANCH", "FalseBranchCode");
        bytecode.put("GOTO", "GoToCode");
        bytecode.put("STORE", "StoreCodeDebugger");
        bytecode.put("LOAD", "LoadCode");
        bytecode.put("LIT", "LitCodeDebugger");
        bytecode.put("ARGS", "ArgsCode");
        bytecode.put("CALL", "CallCodeDebugger");
        bytecode.put("RETURN", "ReturnCodeDebugger");
        bytecode.put("BOP", "BopCode");
        bytecode.put("READ", "ReadCode");
        bytecode.put("WRITE", "WriteCode");
        bytecode.put("LABEL", "LabelCode");
        bytecode.put("DUMP", "DumpCode");
        bytecode.put("LINE", "LineCode");
        bytecode.put("FUNCTION", "FunctionCode");
        bytecode.put("FORMAL", "FormalCode");
    }
}
