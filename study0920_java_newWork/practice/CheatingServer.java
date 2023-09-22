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

public class CheatingServer extends Thread {

    static List<CheatingServer> serverList = new LinkedList<>();
    Socket socket;
    BufferedWriter writer;

    public CheatingServer(Socket socket) {
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

                System.out.println(getName() + ":" + line);
                String[] tokens = line.trim().split(":");

                if (tokens.length == 1) {
                    if (tokens[0].equalsIgnoreCase("who")) {
                        writer.write(getName() + '\n');
                        writer.flush();
                    }
                } else if (tokens.length > 1) {
                    if (tokens[0].equalsIgnoreCase("ID")) {
                        setName(tokens[1]);
                    } else if ((tokens[0].charAt(0) == '@') && (tokens[0].length() > 1)) {
                        String targetID = tokens[0].substring(1, tokens[0].length());
                        if (targetID.equals("@")) {
                            for (CheatingServer server : CheatingServer.serverList) {
                                server.writer("#" + getName() + " : " + tokens[1]);
                            }
                        } else {
                            for (CheatingServer server : serverList) {
                                if (server.getName().equals(targetID)) {
                                    server.writer("#" + getName() + " : " + tokens[1] + '\n');
                                    break;
                                }
                            }
                        }

                        // serverList.stream().filter(server
                        // ->server.getName().equals(targetID)).forEach(server-> server.writer(...));

                    }
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
        // List<CheatingServer> serverList = new LinkedList<>();
        // String[] tokens = "ID:A".split(":");
        // System.out.println(Arrays.toString(tokens));

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (!Thread.currentThread().isInterrupted()) {
                Socket socket = serverSocket.accept();

                CheatingServer Cheating = new CheatingServer(socket);
                // serverList.add(Cheating);
                Cheating.start();

                // socket.close();
            }

        } catch (IOException e) {
            System.out.println(e);
        }

        for (CheatingServer server : serverList) {
            server.interrupt();
            try {
                server.join();
            } catch (InterruptedException ingore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
