import java.util.*;

class Solution {

    int n;
    int m;

    int[] dr = {-1, 1, 0, 0};
    int[] dc = {0, 0, -1, 1};

    Map<Long, Result> memo = new HashMap<>();

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        n = board.length;
        m = board[0].length;

        int mask = 0;

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                if (board[r][c] == 1) {
                    mask |= 1 << (r * m + c);
                }
            }
        }

        Result result = dfs(
                aloc[0],
                aloc[1],
                bloc[0],
                bloc[1],
                mask
        );

        return result.count;
    }

    Result dfs(int ar, int ac, int br, int bc, int mask) {
        long key = makeKey(ar, ac, br, bc, mask);

        Result cached = memo.get(key);

        if (cached != null) {
            return cached;
        }

        int current = ar * m + ac;

        if ((mask & (1 << current)) == 0) {
            return new Result(false, 0);
        }

        boolean canMove = false;

        boolean canWin = false;
        int minWinCount = Integer.MAX_VALUE;
        int maxLoseCount = 0;

        for (int d = 0; d < 4; d++) {
            int nr = ar + dr[d];
            int nc = ac + dc[d];

            if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
                continue;
            }

            int next = nr * m + nc;

            if ((mask & (1 << next)) == 0) {
                continue;
            }

            canMove = true;

            int nextMask = mask & ~(1 << current);

            Result nextResult = dfs(
                    br,
                    bc,
                    nr,
                    nc,
                    nextMask
            );

            int count = nextResult.count + 1;

            if (!nextResult.win) {
                canWin = true;
                minWinCount = Math.min(minWinCount, count);
            } else {
                maxLoseCount = Math.max(maxLoseCount, count);
            }
        }

        Result result;

        if (!canMove) {
            result = new Result(false, 0);
        } else if (canWin) {
            result = new Result(true, minWinCount);
        } else {
            result = new Result(false, maxLoseCount);
        }

        memo.put(key, result);

        return result;
    }

    long makeKey(int ar, int ac, int br, int bc, int mask) {
        long key = mask;

        key = (key << 5) | (ar * m + ac);
        key = (key << 5) | (br * m + bc);

        return key;
    }

    static class Result {
        boolean win;
        int count;

        Result(boolean win, int count) {
            this.win = win;
            this.count = count;
        }
    }
}