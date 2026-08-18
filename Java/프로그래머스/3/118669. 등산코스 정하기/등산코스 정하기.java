import java.util.*;

class Solution {

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<int[]>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] path : paths) {
            int a = path[0];
            int b = path[1];
            int w = path[2];

            graph[a].add(new int[]{b, w});
            graph[b].add(new int[]{a, w});
        }

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];

        for (int gate : gates) {
            isGate[gate] = true;
        }

        for (int summit : summits) {
            isSummit[summit] = true;
        }

        long[] intensity = new long[n + 1];
        Arrays.fill(intensity, Long.MAX_VALUE);

        PriorityQueue<long[]> pq = new PriorityQueue<>(
                Comparator.comparingLong(a -> a[1])
        );

        for (int gate : gates) {
            intensity[gate] = 0;
            pq.offer(new long[]{gate, 0});
        }

        while (!pq.isEmpty()) {
            long[] current = pq.poll();

            int node = (int) current[0];
            long currentIntensity = current[1];

            if (currentIntensity > intensity[node]) {
                continue;
            }

            if (isSummit[node]) {
                continue;
            }

            for (int[] edge : graph[node]) {
                int next = edge[0];
                int weight = edge[1];

                if (isGate[next]) {
                    continue;
                }

                long nextIntensity = Math.max(currentIntensity, weight);

                if (nextIntensity < intensity[next]) {
                    intensity[next] = nextIntensity;
                    pq.offer(new long[]{next, nextIntensity});
                }
            }
        }

        int bestSummit = Integer.MAX_VALUE;
        long bestIntensity = Long.MAX_VALUE;

        Arrays.sort(summits);

        for (int summit : summits) {
            if (intensity[summit] < bestIntensity) {
                bestIntensity = intensity[summit];
                bestSummit = summit;
            }
        }

        return new int[]{bestSummit, (int) bestIntensity};
    }
}