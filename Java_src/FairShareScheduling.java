import java.util.HashMap;
import java.util.Map;

/*Fair Share Scheduling Algorithm*/
/*Used for multi-user systems where users submit multiple
/*tasks simultaneously in cloud environments.*/

public final class FairShareScheduling {
    static class User {
        String name;
        int allocated_resources;
        int total_weight;

        User(String name) {
            this.name = name;
            this.allocated_resources = 0;
            this.total_weight = 0;
        }

        void add_weight(int weight) {
            this.total_weight += weight;
        }
    }

    private final Map<String, User> users;

    public FairShareScheduling() {
        users = new HashMap<>();
    }

    public void add_user(String user_name) {
        users.putIfAbsent(user_name, new User(user_name));
    }

    public void add_task(String user_name, int weight) {
        User user = users.get(user_name);
        if (user != null) {
            user.add_weight(weight);
        }
    }

    public void allocate_resources(int total_resources) {
        int total_weights = users.values().stream().mapToInt(user -> user.total_weight).sum();
        for (User user:users.values()) {
            user.allocated_resources = (int) ((double) user.total_weight / total_weights*total_resources);
        }
    }

    public Map<String, Integer> get_allocated_resources() {
        Map<String, Integer> allocation = new HashMap<>();
        for (User user:users.values()) {
            allocation.put(user.name, user.allocated_resources);
        }
        return allocation;
    }
}