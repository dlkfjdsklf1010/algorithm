class Solution {

    int maxDiff = 0;
    int[] answer = {-1};

    public int[] solution(int n, int[] info) {

        dfs(0, n, info, new int[11]);

        return answer;
    }

    private void dfs(int idx, int remain, int[] info, int[] lion) {

        if (idx == 11) {

            lion[10] += remain;

            int lionScore = 0;
            int apeachScore = 0;

            for (int i = 0; i < 11; i++) {

                int score = 10 - i;

                if (lion[i] == 0 && info[i] == 0) continue;

                if (lion[i] > info[i]) {
                    lionScore += score;
                } else {
                    apeachScore += score;
                }
            }

            int diff = lionScore - apeachScore;

            if (diff > 0) {

                if (diff > maxDiff) {
                    maxDiff = diff;
                    answer = lion.clone();
                } else if (diff == maxDiff) {

                    for (int i = 10; i >= 0; i--) {
                        if (lion[i] > answer[i]) {
                            answer = lion.clone();
                            break;
                        } else if (lion[i] < answer[i]) {
                            break;
                        }
                    }
                }
            }

            lion[10] -= remain;
            return;
        }

        int need = info[idx] + 1;

        if (remain >= need) {
            lion[idx] = need;
            dfs(idx + 1, remain - need, info, lion);
            lion[idx] = 0;
        }

        dfs(idx + 1, remain, info, lion);
    }
}