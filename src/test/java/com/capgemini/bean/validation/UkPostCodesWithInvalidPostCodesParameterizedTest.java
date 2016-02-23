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
 * This parameterized test-class asserts combinations of invalid postcodes.
 * 
 * @author Abbas Attarwala
 */
@RunWith(Parameterized.class)
public class UkPostCodesWithInvalidPostCodesParameterizedTest extends UkPostCodeFormatConstraintTest {
    final private String datum;
    final private int expectedNumberOfViolations;
    final private String expectedMessage;
    
    /*
     * JUnit will instantiate one instance of this class for every element in
     * the @Parameters collection.
     */
    public UkPostCodesWithInvalidPostCodesParameterizedTest(String datum, int expectedNumberOfViolations, String expectedMessage)
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
        Object[][] ukPostCodeTests = {
                
                // Partial Post Codes
                // 2 characters
                { "AA", 1, "Invalid UK Postcode." },    // character-character
                { "22", 1, "Invalid UK Postcode." },    // digit-digit
                { new Integer(44).toString(), 1, "Invalid UK Postcode." },
                { "2A", 1, "Invalid UK Postcode." },    // digit-character
                
                // 3 characters
                { "AAA", 1, "Invalid UK Postcode." },    // character-character-character
                { "222", 1, "Invalid UK Postcode." },   // digit-digit-digit
                { new Integer(444).toString(), 1, "Invalid UK Postcode." },
                { "4F9", 1, "Invalid UK Postcode." },   //digit-character-digit
                { "69A", 1, "Invalid UK Postcode." },   // digit-digit-character
                
                // 4 characters
                { "AtFg", 1, "Invalid UK Postcode." },   // character-character-character-character
                { "2946", 1, "Invalid UK Postcode." },  // digit-digit-digit-digit
                { new Integer(0245).toString(), 1, "Invalid UK Postcode." },
                { "f83B", 1, "Invalid UK Postcode." },   // character-digit-digit-character
                { "A5vD", 1, "Invalid UK Postcode." },   // character-digit-character-character
                { "C9f5", 1, "Invalid UK Postcode." },  // character-digit-character-digit
                { "9c5T", 1, "Invalid UK Postcode." },  // digit-character-digit-character
                { "7j55", 1, "Invalid UK Postcode." },  // digit-character-digit-digit
                { "03la", 1, "Invalid UK Postcode." },  // digit-digit-character-character
                { "79hf", 1, "Invalid UK Postcode." },  // digit-digit-character-character
                { "83a5", 1, "Invalid UK Postcode." },  // digit-digit-character-digit
            
                // Full Post Codes
                // First part invalid, second part valid.
                // 5 characters
                { "AA1AA", 1, "Invalid UK Postcode." },
                { "tt2tt", 1, "Invalid UK Postcode." },
                
                // 6 characters
                { "AAA1AA", 1, "Invalid UK Postcode." },
                { "ttt2ss", 1, "Invalid UK Postcode." },

                // 7 characters
                { "AtFg1Zz", 1, "Invalid UK Postcode." },
                { "gBuD2Sd", 1, "Invalid UK Postcode." },
                { "C9FH7JK", 1, "Invalid UK Postcode." },
                { "C9f58Jk", 1, "Invalid UK Postcode." },
                { "03la8cx", 1, "Invalid UK Postcode." },
                { "92FV6VF", 1, "Invalid UK Postcode." },
                { "79hf4vd", 1, "Invalid UK Postcode." },
                
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

        return Arrays.asList(ukPostCodeTests);
    }
    
    /*
     * The test - run once for every element in the collection returned by the data generator.
     */
    @Test
    public void ukPostCodesAreInvalid() {

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
