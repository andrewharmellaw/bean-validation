package com.capgemini.bean.validation;


import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * This parameterized test-class asserts combinations of valid postcodes
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class ValidUkPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    
    final private String datum;
    final private int expectedNumberOfViolations;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public ValidUkPostCodesParameterizedTest(String datum, int expectedNumberOfViolations)
    {
        this.datum = datum;
        this.expectedNumberOfViolations = expectedNumberOfViolations;
    }
    
    /*
     * Test data and expected results generator.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> generateData()
    {
        Object[][] ukPostCodeTests = {
            
                        // Partial Postcodes
                        { "T3", 0 },    // 2 chars, character-digit
                        { "SW8", 0 },   // 3 chars, character-character-digit
                        { "A33", 0 },   // 3 chars, character-digit-digit
                        { "A3A", 0 },   // 3 chars, character-digit-character
                        { "AA3A", 0 },  // 4 chars, character-character-digit-character
                        { "AA33", 0 },  // 4 chars, character-character-digit-digit
                        
                        // Full Postcodes
                        // 5 characters
                        { "T38GB", 0 },
                        // 6 characters
                        { "SW84AF", 0 },
                        { "TW83UA", 0 },
                        { "A339BN", 0 },
                        // 7 characters
                        { "AA3A0BG", 0 },
                        { "TT4T5GH", 0 },
        };

        return Arrays.asList(ukPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void ukPostCodesAreValid() {

        // Given
        UkPostCodeFormatConstraintTest.UkPostCodeHost host = new UkPostCodeFormatConstraintTest.UkPostCodeHost();
        host.ukPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
    }
}
