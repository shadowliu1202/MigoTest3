package com.shadow.migotest3.domain.model;


import org.junit.Test;
import org.threeten.bp.LocalDateTime;

public class EventTest {
    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testTitleLength() {
        char[] test = new char[55];
        Event.builder()
                .addTitle(String.valueOf(test))
                .order(0)
                .addDescription("")
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .category(Event.Category.Others);
    }

    @Test(expected = StringIndexOutOfBoundsException.class)
    public void testDescriptionTest() {
        char[] test = new char[10001];
        Event.builder()
                .addTitle("test")
                .order(0)
                .addDescription(String.valueOf(test))
                .startDateTime(LocalDateTime.now())
                .endDateTime(LocalDateTime.now())
                .category(Event.Category.Others);
    }
}