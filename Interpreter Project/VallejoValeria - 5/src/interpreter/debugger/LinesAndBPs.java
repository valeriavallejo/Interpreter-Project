package interpreter.debugger;

/**
 * The LinesAndBPs class is a structure where the member variables contain a source
 * line and a boolean that states whether that line has a breakpoint or not
 */
public class LinesAndBPs {
    String sourceLine;
    boolean hasBreakpoint;
    
    LinesAndBPs(String line){
        this.sourceLine = line;
    }
    
    public void addBP(){
        this.hasBreakpoint = true;
    }
    public void removeBP(){
        this.hasBreakpoint = false;
    }
    
    public String getLine(){
        return this.sourceLine;
    }
    public boolean hasBreakpoint(){
        return this.hasBreakpoint;
    }
}
