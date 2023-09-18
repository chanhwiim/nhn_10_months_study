package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Quiz03 {
    public static void main(String[] args) {
        // for (int port = 1; port < 65536; port++) {

        try (Socket socket = new Socket("ems.nhnacademy.com", 12345);) {

            System.out.println("서버에 연결되었습니다.");
            System.out.println("Local address : " + socket.getLocalAddress());
            System.out.println("Local port : " + socket.getLocalPort());
            System.out.println("Remote address : " + socket.getInetAddress().getHostAddress());
            System.out.println("Remote port : " + socket.getPort());
            // System.out.println("Remote port : " + socket.getRemoteSocketAddress());

        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // }
    }
}
