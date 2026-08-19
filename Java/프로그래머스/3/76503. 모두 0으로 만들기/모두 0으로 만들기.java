class Solution {

    public long solution(int[] a, int[][] edges) {
        int n = a.length;

        long sum = 0;
        long[] weight = new long[n];

        for (int i = 0; i < n; i++) {
            weight[i] = a[i];
            sum += a[i];
        }

        if (sum != 0) {
            return -1;
        }

        int[] degree = new int[n];

        for (int[] edge : edges) {
            degree[edge[0]]++;
            degree[edge[1]]++;
        }

        int[][] graph = new int[n][];
        int[] count = new int[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new int[degree[i]];
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph[u][count[u]++] = v;
            graph[v][count[v]++] = u;
        }

        int[] parent = new int[n];
        int[] order = new int[n];

        int head = 0;
        int tail = 0;
        int orderSize = 0;

        int[] queue = new int[n];

        queue[tail++] = 0;
        parent[0] = -1;

        while (head < tail) {
            int current = queue[head++];
            order[orderSize++] = current;

            for (int next : graph[current]) {
                if (next == parent[current]) {
                    continue;
                }

                parent[next] = current;
                queue[tail++] = next;
            }
        }

        long answer = 0;

        for (int i = n - 1; i > 0; i--) {
            int current = order[i];
            int p = parent[current];

            answer += Math.abs(weight[current]);
            weight[p] += weight[current];
        }

        return answer;
    }
}