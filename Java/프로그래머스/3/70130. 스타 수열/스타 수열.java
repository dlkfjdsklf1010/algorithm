class Solution {

    public int solution(int[] a) {

        int n = a.length;
        int max = 0;

        int[] count = new int[n];

        for (int value : a) {
            count[value]++;
        }

        for (int value = 0; value < n; value++) {

            if (count[value] * 2 <= max) {
                continue;
            }

            int pairCount = 0;
            int i = 0;

            while (i < n - 1) {

                if (a[i] == value && a[i + 1] != value) {
                    pairCount++;
                    i += 2;
                } else if (a[i] != value && a[i + 1] == value) {
                    pairCount++;
                    i += 2;
                } else {
                    i++;
                }
            }

            max = Math.max(max, pairCount * 2);
        }

        return max;
    }
}