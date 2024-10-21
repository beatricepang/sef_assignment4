import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date; // Ensure this import is present

public class PrescriptionTest {

    @Test
    public void testValidPrescription() {
        // Correctly instantiate the Date object
        Prescription p = new Prescription("John", "smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f, 90,
                new Date(), "OptoHealth");
        assertTrue(p.addPrescription());
    }

    @Test
    public void testInvalidFirstName() {
        Prescription p = new Prescription("Jo", "smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f, 90,
                new Date(), "OptoHealth");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testInvalidAddress() {
        Prescription p = new Prescription("John", "smith", "Short Address", 1.50f, -1.25f, 90, new Date(),
                "OptoHealth");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testInvalidSphere() {
        Prescription p = new Prescription("John", "smith", "123 Example St, Melbourne, VIC, 3000", 21.00f, -1.25f, 90,
                new Date(), "OptoHealth");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testInvalidCylinderRange() {
        Prescription p = new Prescription("John", "smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -5.00f, 90,
                new Date(), "OptoHealth");
        assertFalse(p.addPrescription());
    }

    @Test
    public void testInvalidOptometristName() {
        Prescription p = new Prescription("John", "smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f, 90,
                new Date(), "Opto");
        assertFalse(p.addPrescription());
    }
}
