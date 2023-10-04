package com.nhnacademy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBaeldung {
    @Test
    public void givenText_whenSimpleRegexMatches_thenCorrect() {
        Pattern pattern = Pattern.compile("foo");
        Matcher matcher = pattern.matcher("foo");

        assertTrue(matcher.find());
    }
}
