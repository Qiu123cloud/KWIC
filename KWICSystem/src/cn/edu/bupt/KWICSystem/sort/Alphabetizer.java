package cn.edu.bupt.KWICSystem.sort;

import cn.edu.bupt.KWICSystem.shift.CircularShifter;

public interface Alphabetizer {
    void alpha(CircularShifter shif);
    String[] getLine(int lines);
    int getLineCount();
    String getLineAsString(int lines);

}

