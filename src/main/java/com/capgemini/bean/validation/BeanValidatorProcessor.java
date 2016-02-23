package com.capgemini.bean.validation;

import com.capgemini.camel.exception.data.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

/**
 * Processes occurrences of the {@link BeanValidator} annotation to validate the fields that it annotates
 * 
 * @author Jon Hatfield
 */
public class BeanValidatorProcessor {

    /**
     * Validates all fields of a bean that are annotated with @BeanValidator
     * 
     * @param obj
     * @throws Exception
     */
    public static void process(Object obj) throws Exception {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BeanValidator.class)) {
                BeanValidator anno = field.getAnnotation(BeanValidator.class);
                String getterName = "get" + StringUtils.capitalize(field.getName());
                PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), obj.getClass(), getterName, null);
                Object fieldValue = descriptor.getReadMethod().invoke(obj);
                validateIsRequired(field, fieldValue, anno.required());
                validateMinLength(field, fieldValue, anno.minLength());
                validateMaxLength(field, fieldValue, anno.maxLength());
                validateRegex(field, fieldValue, anno.regex());
            }
        }
    }
    
    private static void validateIsRequired(Field field, Object fieldValue, boolean isRequired)
            throws ValidationException {
        if(fieldValue == null && isRequired) {
            throw new ValidationException("Field \"" + field.getName() + "\" is required");
        }
    }
    
    private static void validateMinLength(Field field, Object fieldValue, int minLength) throws ValidationException {
        if(fieldValue != null) {
            boolean invalid = fieldValue instanceof String && ((String) fieldValue).length() < minLength;
            if(fieldValue instanceof Integer) {
                invalid = String.valueOf((Integer)fieldValue).length() < minLength;
            }
            if(invalid) {
                throw new ValidationException("Field \"" + field.getName() + "\" is below minimum length");
            }
        }
    }
    
    private static void validateMaxLength(Field field, Object fieldValue, int maxLength) throws ValidationException {
        if(fieldValue != null) {
            boolean invalid = fieldValue instanceof String && ((String) fieldValue).length() > maxLength;
            if(fieldValue instanceof Integer) {
                invalid = String.valueOf((Integer)fieldValue).length() > maxLength;
            }
            if(invalid) {
                throw new ValidationException("Field \"" + field.getName() + "\" is above maximum length");
            }
        }
    }
    
    private static void validateRegex(Field field, Object fieldValue, String regex) throws ValidationException {
        if(fieldValue != null && fieldValue instanceof String && !((String) fieldValue).matches(regex)) {
            throw new ValidationException("Field \"" + field.getName() + "\" does not match the regex");
        }
    }
}