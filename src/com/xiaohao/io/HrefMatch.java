package com.xiaohao.io;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HrefMatch {

  public static void main(String[] args) {
    String url;
    if (args.length > 0) {
      url = args[0];
    } else {
      url = "http://www.mi.com";
    }

    StringBuilder content = new StringBuilder();
    try (InputStreamReader input = new InputStreamReader(new URL(url).openStream())) {
      int c;
      while ((c = input.read()) != -1) {
        content.append((char) c);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(content);

    String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\")[^>]*>";
    Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(content);

    int count = 0;
    while (matcher.find()) {
      int start = matcher.start();
      int end = matcher.end();
      String matchUrl = content.substring(start, end);
      System.out.println(matchUrl);
      count++;
    }
    System.out.println("Found " + count + " hrperlinks!");
  }

}