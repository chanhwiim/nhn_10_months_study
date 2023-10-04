package com.nhnacademy;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

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
    String url = "http://example.com:80/docs/books/tutorial/index.html?name=networking#DOWNLOADING";
    String host = "localhost";
    String version = "HTTP/1.1";
    int port = 80;

    public SimpleCurl() {
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
        formatter.printHelp(Scurl.class.getSimpleName() + " [option] url", options);
    }

    // http://im.com:8080/
    public void run() {
        StringBuilder builder = new StringBuilder();

        builder.append(String.format("%s %s %s", method, url, version));
        String sBuilder = builder.toString();

        try (Socket socket = new Socket(host, port)) {
            socket.getOutputStream().write(sBuilder.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOption(String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine = parser.parse(options, args);

        if (commandLine.hasOption("h")) {
            showHelp();
            System.exit(0);
        }

        if (commandLine.hasOption("X")) {
            method = commandLine.getOptionValue("X");

            if (!((method.equalsIgnoreCase("GET")) || !(method.equalsIgnoreCase("POST"))
                    || !(method.equalsIgnoreCase("PUT")))) {
                throw new InvaliedMethodException("Method 설정이 잘못되었습니다.");
            }
        }
        // if (commandLine.getArgs().length == 0) {
        // throw new InvaliedUrlException();
        // }

        // String[] splitUrl = url.split("://");
        // if (splitUrl.length != 2) {
        // throw new InvaliedUrlException();
        // }

        // // im.com:8080/post -> ["im.com:8080" , "post"]
        // String[] field = splitUrl[1].split("/");

        // if (field[0].contains(":")) {
        // host = field[0].split(":")[0];
        // port = Integer.parseInt(field[0].split(":")[0]);
        // }
    }

    public void split() {
        try {
            URL aURL = new URL(url);
            System.out.println("protocol = " + aURL.getProtocol());
            System.out.println("authority = " + aURL.getAuthority());
            System.out.println("host = " + aURL.getHost());
            System.out.println("port = " + aURL.getPort());
            System.out.println("path = " + aURL.getPath());
            System.out.println("query = " + aURL.getQuery());
            System.out.println("filename = " + aURL.getFile());
            System.out.println("ref = " + aURL.getRef());
        } catch (MalformedURLException ignore) {
            //
        }
    }

    public static void main(String[] args) {

        SimpleCurl scurl = new SimpleCurl();
        try {
            scurl.setOption(args);
            System.out.println("URL: " + scurl.url);
            scurl.split();
        } catch (ParseException ignore) {
            scurl.showHelp();
        } catch (InvaliedMethodException e) {
            System.err.println(e);
        } catch (InvaliedUrlException e) {
            System.out.println("Url이 잘못되었습니다. ");
        }
    }
}
