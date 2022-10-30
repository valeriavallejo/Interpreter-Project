package interpreter.bytecode;

import interpreter.VirtualMachine;
import java.util.ArrayList;

/**
 * The LitCode class pushes an integer object to the top of the runStack. It also
 * has a variable name for when dumping is on
 * 
 */
public class LitCode extends ByteCode{
    protected ArrayList<String> args;
    protected Integer literal;
    protected String var;
    
    public LitCode(){
        
    }
    public void init(ArrayList<String> args){
        this.args = args;
        this.literal = new Integer(Integer.valueOf(args.get(0)));
        if(args.size() > 1){
            this.var = args.get(1);
        }
    }
    public void execute(VirtualMachine vm){
        vm.push(this.literal);
    }
    
    public ArrayList<String> getArgs(){
        return this.args;
    }
    
    public String dumpString(VirtualMachine vm){
        if(vm.dumpStatus == "ON" && args.size() > 1){
            return "LIT " + literal + " " +  var + "   int " + var;
        }
        return "LIT " + literal;
    }
}
