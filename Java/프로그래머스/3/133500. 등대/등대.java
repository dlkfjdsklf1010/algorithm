import java.util.*;

class Solution {

    public int solution(int n, int[][] lighthouse) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n];
        int idx = 0;

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        parent[1] = -1;

        while (!stack.isEmpty()) {
            int cur = stack.pop();
            order[idx++] = cur;

            for (int next : graph[cur]) {
                if (next == parent[cur]) {
                    continue;
                }
                parent[next] = cur;
                stack.push(next);
            }
        }

        int[][] dp = new int[n + 1][2];

        for (int i = n - 1; i >= 0; i--) {
            int cur = order[i];
            dp[cur][1] = 1;

            for (int next : graph[cur]) {
                if (next == parent[cur]) {
                    continue;
                }

                dp[cur][0] += dp[next][1];
                dp[cur][1] += Math.min(dp[next][0], dp[next][1]);
            }
        }

        return Math.min(dp[1][0], dp[1][1]);
    }
}