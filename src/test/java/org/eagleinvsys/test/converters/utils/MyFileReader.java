package org.eagleinvsys.test.converters.utils;

import java.io.FileInputStream;
import java.io.IOException;

public class MyFileReader {


    public static String readFile (String filename) {
        StringBuilder builder = new StringBuilder();
        try(FileInputStream fileInputStream = new FileInputStream(filename)){
            int i = -1;
            while ((i=fileInputStream.read())!=-1){
                builder.append((char)i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();

    }
}
