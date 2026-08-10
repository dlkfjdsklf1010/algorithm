class Solution {

    public int solution(int n, long l, long r) {
        return count(n, r) - count(n, l - 1);
    }

    private int count(int n, long x) {

        if (x <= 0) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        long length = 1;

        for (int i = 0; i < n - 1; i++) {
            length *= 5;
        }

        int result = 0;

        if (x <= length) {
            return count(n - 1, x);
        }

        result += count(n - 1, length);
        x -= length;

        if (x <= length) {
            return result + count(n - 1, x);
        }

        result += count(n - 1, length);
        x -= length;

        if (x <= length) {
            return result;
        }

        x -= length;

        if (x <= length) {
            return result + count(n - 1, x);
        }

        result += count(n - 1, length);
        x -= length;

        return result + count(n - 1, x);
    }
}