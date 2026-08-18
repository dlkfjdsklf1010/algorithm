class Solution {

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        int distance = Math.abs(x - r) + Math.abs(y - c);

        if (distance > k || (k - distance) % 2 != 0) {
            return "impossible";
        }

        StringBuilder answer = new StringBuilder();

        int[] dx = {1, 0, 0, -1};
        int[] dy = {0, -1, 1, 0};
        char[] direction = {'d', 'l', 'r', 'u'};

        for (int i = 0; i < k; i++) {
            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                int remain = k - i - 1;
                int nextDistance = Math.abs(nx - r) + Math.abs(ny - c);

                if (nextDistance <= remain && (remain - nextDistance) % 2 == 0) {
                    answer.append(direction[d]);
                    x = nx;
                    y = ny;
                    break;
                }
            }
        }

        return answer.toString();
    }
}