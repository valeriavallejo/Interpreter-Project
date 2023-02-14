/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functionenvironmentrecord;

/**
 *
 * @author veeva
 */
public class FunctionEnvironmentRecord {
    Table symbolTable = new Table(); 
    String funcName;
    int startLine, endLine, currLine;
    Binder currBinder;
    
    FunctionEnvironmentRecord(){
        this.startLine = -1;
        this.endLine = -1;
        this.currLine = -1;
        this.funcName = "-";
    }
    
    public void beginScope(){
        symbolTable.beginScope();
        printEnvironment();
    }
    public void setFunctionInfo(String name, int start, int end){
        this.funcName = name;
        this.startLine = start;
        this.endLine = end;
        
        printEnvironment();
    }
    public void setCurrentLineNumber(int curr){
        this.currLine = curr;
        
        printEnvironment();
    }
    public void setVarVal(String var, int value){
        symbolTable.put(var, value);
        currBinder = symbolTable.getBinder(var);
        printEnvironment();
    }
    public void doPop(int amount){
        symbolTable.pop(amount);
        currBinder = symbolTable.getBinder(symbolTable.getTop());
        printEnvironment();
    }
    
    public String getFuncName(){
        return this.funcName;
    }
    public int getCurrLine(){
        return this.currLine;
    }
    public int getStartLine(){
        return this.startLine;
    }
    public int getEndLine(){
        return this.endLine;
    }
    
    public void setFuncName(String name){
        this.funcName = name;
    }
    public void setCurrLine(int line){
        this.currLine = line;
    }
    public void setEndLine(int line){
        this.endLine = line;
    }
    public void setStartLine(int line){
        this.startLine = line;
    }
    
    /**Printing function that is called after every function that changes a 
     * member variable of the FunctionEnvironmentRecord.
    */
    public void printEnvironment(){
        if(currBinder != null){
            System.out.print("(<");
            Binder tempBinder = currBinder;
            String currKey = symbolTable.getTop();
            for(int i = 0; i < symbolTable.getSize(); i++){
                int currValue = symbolTable.get(currKey);
                System.out.print(currKey + "/" + currValue + ",");
                currKey = tempBinder.getPrevtop();
                tempBinder = symbolTable.getBinder(currKey);
            }
        }
        else{
            System.out.print("(<");
        }
        System.out.println(">, " + funcName + ", " + startLine + ", " + endLine + ", " + currLine + ")");
    }
    public static void main(String[] args) {
        FunctionEnvironmentRecord fr = new FunctionEnvironmentRecord();

        fr.beginScope();

        fr.setFunctionInfo("g",1,20);

        fr.setCurrentLineNumber(5);

        fr.setVarVal("a",4);

        fr.setVarVal("b",2);

        fr.setVarVal("c",7);

        fr.setVarVal("a",1);

        fr.doPop(2);

        fr.doPop(1);
    }
    
}
