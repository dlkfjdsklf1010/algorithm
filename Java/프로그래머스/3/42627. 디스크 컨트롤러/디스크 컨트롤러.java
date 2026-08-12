import java.util.*;

class Solution {

    public int solution(int[][] jobs) {

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {

            if (a[1] != b[1]) {
                return a[1] - b[1];
            }
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            return a[2] - b[2];
        });

        int time = 0;
        int index = 0;
        int total = 0;

        while (index < jobs.length || !pq.isEmpty()) {

            while (index < jobs.length && jobs[index][0] <= time) {
                pq.offer(new int[]{
                    jobs[index][0],
                    jobs[index][1],
                    index
                });
                index++;
            }

            if (pq.isEmpty()) {
                time = jobs[index][0];
                continue;
            }

            int[] job = pq.poll();

            time += job[1];

            total += time - job[0];
        }

        return total / jobs.length;
    }
}