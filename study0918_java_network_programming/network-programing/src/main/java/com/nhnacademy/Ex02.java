package com.nhnacademy;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Ex02 {
    public static void main(String[] args) {
        try (Socket socket = new Socket("ems.nhnacademy.com", 12345);) {

            System.out.println("연결되었습니다.");
            socket.getOutputStream().write("hello!!".getBytes());
            socket.getOutputStream().flush();

        } catch (UnknownHostException e) {
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
