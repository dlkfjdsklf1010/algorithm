class Solution {

    public long solution(int[] sequence) {

        long max1 = 0;
        long max2 = 0;

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < sequence.length; i++) {

            long value1 = (i % 2 == 0) ? sequence[i] : -sequence[i];
            long value2 = -value1;

            sum1 = Math.max(value1, sum1 + value1);
            sum2 = Math.max(value2, sum2 + value2);

            max1 = Math.max(max1, sum1);
            max2 = Math.max(max2, sum2);
        }

        return Math.max(max1, max2);
    }
}