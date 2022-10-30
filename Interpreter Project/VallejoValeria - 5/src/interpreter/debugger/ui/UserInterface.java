package interpreter.debugger.ui;

import interpreter.debugger.DebugVirtualMachine;
import interpreter.debugger.LinesAndBPs;
import interpreter.debugger.SourceCode;
import java.util.*;

/**
 * The UserInterface class allows the user to control the debugVM with multiple
 * commands. It continues to run until the user says to quit.
 */

public class UserInterface {
    DebugVirtualMachine dvm;
    boolean runUI = true;
    
    public UserInterface (DebugVirtualMachine debugvm){
        this.dvm = debugvm;
    }
    
    public void run(){
        printSourceCode();
        while(runUI){
            System.out.println("\nType ? for help");
            getCommand();
        }
        
    }
    
    public void help(){
        System.out.println("\nUser Commands: \n"
                         + "Set Breakpoint: bp <line number>\n"
                         + "Clear Breakpoint: clr <line number>\n"
                         + "Display Current Function: currFunc\n" //change
                         + "Continue Execution: c\n"
                         + "Display Variables: vars\n"
                         + "Step Out of Function: stepout\n"
                         + "Quit Execution: quit");
    }
    
    public String setBreakpoint(int breakpoint){
        return dvm.setBreakpoint(breakpoint);
    }
    public String clearBreakpoint(int breakpoint){
        return dvm.clearBreakpoint(breakpoint);
    }
    public String displayFunction(){
        return dvm.displayFunction();
    }
    public String displayVariables(){
        return dvm.displayVariables();
    }
    public void continueExe(){
        dvm.continueExe();
    }
    public void quitExe(){
        dvm.quitExe();
        System.out.println("\n******Execution Quit******\n");
    }
    public void stepOut(){
        dvm.stepOut();
    }
    public void getCommand(){
        Scanner input = new Scanner(System.in);
        String command = input.next();
        switch(command){
                case "bp":
                    int bpLineNum = input.nextInt();
                    System.out.println(setBreakpoint(bpLineNum));
                    break;
                case "clr":
                    int clrLineNum = input.nextInt();
                    System.out.println(clearBreakpoint(clrLineNum));
                    break;
                case "currFunc":
                    System.out.println(dvm.displayFunction());
                    break;
                case "c":
                    continueExe();
                    break;
                case "vars":
                    System.out.println(displayVariables());
                    break;
                case "stepout":
                    stepOut();
                     break;
                case "quit":
                    quitExe();
                    runUI = false;
                    break;
                case "?":
                    help();
                    break;
                default:
                    System.out.println("Invalid command.");
                    break;
            }
    }
    
    public void printSourceCode(){
        SourceCode source = dvm.getSource();
        for(int i = 1; i < source.size(); i++){
            LinesAndBPs currLineAndBP = source.getLinesAndBPs(i);
            System.out.println(i + ". " + currLineAndBP.getLine());
        }
    }
    
}
