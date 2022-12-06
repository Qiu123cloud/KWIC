package cn.edu.bupt.KWICSystem.line;

public interface LineStorage {
    void setChar(char c, int position, int word_index, int line_index);
    char getChar(int position, int word_index, int line_index);
    void addChar(char c, int word_index, int line_index);
    void deleteChar(int position, int word_index, int line_index);
    int getCharCount(int word_index, int line_index);
    void setWord(char[] chars, int word_index, int line_index);
    void setWord(String chars, int word_index, int line_index);
    String getWord(int word_index, int line_index);
    void addWord(char[] chars, int line_index);
    void addWord(String chars, int line_index);
    void addEmptyWord(int line_index);
    void deleteWord(int word_index, int line_index);
    int getWordCount(int line_index);
    void setLine(char[][] words, int line_index);
    void setLine(String[] words, int line_index);
    String[] getLine(int line_index);
    String getLineAsString(int line_index);
    void addLine(char[][] words);
    void addLine(String[] words);
    void addEmptyLine();
    void deleteLine(int line_index);
    int getLineCount();
}
