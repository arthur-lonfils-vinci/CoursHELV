import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TodoListTest {

    private TodoList todoList;
    private Task validTask;

    @BeforeEach
    void setUp() {
        todoList = new TodoList();
        validTask = new Task("title", "description");
    }

    @Test
    void addTask() {
        assertAll(
                () -> assertTrue(todoList.addTask(validTask)),
                () -> assertTrue(todoList.containsTask(validTask))
        );
    }

    @Test
    void addEmptyTask() {
        assertAll(
                () -> assertFalse(todoList.addTask(null)),
                () -> assertFalse(todoList.containsTask(null))
        );
    }

    @Test
    void addExistingTask() {
        todoList.addTask(validTask);
        assertFalse(todoList.addTask(validTask));
    }

    @Test
    void removeTask() {
        todoList.addTask(validTask);
        assertTrue(todoList.removeTask(validTask));
    }

    @Test
    void removeNonExistingTask() {
        assertFalse(todoList.removeTask(validTask));
    }

    @Test
    void addTaskWithSameTitle() {
        Task task1 = new Task("title", "description1");
        Task task2 = new Task("title", "description2");
        todoList.addTask(task1);
        assertTrue(todoList.addTask(task2));
    }

    @Test
    void endTask() {
        todoList.addTask(validTask);
        assertTrue(todoList.endTask(validTask));
        assertTrue(validTask.isDone());
    }

    @Test
    void endNonExistingTask() {
        assertFalse(todoList.endTask(validTask));
    }

    @Test
    void updateTitle() {
        todoList.addTask(validTask);
        assertTrue(todoList.updateTitle(validTask, "new title"));
        validTask.setTitle("new title");
        assertTrue(todoList.containsTask(validTask));
    }

    @Test
    void updateTitleWithExistingTask() {
        Task task1 = new Task("title1", "description1");
        Task task2 = new Task("title2", "description1");
        todoList.addTask(task1);
        todoList.addTask(task2);
        assertFalse(todoList.updateTitle(task1, "title2"));
    }

    @Test
    void updateDescription() {
        todoList.addTask(validTask);
        assertTrue(todoList.updateDescription(validTask, "new description"));
        validTask.setDescription("new description");
        assertTrue(todoList.containsTask(validTask));
    }

    @Test
    void updateDescriptionWithExistingTask() {
        Task task1 = new Task("title1", "description1");
        Task task2 = new Task("title1", "description2");
        todoList.addTask(task1);
        todoList.addTask(task2);
        assertFalse(todoList.updateDescription(task1, "description2"));
    }

    @Test
    void updateDescriptionWithNullDescription() {
        todoList.addTask(validTask);
        assertThrows(IllegalArgumentException.class, () -> todoList.updateDescription(validTask, null));
    }

    @Test
    void getTasks() {
        todoList.addTask(validTask);
        assertEquals(validTask,todoList.getTask(validTask));
    }

    @Test
    void getNonExistingTask() {
        assertNull(todoList.getTask(validTask));
    }

    @Test
    void replaceTask() {
        Task task1 = new Task("title1", "description1");
        Task task2 = new Task("title2", "description2");
        todoList.addTask(task1);
        assertTrue(todoList.replaceTask(task1, task2));
        assertFalse(todoList.containsTask(task1));
        assertTrue(todoList.containsTask(task2));
    }

}
