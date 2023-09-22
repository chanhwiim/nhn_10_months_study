package practice;

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
    Socket socket;
    static List<BroadcastingServer> serverList = new LinkedList<>();
    BufferedWriter writer;

    public BroadcastingServer(Socket socket) {
        this.socket = socket;
        serverList.add(this);
    }

    public void write(String line) throws IOException {
        writer.write(line);
        writer.flush();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter write = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            this.writer = writer;
            while (!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + "\n";
                for (BroadcastingServer broadcastingServer : serverList) {
                    broadcastingServer.write(line);
                }
                // writer.write(reader.readLine() + '\n');
                // writer.flush();
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

    public static void main(String[] args) {
        int port = 1234;
        // List<BroadcastingServer> serverList = new LinkedList<>();

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                BroadcastingServer broadcastingServer = new BroadcastingServer(socket);
                // serverList.add(broadcastingServer);
                broadcastingServer.start();
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
