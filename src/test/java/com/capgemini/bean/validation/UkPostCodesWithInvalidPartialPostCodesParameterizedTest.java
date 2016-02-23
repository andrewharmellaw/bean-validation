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
 * This parameterized test-class asserts combinations of invalid partial postcodes.
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesWithInvalidPartialPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    private String datum;
    private int expectedNumberOfViolations;
    private String expectedMessage;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesWithInvalidPartialPostCodesParameterizedTest(String datum, int expectedNumberOfViolations, String expectedMessage)
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
                // 2 characters
                { "AA", 1, "Invalid UK Postcode." },    // character-character
                { "tt", 1, "Invalid UK Postcode." },
                { "22", 1, "Invalid UK Postcode." },    // digit-digit
                { new Integer(44).toString(), 1, "Invalid UK Postcode." },
                { "2A", 1, "Invalid UK Postcode." },    // digit-character
                { "3t", 1, "Invalid UK Postcode." },
                
                // 3 characters
                { "AAA", 1, "Invalid UK Postcode." },    // character-character-character
                { "ttt", 1, "Invalid UK Postcode." },
                { "222", 1, "Invalid UK Postcode." },   // digit-digit-digit
                { new Integer(444).toString(), 1, "Invalid UK Postcode." },
                { "4F9", 1, "Invalid UK Postcode." },   //digit-character-digit
                { "2t6", 1, "Invalid UK Postcode." },
                { "69A", 1, "Invalid UK Postcode." },   // digit-digit-character
                { "58t", 1, "Invalid UK Postcode." },
                
                // 4 characters
                { "AtFg", 1, "Invalid UK Postcode." },   // character-character-character-character
                { "gBuD", 1, "Invalid UK Postcode." },
                { "2946", 1, "Invalid UK Postcode." },  // digit-digit-digit-digit
                { new Integer(0245).toString(), 1, "Invalid UK Postcode." },
                { "f83B", 1, "Invalid UK Postcode." },   // character-digit-digit-character
                { "V93C", 1, "Invalid UK Postcode." },
                { "A5vD", 1, "Invalid UK Postcode." },   // character-digit-character-character
                { "C9FH", 1, "Invalid UK Postcode." },
                { "C9f5", 1, "Invalid UK Postcode." },  // character-digit-character-digit
                { "T3B0", 1, "Invalid UK Postcode." },
                { "9c5T", 1, "Invalid UK Postcode." },  // digit-character-digit-character
                { "6B5E", 1, "Invalid UK Postcode." },
                { "7j55", 1, "Invalid UK Postcode." },  // digit-character-digit-digit
                { "2S29", 1, "Invalid UK Postcode." },
                { "03la", 1, "Invalid UK Postcode." },  // digit-digit-character-character
                { "92FV", 1, "Invalid UK Postcode." },
                { "79hf", 1, "Invalid UK Postcode." },  // digit-digit-character-character
                { "03BK", 1, "Invalid UK Postcode." },
                { "83a5", 1, "Invalid UK Postcode." },  // digit-digit-character-digit
                { "93A5", 1, "Invalid UK Postcode." },

        };

        return Arrays.asList(partialUkPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void partialPostCodesAreInvalid() {

        // Given
        UkPostCodeFormatConstraintTest.UkPartialPostCodeHost host = new UkPostCodeFormatConstraintTest.UkPartialPostCodeHost();
        host.ukPartialPostCode = this.datum;

        // When
        Set<ConstraintViolation<UkPartialPostCodeHost>> constraintViolations = validator.validate(host);

        // Then
        assertThat(constraintViolations.size(), is(equalTo(expectedNumberOfViolations)));
        assertThat(constraintViolations.iterator().next().getMessage(), is(equalTo(expectedMessage)));
    }
}
