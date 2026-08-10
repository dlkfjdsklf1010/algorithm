class Solution {

    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

        long answer = 0;

        int deliveryRemain = 0;
        int pickupRemain = 0;

        for (int i = n - 1; i >= 0; i--) {

            deliveryRemain += deliveries[i];
            pickupRemain += pickups[i];

            while (deliveryRemain > 0 || pickupRemain > 0) {

                answer += (long) (i + 1) * 2;

                deliveryRemain -= cap;
                pickupRemain -= cap;
            }
        }

        return answer;
    }
}