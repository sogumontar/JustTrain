package com.example.demo;

import com.example.demo.service.TLVService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sogumontar Hendra Simangunsong on 8/24/2021.
 */

@SpringBootTest
public class TLVServiceTest {
  private String inputStream;
  private List<String> expectedOutput = new ArrayList<>();

  @Autowired
  private TLVService tlvService;

  @BeforeEach
  void setUp() {
    inputStream = "UPPRCS-0005-abcde\n" + "REPLCE-0003-123\n"
        + "UPPRCS-0007-AbcdefghREPLCE-0003-123REPLCE-0001-Z\n" + "TAG001-0012-abcdefgh1234\n"
        + "UPPRCS-0004-1234";

    expectedOutput.add("UPPRCS-ABCDE");
    expectedOutput.add("REPLCE-THIS STRING");
    expectedOutput.add("UPPRCS-ABCDEFGh");
    expectedOutput.add("REPLCE-THIS STRING");
    expectedOutput.add("REPLCE-THIS STRING");
    expectedOutput.add("Type not valid");
    expectedOutput.add("UPPRCS-1234");
  }


  @Test
  void mergedContextLoads() {
    Assert.assertEquals(expectedOutput, tlvService.TLVProcessor(inputStream));
  }

}
