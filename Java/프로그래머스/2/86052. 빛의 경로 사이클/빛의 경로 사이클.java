import java.util.*;

class Solution {

    public int[] solution(String[] grid) {

        int n = grid.length;
        int m = grid[0].length();

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        boolean[][][] visited = new boolean[n][m][4];

        List<Integer> cycles = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < m; c++) {
                for (int dir = 0; dir < 4; dir++) {

                    if (visited[r][c][dir]) {
                        continue;
                    }

                    int length = 0;

                    int cr = r;
                    int cc = c;
                    int cd = dir;

                    while (!visited[cr][cc][cd]) {

                        visited[cr][cc][cd] = true;
                        length++;

                        if (grid[cr].charAt(cc) == 'L') {
                            cd = (cd + 3) % 4;
                        } else if (grid[cr].charAt(cc) == 'R') {
                            cd = (cd + 1) % 4;
                        }
                        cr = (cr + dr[cd] + n) % n;
                        cc = (cc + dc[cd] + m) % m;
                    }

                    cycles.add(length);
                }
            }
        }

        Collections.sort(cycles);

        int[] answer = new int[cycles.size()];

        for (int i = 0; i < cycles.size(); i++) {
            answer[i] = cycles.get(i);
        }

        return answer;
    }
}