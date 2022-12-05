package cn.edu.bupt.KWICSystem.shift.impl;

import cn.edu.bupt.KWICSystem.line.LineStorage;
import cn.edu.bupt.KWICSystem.line.impl.LineStorageImpl;
import cn.edu.bupt.KWICSystem.shift.CircularShifter;

import java.util.List;
//对LineStorage 模块的行进行循环移位。
public class CircularShifterImpl implements CircularShifter {


    private LineStorage shift = new LineStorageImpl();

    @Override
    public char getChar(int position, int word, int lines) {
        return shift.getChar(position, word, lines);
    }

    @Override
    public int getCharCount(int word, int lines) {
        return shift.getCharCount(word, lines);
    }

    @Override
    public String getWord(int word, int lines) {
        return shift.getWord(word, lines);
    }

    @Override
    public int getWordCount(int lines) {
        return shift.getWordCount(lines);
    }

    @Override
    public String[] getLine(int lines) {
        return shift.getLine(lines);
    }

    @Override
    public String getLineAsString(int lines) {
        return shift.getLineAsString(lines);
    }

    @Override
    public int getLineCount() {
        return shift.getLineCount();
    }

    @Override
    public void setup(String lines) {
        String[] words = lines.split("\\s+");

        // 遍历当前行的所有单词
        for(int j = 0; j < words.length; j++){

            // 为当前shift添加新的空行
            shift.addEmptyLine();

            // 将当前 shift的所有单词相加
            for(int k = j; k < (words.length + j); k++)
                //将当前单词添加到最后一行
                // index是k除以line.length的余数
                shift.addWord(words[k % words.length], shift.getLineCount() - 1);

        }
    }

    @Override
    public void setup(List<String> fileLines) {
        if (fileLines == null) {
            System.out.println("读取文件失败！");
        }
        for (String line : fileLines) {
            setup(line);
        }
    }



}
