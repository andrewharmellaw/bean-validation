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
 * This parameterized test class asserts nillable true values for postcodes
 *
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesNillableTrueTest extends UkPostCodeFormatConstraintTest {
    final private String datum;
    final private int expectedNumberOfViolations;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesNillableTrueTest(String datum, int expectedNumberOfViolations)
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
            { null, 0 },    // null postcodes should pass
            
            { "", 0 },    // empty postcodes should pass
            { new String(""), 0 },

        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void nillablePostCodesWithNullAndEmptyValuesPass() {

        // Given
        UkPostCodeFormatConstraintTest.UkNillablePostCodeHost host = new UkPostCodeFormatConstraintTest.UkNillablePostCodeHost();
        host.nillablePostCode = this.datum;

        // When
        Set<ConstraintViolation<UkNillablePostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
    }
}
