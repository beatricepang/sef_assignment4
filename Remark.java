import java.io.FileWriter;
import java.io.IOException;

public class Remark {
    private String remark;
    private String category;
    private int remarkCount = 0;

    // Constructor
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
        // this is for the only categories
        if (!category.equalsIgnoreCase("client") && !category.equalsIgnoreCase("optometrist")) {
            return false;
        }

        // Condition 3: No more than 2 remarks
        if (remarkCount >= 2) {
            return false;
        }

        // If all conditions pass, write the remark to the file
        try (FileWriter writer = new FileWriter("review.txt", true)) {
            writer.write(remark + " (" + category + ")\n");
            remarkCount++;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Create a valid Remark object and test adding a remark
        Remark r = new Remark(
                "Excellent service provided by the optometrist", // Valid remark
                "client" // Valid category
        );

        // Call the addRemark method and print the result
        boolean result = r.addRemark();
        System.out.println("Remark added: " + result); // Should print "Remark added: true"

        // Create a second valid Remark object and test adding another remark
        Remark r2 = new Remark(
                "The client had issues with their glasses.", // Valid remark
                "optometrist" // Valid category
        );

        // Call the addRemark method and print the result for the second remark
        boolean result2 = r2.addRemark();
        System.out.println("Second remark added: " + result2); // Should print "Second remark added: true"
    }
}
