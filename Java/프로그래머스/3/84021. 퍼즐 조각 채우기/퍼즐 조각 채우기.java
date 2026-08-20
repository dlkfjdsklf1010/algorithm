import java.util.*;

class Solution {

    static int n;
    static int[][] board;
    static boolean[][] visited;

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        List<List<Point>> blanks = getShapes(game_board, 0);
        List<List<Point>> pieces = getShapes(table, 1);

        boolean[] used = new boolean[pieces.size()];

        int answer = 0;

        for (List<Point> blank : blanks) {
            for (int i = 0; i < pieces.size(); i++) {
                if (used[i]) {
                    continue;
                }

                if (blank.size() != pieces.get(i).size()) {
                    continue;
                }

                if (isSame(blank, pieces.get(i))) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    static List<List<Point>> getShapes(int[][] map, int target) {
        board = map;
        visited = new boolean[n][n];

        List<List<Point>> shapes = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (visited[r][c] || board[r][c] != target) {
                    continue;
                }

                List<Point> shape = bfs(r, c, target);
                normalize(shape);
                shapes.add(shape);
            }
        }

        return shapes;
    }

    static List<Point> bfs(int startR, int startC, int target) {
        Queue<Point> queue = new LinkedList<>();
        List<Point> shape = new ArrayList<>();

        queue.offer(new Point(startR, startC));
        visited[startR][startC] = true;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            shape.add(cur);

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) {
                    continue;
                }

                if (visited[nr][nc] || board[nr][nc] != target) {
                    continue;
                }

                visited[nr][nc] = true;
                queue.offer(new Point(nr, nc));
            }
        }

        return shape;
    }

    static void normalize(List<Point> shape) {
        int minR = Integer.MAX_VALUE;
        int minC = Integer.MAX_VALUE;

        for (Point p : shape) {
            minR = Math.min(minR, p.r);
            minC = Math.min(minC, p.c);
        }

        for (Point p : shape) {
            p.r -= minR;
            p.c -= minC;
        }

        shape.sort((a, b) -> {
            if (a.r != b.r) {
                return a.r - b.r;
            }
            return a.c - b.c;
        });
    }

    static boolean isSame(List<Point> blank, List<Point> piece) {
        List<Point> current = new ArrayList<>();

        for (Point p : piece) {
            current.add(new Point(p.r, p.c));
        }

        for (int rotate = 0; rotate < 4; rotate++) {
            normalize(current);

            if (equalsShape(blank, current)) {
                return true;
            }

            current = rotate(current);
        }

        return false;
    }

    static List<Point> rotate(List<Point> shape) {
        List<Point> rotated = new ArrayList<>();

        for (Point p : shape) {
            rotated.add(new Point(p.c, -p.r));
        }

        normalize(rotated);

        return rotated;
    }

    static boolean equalsShape(List<Point> a, List<Point> b) {
        if (a.size() != b.size()) {
            return false;
        }

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).r != b.get(i).r ||
                a.get(i).c != b.get(i).c) {
                return false;
            }
        }

        return true;
    }

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}