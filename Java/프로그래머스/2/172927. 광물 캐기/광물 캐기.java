import java.util.*;

class Solution {

    static class Group {
        int dia;
        int iron;
        int stone;

        Group(int dia, int iron, int stone) {
            this.dia = dia;
            this.iron = iron;
            this.stone = stone;
        }

        int weight() {
            return dia * 25 + iron * 5 + stone;
        }
    }

    public int solution(int[] picks, String[] minerals) {

        int totalPick = picks[0] + picks[1] + picks[2];

        int limit = Math.min(minerals.length, totalPick * 5);

        List<Group> groups = new ArrayList<>();

        for (int i = 0; i < limit; i += 5) {

            int dia = 0;
            int iron = 0;
            int stone = 0;

            for (int j = i; j < Math.min(i + 5, limit); j++) {

                if (minerals[j].equals("diamond")) dia++;
                else if (minerals[j].equals("iron")) iron++;
                else stone++;
            }

            groups.add(new Group(dia, iron, stone));
        }

        groups.sort((a, b) -> b.weight() - a.weight());

        int answer = 0;
        int idx = 0;

        while (picks[0] > 0 && idx < groups.size()) {

            Group g = groups.get(idx++);

            answer += g.dia + g.iron + g.stone;

            picks[0]--;
        }

        while (picks[1] > 0 && idx < groups.size()) {

            Group g = groups.get(idx++);

            answer += g.dia * 5 + g.iron + g.stone;

            picks[1]--;
        }

        while (picks[2] > 0 && idx < groups.size()) {

            Group g = groups.get(idx++);

            answer += g.dia * 25 + g.iron * 5 + g.stone;

            picks[2]--;
        }

        return answer;
    }
}