import java.util.*;

class Solution {
    static class Node {
        int x, y, cnt;

        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        int sx = 0, sy = 0;
        int gx = 0, gy = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = board[i].charAt(j);
                if (c == 'R') {
                    sx = i;
                    sy = j;
                } else if (c == 'G') {
                    gx = i;
                    gy = j;
                }
            }
        }

        boolean[][] visited = new boolean[n][m];
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(sx, sy, 0));
        visited[sx][sy] = true;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (cur.x == gx && cur.y == gy) {
                return cur.cnt;
            }

            for (int d = 0; d < 4; d++) {
                int nx = cur.x;
                int ny = cur.y;

                while (true) {
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];

                    if (tx < 0 || tx >= n || ty < 0 || ty >= m ||
                        board[tx].charAt(ty) == 'D') {
                        break;
                    }

                    nx = tx;
                    ny = ty;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Node(nx, ny, cur.cnt + 1));
                }
            }
        }

        return -1;
    }
}