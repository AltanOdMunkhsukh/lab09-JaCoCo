package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class ScholarshipCalculatorTest {

    private final ScholarshipCalculator calc = new ScholarshipCalculator();

    // ---------- Part A: happy path ----------
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

    // ---------- Part B: letterGrade ----------
    @Test
    void letterGrade_negative_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
    }

    @Test
    void letterGrade_over100_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    void letterGrade_75_isC() {
        assertEquals("C", calc.letterGrade(75));
    }

    @Test
    void letterGrade_65_isD() {
        assertEquals("D", calc.letterGrade(65));
    }

    @Test
    void letterGrade_50_isF() {
        assertEquals("F", calc.letterGrade(50));
    }

    // ---------- Part B: gpa ----------
    @Test
    void gpa_null_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(null));
    }

    @Test
    void gpa_empty_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.gpa(new double[]{}));
    }

    @Test
    void gpa_allGradeTypes_returnsAverage() {
        assertEquals(2.0, calc.gpa(new double[]{95, 85, 75, 65, 50}), 0.001);
    }

    // ---------- Part B: scholarship ----------
    @Test
    void scholarship_negativeGpa_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(-0.1, 10, false));
    }

    @Test
    void scholarship_gpaAbove4_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(4.1, 10, false));
    }

    @Test
    void scholarship_negativeCredits_throws() {
        assertThrows(IllegalArgumentException.class, () -> calc.scholarship(3.5, -1, false));
    }

    @Test
    void scholarship_violation_is0() {
        assertEquals(0, calc.scholarship(3.9, 16, true));
    }

    @Test
    void scholarship_highGpaFewCredits_fallsTo70() {
        assertEquals(70, calc.scholarship(3.9, 14, false));
    }

    @Test
    void scholarship_gpa70CreditsTooFew_is50() {
        assertEquals(50, calc.scholarship(3.5, 11, false));
    }

    @Test
    void scholarship_midGpaManyCredits_is50() {
        assertEquals(50, calc.scholarship(3.2, 20, false));
    }

    @Test
    void scholarship_lowGpa_is0() {
        assertEquals(0, calc.scholarship(2.5, 20, false));
    }

    // ---------- Part C: loop boundary for gpa ----------
    @Test
    void gpa_oneScore_returnsThatGrade() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }

    @Test
    void gpa_manyScores_returnsAverage() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }

    // ---------- Part D: boundary values from the spec ----------
    @ParameterizedTest(name = "letterGrade({0}) = {1}")
    @CsvSource({
        "0, F",
        "59.99, F",
        "60, D",
        "69.99, D",
        "89.99, B",
        "90, A",
        "100, A"
    })
    void letterGrade_boundaries(double score, String expected) {
        assertEquals(expected, calc.letterGrade(score));
    }

    // ---------- Part C: loop boundary for gpa ----------
    @Test
    void gpa_oneScore_returnsThatGrade() {
        assertEquals(4.0, calc.gpa(new double[]{95}), 0.001);
    }

    @Test
    void gpa_manyScores_returnsAverage() {
        assertEquals(2.5, calc.gpa(new double[]{95, 85, 75, 65}), 0.001);
    }
}
