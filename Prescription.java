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

    private int remarkCount = 0;
    private ArrayList<String> postRemarks = new ArrayList<>(); // To store remarks for the prescription

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

    // Method to add a prescription
    public boolean addPrescription() {

        // Condition 1: First and last names must be between 4 and 15 characters
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }
        // Condition 2: Address must be at least 20 characters long
        if (address.length() < 20) {
            return false;
        }
        // Condition 3: Sphere (-20.00 to +20.00), Cylinder (-4.00 to +4.00), Axis (0 to
        // 180)
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }
        // Condition 4: Optometrist name must be between 8 and 25 characters
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all conditions are met, write the prescription details to a file
        try (FileWriter writer = new FileWriter("prescriptionDetails.txt", true)) {
            writer.write(toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Method to add a remark
    public boolean addRemark(String remark, String category) {
        // Condition 1: Remark word count must be between 6 and 20, first letter must be
        // uppercase
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Condition 2: Category must be "client" or "optometrist"
        if (!category.equalsIgnoreCase("client") && !category.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Condition 3: No more than 2 remarks per prescription
        if (remarkCount >= 2) {
            return false;
        }

        // If all conditions are met, write the remark to a file and add to the list
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write(remark + " (" + category + ")\n");
            remarkCount++;
            postRemarks.add(remark + " (" + category + ")"); // Add the remark to the list
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
}
