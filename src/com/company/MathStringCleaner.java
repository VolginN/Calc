package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MathStringCleaner {
    public String checkString(String in){
        in=in.replaceAll(",",".");
        in=in.replaceAll("\\s+", "");
        Pattern pattern = Pattern.compile("[^.()0-9+*^/-]");
        Matcher m = pattern.matcher(in);
        if (m.find( )){
            throw new IllegalArgumentException("your expression contains letters");
        }

        return in;
    }
}
