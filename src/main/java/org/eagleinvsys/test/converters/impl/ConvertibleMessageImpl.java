package org.eagleinvsys.test.converters.impl;

import org.eagleinvsys.test.converters.ConvertibleMessage;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConvertibleMessageImpl extends LinkedHashMap<String, String> implements ConvertibleMessage {

    /**
     * Here i decided to use one of the Map<> implementations, because of hint in a StandardConverter.
     * But there is a "identical keys" problem. That can be solved with custom Map<> realization,
     * Appace/Guava Multimap, or two-dimensional array.
     **/
    private final String header;

    public ConvertibleMessageImpl(String header, Map<String, String> mapOfRecords) {
        this.header = header;
        this.putAll(mapOfRecords);
    }


    @Override
    public String getElement(String elementId) {
        return this.get(elementId);
    }

    public String getHeader() {
        return this.header;
    }

}
