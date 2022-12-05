package cn.edu.bupt.KWICSystem.socket.impl;

import cn.edu.bupt.KWICSystem.output.Impl.OutputImpl;
import cn.edu.bupt.KWICSystem.output.Output;
import cn.edu.bupt.KWICSystem.shift.CircularShifter;
import cn.edu.bupt.KWICSystem.shift.impl.CircularShifterImpl;
import cn.edu.bupt.KWICSystem.socket.SocketServer;
import cn.edu.bupt.KWICSystem.sort.Alphabetizer;
import cn.edu.bupt.KWICSystem.sort.impl.AlphabetizerImpl;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.List;

public class SocketServerImpl extends Thread implements SocketServer {
    private ServerSocket serverSocket;

    private CircularShifter circularShifter;
    private Alphabetizer alphabetizer;
    private Output output;

    public SocketServerImpl(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(60000);

        circularShifter = new CircularShifterImpl();
        alphabetizer = new AlphabetizerImpl();
        output = new OutputImpl();
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for being connected...");
                Socket server = serverSocket.accept();
                System.out.println("Connection success!");
                System.out.println("Remote address is: " + server.getRemoteSocketAddress());
                DataInputStream in = new DataInputStream(server.getInputStream());

                String lines = in.readUTF();
                System.out.println(lines);

                // 循环移位
                circularShifter.setup(lines);
                // 排序
                alphabetizer.alpha(circularShifter);
                // 输出
                List<String> res = output.printResult(alphabetizer);

                DataOutputStream out = new DataOutputStream(server.getOutputStream());

                StringBuilder builder = new StringBuilder();
                builder.append("The result is:\n");
                for (String s : res) {
                    builder.append(s + "\n");
                }
                out.writeUTF(builder.toString());
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("The socket time out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
}
