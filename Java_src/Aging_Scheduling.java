import java.util.LinkedList;
import java.util.Queue;

/*Aging Scheduling Algorithm */
/*Used for mixed workloads to avoid lower priority tasks being
/*starved by higher priority tasks.*/

public final class Aging_Scheduling {
    static class Task {
        String name;
        int wait_time;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.priority = priority;
            this.wait_time = 0;
        }
    }

    private final Queue<Task> task_queue;

    public Aging_Scheduling() {
        task_queue = new LinkedList<>();
    }

    public void add_task(String name, int priority) {
        task_queue.offer(new Task(name, priority));
    }

    public String schedule_next() {
        if (task_queue.isEmpty()) {
            return null;
        }
        Task next_task = task_queue.poll();
        next_task.wait_time++;
        next_task.priority += next_task.wait_time;
        task_queue.offer(next_task);
        return next_task.name;
    }
}