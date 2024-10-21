import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RemarkTest {

    @Test
    public void testValidRemark() {
        Remark r = new Remark("This is a great and valid remark slay.", "client");
        assertTrue(r.addRemark());
    }

    @Test
    public void testInvalidWordCount() {
        Remark r = new Remark("Too short", "client");
        assertFalse(r.addRemark());
    }

    @Test
    public void testInvalidCategory() {
        Remark r = new Remark("This is a valid remark.", "invalidCategory");
        assertFalse(r.addRemark());
    }

    @Test
    public void testRemarkExceedingWordLimit() {
        Remark r = new Remark(
                "This is a very long remark that exceeds the maximum allowed number of words in a valid remark and will most definitely definitely definiltey MY LITTLE PONYY MY LITTLE PONYYY LALALALAAAAAAAAAAAAAAAAAAAAAA.",
                "client");
        assertFalse(r.addRemark());
    }

    @Test
    public void testRemarkWithoutUppercaseFirstLetter() {
        Remark r = new Remark("this is a valid remark but starts with a lowercase letter.", "client");
        assertFalse(r.addRemark());
    }

    @Test
    public void testForInvalidCategory_MultipleEntries() {
        Remark r1 = new Remark("This is the first valid remark.", "client");
        Remark r2 = new Remark("This is the second valid remark.", "optometrist");
        Remark r3 = new Remark(
                "This is the third remark that should faillllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll.",
                "csnkafnkewjofjqle");

        assertTrue(r1.addRemark()); // First remark should pass
        assertTrue(r2.addRemark()); // Second remark should pass
        assertFalse(r3.addRemark()); // Third remark should fail
    }
}
