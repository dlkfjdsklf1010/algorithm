class Solution {

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long minX = x;
        long maxX = x;
        long minY = y;
        long maxY = y;

        for (int i = queries.length - 1; i >= 0; i--) {
            int command = queries[i][0];
            long dx = queries[i][1];

            if (command == 0) {
                if (minY != 0) {
                    minY += dx;
                }

                maxY = Math.min((long) m - 1, maxY + dx);

            } else if (command == 1) {
                minY = Math.max(0, minY - dx);

                if (maxY != m - 1) {
                    maxY -= dx;
                }

            } else if (command == 2) {
                if (minX != 0) {
                    minX += dx;
                }

                maxX = Math.min((long) n - 1, maxX + dx);

            } else {
                minX = Math.max(0, minX - dx);

                if (maxX != n - 1) {
                    maxX -= dx;
                }
            }

            if (minX > maxX || minY > maxY) {
                return 0;
            }
        }

        return (maxX - minX + 1) * (maxY - minY + 1);
    }
}