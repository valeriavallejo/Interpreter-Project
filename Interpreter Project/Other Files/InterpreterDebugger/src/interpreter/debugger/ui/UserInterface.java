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
                         + "Clear Breakpoints: clr <line number>\n"
                         + "Display Current Function: currFunc\n"
                         + "Display Current Breakpoints: bplst\n"
                         + "Continue Execution: c\n"
                         + "Display Variables: vars\n"
                         + "Step Out of Function: stepout\n"
                         + "Step Into a Function: stepin\n"
                         + "Step Over a Function: stepover\n"
                         + "Toggle Tracing ON/OFF: trace"
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
    public void stepIn(){
        dvm.stepIn();
    }
    public void stepOver(){
        dvm.stepOver();
    }
    public void trace(){
        dvm.trace();
    }
    public void getCommand(){
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        String[] inputArr = command.split(" ");
        switch(inputArr[0]){
                case "bp":
                    for (int i = 1; i < inputArr.length; i ++){
                        System.out.println(setBreakpoint(Integer.valueOf(inputArr[i])));
                    }
                    //int bpLineNum = input.nextInt();
                    //System.out.println(setBreakpoint(bpLineNum));
                    break;
                case "clr":
                    for(int i = 1; i < inputArr.length; i ++){
                        System.out.println(clearBreakpoint(Integer.valueOf(inputArr[i])));
                    }
                    break;
                case "currFunc":
                    System.out.println(dvm.displayFunction());
                    break;
                case "bplst":
                    System.out.println(dvm.getAllBPs());
                    break;
                case "c":
                    continueExe();
                    System.out.println(displayFunction());
                    break;
                case "vars":
                    System.out.println(displayVariables());
                    break;
                case "stepout":
                    stepOut();
                    System.out.println(displayFunction());
                     break;
                case "stepin":
                    stepIn();
                    System.out.println(displayFunction());
                     break;
                case "stepover":
                    stepOver();
                    System.out.println(displayFunction());
                     break;
                case "trace":
                    trace();
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
