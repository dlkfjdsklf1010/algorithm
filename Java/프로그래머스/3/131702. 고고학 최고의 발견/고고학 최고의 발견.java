class Solution {

    public int solution(int[][] clockHands) {
        int n = clockHands.length;
        int answer = Integer.MAX_VALUE;

        int[] first = new int[n];

        while (true) {
            int[][] board = new int[n][n];

            for (int i = 0; i < n; i++) {
                board[i] = clockHands[i].clone();
            }

            int count = 0;

            for (int c = 0; c < n; c++) {
                for (int k = 0; k < first[c]; k++) {
                    turn(board, 0, c);
                    count++;
                }
            }

            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int need = (4 - board[r - 1][c]) % 4;

                    for (int k = 0; k < need; k++) {
                        turn(board, r, c);
                        count++;
                    }
                }
            }

            boolean solved = true;

            for (int c = 0; c < n; c++) {
                if (board[n - 1][c] != 0) {
                    solved = false;
                    break;
                }
            }

            if (solved) {
                answer = Math.min(answer, count);
            }

            int index = 0;

            while (index < n) {
                first[index]++;

                if (first[index] < 4) {
                    break;
                }

                first[index] = 0;
                index++;
            }

            if (index == n) {
                break;
            }
        }

        return answer;
    }

    private void turn(int[][] board, int r, int c) {
        int n = board.length;

        board[r][c] = (board[r][c] + 1) % 4;

        if (r > 0) {
            board[r - 1][c] = (board[r - 1][c] + 1) % 4;
        }

        if (r < n - 1) {
            board[r + 1][c] = (board[r + 1][c] + 1) % 4;
        }

        if (c > 0) {
            board[r][c - 1] = (board[r][c - 1] + 1) % 4;
        }

        if (c < n - 1) {
            board[r][c + 1] = (board[r][c + 1] + 1) % 4;
        }
    }
}