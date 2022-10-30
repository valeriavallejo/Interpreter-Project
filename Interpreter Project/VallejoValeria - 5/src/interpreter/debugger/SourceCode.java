/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import java.util.Vector;

/**
 * The SourceCode class has a vector of LinesAndBPs as its member variable so 
 * that the debugVM can refer to the source code and see where to breakpoints
 * are
 */
public class SourceCode {
    private static Vector<LinesAndBPs> entries = new Vector<LinesAndBPs>();
    
    SourceCode(){
        entries.add(new LinesAndBPs("")); //add empty object to align indexing
    }
    public LinesAndBPs getLinesAndBPs(int lineNum){
        return entries.elementAt(lineNum);
    }
    public void addLine(String line){
        entries.add(new LinesAndBPs(line));
    }
    public int size(){
        return entries.size();
    }
}
