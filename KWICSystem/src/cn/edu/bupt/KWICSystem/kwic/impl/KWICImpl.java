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

    // private final String OPERATION_MODE_CHOOSE = "a、命令行输入\nb、文件输入\nc、Socket输入\nd、退出\n请输入序号以选择运行模式：";
    // private final String CMD_INPUT_INDICATION = "Please enter: ";
    // private final String FILE_INPUT_INDICATION = "Please enter the file path like \"C:\\Desktop\\input.txt\"：";

    // private boolean isCmdMode = true;

    // private final String CMD_INPUT = "CMD_INPUT";
    // private final String FILE_INPUT = "FILE_INPUT";
    // private final String SOCKET_INPUT = "SOCKET_INPUT";
    // private final String QUIT = "QUIT";

    private void initialize() {
        input = new InputImpl();
        circularShifter = new CircularShifterImpl();
        alphabetizer = new AlphabetizerImpl();
        output = new OutputImpl();
    }

    // private String analysisOperation(String operation) {
    //     String operationMode = null;
    //     if("a".equals(operation)) {
    //         operationMode = CMD_INPUT;
    //     } else if ("b".equals(operation)) {
    //         operationMode = FILE_INPUT;
    //     }  else if ("c".equals(operation)) {
    //         operationMode = SOCKET_INPUT;
    //     } else if ("d".equals(operation)) {
    //         operationMode = QUIT;
    //     }

    //     return operationMode;
    // }

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

        // while (!"q".equals(lines)) {
        //     if (isCmdMode) {
        //         System.out.print(OPERATION_MODE_CHOOSE);
        //     } else {
        //         if (operationMode == CMD_INPUT) {
        //             System.out.print(CMD_INPUT_INDICATION);
        //         } else if (operationMode == FILE_INPUT) {
        //             System.out.print(FILE_INPUT_INDICATION);
        //         }
        //     }
        //     lines = input.lineInput();

        //     if (isCmdMode) {
        //         operationMode = analysisOperation(lines);
        //         switch (operationMode) {
        //             case CMD_INPUT:
        //                 isCmdMode = false;
        //                 break;
        //             case FILE_INPUT:
        //                 isCmdMode = false;
        //                 break;
        //             case SOCKET_INPUT:
        //                 try {
        //                     Thread scThread = new SocketServerImpl(8001);
        //                     scThread.run();
        //                 } catch(IOException e) {
        //                     e.printStackTrace();
        //                 }
        //                 break;
        //             case QUIT:
        //                 System.out.println("Quit Success!");
        //                 System.exit(0);
        //                 break;

        //             default:
        //                 System.out.println("Please choose correct mode('a', 'b', 'c' or 'd')!");
        //                 break;
        //         }
        //     } else {
        //         // 循环移位
        //         if (operationMode == CMD_INPUT) {
        //             circularShifter.setup(lines);
        //         } else if (operationMode == FILE_INPUT) {
        //             List<String> fileLines = input.fileInput(lines);
        //             circularShifter.setup(fileLines);
        //         }
        //         if (circularShifter.getLineCount() == 0) {
        //             System.out.println("The content is empty!");
        //         } else {
        //             // 排序
        //             alphabetizer.alpha(circularShifter);

        //             // 输出
        //             if (operationMode == CMD_INPUT) {
        //                 output.printResult(alphabetizer);
        //             } else if (operationMode == FILE_INPUT) {
        //                 output.writeFile(alphabetizer);
        //             }
        //         }
        //         isCmdMode = true;
        //     }
        // }
    }
}
