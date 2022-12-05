package cn.edu.bupt.KWICSystem.output;

import cn.edu.bupt.KWICSystem.sort.Alphabetizer;

import java.util.List;

public interface Output {
    List<String> printResult(Alphabetizer alphabetizer);
    void writeFile(Alphabetizer alphabetizer);
}
