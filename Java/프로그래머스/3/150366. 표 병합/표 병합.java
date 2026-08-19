import java.util.*;

class Solution {

    public String[] solution(String[] commands) {
        int size = 2500;

        int[] parent = new int[size];
        String[] value = new String[size];

        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }

        List<String> answer = new ArrayList<>();

        for (String command : commands) {
            String[] parts = command.split(" ");

            if (parts[0].equals("UPDATE")) {
                if (parts.length == 4) {
                    int r = Integer.parseInt(parts[1]) - 1;
                    int c = Integer.parseInt(parts[2]) - 1;
                    String newValue = parts[3];

                    int root = find(parent, r * 50 + c);
                    value[root] = newValue;
                } else {
                    String oldValue = parts[1];
                    String newValue = parts[2];

                    for (int i = 0; i < size; i++) {
                        int root = find(parent, i);

                        if (root == i && oldValue.equals(value[i])) {
                            value[i] = newValue;
                        }
                    }
                }

            } else if (parts[0].equals("MERGE")) {
                int r1 = Integer.parseInt(parts[1]) - 1;
                int c1 = Integer.parseInt(parts[2]) - 1;
                int r2 = Integer.parseInt(parts[3]) - 1;
                int c2 = Integer.parseInt(parts[4]) - 1;

                int cell1 = r1 * 50 + c1;
                int cell2 = r2 * 50 + c2;

                int root1 = find(parent, cell1);
                int root2 = find(parent, cell2);

                if (root1 == root2) {
                    continue;
                }

                String mergedValue = value[root1] != null
                        ? value[root1]
                        : value[root2];

                parent[root2] = root1;
                value[root1] = mergedValue;
                value[root2] = null;

            } else if (parts[0].equals("UNMERGE")) {
                int r = Integer.parseInt(parts[1]) - 1;
                int c = Integer.parseInt(parts[2]) - 1;

                int cell = r * 50 + c;
                int root = find(parent, cell);
                String mergedValue = value[root];

                List<Integer> members = new ArrayList<>();

                for (int i = 0; i < size; i++) {
                    if (find(parent, i) == root) {
                        members.add(i);
                    }
                }

                for (int member : members) {
                    parent[member] = member;
                    value[member] = null;
                }

                value[cell] = mergedValue;

            } else if (parts[0].equals("PRINT")) {
                int r = Integer.parseInt(parts[1]) - 1;
                int c = Integer.parseInt(parts[2]) - 1;

                int root = find(parent, r * 50 + c);

                if (value[root] == null) {
                    answer.add("EMPTY");
                } else {
                    answer.add(value[root]);
                }
            }
        }

        return answer.toArray(new String[0]);
    }

    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }

        return parent[x];
    }
}