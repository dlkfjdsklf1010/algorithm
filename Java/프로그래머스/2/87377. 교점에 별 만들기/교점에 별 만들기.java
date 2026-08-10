import java.util.ArrayList;
import java.util.List;

class Solution {

    public String[] solution(int[][] line) {

        List<long[]> points = new ArrayList<>();

        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        // 모든 직선의 쌍을 확인
        for (int i = 0; i < line.length; i++) {

            long A = line[i][0];
            long B = line[i][1];
            long C = line[i][2];

            for (int j = i + 1; j < line.length; j++) {

                long D = line[j][0];
                long E = line[j][1];
                long F = line[j][2];
                long denominator = A * E - B * D;

                if (denominator == 0) {
                    continue;
                }
                long xNumerator = B * F - C * E;
                long yNumerator = C * D - A * F;

                if (xNumerator % denominator != 0 ||
                    yNumerator % denominator != 0) {
                    continue;
                }

                long x = xNumerator / denominator;
                long y = yNumerator / denominator;

                points.add(new long[]{x, y});

                minX = Math.min(minX, x);
                maxX = Math.max(maxX, x);
                minY = Math.min(minY, y);
                maxY = Math.max(maxY, y);
            }
        }

        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);

        char[][] board = new char[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = '.';
            }
        }

        for (long[] point : points) {

            long x = point[0];
            long y = point[1];

            int row = (int) (maxY - y);
            int col = (int) (x - minX);

            board[row][col] = '*';
        }

        String[] answer = new String[height];

        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }

        return answer;
    }
}