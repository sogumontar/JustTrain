package com.example.demo;

import com.example.demo.constant.Type;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class InterviewPreparationApplication {

  public static void main(String[] args) {
    SpringApplication.run(InterviewPreparationApplication.class, args);
  }

  public static List<String> TLVProcessor(String input) {
    List<String> actualOutput = new ArrayList<>();
    String replacedString = getReplace(input.replace("\n", "-"), "\r", "");
    replacedString = getReplace(replacedString, Type.REPLCE.name(), "-REPLCE");
    replacedString = getReplace(replacedString, "--REPLCE", "-REPLCE");
    List<String> splittedString = Arrays.asList(replacedString.split("-"));

    for (int i = 0; i < splittedString.size(); i += 3) {
      String result = "";
      if (splittedString.get(i).contains(Type.UPPRCS.name())) {
        result += "UPPRCS-";
        String originalValue = splittedString.get(i + 2);
        String originalUpperCase =
            originalValue.substring(0, Integer.parseInt(splittedString.get(i + 1))).toUpperCase();
        result += originalUpperCase + originalValue.substring(Integer.parseInt(splittedString.get(
            i + 1)));
      } else if (splittedString.get(i).contains(Type.REPLCE.name())) {
        result = "REPLCE-THIS STRING";
      } else {
        result = "Type not valid";
      }
      actualOutput.add(result);
    }
    return actualOutput;
  }

  static String getReplace(String replace, String s, String s2) {
    return replace.replace(s, s2);
  }

}
