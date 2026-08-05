import java.util.*;

class Solution {

    static class Task {
        String name;
        int start;
        int play;

        Task(String name, int start, int play) {
            this.name = name;
            this.start = start;
            this.play = play;
        }
    }

    public String[] solution(String[][] plans) {

        Task[] tasks = new Task[plans.length];

        for (int i = 0; i < plans.length; i++) {
            tasks[i] = new Task(
                    plans[i][0],
                    convert(plans[i][1]),
                    Integer.parseInt(plans[i][2]));
        }

        Arrays.sort(tasks, (a, b) -> a.start - b.start);

        Stack<Task> stack = new Stack<>();
        List<String> result = new ArrayList<>();

        for (int i = 0; i < tasks.length - 1; i++) {

            Task cur = tasks[i];
            Task next = tasks[i + 1];

            int remain = next.start - cur.start;

            if (cur.play <= remain) {
                result.add(cur.name);
                remain -= cur.play;

                while (!stack.isEmpty() && remain > 0) {
                    Task prev = stack.pop();

                    if (prev.play <= remain) {
                        remain -= prev.play;
                        result.add(prev.name);
                    } else {
                        prev.play -= remain;
                        stack.push(prev);
                        remain = 0;
                    }
                }

            } else {
                cur.play -= remain;
                stack.push(cur);
            }
        }

        result.add(tasks[tasks.length - 1].name);

        while (!stack.isEmpty()) {
            result.add(stack.pop().name);
        }

        return result.toArray(new String[0]);
    }

    private int convert(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}