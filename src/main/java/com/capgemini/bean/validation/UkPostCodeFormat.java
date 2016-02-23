package com.capgemini.bean.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * JSR-303 Custom Constraint to validate UK PostCodes.
 *
 * @author Abbas Attarwala
 */
@Target( { TYPE, METHOD, FIELD, PARAMETER, ANNOTATION_TYPE, CONSTRUCTOR, LOCAL_VARIABLE } )
@Retention(RUNTIME)
@Constraint(validatedBy = UkPostCodeFormatValidator.class)
@Documented
public @interface UkPostCodeFormat {
    
    // If this is set to true, an empty postcode is considered to be valid.
    boolean nillable() default false;
    
    // If this is set explicitly to FULL or PARTIAL, the relevant validation rules will apply.
    PostcodeType postcodeType() default PostcodeType.FULL_OR_PARTIAL;
    
    // The error message to be displayed in case of an invalid postcode.
    String message() default "Invalid UK Postcode.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
