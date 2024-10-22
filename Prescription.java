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
        // condition 1 : checks for the first and last name being more than 4, and less
        // than 15
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }
        // condition 2: the address cant be less than 20
        if (address.length() < 20) {
            return false;
        }
        // condition 3: the sphere must not exceed +-20.00
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }
        // condition 4: the optometrist length is between 8 and 25
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // if all conditions are met
        // create an instance of the filewriter
        // to write to prescriptionDetails.txt when append is true
        // using the tostring method below to write to the presciption details
        // the try and cathch is utalised for error handling
        try (FileWriter write_to_file = new FileWriter("prescriptionDetails.txt", true)) {
            write_to_file.write(toString() + "\n"); // Write the prescription details to the file
        } catch (IOException exception) {
            exception.printStackTrace();
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

    // remark function

    public class Remark {
        private String remark;
        private String category;
        private int remarkCount = 0;

        // Constructor for Remark
        public Remark(String remark, String category) {
            this.remark = remark;
            this.category = category;
        }

        public boolean addRemark() {
            // Condition 1: Remark word count validation and first letter uppercase
            // the split function will split the array holding the string by its whtiespaces
            // segregating it into seperate words so we can check the first and last name
            String[] words = remark.split(" ");
            if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
                return false;
            }

            // Condition 2: Remark category validation
            // in the event that the category is not client or optometrist, it will return
            // false
            if (!category.equalsIgnoreCase("client") && !category.equalsIgnoreCase("optometrist")) {
                return false;
            }

            // Condition 3: No more than 2 remarks
            if (remarkCount >= 2) {
                return false;
            }

            // If the conditions pass write to the file and append it.
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

}
