class Solution {

    public int solution(String s) {

        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int answer = 1;

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        for (int i = 0; i < n - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                answer = 2;
            }
        }

        for (int length = 3; length <= n; length++) {
            for (int start = 0; start + length <= n; start++) {

                int end = start + length - 1;

                if (s.charAt(start) == s.charAt(end)
                        && dp[start + 1][end - 1]) {

                    dp[start][end] = true;
                    answer = length;
                }
            }
        }

        return answer;
    }
}