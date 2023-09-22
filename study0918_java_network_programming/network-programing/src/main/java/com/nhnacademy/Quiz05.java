package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;

import java.io.InputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.util.Scanner;

public class Quiz05 {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];

        try (Socket socket = new Socket("ems.nhnacademy.com", 12345);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            System.out.println("서버에 연결되었습니다. ");

            int length;
            while ((line = reader.length = bufferedInputStream.read(buffer)) >= 0) {
                String line = new String(buffer, 0, length).trim();
                if (line.equals("exit")) {
                    break;
                }
                System.out.println("Line : " + line);
                System.out.println("Read length : " + length);
                System.out.print("Read : " + new String(buffer, 0, length));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
