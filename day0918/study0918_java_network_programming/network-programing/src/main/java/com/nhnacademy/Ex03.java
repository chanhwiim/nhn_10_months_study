package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex03 {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            System.out.println("연결되었습니다.");

            int ch;

            while ((ch = socket.getInputStream().read()) >= 0) {
                System.out.write(ch);
            }

            socket.close();
        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
