package com.capgemini.bean.validation;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;

/**
 * Parent test class to setup parameterized tests.
 * 
 * @author Abbas Attarwala
 */
public class UkPostCodeFormatConstraintTest {
    
    public static final String CUSTOM_ERROR_MESSAGE = "The custom error message.";
    
    protected static Validator validator;
    
    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    /**
     * A host for a UK Post Code
     */
    protected class UkPostCodeHost {

        @UkPostCodeFormat
        public String ukPostCode;
    }
    
    /**
     * A host for UK Post Codes with custom error messages
     */
    protected class UkCustomErrorMessagePostCodeHost {
                
        @UkPostCodeFormat(message = CUSTOM_ERROR_MESSAGE)
        public String customErrorMessagePostCode;
    }
    
    /**
     * A host for UK Post Codes with nillable true
     */
    protected class UkNillablePostCodeHost {
                
        @UkPostCodeFormat(nillable = true)
        public String nillablePostCode;
    }
    
    /**
     * A host for UK Partial Post Codes
     */
    protected class UkPartialPostCodeHost {
                
        @UkPostCodeFormat(postcodeType = PostcodeType.PARTIAL)
        public String ukPartialPostCode;
    }
    
    /**
     * A host for UK Full Post Codes
     */
    protected class UkFullPostCodeHost {
                
        @UkPostCodeFormat(postcodeType = PostcodeType.FULL)
        public String ukFullPostCode;
    }
}
