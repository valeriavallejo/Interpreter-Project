package interpreter.debugger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.StringTokenizer;

/**
 * The SourceCodeLoader class loads the source code into a SourceCode structure
 */
public class SourceCodeLoader{
    SourceCode entries = new SourceCode();
    
    public SourceCodeLoader (String programFile)throws IOException{
        String file = new String(Files.readAllBytes(Paths.get(programFile)));
        StringTokenizer st = new StringTokenizer(file, "\n");
        while(st.hasMoreTokens()){
            String line = st.nextToken();
            entries.addLine(line);
        }
    }
    public SourceCode loadLines(){
        return entries;
    }
}
