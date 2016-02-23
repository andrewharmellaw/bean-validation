package com.capgemini.bean.validation;

import org.junit.Test;

import com.capgemini.camel.exception.data.ValidationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BeanValidatorProcessorTest {
    
    private BeanValidatorTestObject testObject;
    
    @Test
    public void testValidValues() throws Exception {
        testObject = new BeanValidatorTestObject("test-field-with-defaults", "valid");
        BeanValidatorProcessor.process(testObject);
    }
    
    @Test
    public void testInvalidIsRequired() throws Exception {
        testObject = new BeanValidatorTestObject(null, null);
        verifyFailureScenario("Field \"testAllAttributesField\" is required");
    }
    
    @Test
    public void testInvalidMinLength() throws Exception {
        testObject = new BeanValidatorTestObject(null, "min");
        verifyFailureScenario("Field \"testAllAttributesField\" is below minimum length");
    }
    
    @Test
    public void testInvalidMaxLength() throws Exception {
        testObject = new BeanValidatorTestObject(null, "maximumtoohigh");
        verifyFailureScenario("Field \"testAllAttributesField\" is above maximum length");
    }
    
    @Test
    public void testInvalidRegex() throws Exception {
        testObject = new BeanValidatorTestObject(null, "ABC123!");
        verifyFailureScenario("Field \"testAllAttributesField\" does not match the regex");
    }
    
    @Test
    public void testInvalidIntegerIsRequired() throws Exception {
        testObject = new BeanValidatorTestObject(null);
        verifyFailureScenario("Field \"testInteger\" is required");
    }
    
    @Test
    public void testInvalidIntegerMinLength() throws Exception {
        testObject = new BeanValidatorTestObject(123);
        verifyFailureScenario("Field \"testInteger\" is below minimum length");
    }
    
    @Test
    public void testInvalidIntegerMaxLength() throws Exception {
        testObject = new BeanValidatorTestObject(1234567890);
        verifyFailureScenario("Field \"testInteger\" is above maximum length");
    }
    
    private void verifyFailureScenario(String errorMessage) throws Exception {
        try {
            BeanValidatorProcessor.process(testObject);
            fail("Expected exception did not occur");
        } catch (ValidationException e) {
            assertEquals(errorMessage, e.getMessage());
        }
    }
}