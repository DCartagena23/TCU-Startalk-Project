package edu.cs.tcu.tcustartalkproject.Chapter;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.cs.tcu.tcustartalkproject.Book.Book;
import edu.cs.tcu.tcustartalkproject.GrammarWord.GrammarWord;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Document(collection= "Chapter")
public class Chapter {
    @Id
    private String id;

    private Integer number = null;
    private String title = null;

    List<String> text = null;

    @DBRef
    @JsonBackReference
    private Book book;

    @DBRef
    @JsonManagedReference
    private List<GrammarWord> grammarWords = new ArrayList<GrammarWord>();

    private List<Pinyin> pinyin = new ArrayList<Pinyin>();

    private List<TimeStamp> timeStamp = new ArrayList<TimeStamp>();


    /**
     * Constructor for Chapter without arguments
     */
    public Chapter() {
        super();
        this.setTextFromString("Please input something!");
    }

    /**
     * Constructor for Chapter with arguments
     * @param id index of chapter in the database
     * @param number chapter number
     * @param title title of chapter
     */
    public Chapter(String id, Integer number, String title) {
        this.id = id;
        this.number = number;
        this.title = title;
        this.setTextFromString("Please input something!");
    }

    /**
     * Get index of chapter in the database
     * @return index of chapter
     */
    public String getId() {
        return id;
    }

    /**
     * Set index of chapter in the database
     * @param id index to be set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get chapter number
     * @return chapter number
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Set chapter number
     * @param number number to be set for the chapter
     */
    public void setNumber(Integer number) {
        this.number = number;
    }

    /**
     * Get title of chapter
     * @return title to be set
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of chapter
     * @param title title to be set for the chapter
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get text of chapters.
     * @return List of sentences.
     */
    public List<String> getText() {
        return text;
    }

    /**
     * Set text of chapters
     * @param text List of sentences of that chapter.
     */
    public void setText(List<String> text) {
        this.text = text;
    }

    /**
     * Set text of chapters given a string.
     * The method parses the string based on '\n' and ' ', then inserts them into an ArrayList<String>
     * @param str text of chapters.
     */
    public void setTextFromString(String str){
        List<String> text = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(str,"\n");
        while (st.hasMoreTokens()){
            StringTokenizer st2 = new StringTokenizer(st.nextToken()," ");
            while (st2.hasMoreTokens()){
                text.add(st2.nextToken());
                if (!st2.hasMoreTokens() && st.hasMoreTokens()){
                    text.add("\n");
                }
            }
        }
        this.text = text;
    }

    /**
     * Get book that contains this chapter.
     * @return Book object
     */
    public Book getBook() {
        return book;
    }

    /**
     * Set book that contains this chapter.
     * @param book Book object
     */
    public void setBook(Book book) {
        this.book = book;
    }

    /**
     * Get grammar words in this chapter.
     * @return grammar words in a list.
     */
    public List<GrammarWord> getGrammarWords() {
        return grammarWords;
    }

    /**
     * Set grammar words in this chapter.
     * @param grammarWords list of grammar words to be set.
     */
    public void setGrammarWords(List<GrammarWord> grammarWords) {
        this.grammarWords = grammarWords;
    }

    /**
     * Add a new grammar word to this chapter.
     * @param grammarWord grammar word to be added.
     */
    public void addGrammarWords(GrammarWord grammarWord){
        this.grammarWords.add(grammarWord);
        grammarWord.setChapter(this);
    }

    /**
     * Get pinyin from this chapter as a list.
     * @return list of pinyin.
     */
    public List<Pinyin> getPinyin() {
        return pinyin;
    }

    /**
     * Set pinyin from this chapter
     * @param pinyin List of pinyin
     */
    public void setPinyin(List<Pinyin> pinyin) {
        this.pinyin = pinyin;
    }

    /**
     * Add pinyin to this chapter
     * @param pinyin to be added
     */
    public void addPinyin(Pinyin pinyin){
        this.pinyin.add(pinyin);
    }

    /**
     * Get timestamps of this chapter
     * @return List of timestamps
     */
    public List<TimeStamp> getTimeStamp() {
        return timeStamp;
    }

    /**
     * Set timestamps of this chapter
     * @param timeStamp List of timestamps to be set
     */
    public void setTimeStamp(List<TimeStamp> timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Add a timestamp to this chapter
     * @param timeStamp timestamp to be added.
     */
    public void addTimeStamp(TimeStamp timeStamp){
        this.timeStamp.add(timeStamp);
    }
}
