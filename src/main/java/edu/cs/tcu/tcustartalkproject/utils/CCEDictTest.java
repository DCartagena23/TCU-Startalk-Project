package edu.cs.tcu.tcustartalkproject.utils;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class CCEDictTest {
    public static void main(String[] args) throws IOException {
        CCCEDictParser parser = new CCCEDictParser();
        try {
            System.out.println(parser.getDict("simple").get("你好").getMandarinPinyin());

        } catch (Exception e){
            System.out.println("Not in dict");
        }
    }

    @Test
    public void test_Pinyin() throws IOException {
        CCCEDictParser parser = new CCCEDictParser();
        String str1="nǐ hǎo";
        assertEquals(parser.getDict("simple").get("你好").getMandarinPinyin(), str1);
    }
}