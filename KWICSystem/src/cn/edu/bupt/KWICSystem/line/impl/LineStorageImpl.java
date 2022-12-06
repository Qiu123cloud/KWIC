package cn.edu.bupt.KWICSystem.line.impl;

import cn.edu.bupt.KWICSystem.line.Line;
import cn.edu.bupt.KWICSystem.line.LineStorage;

import java.util.ArrayList;

public class LineStorageImpl implements LineStorage {
    private ArrayList<Line> lines_ = new ArrayList<>();

    @Override
    public void setChar(char ch, int pos, int word_index, int line_index) {
        lines_.get(line_index).setChar(ch, pos, word_index);
    }

    @Override
    public char getChar(int pos, int word_index, int line_index) {
        return (lines_.get(line_index).getChar(pos, word_index));
    }

    @Override
    public void addChar(char ch, int word_index, int line_index) {
        lines_.get(line_index).addChar(ch, word_index);
    }

    @Override
    public void deleteChar(int pos, int word_index, int line_index) {
        lines_.get(line_index).deleteChar(pos, word_index);
    }

    @Override
    public int getCharCount(int word_index, int line_index) {
        return (lines_.get(line_index).getCharCount(word_index));
    }

    @Override
    public void setWord(char[] chars, int word_index, int line_index) {
        setWord(new String(chars), word_index, line_index);
    }

    @Override
    public void setWord(String word, int word_index, int line_index) {
        // replace the old word with the new one
        lines_.get(line_index).setWord(word, word_index);
    }

    @Override
    public String getWord(int word_index, int line_index) {
        return (lines_.get(line_index).getWord(word_index));
    }

    @Override
    public void addWord(char[] chars, int line_index) {
        addWord(new String(chars), line_index);
    }

    @Override
    public void addWord(String word, int line_index) {
        // add the new word
        lines_.get(line_index).addWord(word);
    }

    @Override
    public void addEmptyWord(int line_index) {
        // add the new word
        lines_.get(line_index).addEmptyWord();
    }

    @Override
    public void deleteWord(int word_index, int line_index) {
        // delete the specified word
        lines_.get(line_index).deleteWord(word_index);
    }

    @Override
    public int getWordCount(int line_index) {
        return (lines_.get(line_index)).getWordCount();
    }

    @Override
    public void setLine(char[][] words, int line_index) {
        String[] tmp = new String[words.length];
        for(int i = 0; i < words.length; i++)
            tmp[i] = new String(words[i]);
        setLine(tmp, line_index);
    }

    @Override
    public void setLine(String[] words, int line_index) {
        Line new_line = new LineImpl();
        for (int i = 0; i < words.length; i++) {
            new_line.addWord(words[i]);
        }
        lines_.set(line_index, new_line);
    }

    @Override
    public String[] getLine(int line_index) {
        Line current_line = lines_.get(line_index);
        // create the String[] representation of the line
        String[] tmp = new String[current_line.getWordCount()];
        for(int i = 0; i < tmp.length; i++)
            tmp[i] = current_line.getWord(i);
        return tmp;
    }

    @Override
    public String getLineAsString(int line_index) {
        Line current_line = lines_.get(line_index);

        // calculate the length of the line
        int size = current_line.getWordCount();
        int length = 0;
        for(int i = 0; i < size; i++)
            length += getWord(i, line_index).length();
        // add the length of space characters delimiting the words
        length += size - 1;
        // initialize the char[]
        char[] tmp = new char[length];
        // create the String representation of the line
        int count = 0;
        for(int i = 0; i < size; i++){
            getWord(i, line_index).getChars(0, getWord(i, line_index).length(), tmp, count);
            count += getWord(i, line_index).length();
            if(i != (size - 1))
                tmp[count++] = ' ';
        }

        return new String(tmp);
    }

    @Override
    public void addLine(char[][] words) {
        String[] tmp = new String[words.length];
        for(int i = 0; i < words.length; i++)
            tmp[i] = new String(words[i]);
        addLine(tmp);
    }

    @Override
    public void addLine(String[] words) {
        Line current_line = new LineImpl();
        // add words
        for(int i = 0; i < words.length; i++)
            current_line.addWord(words[i]);
        // add the new line at the end
        lines_.add(current_line);
    }

    @Override
    public void addEmptyLine() {
        // add the new line at the end
        lines_.add(new LineImpl());
    }

    @Override
    public void deleteLine(int line_index) {
        lines_.remove(line_index);
    }

    @Override
    public int getLineCount() {
        return lines_.size();
    }
}
