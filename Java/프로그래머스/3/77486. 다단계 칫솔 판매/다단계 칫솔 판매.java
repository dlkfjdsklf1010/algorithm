import java.util.*;

class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int n = enroll.length;

        Map<String, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            indexMap.put(enroll[i], i);
        }

        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            if (referral[i].equals("-")) {
                parent[i] = -1;
            } else {
                parent[i] = indexMap.get(referral[i]);
            }
        }

        int[] answer = new int[n];

        for (int i = 0; i < seller.length; i++) {
            int current = indexMap.get(seller[i]);
            int profit = amount[i] * 100;

            while (current != -1 && profit > 0) {
                int referralProfit = profit / 10;
                int myProfit = profit - referralProfit;

                answer[current] += myProfit;

                profit = referralProfit;
                current = parent[current];
            }
        }

        return answer;
    }
}