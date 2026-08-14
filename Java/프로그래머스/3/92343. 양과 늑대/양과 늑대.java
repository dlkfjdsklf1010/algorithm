import java.util.*;

class Solution {

    private int[] info;
    private List<Integer>[] graph;
    private int answer;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        this.answer = 0;

        graph = new ArrayList[info.length];

        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        List<Integer> candidates = new ArrayList<>();

        for (int child : graph[0]) {
            candidates.add(child);
        }

        dfs(0, 1, 0, candidates);

        return answer;
    }

    private void dfs(int node, int sheep, int wolf, List<Integer> candidates) {
        answer = Math.max(answer, sheep);

        for (int i = 0; i < candidates.size(); i++) {
            int next = candidates.get(i);

            int nextSheep = sheep;
            int nextWolf = wolf;

            if (info[next] == 0) {
                nextSheep++;
            } else {
                nextWolf++;
            }

            if (nextWolf >= nextSheep) {
                continue;
            }

            List<Integer> nextCandidates = new ArrayList<>(candidates);
            nextCandidates.remove(i);

            for (int child : graph[next]) {
                nextCandidates.add(child);
            }

            dfs(next, nextSheep, nextWolf, nextCandidates);
        }
    }
}