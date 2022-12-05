package cn.edu.bupt.KWICSocktClient;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String serverName = "127.0.0.1";
        int port = 8001;
        try
        {
            System.out.println("Request to connect to " + serverName + ":" + port);
            Socket client = new Socket(serverName, port);
            System.out.println("Connection success!");
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            System.out.print("Please enter: \n");

            Scanner scanner = new Scanner(System.in);
            out.writeUTF(scanner.nextLine());

            InputStream inFromServer = client.getInputStream();
            DataInputStream in = new DataInputStream(inFromServer);
            System.out.println("Server's response is:\n" + in.readUTF());
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
