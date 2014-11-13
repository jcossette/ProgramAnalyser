package Lexical;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class parses an input text file and returns its individual words.
 * Created by Julien Cossette on 11/10/2014.
 */
public class LexicalParser {
    private FileReader myReader;
    private BlockingQueue<Word> myWordStack;
    private String fileToParse;

    public LexicalParser(String filename) {
        fileToParse = filename;
        myWordStack = new LinkedBlockingQueue<Word>();
    }

    /**
     * This method parses the specified file and returns a word stack for this file.
     */
    public void parse() {
        try {
            int result;
            char currentChar;
            String currentWord = "";

            myReader = new FileReader(fileToParse);

            while ((result = myReader.read()) != -1) {
                currentChar = (char) result;
                if (checkValidChar(currentChar) == true) {
                    currentWord += currentChar;
                } else if (checkSymbol(currentChar) == true) {
                    stackWord(currentWord);
                    currentWord = "";
                    stackWord("" + currentChar);
                } else {
                    stackWord(currentWord);
                    currentWord = "";
                }
            }
            stackWord(currentWord);

        } catch (Exception e) {
            System.out.println("File could not be opened: " + e.getMessage());
        }
    }

    /**
     * This method checks if we have reached the end of the word. (Not the world)
     *
     * @return True if we have reached the end of the word and false if we are still in the same word.
     */
    public boolean checkValidChar(char c) {
        if (Character.isLetter(c) || Character.isDigit(c) || c == '_') {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the specified char is a symbol.
     *
     * @param c Char to check.
     * @return True if its a symbol, False if its not.
     */
    public boolean checkSymbol(char c) {
        switch (c) {
            case ':':
            case ';':
            case '(':
            case ')':
            case '+':
            case '-':
            case '*':
            case '/':
                return true;
            default:
                return false;
        }
    }

    /**
     * Stacks a word on the word stack.
     *
     * @param word The word to stack.
     */
    public void stackWord(String word) {
        Word toStack = new Word(word);
        try {
            if(word != "" && word != " "){
                myWordStack.put(toStack);
            }
        } catch (Exception e) {
            System.out.println("Cannot stack to queue: " + e.getMessage());
        }
    }

    /**
     * Prints to the console the current content of the word stack.
     */
    public void printWordStack() {
        Word currentWord;
        while ((currentWord = myWordStack.poll()) != null) {
            System.out.println(currentWord.getContent());
        }
    }
}
