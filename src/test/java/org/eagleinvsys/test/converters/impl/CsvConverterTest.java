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

    private ConvertibleCollectionImpl collection;
    private OutputStream stream;


    @Before
    public void setUp() {


        try {
            stream = new FileOutputStream("csvFile");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        collection = new ConvertibleCollectionImpl();
        StringBuilder testBuilder = new StringBuilder();


        var testMap = new LinkedHashMap<String, String>();


        for (int i = 1; i < ThreadLocalRandom.current().nextInt(0,20); i++) {
            String header = "HeaderSapmle"+i;
            testBuilder.append(header).append(",\n");
            for (int j = 0; j < ThreadLocalRandom.current().nextInt(0,20); j++) {
                String key = "KeySample"+j;
                String value = "ValueSample"+j;
                testMap.put(key, value);
                testBuilder.append(key + "," + value + "\n");

            }
            collection.add(new ConvertibleMessageImpl(header,testMap));
        }
        try(FileOutputStream testStream = new FileOutputStream("csvFileTest")) {
            testStream.write(testBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test() {

        csvConverter.convert(collection,stream);

        assertEquals(MyFileReader.readFile("csvFile"),MyFileReader.readFile("csvFileTest"));
        
        


    }
}