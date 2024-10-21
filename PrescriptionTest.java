import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

        // Prescription Test Cases
        @Test
        public void testValidPrescription() {
                // Correctly instantiate the Date object
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                assertTrue(p.addPrescription());
        }

        @Test
        public void testInvalidFirstName() {
                Prescription p = new Prescription("Jo", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f,
                                90,
                                new Date(), "OptoHealth");
                assertFalse(p.addPrescription());
        }

        @Test
        public void testInvalidAddress() {
                Prescription p = new Prescription("John", "Smith", "Short Address", 1.50f, -1.25f, 90, new Date(),
                                "OptoHealth");
                assertFalse(p.addPrescription());
        }

        @Test
        public void testInvalidSphere() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 21.00f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                assertFalse(p.addPrescription());
        }

        @Test
        public void testInvalidCylinderRange() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -5.00f, 90,
                                new Date(), "OptoHealth");
                assertFalse(p.addPrescription());
        }

        @Test
        public void testInvalidOptometristName() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "Opto");
                assertFalse(p.addPrescription());
        }

        // Remark Test Cases (adjusted to properly instantiate Prescription)
        @Test
        public void testValidRemark() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r = p.new Remark("This is a great and valid remark slay.", "client");
                assertTrue(r.addRemark());
        }

        @Test
        public void testInvalidWordCount() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r = p.new Remark("Too short", "client");
                assertFalse(r.addRemark());
        }

        @Test
        public void testInvalidCategory() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r = p.new Remark("This is a valid remark.", "invalidCategory");
                assertFalse(r.addRemark());
        }

        @Test
        public void testRemarkExceedingWordLimit() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r = p.new Remark(
                                "This is a very long remark that exceeds the maximum allowed number of words in a valid remark and will most definitely definitely definiltey MY LITTLE PONYY MY LITTLE PONYYY LALALALAAAAAAAAAAAAAAAAAAAAAA.",
                                "client");
                assertFalse(r.addRemark());
        }

        @Test
        public void testRemarkWithoutUppercaseFirstLetter() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r = p.new Remark("this is a valid remark but starts with a lowercase letter.",
                                "client");
                assertFalse(r.addRemark());
        }

        @Test
        public void testForInvalidCategory_MultipleEntries() {
                Prescription p = new Prescription("John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f,
                                -1.25f, 90,
                                new Date(), "OptoHealth");
                Prescription.Remark r1 = p.new Remark("This is the first valid remark.", "client");
                Prescription.Remark r2 = p.new Remark("This is the second valid remark.", "optometrist");
                Prescription.Remark r3 = p.new Remark(
                                "This is the third remark that should faillllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll.",
                                "csnkafnkewjofjqle");

                assertTrue(r1.addRemark()); // First remark should pass
                assertTrue(r2.addRemark()); // Second remark should pass
                assertFalse(r3.addRemark()); // Third remark should fail
        }
}
