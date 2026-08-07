import java.util.Arrays;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }
            return a[1] - b[1];
        });

        int answer = 0;
        int missile = -1;

        for (int[] target : targets) {
            int start = target[0];
            int end = target[1];

            if (missile <= start) {
                answer++;
                missile = end;
            }
        }

        return answer;
    }
}