class Solution {

    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {

        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {

            int x = balls[i][0];
            int y = balls[i][1];

            long min = Long.MAX_VALUE;

            if (!(startX == x && startY < y)) {
                long dx = startX - x;
                long dy = startY - (2L * n - y);

                min = Math.min(min, dx * dx + dy * dy);
            }

            if (!(startX == x && startY > y)) {
                long dx = startX - x;
                long dy = startY + y;

                min = Math.min(min, dx * dx + dy * dy);
            }

            if (!(startY == y && startX > x)) {
                long dx = startX + x;
                long dy = startY - y;

                min = Math.min(min, dx * dx + dy * dy);
            }

            if (!(startY == y && startX < x)) {
                long dx = startX - (2L * m - x);
                long dy = startY - y;

                min = Math.min(min, dx * dx + dy * dy);
            }

            answer[i] = (int) min;
        }

        return answer;
    }
}