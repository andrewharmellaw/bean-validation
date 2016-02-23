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
 * A parameterized test-class that asserts combinations of channel islands partial postcodes.
 *
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class ValidUkPartialPostCodesWithChannelIslandsParameterizedTest extends UkPostCodeFormatConstraintTest {
    
    final private String datum;
    final private int expectedNumberOfViolations;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public ValidUkPartialPostCodesWithChannelIslandsParameterizedTest(String datum, int expectedNumberOfViolations)
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
                        { "IM", 0 },    // Isle of Man
                        { "JE", 0 },    // Jersey
                        { "GY", 0 },    // Guernsey
        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void ukPartialPostCodesWithChannelIslandsAreValid() {

        // Given
        UkPartialPostCodeHost host = new UkPartialPostCodeHost();
        host.ukPartialPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkPartialPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
    }
}
