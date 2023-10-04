package com.nhnacademy;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class HTTPRequest {
    SimpleCurl simpleCurl;
    String url;
    URLConnection conn;
    HttpServletRequest request;
    Map<String, String> headers;
    String method;
    int port;

    public HTTPRequest(String method, int port) throws MalformedURLException {
        simpleCurl = new SimpleCurl();
        this.method = method;
        this.port = port;
        headers = new HashMap<>();
    }

    // public String getRequest() {
    // return (url = simpleCurl.getHTTPRequest());
    // }

    public void addHeader(String key, int value) {

    }

    // public void setHost(String host, int port) {
    // simpleCurl.aURL.getHost();
    // }

    public void setHeader() {
        request.getHeader(url);
    }

    public void setURL() {
        request.getRequestURI();
    }

    // public String toString() {
    // StringBuilder builder = new StringBuilder();

    // builder.append(String.format("%s, %s, %s", method, url, version));
    // return headers.forEach(null);
    // }
}
