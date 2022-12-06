package cn.edu.bupt.KWICSystem.line.impl;

import cn.edu.bupt.KWICSystem.line.Line;
import java.util.ArrayList;

public class LineImpl implements Line {
    private ArrayList<String> words_ = new ArrayList<>();

    @Override
    public void setChar(char ch, int pos, int word_index) {
        char[] word_;

        if (word_index < words_.size()) {
            word_ = words_.get(word_index).toCharArray();
            word_[pos] = ch;
            words_.set(word_index, new String(word_, 0, word_.length));
        }
    }

    @Override
    public char getChar(int pos, int word_index) {
        if (word_index < words_.size()) {
            char[] word = words_.get(word_index).toCharArray();
            return word[pos];
        }
        return 0;
    }

    @Override
    public void addChar(char ch, int word_index) {
        char[] word_;
        char[] new_word_;

        if (word_index < words_.size()) {
            word_ = words_.get(word_index).toCharArray();
            new_word_ = new char[word_.length + 1];
            new_word_[word_.length] = ch;
            words_.set(word_index, new String(new_word_, 0, word_.length + 1));
        }
    }

    @Override
    public void deleteChar(int pos, int word_index) {
        StringBuffer word_;

        if (word_index < words_.size()) {
            word_ = new StringBuffer(words_.get(word_index));
            word_.deleteCharAt(pos);
            words_.set(word_index, word_.toString());
        }
    }

    @Override
    public int getCharCount(int word_index) {
        if (word_index < words_.size()) {
            return words_.get(word_index).length();
        }
        return -1;
    }

    @Override
    public void setWord(char[] word, int word_index) {
        setWord(new String(word), word_index);
    }

    @Override
    public void setWord(String word, int word_index) {
        if (word_index < words_.size()) {
            words_.set(word_index, word);
        }
    }

    @Override
    public String getWord(int word_index) {
        if (word_index < words_.size()) {
            return words_.get(word_index);
        }
        return null;
    }

    @Override
    public void addWord(char[] word) {
        addWord(new String(word));
    }

    @Override
    public void addWord(String word) {
        words_.add(word);
    }

    @Override
    public void addEmptyWord() {
        words_.add(new String());
    }

    @Override
    public void deleteWord(int word_index) {
        if (word_index < words_.size()) {
            words_.remove(word_index);
        }
    }

    @Override
    public int getWordCount() {
        return words_.size();
    }
}
