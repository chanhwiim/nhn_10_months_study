package main.java;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class MyLogback {
    private static final Logger logger = LoggerFactory.getLogger(MyLogback.class);

    public static int sum(int a, int b) {
        logger.trace("테스트 용 = {}, {}", a, b);
        logger.info("덧셈 연산 수행 : {} + {}", a, b);
        return a + b;
    }

    public static double divide(double a, double b) {
        if (b == 0) {
            logger.warn("덧셈 연산 실패.. 0으로 나눌 수 없음");
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        } else {
            return a / b;
        }
    }

    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        System.out.println(sum(a, b));
        System.out.println(divide(a, b));
        System.out.println(divide(a, 0));
    }
}
