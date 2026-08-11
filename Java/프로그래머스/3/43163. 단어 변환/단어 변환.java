import java.util.*;

class Solution {

    public int solution(String begin, String target, String[] words) {
        Queue<String> queue = new LinkedList<>();
        Queue<Integer> count = new LinkedList<>();

        boolean[] visited = new boolean[words.length];

        queue.offer(begin);
        count.offer(0);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            int currentCount = count.poll();

            if (current.equals(target)) {
                return currentCount;
            }

            for (int i = 0; i < words.length; i++) {
                if (visited[i]) {
                    continue;
                }

                if (canChange(current, words[i])) {
                    visited[i] = true;
                    queue.offer(words[i]);
                    count.offer(currentCount + 1);
                }
            }
        }

        return 0;
    }

    private boolean canChange(String current, String next) {
        int different = 0;

        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != next.charAt(i)) {
                different++;
            }
        }

        return different == 1;
    }
}