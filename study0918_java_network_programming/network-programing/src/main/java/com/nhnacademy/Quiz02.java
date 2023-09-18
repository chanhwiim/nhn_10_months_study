package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz02 {
    public static void main(String[] args) {
        int minPortNum = 0;
        int maxPortNum = 65535;

        // minPortNum = Integer.parseInt(args[0]);
        // maxPortNum = Integer.parseInt(args[1]);

        for (int port = minPortNum; port < maxPortNum; port++) {
            try (Socket socket = new Socket("localhost", port)) {

                System.out.println(port + "가 열려 있습니다. ");

            } catch (IOException ignore) {
            }

        }

    }
}
