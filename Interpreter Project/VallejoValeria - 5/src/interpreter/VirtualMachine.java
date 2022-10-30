/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecode.ByteCode;
import java.util.*;

/**
 * The Virtual Machine class allows us to walk through the program and execute the
 * ByteCodes one by one until we hit the HALT code.
 * This class is the connection between the ByteCode classes and the RunTimeStack
 */

public class VirtualMachine {
    protected RunTimeStack runStack = new RunTimeStack();
    protected int pc;
    protected Stack returnAddrs = new Stack();
    
    public boolean isRunning;
    public Program program = new Program();
    public String dumpStatus = "OFF";
    
    public VirtualMachine(Program prog){
        
    }
    
    public void executeProgram() {
        pc = 0;
        isRunning = true;
        while (isRunning) {
            ByteCode code = program.getCode(pc);
            code.execute(this);
            this.dump(code);
            pc++;
        }
    }
    
    public void dump(ByteCode code){
        if(!code.getClass().getName().equals("interpreter.bytecode.DumpCode")){
            System.out.println(code.dumpString(this));
            runStack.printFrames();
        }

    }
    public int getPC(){
        return this.pc;
    }
    public void setPC(int newPC){
        this.pc = newPC;
    }
    public void pushReturnAddr(int currPC){
        this.returnAddrs.add(currPC);
    }
    public int popReturnAddr(){
        return (int)this.returnAddrs.pop();
    }
    
    public int peek(){
        return this.runStack.peek();
    }
    public int pop(){
        return this.runStack.pop();
    }
    public int push(int i){
        return this.runStack.push(i);
    }
    
    public void newFrameAt(int offset){
        this.runStack.newFrameAt(offset);
    }
    public void popFrame(){
        this.runStack.popFrame();
    }
    
    public int store(int offset){
        this.runStack.store(offset);
        return 0;
    }
    public int load(int offset){
        this.runStack.load(offset);
        return 0;
    }
    public Integer push(Integer i){
        return this.runStack.push(i);
    }

    public int getReturnAddrsSize(){
        return this.returnAddrs.size();
    }
    
   public int runStackSize(){
       return runStack.runStackSize();
   }
   public int framePtrPeek(){
       return runStack.framePtrPeek();
   }
   public int getValAt(int i){
       return runStack.getValAt(i);
   }
}
