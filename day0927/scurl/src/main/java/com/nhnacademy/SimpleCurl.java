package com.nhnacademy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class SimpleCurl {
    Options options;
    String method = "GET";
    String url = "";
    String version = "HTTP/1.1";
    int port = 80;
    String host = "";
    String path = "";
    String header = "";
    boolean headerCheck = false;
    Map<String, String> headers = new HashMap<>();
    StringBuilder builder = new StringBuilder();

    public SimpleCurl() throws MalformedURLException {
        options = new Options();
        options.addOption(Option.builder("v").desc("verbose, 요청, 응답 헤더를 출력한다").build());
        options.addOption(Option.builder("H").hasArg().argName("line").desc("임의의 헤더를 서버로 전송한다").build());
        options.addOption(Option.builder("d").hasArg().argName("data").desc("POST, PUT 등에 데이터를 전송한다").build());
        options.addOption(
                Option.builder("X").hasArg().argName("command").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption(Option.builder("L").desc("서버의 응답이 30x 계열이면 다음 응답을 따라 간다.").build());
        options.addOption(Option.builder("F").hasArg().argName("name=content")
                .desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());
        options.addOption("?", false, "사용법을 출력한다.");
    }

    public void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp(SimpleCurl.class.getSimpleName() + " [option] url", options);
    }

    public void build() {
        builder.append(String.format("%s %s %s \n", method, path, version));
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            builder.append(String.format("%s: %s\n", entry.getKey(), entry.getValue()));
        }
        builder.append(String.format("Host: %s \n", host));
        builder.append("\r\n");
    }

    // http://im.com:8080/
    public void run() throws IOException {

        try (Socket socket = new Socket(host, port)) {
            build();
            socket.getOutputStream().write(builder.toString().getBytes());

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // 여기서부터
            int contentLength = 0;
            if (headers.containsKey("Content-Length")) {
                contentLength = Integer.parseInt(headers.get("Content-Length"));
            }

            if (contentLength > 0) {
                char[] buffer = new char[contentLength];
                int startbyte = 0;

                while (contentLength > startbyte) {
                    int chars = reader.read(buffer, startbyte, contentLength);
                    if (chars == -1) {
                        break;
                    }
                    startbyte += chars; //
                }

                String responseCL = new String(buffer);
                System.out.println("responseCL: ");
                System.out.println(responseCL);

            }
            // 여기까지. Content-Length의 길이만큼 읽어오는거. 맞는것 같은데, 바로 안끊긴다.

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOption(CommandLine commandLine) {
        String[] args = commandLine.getArgs();

        if (commandLine.hasOption("h")) {
            showHelp();
            System.exit(0);
        }

        if (commandLine.hasOption("H")) {// > == 요청 , < == 답변.
            String[] headerArray = commandLine.getOptionValues("H");

            if (headerArray != null) {
                for (String header : headerArray) {
                    String[] headerNum = header.split(":");
                    if (headerNum.length == 2) {
                        headers.put(headerNum[0], headerNum[1]);
                    }
                }

            }
            headerCheck = true;
        }
        if (commandLine.hasOption("v")) {
            header = "verbose";
            url = args[0];
        }

        if (commandLine.hasOption("d")) {
            String chooseMethod = commandLine.getOptionValue("d");
            method = chooseMethod;
        }

        if (commandLine.hasOption("L")) {
            method = commandLine.getOptionValue("L");

            // if ((method = ("HTTP/1.1 301") || ("HTTP/1.1 302") || ("HTTP/1.1 307") ||
            // ("HTTP/1.1 308"))) {
            // next;
            // }
        }

        if (commandLine.hasOption("F")) {
            method = commandLine.getOptionValue("F");
        }

        if (commandLine.hasOption("X")) {
            method = commandLine.getOptionValue("X");

            // if (!((method.equalsIgnoreCase("GET")) || (method.equalsIgnoreCase("POST"))
            // || (method.equalsIgnoreCase("PUT"))))
            if (!method.equalsIgnoreCase("GET") && !method.equalsIgnoreCase("POST")
                    && !method.equalsIgnoreCase("PUT")) {
                throw new InvaliedMethodException("Method 설정이 잘못되었습니다.");
            }
            url = args[0];
        }
    }

    public void setURL(String url) {
        String[] field = url.split("://");
        if (field.length != 2) {
            throw new InvaliedUrlException();
        }

        if (!field[0].equalsIgnoreCase("http")) {
            throw new UnsupportedCaseException();
        }

        int index = field[1].indexOf("/", 0);
        if (index == 0) {
            throw new InvaliedMethodException();
        } else {
            if (index > 0) {
                path = field[1].substring(index);
            }

            host = field[1].substring(0, index);
            if (host.split(":").length == 2) {
                try {
                    port = Integer.parseInt(host.split(":")[1]);
                } catch (Exception e) {
                    throw new InvaliedUrlException();
                }
                host = host.split(":")[0];
            } else if (host.split(":").length > 2) {
                throw new InvaliedUrlException();
            }
        }
        System.out.println(host + " " + port + " " + path + "\n");
    }

    public static void main(String[] args) throws IOException, ServletException {

        SimpleCurl scurl = new SimpleCurl();
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;

        try {
            commandLine = parser.parse(scurl.options, args);
            if (commandLine.hasOption("h")) {
                throw new InvaliedMethodException();
            }
            scurl.setOption(commandLine);
            scurl.setURL(scurl.url);
            scurl.run();
            System.out.println("URL: " + scurl.url);

        } catch (ParseException | InvaliedMethodException ignore) {
            scurl.showHelp();
        } catch (InvaliedUrlException e) {
            System.out.println("Url이 잘못되었습니다. ");
        }
    }
}

// URL responseServer = new URL(scurl.url); // response
// URLConnection conn = responseServer.openConnection();
// for (Map.Entry<String, List<String>> entry : map.entrySet()) {
// System.out.println("Key : " + entry.getKey() +
// " ,Value : " + entry.getValue());
// }

// String contentLengthHeader = conn.getHeaderField("Content-Length");
// if (contentLengthHeader != null) {
// int contentLength = Integer.parseInt(contentLengthHeader);
// InputStream inputStream = conn.getInputStream();
// byte[] buffer = new byte[contentLength];
// int byteLength = inputStream.read(buffer);

// if (byteLength == contentLength) {
// String response = new String(buffer, "UTF-8");
// System.out.println(response);
// }
// inputStream.close();
// }

// String server = conn.getHeaderField("Server");