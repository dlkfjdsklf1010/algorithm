import java.util.*;

class Solution {

    public int solution(int[][] scores) {
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        int[][] employees = new int[scores.length][3];

        for (int i = 0; i < scores.length; i++) {
            employees[i][0] = scores[i][0];
            employees[i][1] = scores[i][1];
            employees[i][2] = i;
        }

        Arrays.sort(employees, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(b[0], a[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int maxB = -1;
        List<Integer> sums = new ArrayList<>();

        for (int[] employee : employees) {
            int a = employee[0];
            int b = employee[1];
            int index = employee[2];

            if (b < maxB) {
                if (index == 0) {
                    return -1;
                }
                continue;
            }

            maxB = Math.max(maxB, b);
            sums.add(a + b);
        }

        int rank = 1;

        for (int sum : sums) {
            if (sum > wanhoSum) {
                rank++;
            }
        }

        return rank;
    }
}