package interpreter.bytecode.debuggerByteCodes;

import interpreter.bytecode.ByteCode;
import interpreter.debugger.DebugVirtualMachine;
import interpreter.VirtualMachine;
import interpreter.debugger.LinesAndBPs;
import java.util.ArrayList;

/**
 * The LineCode class has a member variable corresponding to a line in the source
 * code. When it executes, it updates the current line number in the debugVM
 * and checks to see if the debugVM is at a breakpoint or not, so it can stop 
 * executing bytecodes if it is at one
 */
public class LineCode extends ByteCode{
    int lineNum;
    ArrayList<String> args;
    
    public LineCode(){
    }
    
    public void init(ArrayList<String> args){
        this.lineNum = Integer.parseInt(args.get(0));
        this.args = args;
    }
    public void execute(VirtualMachine vm){
        DebugVirtualMachine dvm = (DebugVirtualMachine)vm;
        dvm.currLine = lineNum;
        if(dvm.currLine > -1){
            LinesAndBPs currLineAndBP = dvm.getLineAndBP(lineNum);
            if(currLineAndBP.hasBreakpoint()){
                dvm.quitExe();
            }
        }
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    public int getLineNum(){
        return this.lineNum;
    }
    
    public String dumpString(VirtualMachine dvm){
        return "LINE " + lineNum;
    }
}
