import java.util.*;

class Solution {

    public int solution(int N, int number) {

        if (N == number) {
            return 1;
        }

        List<Set<Integer>> dp = new ArrayList<>();

        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        for (int i = 1; i <= 8; i++) {

            int repeated = 0;

            for (int j = 0; j < i; j++) {
                repeated = repeated * 10 + N;
            }

            dp.get(i).add(repeated);

            for (int j = 1; j < i; j++) {

                int k = i - j;

                for (int a : dp.get(j)) {
                    for (int b : dp.get(k)) {

                        dp.get(i).add(a + b);
                        dp.get(i).add(a - b);
                        dp.get(i).add(a * b);

                        if (b != 0) {
                            dp.get(i).add(a / b);
                        }
                    }
                }
            }

            if (dp.get(i).contains(number)) {
                return i;
            }
        }

        return -1;
    }
}