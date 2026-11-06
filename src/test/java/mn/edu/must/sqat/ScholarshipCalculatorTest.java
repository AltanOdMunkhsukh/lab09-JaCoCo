package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    @Test
    void letterGrade_95_isA() {
        assertEquals("A", calc.letterGrade(95));
    }

    @Test
    void letterGrade_85_isB() {
        assertEquals("B", calc.letterGrade(85));
    }

    @Test
    void gpa_twoScores_returnsAverage() {
        assertEquals(3.5, calc.gpa(new double[]{95, 85}), 0.001);
    }

    @Test
    void scholarship_highGpaEnoughCredits_is100() {
        assertEquals(100, calc.scholarship(3.9, 16, false));
    }

    @Test
    void scholarship_goodGpaEnoughCredits_is70() {
        assertEquals(70, calc.scholarship(3.5, 13, false));
    }
}
