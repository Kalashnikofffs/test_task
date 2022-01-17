package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.utils.MyFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class StandardCsvConverterTest {

    // TODO: implement JUnit 5 tests for StandardCsvConverter

    private final StandardCsvConverter standartCsvConverter = new StandardCsvConverter(new CsvConverter());
    private List<Map<String,String>> testList;
    private OutputStream stream1;
    private OutputStream stream2;
    private ArrayList<Map<String,String>> hardcodedList;

    @Before
    public void setUp() {

        /* For test with generated data */

        try {
            stream1 = new FileOutputStream("standardCsvFile");
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

        /* For test with hardcoded data*/


        hardcodedList = new ArrayList<>();

        try {
            stream2 = new FileOutputStream("StandardCsvFileHardcoded");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var testMap1 = new LinkedHashMap<String,String>();
        testMap1.put("1","one");
        testMap1.put("2","two");
        testMap1.put("3","three");
        testMap1.put("4","four");
        testMap1.put("5","five");
        var testMap2 = new LinkedHashMap<String,String>();
        testMap2.put("6","six");
        testMap2.put("7","seven");
        testMap2.put("8","eight");
        testMap2.put("9","nine");
        testMap2.put("10","ten");
        testMap2.put("11","eleven");
        testMap2.put("12","twelve");
        var testMap3 = new LinkedHashMap<String,String>();
        testMap3.put("13","thirteen");
        testMap3.put("14","fourteen");
        testMap3.put("15","fifteen");
        testMap3.put("16","sixrteen");
        testMap3.put("17","sevenrteen");
        testMap3.put("18","eighteen");
        hardcodedList.add(testMap1);
        hardcodedList.add(testMap2);
        hardcodedList.add(testMap3);
    }

    @Test
    public void testGeneratedData() {

        standartCsvConverter.convert(testList, stream1);
        assertEquals(MyFileReader.readFile("StandardCsvFile"), MyFileReader.readFile("StandardCsvFileTest"));

    }

    @Test
    public void testHardcodedData() {

        standartCsvConverter.convert(hardcodedList, stream2);

        assertEquals(MyFileReader.readFile("StandardCsvFileHardcoded"), MyFileReader.readFile("StandardCsvFileHardcodedTest"));



    }
}
