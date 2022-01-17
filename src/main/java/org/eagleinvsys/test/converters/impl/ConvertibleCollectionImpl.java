package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleCollection;
import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.*;

public class ConvertibleCollectionImpl extends ArrayList<ConvertibleMessage> implements ConvertibleCollection {


    @Override
    public Collection<String> getHeaders() {
        ArrayList<String> headers = new ArrayList<>();
        for (ConvertibleMessage message: this.getRecords()
             ) {
            headers.add(((ConvertibleMessageImpl) message).getHeader());
        }
        return headers;
    }

    @Override
    public Iterable<ConvertibleMessage> getRecords() {
        List<ConvertibleMessage> list = new ArrayList<>();

        for (ConvertibleMessage message: this
             ) {
            LinkedHashMap<String,String> mapOfValues = new LinkedHashMap<>();
            for (String key: ((ConvertibleMessageImpl) message).keySet()
                 ) {
                mapOfValues.put(key, message.getElement(key));
            }

            list.add(new ConvertibleMessageImpl(((ConvertibleMessageImpl) message).getHeader() ,mapOfValues));
            mapOfValues.clear();
        }

        return list;
    }
}
