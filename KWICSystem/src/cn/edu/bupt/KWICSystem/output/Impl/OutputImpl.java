package cn.edu.bupt.KWICSystem.output.Impl;

import cn.edu.bupt.KWICSystem.output.Output;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OutputImpl implements Output {

    /**
     * 打印运行结果
     * @param alphabetizer：输出排序后的结果
     */
    @Override
    public List<String> printResult(Alphabetizer alphabetizer) {
        List<String> res = new ArrayList<>();

        System.out.println("运行结果:");
        for(int i = 0; i < alphabetizer.getLineCount(); i++) {
            res.add(alphabetizer.getLineAsString(i));
            System.out.println(alphabetizer.getLineAsString(i));
        }

        return res;
    }

    /**
     * 写入运行结果到文件：输出排序后的结果
     * @param alphabetizer
     */
    @Override
    public void writeFile(Alphabetizer alphabetizer, String path) {
        //输出结果的文件名称
        File filename = new File(path);

        try {
            filename.createNewFile();
            BufferedWriter result = new BufferedWriter(new FileWriter(filename));
            for(int i = 0; i < alphabetizer.getLineCount(); i++) {
                result.write(alphabetizer.getLineAsString(i) + "\r\n");
            }
            result.flush();
            result.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("运行结果已经成功存放在文件：" + filename.getAbsolutePath());
    }
}
