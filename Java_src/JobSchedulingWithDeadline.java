import java.util.Arrays;
import java.util.Comparator;

/**
 * A class that implements a job scheduling algorithm to maximize profit
 * while adhering to job deadlines and arrival times.
 *
 * This class provides functionality to schedule jobs based on their profit,
 * arrival time, and deadlines to ensure that the maximum number of jobs is completed
 * within the given timeframe. It sorts the jobs in decreasing order of profit
 * and attempts to assign them to the latest possible time slots.
 */
public final class JobSchedulingWithDeadline {
    private JobSchedulingWithDeadline() {
    }

    /**
     * Represents a job with an ID, arrival time, deadline, and profit.
     *
     * Each job has a unique identifier, an arrival time (when it becomes available for scheduling),
     * a deadline by which it must be completed, and a profit associated with completing the job.
     */
    static class Job {
        int jobId;
        int arrivalTime;
        int deadline;
        int profit;

        Job(int jobId, int arrivalTime, int deadline, int profit) {
            this.jobId = jobId;
            this.arrivalTime = arrivalTime;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static int[] JobSchedulingWithDeadline(Job[] jobs) {
        Arrays.sort(jobs, Comparator.comparingInt(job -> - job.profit));

        int maxDeadline = Arrays.stream(jobs).mapToInt(job -> job.deadline).max().orElse(0);

        int[] timeSlots = new int[maxDeadline];
        Arrays.fill(timeSlots, -1);

        int count = 0;
        int maxProfit = 0;

        // Schedule the jobs
        for (Job job : jobs) {
            if (job.arrivalTime <= job.deadline) {
                for (int i = Math.min(job.deadline - 1, maxDeadline - 1); i >= job.arrivalTime - 1; i--) {
                    if (timeSlots[i] == -1) {
                        timeSlots[i] = job.jobId;
                        count++;
                        maxProfit += job.profit;
                        break;
                    }
                }
            }
        }

        return new int[] {count, maxProfit};
    }
}