/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interpreter.debugger;

/** <pre>
* Binder objects group 3 fields
* 1. a value
* 2. the next link in the chain of symbols in the current scope
* 3. the next link of a previous Binder for the same identifier
* in a previous scope
* </pre>
*/
class Binder {
    private int value;
    private String prevtop; // prior symbol in same scope
    private Binder tail; // prior binder for same symbol
    // restore this when closing scope
    Binder(int v, String p, Binder t) {
        value=v; prevtop=p; tail=t;
    }
    int getValue() { return value; }
    String getPrevtop() { return prevtop; }
    Binder getTail() { return tail; }
}

public class Table {
    private java.util.HashMap<String,Binder> symbols = new
   java.util.HashMap<String,Binder>();
    private String top; // reference to last symbol added to
        // current scope; this essentially is the
        // start of a linked list of symbols in scope
    private Binder marks; // scope mark; essentially we have a stack of
        // marks - push for new scope; pop when closing
        // scope


 public Table(){}
/**
 * Gets the object associated with the specified symbol in the Table.
 */
    public int get(String key) {
        Binder e = symbols.get(key);
        return e.getValue();
    }
    
    public Binder getBinder(String key){
        return symbols.get(key);
    }
    
    public String getTop(){
        return this.top;
    }
    public int getSize(){
        return symbols.size();
    }
/**
 * Puts the specified value into the Table, bound to the specified Symbol.<br>
 * Maintain the list of symbols in the current scope (top);<br>
 * Add to list of symbols in prior scope with the same string identifier
 */
 public void put(String key, int value) {
        symbols.put(key, new Binder(value, top, symbols.get(key)));
        top = key;
 }
 
 /**
  * Pops a number of pairs from the table, specified by the parameter "amount"
  * It first saves the previous top
  * Then it restores any previous instances of that variable
  * Lastly, it removes the item from the map and sets a new top.
 */
 public void pop(int amount){
     for(int i = 0; i < amount; i++){
        String newTop = symbols.get(top).getPrevtop();
        if(symbols.get(top).getTail() != null){
            Binder prevVar = symbols.get(top).getTail();
            symbols.remove(top);
            symbols.put(top, prevVar);
        }
        else{
            symbols.remove(top);
        }
         top = newTop;
     }
     //restore if there was a previous one with same var
 }
/**
 * Remembers the current state of the Table; push new mark on mark stack
 */
 public void beginScope() {
    marks = new Binder(-1,top,marks);
    top=null;
 }
 public java.util.Set<String> keys() {return symbols.keySet();}
}