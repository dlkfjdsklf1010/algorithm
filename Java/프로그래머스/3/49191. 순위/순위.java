class Solution {

    public int solution(int n, int[][] results) {
        boolean[][] graph = new boolean[n][n];

        for (int[] result : results) {
            int winner = result[0] - 1;
            int loser = result[1] - 1;

            graph[winner][loser] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (graph[i][k] && graph[k][j]) {
                        graph[i][j] = true;
                    }
                }
            }
        }

        int answer = 0;

        for (int i = 0; i < n; i++) {
            int count = 0;

            for (int j = 0; j < n; j++) {
                if (graph[i][j] || graph[j][i]) {
                    count++;
                }
            }

            if (count == n - 1) {
                answer++;
            }
        }

        return answer;
    }
}