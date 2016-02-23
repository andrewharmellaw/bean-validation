package com.capgemini.bean.validation;

public class BeanValidatorTestObject {

    @BeanValidator
    private String testDefaultsField;
    
    @BeanValidator(required = true, minLength = 5, maxLength = 10, regex = "[a-z]*")
    private String testAllAttributesField;
    
    @BeanValidator(required = true, minLength = 5, maxLength = 8)
    private Integer testInteger;

    public BeanValidatorTestObject(String testDefaultsField, String testAllAttributesField) {
        this.testDefaultsField = testDefaultsField;
        this.testAllAttributesField = testAllAttributesField;
        testInteger = 12345;
    }
    
    public BeanValidatorTestObject(Integer testInteger) {
        this.testInteger = testInteger;
        testAllAttributesField = "valid";
    }

    public String getTestDefaultsField() {
        return testDefaultsField;
    }

    public String getTestAllAttributesField() {
        return testAllAttributesField;
    }
    
    public Integer getTestInteger() {
        return testInteger;
    }
}