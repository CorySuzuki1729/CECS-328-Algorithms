import java.util.PriorityQueue;

public final class SelfAdjustingScheduling {
    private static class Task implements Comparable<Task> {
        String name;
        int wait_time;
        int priority;

        Task(String name, int priority) {
            this.name = name;
            this.wait_time = 0;
            this.priority = priority;
        }

        void incrementWaitTime() {
            wait_time++;
            priority = priority + wait_time;
        }

        @Override
        public int compareTo(Task other) {
            return Integer.compare(this.priority, other.priority);
        }
    }

    private final PriorityQueue<Task> taskQueue;

    public SelfAdjustingScheduling() {
        taskQueue = new PriorityQueue<>();
    }

    public void addTask(String name, int priority) {
        taskQueue.offer(new Task(name, priority));
    }

    public String scheduleNext() {
        if (taskQueue.isEmpty()) {
            return null;
        }
        Task nextTask = taskQueue.poll();
        nextTask.incrementWaitTime();
        taskQueue.offer(nextTask);
        return nextTask.name;
    }

    public boolean isEmpty() {
        return taskQueue.isEmpty();
    }
}