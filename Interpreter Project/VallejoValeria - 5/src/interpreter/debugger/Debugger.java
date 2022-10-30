/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

import java.io.IOException;
/**
 *
 * @author veeva
 */
public class Debugger{
    SourceCodeLoader scl;
    Debugger(String source) throws IOException{
        try{
            
            scl = new SourceCodeLoader(source);
        
        }catch(IOException e){
            System.out.println("File not loaded");
        }
    }
    
    void run(){
        SourceCode entries = scl.loadLines();
        
        //DebugVirtualMachine dvm = new DebugVirtualMachine(prog);

        //dvm.executeDebug();
    }
}
