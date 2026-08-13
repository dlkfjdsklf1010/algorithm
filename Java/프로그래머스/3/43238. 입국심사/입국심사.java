class Solution {

    public long solution(int n, int[] times) {

        long left = 1;
        long right = 0;

        for (int time : times) {
            right = Math.max(right, (long) time * n);
        }

        while (left <= right) {

            long mid = (left + right) / 2;

            long count = 0;

            for (int time : times) {
                count += mid / time;

                if (count >= n) {
                    break;
                }
            }

            if (count >= n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }
}