class Solution {

    int[] discount = {10, 20, 30, 40};

    int maxMember = 0;
    int maxSales = 0;

    public int[] solution(int[][] users, int[] emoticons) {

        int[] selected = new int[emoticons.length];

        dfs(0, selected, users, emoticons);

        return new int[]{maxMember, maxSales};
    }

    private void dfs(int depth, int[] selected, int[][] users, int[] emoticons) {

        if (depth == emoticons.length) {

            int member = 0;
            int sales = 0;

            for (int[] user : users) {

                int needDiscount = user[0];
                int limit = user[1];

                int sum = 0;

                for (int i = 0; i < emoticons.length; i++) {

                    if (selected[i] >= needDiscount) {
                        sum += emoticons[i] * (100 - selected[i]) / 100;
                    }
                }

                if (sum >= limit) {
                    member++;
                } else {
                    sales += sum;
                }
            }

            if (member > maxMember) {
                maxMember = member;
                maxSales = sales;
            } else if (member == maxMember && sales > maxSales) {
                maxSales = sales;
            }

            return;
        }

        for (int d : discount) {
            selected[depth] = d;
            dfs(depth + 1, selected, users, emoticons);
        }
    }
}