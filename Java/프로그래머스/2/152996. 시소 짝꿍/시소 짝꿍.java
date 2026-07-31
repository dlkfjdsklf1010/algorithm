import java.util.*;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);

        Map<Integer, Integer> map = new HashMap<>();

        for (int w : weights) {

            answer += map.getOrDefault(w, 0);

            if (w * 2 % 3 == 0) {
                answer += map.getOrDefault(w * 2 / 3, 0);
            }

            if (w % 2 == 0) {
                answer += map.getOrDefault(w / 2, 0);
            }

            if (w * 3 % 4 == 0) {
                answer += map.getOrDefault(w * 3 / 4, 0);
            }

            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        return answer;
    }
}