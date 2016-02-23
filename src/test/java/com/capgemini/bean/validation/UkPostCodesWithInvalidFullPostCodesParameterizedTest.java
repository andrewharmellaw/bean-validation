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
 * This parameterized test-class asserts combinations of invalid full postcodes.
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesWithInvalidFullPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    final private String datum;
    final private int expectedNumberOfViolations;
    final private String expectedMessage;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesWithInvalidFullPostCodesParameterizedTest(String datum, int expectedNumberOfViolations, String expectedMessage)
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
        Object[][] fullUkPostCodeTests = {
                
                // First part invalid, second part valid.
                // 5 characters
                { "AA1AA", 1, "Invalid UK Postcode." },
                { "tt2tt", 1, "Invalid UK Postcode." },
                { "223FF", 1, "Invalid UK Postcode." },
                { "2A4GG", 1, "Invalid UK Postcode." },
                { "3t5hh", 1, "Invalid UK Postcode." },
                
                // 6 characters
                { "AAA1AA", 1, "Invalid UK Postcode." },
                { "ttt2ss", 1, "Invalid UK Postcode." },
                { "2223DD", 1, "Invalid UK Postcode." },
                { "4F94FF", 1, "Invalid UK Postcode." },
                { "2t65dc", 1, "Invalid UK Postcode." },
                { "69A6GB", 1, "Invalid UK Postcode." },
                { "58t7AD", 1, "Invalid UK Postcode." },
                
                // 7 characters
                { "AtFg1Zz", 1, "Invalid UK Postcode." },
                { "gBuD2Sd", 1, "Invalid UK Postcode." },
                { "29463FF", 1, "Invalid UK Postcode." },
                { "f83B4fV", 1, "Invalid UK Postcode." },
                { "V93C5HG", 1, "Invalid UK Postcode." },
                { "A5vD6Dc", 1, "Invalid UK Postcode." },
                { "C9FH7JK", 1, "Invalid UK Postcode." },
                
                
                // First part valid, second part invalid
                // 5 characters
                { "T3123", 1, "Invalid UK Postcode." },
                { "A312S", 1, "Invalid UK Postcode." },
                { "T34F5", 1, "Invalid UK Postcode." },
                { "A3V56", 1, "Invalid UK Postcode." },
                { "T3G4C", 1, "Invalid UK Postcode." },
                { "A3JX3", 1, "Invalid UK Postcode." },
                { "T3OCL", 1, "Invalid UK Postcode." },
                
                // 6 characters
                { "SW8895", 1, "Invalid UK Postcode." },
                { "TW838F", 1, "Invalid UK Postcode." },
                { "A339V4", 1, "Invalid UK Postcode." },
                { "T88J20", 1, "Invalid UK Postcode." },
                { "A3AJ3X", 1, "Invalid UK Postcode." },
                { "S8SKV0", 1, "Invalid UK Postcode." },
                { "SW8ALC", 1, "Invalid UK Postcode." },
                
                // 7 characters
                { "AA3A108", 1, "Invalid UK Postcode." },
                { "TT4T48J", 1, "Invalid UK Postcode." },
                { "AA334J7", 1, "Invalid UK Postcode." },
                { "TT44C87", 1, "Invalid UK Postcode." },
                { "AA3AK3X", 1, "Invalid UK Postcode." },
                { "TT4TCF7", 1, "Invalid UK Postcode." },
                { "AA33KCF", 1, "Invalid UK Postcode." },
                
                // Postcodes ending with C, I, K, M, O, V
                { "AA3A1AC", 1, "Invalid UK Postcode." },
                { "TT4T2BI", 1, "Invalid UK Postcode." },
                { "AA333CK", 1, "Invalid UK Postcode." },
                { "TT444DM", 1, "Invalid UK Postcode." },
                { "AA3A5EO", 1, "Invalid UK Postcode." },
                { "TT4T6FV", 1, "Invalid UK Postcode." },
        };

        return Arrays.asList(fullUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void fullPostCodesAreInvalid() {

        // Given
        UkPostCodeFormatConstraintTest.UkFullPostCodeHost host = new UkPostCodeFormatConstraintTest.UkFullPostCodeHost();
        host.ukFullPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkFullPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
        assertThat(constraintViolations.iterator().next().getMessage(), is(equalTo(expectedMessage)));
    }
}
