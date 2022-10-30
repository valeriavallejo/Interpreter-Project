package interpreter.debugger;

import interpreter.bytecode.ByteCode;
import java.util.*;
import interpreter.Program;
import interpreter.VirtualMachine;

/**
 * The DebugVirtualMachine class extends the VirtualMachine class in order to 
 * maintain the same way of executing ByteCodes while allowing it to do things
 * such as keep an environment record. It also only executes bytecodes until it
 * encounters a HALT bytecode or it comes across a breakpoint at the current line
 */

public class DebugVirtualMachine extends VirtualMachine{
    boolean continueExecuting; //continue, quit
    Stack<FunctionEnvironmentRecord> environmentStack = new Stack();
    SourceCode entries;
    public int currLine;
    public boolean isStepping;
    int envSizeBeforeStep;
    Stack lineStack = new Stack();
    
    public DebugVirtualMachine(Program prog, SourceCode source){
        super(prog);
        this.entries = source;
        isRunning = true;
        pc = 0;
    }
    
    public boolean executeDebugProgram(){
        while(isRunning){
            if(continueExecuting){
                ByteCode code = program.getCode(pc); //cant get to debugger subclasses
                code.execute(this);
                pc++;
            } 
            else{
                return continueExecuting;
            }
        }
        return continueExecuting;
    }
    
    public String setBreakpoint(int lineNum){
        entries.getLinesAndBPs(lineNum).addBP();
        return "Breakpoint set: " + lineNum;
    }
    public String clearBreakpoint(int lineNum){
        entries.getLinesAndBPs(lineNum).removeBP();
        return "Breakpoint cleared: " + lineNum;
    }
    public String displayFunction(){
        String func = "";
        int start = environmentStack.lastElement().getStartLine();
        int end = environmentStack.lastElement().getEndLine();
        for(int i = start; i <= end; i++){
            LinesAndBPs currLineAndBP = entries.getLinesAndBPs(i);
            if(currLineAndBP.hasBreakpoint()){
                func = func + "\n" + "*" + i + ". " + currLineAndBP.getLine();
            }
            else{
                func = func + "\n" + i + ". " + entries.getLinesAndBPs(i).getLine();
            }
            if(currLine == i){
                func = func + "   <-----------";
            }
        }
        return func;
    }
    public String displayVariables(){
        String vars = "";
        Table currSymbolTable = environmentStack.lastElement().getTable();
        Set<String> keys = currSymbolTable.keys();
        Iterator<String> iter = keys.iterator();
        while(iter.hasNext()){
            String currKey = iter.next();
            vars = vars + "\n" + currKey + ": " + currSymbolTable.get(currKey);
        }
        return vars;
    }
    
    public void enter(String name, int offset){
        int index = runStack.framePtrPeek() + offset;
        environmentStack.lastElement().setVarVal(name, runStack.getValAt(index));
    }
    public void newEnvironment(String name, int start, int end){
        FunctionEnvironmentRecord newRecord = new FunctionEnvironmentRecord();
        newRecord.beginScope();
        newRecord.setFunctionInfo(name, start, end);
        newRecord.setCurrLine(currLine);
        environmentStack.push(newRecord);
    }
    public void popCurrEnvironment(){
        currLine = (int)lineStack.pop();
        environmentStack.pop();
        if(isStepping && (environmentStack.size() == envSizeBeforeStep - 1)){
            isStepping = false;
            continueExecuting = false;
        }
    }
    public void pushCurrLine(int line){
        lineStack.push(line);
    }
    public void pop(int amount){
        environmentStack.lastElement().doPop(amount);
    }
    
    public void stepOut(){
        envSizeBeforeStep = environmentStack.size();
        this.isStepping = true;
        continueExe();
    }
    
    public void continueExe(){
        this.continueExecuting = true;
        this.executeDebugProgram();
    }
    public void quitExe(){
        this.continueExecuting = false;
    }
    public boolean isExecuting(){
        return this.continueExecuting;
    }
    
    public SourceCode getSource(){
        return entries;
    }
    public LinesAndBPs getLineAndBP(int lineNum){
        return entries.getLinesAndBPs(lineNum);
    }
}
