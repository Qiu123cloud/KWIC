package cn.edu.bupt.KWICSystem.input.impl;

import cn.edu.bupt.KWICSystem.input.Input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class InputImpl implements Input {
    private Scanner scanner = new Scanner(System.in);

    @Override
    public String lineInput(){
        return scanner.nextLine();
    }

    @Override
    public List<String> fileInput(String fileName) {
        List<String> contentInput = new ArrayList<>();
        Scanner FileData = null;
        try
        {
            FileData = new Scanner(new FileInputStream(fileName));

            while(FileData.hasNextLine())
            {
                String line = FileData.nextLine();

                contentInput.add(line);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("文件未找到，请输入正确的文件路径名");
        }
        finally
        {
            if (FileData  != null) {
                FileData .close();
            }
        }

        return contentInput;
    }

}
