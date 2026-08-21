import java.util.*;

class Solution {

    int k;
    int n;
    int[][] reqs;

    int answer = Integer.MAX_VALUE;

    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        this.n = n;
        this.reqs = reqs;

        int[] mentors = new int[k];

        dfs(0, n, mentors);

        return answer;
    }

    void dfs(int type, int remain, int[] mentors) {
        if (type == k) {
            if (remain == 0) {
                int total = 0;

                for (int i = 0; i < k; i++) {
                    total += calculate(i + 1, mentors[i]);

                    if (total >= answer) {
                        return;
                    }
                }

                answer = Math.min(answer, total);
            }

            return;
        }

        for (int count = 1; count <= remain - (k - type - 1); count++) {
            mentors[type] = count;
            dfs(type + 1, remain - count, mentors);
        }
    }

    int calculate(int type, int mentorCount) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < mentorCount; i++) {
            pq.offer(0);
        }

        int waiting = 0;

        for (int[] req : reqs) {
            if (req[2] != type) {
                continue;
            }

            int start = req[0];
            int duration = req[1];

            int end = pq.poll();

            if (end > start) {
                waiting += end - start;
                start = end;
            }

            pq.offer(start + duration);
        }

        return waiting;
    }
}