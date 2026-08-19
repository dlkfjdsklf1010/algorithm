class Solution {

    public int[] solution(int target) {
        int[] darts = new int[61];
        int[] single = new int[61];

        int index = 0;

        for (int i = 1; i <= 20; i++) {
            darts[index] = i;
            single[index++] = 1;

            darts[index] = i * 2;
            single[index++] = 0;

            darts[index] = i * 3;
            single[index++] = 0;
        }

        darts[index] = 50;
        single[index] = 1;

        int[] count = new int[target + 1];
        int[] singleCount = new int[target + 1];

        for (int i = 1; i <= target; i++) {
            count[i] = Integer.MAX_VALUE;
        }

        for (int score = 1; score <= target; score++) {
            for (int i = 0; i < 61; i++) {
                if (darts[i] > score) {
                    continue;
                }

                int previous = score - darts[i];

                if (count[previous] == Integer.MAX_VALUE) {
                    continue;
                }

                int newCount = count[previous] + 1;
                int newSingleCount = singleCount[previous] + single[i];

                if (newCount < count[score]
                        || (newCount == count[score] && newSingleCount > singleCount[score])) {
                    count[score] = newCount;
                    singleCount[score] = newSingleCount;
                }
            }
        }

        return new int[]{count[target], singleCount[target]};
    }
}