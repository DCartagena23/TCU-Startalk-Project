package edu.cs.tcu.tcustartalkproject.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CCEDictTest {
    public static void main(String[] args) throws IOException {
        CCCEDictParser parser = new CCCEDictParser();
        try {
            System.out.println(parser.getDict("traditional").get("龜苓膏").getMandarinPinyin());
            System.out.println(parser.getDict("simple").get("龟苓膏").getMandarinPinyin());

        } catch (Exception e){
            System.out.println("Not in dict");
        }
    }
}
