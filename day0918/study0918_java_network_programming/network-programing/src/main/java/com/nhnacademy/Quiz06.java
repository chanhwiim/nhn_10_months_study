package com.nhnacademy;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import java.net.Socket;

public class Quiz06 {
    public static void main(String[] args) {
        byte[] buffer = new byte[2048];

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                Socket socket = new Socket("ems.nhnacademy.com", 12345);
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream)) {

            // System.out.println("서버에 연결되었습니다. ");

            String line;
            // byte[] receivedBuffer = new byte[2048];
            while ((line = reader.readLine()) != null) {
                outputStream.write(line.getBytes());
                outputStream.flush();
                int length = inputStream.read(buffer);
                System.out.println(new String(buffer, 0, length));
                // if (line.equals("exit")) {
                // break;
                // }
                // System.out.println("Line : " + line);
                // System.out.println("Read length : " + length);
                // System.out.print("Read : " + new String(buffer, 0, length));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
