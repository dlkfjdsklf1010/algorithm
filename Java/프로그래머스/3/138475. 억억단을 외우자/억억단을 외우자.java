class Solution {

    public int[] solution(int e, int[] starts) {
        int[] count = new int[e + 1];

        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                count[j]++;
            }
        }

        int[] best = new int[e + 1];

        int maxCount = 0;
        int bestNumber = e;

        for (int i = e; i >= 1; i--) {
            if (count[i] >= maxCount) {
                maxCount = count[i];
                bestNumber = i;
            }

            best[i] = bestNumber;
        }

        int[] answer = new int[starts.length];

        for (int i = 0; i < starts.length; i++) {
            answer[i] = best[starts[i]];
        }

        return answer;
    }
}