package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.utils.MyFileReader;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertEquals;

public class CsvConverterTest {

    // TODO: implement JUnit 5 tests for CsvConverter

    private final CsvConverter csvConverter = new CsvConverter();

    private ConvertibleCollectionImpl collection1;
    private ConvertibleCollectionImpl collection2;
    private OutputStream stream1;
    private OutputStream stream2;


    @Before
    public void setUp() {

        /* For test with generated data */

        try {
            stream1 = new FileOutputStream("csvFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        collection1 = new ConvertibleCollectionImpl();
        StringBuilder testBuilder = new StringBuilder();


        var testMap = new LinkedHashMap<String, String>();


        for (int i = 1; i < ThreadLocalRandom.current().nextInt(0, 20); i++) {
            String header = "HeaderSapmle" + i;
            testBuilder.append(header).append(",\n");
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(0, 20); j++) {
                String key = "KeySample" + j;
                String value = "ValueSample" + j;
                testMap.put(key, value);
                testBuilder.append(key + "," + value + "\n");

            }
            collection1.add(new ConvertibleMessageImpl(header, testMap));
        }
        try (FileOutputStream testStream = new FileOutputStream("csvFileTest")) {
            testStream.write(testBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }


        /*
          For test with hardcoded data.
         */
        collection2 = new ConvertibleCollectionImpl();
        try {
            stream2 = new FileOutputStream("csvFileHardcoded");
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
        collection2.add(new ConvertibleMessageImpl("SampleHeader1", testMap1));
        collection2.add(new ConvertibleMessageImpl("SampleHeader2", testMap2));
        collection2.add(new ConvertibleMessageImpl("SampleHeader3", testMap3));

    }


    @Test
    public void testGeneratedData() {
        csvConverter.convert(collection1, stream1);
        assertEquals(MyFileReader.readFile("csvFile"), MyFileReader.readFile("csvFileTest"));
    }

    @Test
    public void testWithHardcodedData() {
        csvConverter.convert(collection2, stream2);
        assertEquals(MyFileReader.readFile("csvFileHardcoded"),MyFileReader.readFile("csvFileHardcodedTest"));
    }
}