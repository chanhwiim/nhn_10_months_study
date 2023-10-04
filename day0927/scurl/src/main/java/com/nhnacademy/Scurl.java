package com.nhnacademy;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Scurl {
    public static void main(String[] args) {
        Options options = new Options();
        String method = "GET";

        options.addOption(Option.builder("v").desc("verbose, 요청, 응답 헤더를 출력한다").build());
        options.addOption(Option.builder("H").hasArg().argName("line").desc("임의의 헤더를 서버로 전송한다").build());
        options.addOption(Option.builder("d").hasArg().argName("data").desc("POST, PUT 등에 데이터를 전송한다").build());
        options.addOption(
                Option.builder("X").hasArg().argName("command").desc("사용할 method를 지정한다. 지정되지 않은 경우, 기본값은 GET").build());
        options.addOption(Option.builder("L").desc("서버의 응답이 30x 계열이면 다음 응답을 따라 간다.").build());
        options.addOption(Option.builder("F").hasArg().argName("name=content")
                .desc("multipart/form-data를 구성하여 전송한다. content 부분에 @filename을 사용할 수 있다.").build());

        HelpFormatter formatter = new HelpFormatter();
        formatter.setOptionComparator(null);
        formatter.printHelp(Scurl.class.getSimpleName() + " [option] url", options);

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("v")) {
                System.out.println(" v 옵션이 추가되어 있습니다.");
            }
            if (cmd.hasOption("H")) {
                System.out.println(" H 옵션이 추가되어 있습니다.");
            }
            if (cmd.hasOption("d")) {
                System.out.println(" d 옵션이 추가되어 있습니다.");
            }
            if (cmd.hasOption("X")) {
                System.out.println(" X 옵션이 추가되어 있습니다.");
            }
            if (cmd.hasOption("L")) {
                System.out.println(" L 옵션이 추가되어 있습니다.");
            }
            if (cmd.hasOption("F")) {
                System.out.println(" F 옵션이 추가되어 있습니다.");
            }
        } catch (ParseException ignore) {
            //
        }
    }
}
