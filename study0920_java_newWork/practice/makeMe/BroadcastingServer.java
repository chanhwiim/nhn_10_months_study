package practice.makeMe;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class BroadcastingServer extends Thread {

    static List<BroadcastingServer> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    public BroadcastingServer(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    public void writer(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.writer = writer;

            while (!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + '\n';

                if (!line.contains("@")) {

                    for (BroadcastingServer server : serverList) {
                        server.writer(line);
                        // server.writer.write(line);
                        // server.writer.flush();
                    }
                } else {

                }

            }
        } catch (IOException ignore) {
            //
        }

        try {
            socket.close();
        } catch (IOException ignore) {
            //
        }
    }

    // public String toString() {
    // return "Client Id : " + ClientId;
    // }

    public static void main(String[] args) {
        int port = 1234;
        List<BroadcastingServer> serverList = new LinkedList<>();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                BroadcastingServer BroadcastingServer = new BroadcastingServer(socket);
                serverList.add(BroadcastingServer);
                BroadcastingServer.start();
            }

        } catch (IOException e) {
            //
        }

        for (BroadcastingServer server : serverList) {
            server.interrupt();
            try {
                server.join();
            } catch (InterruptedException ingore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
