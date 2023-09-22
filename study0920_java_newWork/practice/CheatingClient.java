package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class CheatingClient extends Thread {

    static List<CheatingClient> clientList = new LinkedList<>();
    BufferedWriter writer;
    Socket socket;
    static String host;
    String Id;
    static int port;

    public CheatingClient(Socket socket) {
        this(socket, "localhost", 1234, getRandomValue(1));
        this.socket = socket;
        clientList.add(this);
    }

    public CheatingClient(Socket socket, String host, int port, String Id) {
        CheatingClient.host = host;
        CheatingClient.port = port;
        this.socket = socket;
        this.Id = Id;
        clientList.add(this);
    }

    public static String getRandomValue(int count) {
        StringBuffer stringBuffer = new StringBuffer();
        char str[] = new char[1];

        for (int i = 0; i < count; i++) {
            str[0] = (char) ((Math.random() * 26) + 65);
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    public void writer(String message) throws IOException {
        writer.write(message);
        writer.flush();
    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {
            this.writer = writer;

            writer.write("Id : " + Id);
            writer.flush();

            while (!Thread.currentThread().isInterrupted()) {
                String line = reader.readLine() + '\n';

                if (line.equalsIgnoreCase("!exit")) {
                    Thread.interrupted();
                }

                System.out.println(getName() + ":" + line);
                String[] tokens = line.trim().split(":");

                if ((tokens[0].charAt(0) == '@') && (tokens[0].length() > 1)) {
                    String targetID = tokens[0].substring(1, tokens[0].length());
                    String message = tokens[1];
                    writer.write("@" + targetID + " : " + message);
                } else {
                    writer.write(line);
                }
            }
        } catch (IOException ignore) {
            //
        }
    }

    public static void main(String[] args) {
        List<CheatingClient> clientList = new LinkedList<>();

        try (Socket socket = new Socket(host, port)) {

            CheatingClient Cheating = new CheatingClient(socket);
            clientList.add(Cheating);
            Cheating.start();

        } catch (IOException e) {
            System.out.println(e);
        }

        for (CheatingClient client : clientList) {
            client.interrupt();
            try {
                client.join();
            } catch (InterruptedException ingore) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
