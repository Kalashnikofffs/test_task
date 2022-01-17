package org.eagleinvsys.test.converters.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomStringGenerator {

    public static String generate() {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(0, 20); i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}

