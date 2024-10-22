import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

        // Prescription Test Cases

        // Test for all valid inputs (should pass)
        @Test
        public void testValidPrescription() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertTrue(p.addPrescription());
        }

        // Test for an invalid first name (too short)
        @Test
        public void testInvalidFirstName() {
                Prescription p = new Prescription("Jo", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // Test for an invalid last name (too short)
        @Test
        public void testInvalidLastName() {
                Prescription p = new Prescription("John", "Smi", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // Test for an invalid address (too short)
        @Test
        public void testInvalidAddress() {
                Prescription p = new Prescription("John", "Smith", "Short Address", 1.50f, -1.25f, 90, new Date(),
                                "EyeClear");
                assertFalse(p.addPrescription());
        }

        // Test for an invalid sphere (out of range)
        @Test
        public void testInvalidSphere() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 21.00f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // Test for an invalid optometrist name (too short)
        @Test
        public void testInvalidOptometristName() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "Eyec");
                assertFalse(p.addPrescription());
        }

        // Remark Test Cases

        // Test for a valid remark (should pass)
        @Test
        public void testValidRemark() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertTrue(p.addRemark("This is a great and valid remark.", "client"));
        }

        // Test for an invalid remark (word count too short)
        @Test
        public void testInvalidWordCount() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addRemark("Too short", "client"));
        }

        // Test for an invalid category (not "client" or "optometrist")
        @Test
        public void testInvalidCategory() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addRemark("This is a valid remark.", "invalidCategory"));
        }

        @Test
        public void testRemarkExceedingWordLimit() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");

                String longRemark = "This remark exceeds the allowed word count for a valid entry. "
                                + "It contains unnecessary repetitions and excessive length to ensure "
                                + "that it violates the 20-word limit enforced for this field.";

                assertFalse(p.addRemark(longRemark, "client"));
        }

        // Test for a remark without uppercase first letter (should fail)
        @Test
        public void testRemarkWithoutUppercaseFirstLetter() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");
                assertFalse(p.addRemark("this is a valid remark but starts with lowercase.", "client"));
        }

        // Test for exceeding the maximum number of remarks (more than 2)
        @Test
        public void testForMultipleEntries() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90, new Date(), "EyeClear");

                assertTrue(p.addRemark("This is the first valid remark.", "client")); // First remark should pass
                assertTrue(p.addRemark("This is the second valid remark.", "optometrist"));// Second remark should pass
                assertFalse(p.addRemark(
                                "Crafting this sentence ensures that every word counts toward the required length.",
                                "optometrist")); // Third remark should fail (limit exceeded)
        }
}
