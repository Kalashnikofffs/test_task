package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.Converter;
import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CsvConverter implements Converter {

    /**
     * Converts given {@link ConvertibleCollection} to CSV and outputs result as a text to the provided {@link OutputStream}
     *
     * @param collectionToConvert collection to convert to CSV format
     * @param outputStream        output stream to write CSV conversion result as text to
     */
    @Override
    public void convert(ConvertibleCollection collectionToConvert, OutputStream outputStream) {
        // TODO: implement

        StringBuilder stringBuilder = new StringBuilder();

        for (String header : collectionToConvert.getHeaders()
        ) {
            stringBuilder.append(header).append(",\n");

            for (ConvertibleMessage m : collectionToConvert.getRecords()
            ) {
                for (String key : ((ConvertibleMessageImpl) m).keySet()
                ) {
                    if (((ConvertibleMessageImpl) m).getHeader().equals(header)) {
                        stringBuilder.append(key + "," + m.getElement(key) + "\n");
                    }
                }
            }
        }

        try (outputStream) {
            outputStream.write(stringBuilder.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}