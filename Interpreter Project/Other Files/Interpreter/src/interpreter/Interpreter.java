package interpreter;

import interpreter.virtualmachine.VirtualMachine;
import interpreter.program.Program;
import interpreter.codetable.CodeTable;
import interpreter.bytecodeloader.ByteCodeLoader;
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

public Interpreter(String codeFile) {

try {

CodeTable.init();

bcl = new ByteCodeLoader(codeFile);

} catch (IOException e) {

System.out.println("**** " + e);

}catch (IllegalAccessException e){
    System.out.println("IllegalAccessException");
}

}

void run() {

Program program = bcl.loadCodes();

VirtualMachine vm = new VirtualMachine(program);

vm.executeProgram();

}

public static void main(String args[]) {

if (args.length == 0) {

System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");

System.exit(1);

}

(new Interpreter(args[0])).run();

}

}