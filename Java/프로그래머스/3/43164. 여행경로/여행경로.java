import java.util.*;

class Solution {

    private List<String> answer = new ArrayList<>();
    private boolean[] visited;

    public String[] solution(String[][] tickets) {

        visited = new boolean[tickets.length];

        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        List<String> path = new ArrayList<>();
        path.add("ICN");

        dfs("ICN", tickets, path, 0);

        return answer.toArray(new String[0]);
    }

    private boolean dfs(String current, String[][] tickets,
                        List<String> path, int count) {

        if (count == tickets.length) {
            answer = new ArrayList<>(path);
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {

            if (visited[i]) {
                continue;
            }

            if (!tickets[i][0].equals(current)) {
                continue;
            }

            visited[i] = true;
            path.add(tickets[i][1]);

            if (dfs(tickets[i][1], tickets, path, count + 1)) {
                return true;
            }

            path.remove(path.size() - 1);
            visited[i] = false;
        }

        return false;
    }
}