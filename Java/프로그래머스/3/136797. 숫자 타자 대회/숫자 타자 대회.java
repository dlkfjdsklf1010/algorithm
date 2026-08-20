class Solution {

    public int solution(String numbers) {
        int[][] pos = {
                {3, 1},
                {0, 0},
                {0, 1},
                {0, 2},
                {1, 0},
                {1, 1},
                {1, 2},
                {2, 0},
                {2, 1},
                {2, 2}
        };

        int[][] cost = new int[10][10];

        for (int from = 0; from < 10; from++) {
            for (int to = 0; to < 10; to++) {
                if (from == to) {
                    cost[from][to] = 1;
                    continue;
                }

                int dr = Math.abs(pos[from][0] - pos[to][0]);
                int dc = Math.abs(pos[from][1] - pos[to][1]);

                int diagonal = Math.min(dr, dc);
                int straight = Math.abs(dr - dc);

                cost[from][to] = diagonal * 3 + straight * 2;
            }
        }

        final int INF = Integer.MAX_VALUE / 4;

        int[][] dp = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = INF;
            }
        }

        dp[4][6] = 0;

        for (int i = 0; i < numbers.length(); i++) {
            int target = numbers.charAt(i) - '0';

            int[][] nextDp = new int[10][10];

            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    nextDp[left][right] = INF;
                }
            }

            for (int left = 0; left < 10; left++) {
                for (int right = 0; right < 10; right++) {
                    if (dp[left][right] == INF) {
                        continue;
                    }

                    if (target == left) {
                        nextDp[target][right] = Math.min(
                                nextDp[target][right],
                                dp[left][right] + cost[left][target]
                        );
                    } else if (target == right) {
                        nextDp[left][target] = Math.min(
                                nextDp[left][target],
                                dp[left][right] + cost[right][target]
                        );
                    } else {
                        nextDp[target][right] = Math.min(
                                nextDp[target][right],
                                dp[left][right] + cost[left][target]
                        );

                        nextDp[left][target] = Math.min(
                                nextDp[left][target],
                                dp[left][right] + cost[right][target]
                        );
                    }
                }
            }

            dp = nextDp;
        }

        int answer = INF;

        for (int left = 0; left < 10; left++) {
            for (int right = 0; right < 10; right++) {
                answer = Math.min(answer, dp[left][right]);
            }
        }

        return answer;
    }
}