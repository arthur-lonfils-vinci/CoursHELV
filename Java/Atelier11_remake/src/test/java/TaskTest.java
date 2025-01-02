import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TaskTest {

    @Test
    void validTask() {
        assertAll(
                () -> assertDoesNotThrow(() -> new Task("title", "description")),
                () -> assertDoesNotThrow(() -> new Task("title", ""))
        );
    }

    @Test
    void nullTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Task(null, "description"));
    }

    @Test
    void blankTitle() {
        assertThrows(IllegalArgumentException.class, () -> new Task("", "description"));
    }

    @Test
    void nullDescription() {
        assertThrows(IllegalArgumentException.class, () -> new Task("title", null));
    }
}
