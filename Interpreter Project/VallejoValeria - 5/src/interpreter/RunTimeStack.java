package interpreter;
import interpreter.bytecode.ByteCode;
import interpreter.CodeTable;
import java.util.*;

/**
 *The RunTimeStack class holds all of the data from the executed code and stores
 *the indices of the current frames
 */
public class RunTimeStack {
    Stack framePointers;
    Vector runStack;

    public RunTimeStack(){
        this.framePointers = new Stack();
        this.framePointers.push(0);
        
        this.runStack = new Vector();
    }
    public int peek(){
        int topVal = (int)runStack.get(runStack.size() - 1);
        return topVal;
    }
    public int pop(){
        int topVal = (int)runStack.get(runStack.size() - 1);
        runStack.remove(runStack.size() - 1);
        return topVal;
    }
    public int push(int i){
        runStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset){
        framePointers.add(runStack.size() - offset); 
    }
    public void popFrame(){
        int returnVal = this.pop();
        int currFrameSize = runStack.size() - (int)framePointers.lastElement();
        for(int i = 0; i < currFrameSize; i ++){
            this.pop();
        }

        framePointers.pop();
        this.push(returnVal);
    }
    
    public int store(int offset){
        int topOfStack = this.pop();
        int storeIndex = offset + (int)framePointers.lastElement();
        this.runStack.set(storeIndex, topOfStack);
        return 0;
    }
    public int load(int offset){
        int valIndex = (int)framePointers.lastElement();
        int loadVal = (int)runStack.get(valIndex);
        this.push(loadVal);
        return valIndex;
    }
    
    public Integer push(Integer i){
        this.runStack.add(i);
        return i;
    }
    
    public void printFrames(){
        String currFrame = "[";
            int i;
            for(i = 0; i < runStack.size() - 1; i++){
                if(framePointers.contains(i) && i != 0){
                    currFrame = currFrame + "][" + runStack.get(i);
                }
                currFrame = currFrame + runStack.get(i) + ",";
            }
            currFrame = currFrame + runStack.get(i) + "]";
            
        System.out.println(currFrame);
    }
    
    public int framePtrPeek(){
       return (int)framePointers.lastElement();
   }
    public int runStackSize(){
       return runStack.size();
   }
    public int getValAt(int i){
       return (int)runStack.get(i);
   }
   
}