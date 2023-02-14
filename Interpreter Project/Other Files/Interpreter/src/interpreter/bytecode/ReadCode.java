/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.bytecode;

import interpreter.virtualmachine.VirtualMachine;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * The ReadCode class takes in input from the user and pushes it to the top 
 * of the runStack.
 */
public class ReadCode extends ByteCode{
    ArrayList<String> args;
    
    public ReadCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
    }
    public void execute(VirtualMachine vm) {
        System.out.println("Enter a number: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int input = Integer.MAX_VALUE;
        
        try{
            input = Integer.valueOf(reader.readLine());
        }catch(IOException e){
            System.out.println("Read input error");
        }
        
        vm.push(input);
    }
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        return "READ";
    }
}
