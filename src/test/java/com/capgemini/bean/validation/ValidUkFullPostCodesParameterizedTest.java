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
 * This parameterized test-class asserts combinations of valid full postcodes
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class ValidUkFullPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    
    final private String datum;
    final private int expectedNumberOfViolations;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public ValidUkFullPostCodesParameterizedTest(String datum, int expectedNumberOfViolations)
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
        Object[][] fullUkPostCodeTests = {
                        // 5 characters
                        { "T38GB", 0 },
                        { "A39JF", 0 },
                        
                        // 6 characters
                        { "SW84AF", 0 },
                        { "TW83UA", 0 },
                        { "A339BN", 0 },
                        { "T886BG", 0 },
                        { "A3A8HN", 0 },
                        { "S8S1FH", 0 },
                        
                        // 7 characters
                        { "AA3A0BG", 0 },
                        { "TT4T5GH", 0 },
                        { "AA339JH", 0 },
                        { "TT443LF", 0 },
        };

        return Arrays.asList(fullUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void ukFullPostCodesAreValid() {

        // Given
        UkPostCodeFormatConstraintTest.UkFullPostCodeHost host = new UkPostCodeFormatConstraintTest.UkFullPostCodeHost();
        host.ukFullPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkFullPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
    }
}
