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
    Stack<String> callStack = new Stack();  
    
    public boolean isSteppingOut;
    public boolean isSteppingIn;
    public boolean isSteppingOver;
    public boolean isTracing;

    int envSizeBeforeStep;
    Stack lineStack = new Stack();
    
    public DebugVirtualMachine(Program prog, SourceCode source){
        super(prog);
        this.entries = source;
        isRunning = true;
        continueExecuting = true;
        pc = 0;
    }
    
    public boolean executeDebugProgram(){
        while(isRunning){
            if(continueExecuting){
                ByteCode code = program.getCode(pc);
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
        int currentLine = currLine;
        
        if(environmentStack.lastElement().funcName.equals("Read") ||
            environmentStack.lastElement().funcName.equals("Write")    ){
            start = environmentStack.get(environmentStack.size() - 2).getStartLine();
            end = environmentStack.get(environmentStack.size() - 2).getEndLine();
            currentLine = (int)lineStack.elementAt(lineStack.size() - 1);
        }
        
        for(int i = start; i <= end; i++){
            LinesAndBPs currLineAndBP = entries.getLinesAndBPs(i);
            if(currLineAndBP.hasBreakpoint()){
                func = func + "\n" + "*" + i + ". " + currLineAndBP.getLine();
            }
            else{
                func = func + "\n" + i + ". " + entries.getLinesAndBPs(i).getLine();
            }
            if(currentLine == i){
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
        
        //For Read/Write
        if(isSteppingIn && (environmentStack.size() == envSizeBeforeStep + 1) &&
           (environmentStack.lastElement().startLine == -1) ){
                isSteppingIn = false;
                continueExecuting = false;
        }
    }
    public void popCurrEnvironment(){
        currLine = (int)lineStack.pop();
        environmentStack.pop();
        if((isSteppingOut && (environmentStack.size() == envSizeBeforeStep - 1)) ||
            isSteppingOver && (environmentStack.size() == envSizeBeforeStep)){
            isSteppingOut = false;
            isSteppingOver = false;
            continueExecuting = false;
        }
        
    }
    public void pushCurrLine(int line){
        lineStack.push(line);
    }
    public int peekLine(){
        return (int)lineStack.peek();
    }
    public void pushToCallStack(String call){
        this.callStack.push(call);
    }
    public void pop(int amount){
        environmentStack.lastElement().doPop(amount);
    }
    
    public void stepOut(){
        envSizeBeforeStep = environmentStack.size();
        this.isSteppingOut = true;
        continueExe();
    }
    public void stepIn(){
        envSizeBeforeStep = environmentStack.size();
        this.isSteppingIn = true;
        continueExe();
   }
    public void executeStepIn(int numOfFormals){
        pc++;
        for(int i = 0; i < numOfFormals; i ++){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            pc++;
        }
        isSteppingIn = false;
        continueExecuting = false;
    }
    public void stepOver(){
        envSizeBeforeStep = environmentStack.size();
        this.isSteppingOver = true;
        continueExe();
    }
    public void trace(){
        this.isTracing = true;
    }
    public String getAllBPs(){
        String bps = "Current Breakpoints:";
        for(int i = 1; i < entries.size(); i ++){
            if(entries.getLinesAndBPs(i).hasBreakpoint()){
                bps = bps + "\n" + i;
            }
        }
        return bps;
    }
    
    public boolean isInFunction(){
        if(( (currLine == environmentStack.lastElement().getStartLine() + 1) &&
              environmentStack.size() == envSizeBeforeStep + 1)){
            return true;
        }
        return false;
    }
    public boolean isInSameFunction(){
        if(isSteppingOver && (environmentStack.size() == envSizeBeforeStep)){
            return true;
        }
        return false;
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
