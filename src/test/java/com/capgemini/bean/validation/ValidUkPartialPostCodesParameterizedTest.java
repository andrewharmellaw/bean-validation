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
 * This parameterized test-class asserts combinations of valid partial postcodes
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class ValidUkPartialPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    
    final private String datum;
    final private int expectedNumberOfViolations;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public ValidUkPartialPostCodesParameterizedTest(String datum, int expectedNumberOfViolations)
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
        Object[][] partialUkPostCodeTests = {
                        { "T3", 0 },    // 2 chars, character-digit
                        { "A3", 0 },
                        { "SW8", 0 },   // 3 chars, character-character-digit
                        { "TW8", 0 },
                        { "A33", 0 },   // 3 chars, character-digit-digit
                        { "T88", 0 },
                        { "A3A", 0 },   // 3 chars, character-digit-character
                        { "S8S", 0 },
                        { "AA3A", 0 },  // 4 chars, character-character-digit-character
                        { "TT4T", 0 },
                        { "AA33", 0 },  // 4 chars, character-character-digit-digit
                        { "TT44", 0 },
        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void ukPartialPostCodesAreValid() {

        // Given
        UkPartialPostCodeHost host = new UkPartialPostCodeHost();
        host.ukPartialPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkPartialPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
    }
}
