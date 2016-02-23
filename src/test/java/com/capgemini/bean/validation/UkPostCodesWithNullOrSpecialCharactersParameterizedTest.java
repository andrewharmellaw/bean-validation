package com.capgemini.bean.validation;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesWithNullOrSpecialCharactersParameterizedTest extends UkPostCodeFormatConstraintTest {
    
    private String datum;
    private int expectedNumberOfViolations;
    private String expectedMessage;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesWithNullOrSpecialCharactersParameterizedTest(String datum, int expectedNumberOfViolations, String expectedMessage)
    {
        this.datum = datum;
        this.expectedNumberOfViolations = expectedNumberOfViolations;
        this.expectedMessage = expectedMessage;
    }
    
    /*
     * Test data and expected results generator.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> generateData()
    {
        Object[][] partialUkPostCodeTests = {
                { "*#%&=", 1, "Invalid UK Postcode." },         // Special Characters
                { "@&!$%", 1, "Invalid UK Postcode." },
                
                { null, 1, "Invalid UK Postcode." },            // Null Object
                
                { "", 1, "Invalid UK Postcode." },            // Empty
                { new String(""), 1, "Invalid UK Postcode." },
        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void postCodesAreNullOrHaveSpecialChars() {

        // Given
        UkPostCodeFormatConstraintTest.UkPostCodeHost host = new UkPostCodeFormatConstraintTest.UkPostCodeHost();
        host.ukPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
        assertThat(constraintViolations.iterator().next().getMessage(), is(equalTo(expectedMessage)));
    }
}
