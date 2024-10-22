import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

        // Prescription Test Cases
        // use of assert true and assert false
        // use of assert true to test for all valid inputs
        // use of assert false to test for non valid inputs
        @Test
        public void testValidPrescription() {

                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                assertTrue(p.addPrescription());
        }

        // test for an invalid first name
        @Test
        public void testInvalidFirstName() {
                Prescription p = new Prescription("Jo", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f,
                                90,
                                new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // test for an invalid last name
        @Test
        public void testInvalidLastName() {
                Prescription p = new Prescription("John", "Smi", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f,
                                90,
                                new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // test for an invalid address
        @Test
        public void testInvalidAddress() {
                Prescription p = new Prescription("John", "Smith", "Short Address", 1.50f, -1.25f, 90, new Date(),
                                "EyeClear");
                assertFalse(p.addPrescription());
        }

        // test for invalid sphere range input

        @Test
        public void testInvalidSphere() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 21.00f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                assertFalse(p.addPrescription());
        }

        // test for invalid optometrist name

        @Test
        public void testInvalidOptometristName() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "Eyec"); // incase of a typo
                assertFalse(p.addPrescription());
        }

        // Remark Test Cases

        // test for valid remark
        @Test
        public void testValidRemark() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r = p.new Remark("This is a great and valid remark slay.", "client");
                assertTrue(r.addRemark());
        }

        // test for invalid word count
        @Test
        public void testInvalidWordCount() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r = p.new Remark("Too short", "client");
                assertFalse(r.addRemark());
        }

        // test for invalid category eg: not client or optometrist
        @Test
        public void testInvalidCategory() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r = p.new Remark("This is a valid remark.", "invalidCategory");
                assertFalse(r.addRemark());
        }

        // test for exceeding word limit
        @Test
        public void testRemarkExceedingWordLimit() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r = p.new Remark(
                                "This remark exceeds the allowed word count for a valid entry and contains unnecessary repetitions and off-topic content, which may not meet the professionalism required for documentation.",
                                "client");
                assertFalse(r.addRemark());
        }

        // test for no first upper case
        @Test
        public void testRemarkWithoutUppercaseFirstLetter() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r = p.new Remark("this is a valid remark but starts with a lowercase letter.",
                                "client");
                assertFalse(r.addRemark());
        }

        // test for invalid amount of multiple entries
        @Test
        public void testForMultipleEntries() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "EyeClear");
                Prescription.Remark r1 = p.new Remark("This is the first valid remark.", "client");
                Prescription.Remark r2 = p.new Remark("This is the second valid remark.", "optometrist");
                Prescription.Remark r3 = p.new Remark(
                                "Crafting this sentence carefully ensures that every word and letter counts towards the required length without going over or under, matching it exactly, with proper formatting and structure for precision.",
                                "optometrist");

                assertTrue(r1.addRemark()); // First remark should pass
                assertTrue(r2.addRemark()); // Second remark should pass
                assertFalse(r3.addRemark()); // Third remark should fail
        }
}
