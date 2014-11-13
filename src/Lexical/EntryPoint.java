package Lexical;

/**
 * Created by coylter on 11/10/2014.
 */
public class EntryPoint {
    public static void main(String param[]) {
        LexicalParser myTestParser = new LexicalParser("ProgramToParse.txt");
        myTestParser.parse();
        myTestParser.printWordStack();
    }
}
