package cn.edu.bupt.KWICSystem.kwic.impl;

import cn.edu.bupt.KWICSystem.input.Input;
import cn.edu.bupt.KWICSystem.input.impl.InputImpl;
import cn.edu.bupt.KWICSystem.kwic.KWIC;
import cn.edu.bupt.KWICSystem.output.Impl.OutputImpl;
import cn.edu.bupt.KWICSystem.output.Output;
import cn.edu.bupt.KWICSystem.shift.CircularShifter;
import cn.edu.bupt.KWICSystem.shift.impl.CircularShifterImpl;
import cn.edu.bupt.KWICSystem.socket.impl.SocketServerImpl;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;
import cn.edu.bupt.KWICSystem.sort.impl.AlphabetizerImpl;

import java.io.IOException;
import java.util.List;
import java.util.Arrays;

public class KWICImpl extends Thread implements KWIC {

    private Input input;
    private CircularShifter circularShifter;
    private Alphabetizer alphabetizer;
    private Output output;


    private void initialize() {
        input = new InputImpl();
        circularShifter = new CircularShifterImpl();
        alphabetizer = new AlphabetizerImpl();
        output = new OutputImpl();
    }

    @Override
    public void execute(String[] args) {
        initialize();

        System.out.println("Welcome to the KWIC System!");

        if (Arrays.stream(args).anyMatch(s -> s.startsWith("socket"))){ // socket输入
            System.out.println("Socket Input Mode...");
            try {
                Thread socketThread = new SocketServerImpl(8001);
                socketThread.run();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        else{
            if (args.length == 2){ // 文件输入
                System.out.println("File Input Mode...");
                List<String> inputLines = input.fileInput(args[0]);
                circularShifter.setup(inputLines);
            }
            else{ // 命令行输入
                System.out.println("Command Input Mode...");
                String inputLine = input.lineInput();
                circularShifter.setup(inputLine);
            }
            // 排序
            alphabetizer.alpha(circularShifter);

            // 输出
            if (args.length == 2) {
                output.writeFile(alphabetizer, args[1]);
            } else {
                output.printResult(alphabetizer);
            }
        }
    }
}
