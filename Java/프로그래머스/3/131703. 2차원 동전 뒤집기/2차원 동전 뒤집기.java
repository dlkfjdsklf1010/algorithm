class Solution {

    public int solution(int[][] beginning, int[][] target) {
        int n = beginning.length;
        int m = beginning[0].length;

        int answer = Integer.MAX_VALUE;

        for (int firstRowFlip = 0; firstRowFlip <= 1; firstRowFlip++) {
            int[] rowFlip = new int[n];
            int[] colFlip = new int[m];

            rowFlip[0] = firstRowFlip;

            for (int j = 0; j < m; j++) {
                colFlip[j] = beginning[0][j] ^ rowFlip[0] ^ target[0][j];
            }

            boolean possible = true;

            for (int i = 1; i < n; i++) {
                rowFlip[i] = beginning[i][0] ^ colFlip[0] ^ target[i][0];
            }

            for (int i = 0; i < n && possible; i++) {
                for (int j = 0; j < m; j++) {
                    int value = beginning[i][j] ^ rowFlip[i] ^ colFlip[j];

                    if (value != target[i][j]) {
                        possible = false;
                        break;
                    }
                }
            }

            if (possible) {
                int count = 0;

                for (int i = 0; i < n; i++) {
                    count += rowFlip[i];
                }

                for (int j = 0; j < m; j++) {
                    count += colFlip[j];
                }

                answer = Math.min(answer, count);
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}