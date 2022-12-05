package cn.edu.bupt.KWICSystem.shift;

import java.util.List;

public interface CircularShifter {

    char getChar(int position, int word, int lines);
    int getCharCount(int word, int lines);
    String getWord(int word, int lines);
    String getLineAsString(int lines);
    int getWordCount(int lines);
    String[] getLine(int lines);
    int getLineCount();
    void setup(String lines);
    void setup(List<String> fileLines);
}
