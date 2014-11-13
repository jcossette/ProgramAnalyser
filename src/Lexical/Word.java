package Lexical;

/**
 * This class represents a word that was parsed and includes its content.
 * Created by Julien Cossette on 11/10/2014.
 */
public class Word {
    private String content;

    public Word(String toWordify) {
        this.content = toWordify;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
