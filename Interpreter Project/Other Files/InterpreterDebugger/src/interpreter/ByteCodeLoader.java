/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter;

import interpreter.bytecode.ByteCode;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;
import java.lang.reflect.*;
import java.util.ArrayList;

/**
 * The ByteCodeLoader class reads from the file and creates ByteCode objects by 
 * using the CodeTable
 * It then loads the ByteCode objects to a Program object 
 */

public class ByteCodeLoader extends Object{
    public Program program = new Program();
    public ArrayList bcObjList = new ArrayList();
    
    public ByteCodeLoader(String programFile) throws IOException,
                                                     IllegalAccessException
                                                     {
        String file = new String(Files.readAllBytes(Paths.get(programFile)));
        StringTokenizer st = new StringTokenizer(file, "\n");
        while(st.hasMoreTokens()){
            String tok = st.nextToken();
            
            StringTokenizer line = new StringTokenizer(tok);
            String bcStr = line.nextToken();
            
            ArrayList<String> args = new ArrayList<>();
            while(line.hasMoreTokens()){
                args.add(line.nextToken());
            }

            if( !((CodeTable.getClassName(bcStr)).equals("")) ){
                String className = CodeTable.getClassName(bcStr);
                try{
                    Class byteCodeClass = Class.forName(CodeTable.getPackageClassName(className));
                    Object obj = byteCodeClass.newInstance();
                    ByteCode bc = (ByteCode)obj;

                    bc.init(args);
                    bcObjList.add(bc);

                }catch(ClassNotFoundException e){
                    System.out.println("Class not found.");
                }catch(InstantiationException e){
                    System.out.println("Cannot instantiate object");
                }catch(IllegalAccessException ea){
                    System.out.println("Cannot access");
                }
 
            }
                    
        }
            
    }
    
    public Program loadCodes(){
        for(int i = 0; i < bcObjList.size(); i++){
            Object obj = bcObjList.get(i);
            ByteCode bc = (ByteCode)obj;
        }
        program.setList(bcObjList);   
        return program;
    }
    
    
}
