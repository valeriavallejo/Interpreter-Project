package interpreter;

import interpreter.debugger.DebugVirtualMachine;
import interpreter.debugger.SourceCodeLoader;
import interpreter.debugger.SourceCode;
import interpreter.debugger.ui.UserInterface;
import java.io.*;

/**

*

*

*

* Interpreter class runs the interpreter:

* 1. Perform all initializations

* 2. Load the bytecodes from file

* 3. Run the virtual machine

*

*

*

*/

public class Interpreter{

ByteCodeLoader bcl;
SourceCodeLoader scl;

public Interpreter(String codeFile, boolean debugMode) {

    if(debugMode){
        try {
        CodeTable.debugInit();

        bcl = new ByteCodeLoader(codeFile + ".x.cod");

        } catch (IOException e) {

        System.out.println("**** " + e);

        }catch (IllegalAccessException e){
            System.out.println("IllegalAccessException");
        }

        try{
            
            scl = new SourceCodeLoader(codeFile + ".x");
        
            }catch(IOException e){
            System.out.println("File not loaded");
            }
    }
    else{
        try {

            CodeTable.init();

            bcl = new ByteCodeLoader(codeFile);

        } catch (IOException e) {

            System.out.println("**** " + e);

        }catch (IllegalAccessException e){
            System.out.println("IllegalAccessException");
        }
    }

}

void run() {

Program program = bcl.loadCodes();

VirtualMachine vm = new VirtualMachine(program);

vm.executeProgram();

}

void runDebugger(){
    Program program = bcl.loadCodes();
    
    SourceCode source = scl.loadLines();

    DebugVirtualMachine dvm = new DebugVirtualMachine(program, source);
    
    UserInterface ui = new UserInterface(dvm);

    ui.run();
}

public static void main(String args[]) {
boolean debugMode;
args = args[0].split(" "); //take out to run on command line
if (args.length == 0) {

System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");

System.exit(1);

}
else if((args.length == 2) && args[0].equals("-d")){
    debugMode = true;
    (new Interpreter(args[1], debugMode)).runDebugger();
}
else{
    debugMode = false;
   (new Interpreter(args[0], debugMode)).run(); 
}

}

}