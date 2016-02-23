package com.capgemini.bean.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * javax.validation.ConstraintValidator implementation to check validity of UK PostCodes.
 *
 * @author Abbas Attarwala
 */
public class UkPostCodeFormatValidator implements ConstraintValidator<UkPostCodeFormat, String> {

    private static final String SPACE = "\\s";
    private static final String NOTHING = "";

    private boolean nillable;
    private PostcodeType postcodeType;
    
    // A regex to match UK postcodes
    private static final Pattern ukPostCodePattern = 
        Pattern.compile("^[A-Z]([0-9]|[0-9][0-9]|[0-9][A-Z]|[A-Z][0-9]|[A-Z][0-9][0-9A-Z])[0-9][A-BD-HJLNP-UW-Z][A-BD-HJLNP-UW-Z]$");
    
    private static final Pattern whiteListedPostCodePattern = Pattern.compile("^GIR0AA$");
    
    // A regex to match partial UK postcodes
    private static final Pattern ukPartialPostCodePattern = 
        Pattern.compile("^[A-Z]([0-9]|[0-9][0-9]|[0-9][A-Z]|[A-Z][0-9]|[A-Z][0-9][0-9A-Z])$");
    
    // A regex to match partial UK postcodes for Channel Islands
    private static final Pattern channelIslandsPartialPostCodePattern = Pattern.compile("^(IM|JE|GY)$");
    

    @Override
    public void initialize(UkPostCodeFormat format) {
        nillable = format.nillable();
        postcodeType = format.postcodeType();
    }

    @Override
    public boolean isValid(String postCode, ConstraintValidatorContext cvc) {
        
        if (postCode == null || postCode.isEmpty()) return nillable;
        
        switch(postcodeType) {
            case FULL_OR_PARTIAL:   return isPostcodeValid(postCode);
            case FULL:              return isFullPostcodeValid(postCode);
            case PARTIAL:           return isPartialPostcodeValid(postCode);
            default:                return false;
        }
    }
    
    private boolean isPostcodeValid(String postCode) {
        
        String postCodeWithNoSpaces = stripSpaces(postCode);
        
        if (postCodeWithNoSpaces.length() > 4) {
            return isFullPostcodeValid(postCode);
        } else {
            return isPartialPostcodeValid(postCode);
        }
    }
    
    private boolean isFullPostcodeValid(String postCode) {
        
        String postCodeWithNoSpaces = stripSpaces(postCode);
        
        return  whiteListedPostCodePattern.matcher(postCodeWithNoSpaces).find() || 
                ukPostCodePattern.matcher(postCodeWithNoSpaces).find();
    }
    
    private boolean isPartialPostcodeValid(String postCode) {
        return  channelIslandsPartialPostCodePattern.matcher(postCode).find() ||
                ukPartialPostCodePattern.matcher(postCode).find();
    }

    private String stripSpaces(String stringWithSpaces) {
        return stringWithSpaces.replaceAll(SPACE, NOTHING);
    }
}
