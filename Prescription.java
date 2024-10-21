import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Prescription {

    // Fields for Prescription
    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float cylinder;
    private int axis;
    private Date examDate;
    private String optometrist;

    private ArrayList<Remark> postRemarks = new ArrayList<>(); // To store remarks for the prescription

    // Constructor for Prescription
    public Prescription(String firstName, String lastName, String address, float sphere, float cylinder, int axis,
            Date examDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        this.examDate = examDate;
        this.optometrist = optometrist;
    }

    // Method to add prescription
    public boolean addPrescription() {

        // Validation checks
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        if (address.length() < 20) {
            return false;
        }

        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }

        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        try (FileWriter write_to_file = new FileWriter("prescriptionDetails.txt", true)) {
            write_to_file.write(toString() + "\n"); // Write the prescription details to the file
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // toString method for Prescription
    @Override
    public String toString() {
        return firstName + " " + lastName + ", " + address + ", Sphere: " + sphere + ", Cylinder: " + cylinder
                + ", Axis: " + axis + ", Date: " + examDate + ", Optometrist: " + optometrist;
    }

    // Inner class for Remark
    public class Remark {
        private String remark;
        private String category;
        private int remarkCount = 0;

        // Constructor for Remark
        public Remark(String remark, String category) {
            this.remark = remark;
            this.category = category;
        }

        // Method to add a remark
        public boolean addRemark() {
            // Condition 1: Remark word count validation and first letter uppercase
            String[] words = remark.split(" ");
            if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
                return false;
            }

            // Condition 2: Remark category validation
            if (!category.equalsIgnoreCase("client") && !category.equalsIgnoreCase("optometrist")) {
                return false;
            }

            // Condition 3: No more than 2 remarks
            if (remarkCount >= 2) {
                return false;
            }

            // If all conditions pass, write the remark to the file and add to the list
            try (FileWriter writer = new FileWriter("review.txt", true)) {
                writer.write(remark + " (" + category + ")\n");
                remarkCount++;
                postRemarks.add(this); // Add the remark to the prescription's remarks list
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }

            return true;
        }
    }

    // Main method for testing both Prescription and Remark functionalities
    public static void main(String[] args) {
        // Create a valid Prescription object
        Prescription p = new Prescription(
                "John", "Smith", "123 Example St, Melbourne, VIC, 3000", 1.50f, -1.25f, 90, new Date(), "OptoHealth");

        // Call addPrescription and print result
        boolean prescResult = p.addPrescription();
        System.out.println("Prescription added: " + prescResult); // Should print "Prescription added: true"

        // Test adding remarks to the prescription
        Remark r1 = p.new Remark(
                "Excellent service provided by the optometrist", "client");

        boolean remarkResult1 = r1.addRemark();
        System.out.println("Remark 1 added: " + remarkResult1); // Should print "Remark 1 added: true"

        // Add second remark
        Remark r2 = p.new Remark(
                "The client had issues with their glasses.", "optometrist");

        boolean remarkResult2 = r2.addRemark();
        System.out.println("Remark 2 added: " + remarkResult2); // Should print "Remark 2 added: true"
    }
}
