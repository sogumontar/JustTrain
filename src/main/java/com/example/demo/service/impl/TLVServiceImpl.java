package com.example.demo.service.impl;

import com.example.demo.constant.Type;
import com.example.demo.service.TLVService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sogumontar Hendra Simangunsong on 8/24/2021.
 */
@Service
public class TLVServiceImpl  implements TLVService {
  @Override
   public List<String> TLVProcessor(String input) {
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
