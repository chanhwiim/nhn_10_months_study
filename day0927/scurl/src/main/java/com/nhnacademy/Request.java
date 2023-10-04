package com.nhnacademy;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Request extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Request() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>form</title></head>");
        out.println("<body>");

        Enumeration<String> headerNames = request.getHeaderNames(); // 모든 헤더 이름을 문자열로 반환
        while (headerNames.hasMoreElements()) { // 반환받은 헤더 이름들을 돌면서
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName); // 헤더 이름에 해당하는 값들을 저장
            out.println(headerName + " : " + headerValue + " <br> "); // 브라우저에 출력
        }

        out.println("</body>");
        out.println("</html>");
    }

}
