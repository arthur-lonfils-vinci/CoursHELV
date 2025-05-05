import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private List<Task> tasks = new ArrayList<>();

    public boolean addTask(Task task) {
        if (task == null) return false;
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                return false;
            }
        }
        return tasks.add(task);
    }

    public boolean containsTask(Task task) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                return true;
            }
        }
        return false;
    }

    public boolean removeTask(Task task) {
        return tasks.remove(task);
    }

    public boolean endTask(Task task) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                t.setDone(true);
                return true;
            }
        }
        return false;
    }

    public boolean updateTitle(Task task, String title) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                for (Task t2 : tasks) {
                    if (t2.getTitle().equals(title)) {
                        return false;
                    }
                }
                t.setTitle(title);
                return true;
            }
        }
        return false;
    }

    public boolean updateDescription(Task task, String description) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                for (Task t2 : tasks) {
                    if (t2.getDescription().equals(description)) {
                        return false;
                    }
                }
                t.setDescription(description);
                return true;
            }
        }
        return false;
    }

    public Task getTask(Task task) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                return t;
            }
        }
        return null;
    }

    public boolean replaceTask(Task task, Task newTask) {
        for (Task t : tasks) {
            if (t.getTitle().equals(task.getTitle()) && t.getDescription().equals(task.getDescription())) {
                tasks.remove(t);
                return tasks.add(newTask);
            }
        }
        return false;
    }
}
