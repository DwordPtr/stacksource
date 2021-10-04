package com.dwordptr.zipapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ZipServiceTest {
    private ZipService zipService = new ZipService();

    @Test
    public void getZipCodeString() {
        zipService.insert("37044");
        zipService.insert("37045");
        zipService.insert("37046");
        zipService.insert("37049");
        assertEquals("37044-37046, 37049", zipService.getZipCodeString());
    }

    @Test
    public void testInsertDelete(){
        assertFalse(zipService.inList( "12345"));
        assertTrue(zipService.insert("12345"));
        assertTrue(zipService.inList("12345"));
        assertTrue(zipService.delete("12345"));
        assertFalse(zipService.inList("12345"));
    }
    @Test
    public void testIsValid(){
        assertTrue(zipService.isValidZip("12345"));
        assertTrue(zipService.isValidZip("37055"));
        assertFalse(zipService.isValidZip("bob"));
        assertFalse(zipService.isValidZip("-5"));
        assertFalse(zipService.isValidZip("-37055"));
        assertFalse(zipService.isValidZip("5"));
    }
}