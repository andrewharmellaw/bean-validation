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
 * This parameterized test-class asserts custom error messages for PostCodes.
 *
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesCustomErrorMessagesTest extends UkPostCodeFormatConstraintTest {
    final private String datum;
    final private int expectedNumberOfViolations;
    final private String expectedMessage;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesCustomErrorMessagesTest(String datum, int expectedNumberOfViolations, String expectedMessage)
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
            { "An Invalid PostCode with a Custom Error Message.", 1, CUSTOM_ERROR_MESSAGE },    // custom error message

        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void postCodesGiveCustomErrorMessages() {

        // Given
        UkPostCodeFormatConstraintTest.UkCustomErrorMessagePostCodeHost host = new UkPostCodeFormatConstraintTest.UkCustomErrorMessagePostCodeHost();
        host.customErrorMessagePostCode = this.datum;

        // When
        Set<ConstraintViolation<UkCustomErrorMessagePostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
        assertThat(constraintViolations.iterator().next().getMessage(), is(equalTo(expectedMessage)));
    }
}
