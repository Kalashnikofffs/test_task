package org.eagleinvsys.test.converters.impl;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class StandardCsvConverterTest {

    // TODO: implement JUnit 5 tests for StandardCsvConverter

    private final StandardCsvConverter standartCsvConverter = new StandardCsvConverter(new CsvConverter());
    private List<Map<String,String>> testList;
    private OutputStream stream;

    @Before
    public void setUp() {

        try {
            stream = new FileOutputStream("standardCsvFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        testList = new ArrayList<>();
        StringBuilder testBuilder = new StringBuilder();


        var testMap = new LinkedHashMap<String,String>();

        for (int i = 1; i < ThreadLocalRandom.current().nextInt(0,20); i++) {
            String header = "HeaderSapmle"+i;
            testBuilder.append(header).append(",\n");
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(0,20); j++) {
                String key = "KeySample"+j;
                String value = "ValueSample"+j;
                testMap.put(key, value);
                testBuilder.append(key + "," + value + "\n");
            }
            testList.add(new ConvertibleMessageImpl(header,testMap));
        }
        try(FileOutputStream testStream = new FileOutputStream("standardCsvFileTest")) {
            testStream.write(testBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void convert() {

        standartCsvConverter.convert(testList, stream);
        System.out.println(stream.toString());
    }
}