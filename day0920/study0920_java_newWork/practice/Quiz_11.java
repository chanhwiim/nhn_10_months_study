package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Quiz_11 {
    public static void main(String[] args) {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            Socket socket = serverSocket.accept();
            BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketOut = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            Transfer transfer = new Transfer(socketIn, socketOut);
            transfer.start();

            while ((socket = serverSocket.accept()) != null) {
                System.out.println(socket.getInetAddress().getHostAddress() + " : " + socket.getPort());
                socket.getOutputStream().write("Hello!".getBytes());
                socket.getOutputStream().flush();

            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}