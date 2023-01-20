import java.util.ArrayList;

class Solution {
    static ArrayList<int[]> result = new ArrayList<>();
    static int[] salePercent = new int[] { 10, 20, 30, 40 };

    public int[] solution(int[][] users, int[] emoticons) {
        int[] sales = new int[emoticons.length];
        checkCustomer(users, emoticons, sales, 0);

        result.sort((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });

        return result.get(0);
    }

    private void checkCustomer(int[][] users, int[] emoticons, int[] sales, int depth) {
        if (depth == emoticons.length) {
            int subscribe = 0;
            int income = 0;
            for (int i = 0; i < users.length; i++) {
                int cost = 0;
                for (int j = 0; j < sales.length; j++) {
                    if (sales[j] >= users[i][0]) {
                        cost += (100 - sales[j]) * emoticons[j] / 100;
                    }

                    if (cost >= users[i][1]) {
                        subscribe++;
                        cost = 0;
                        break;
                    }
                }

                if (cost != 0) {
                    income += cost;
                }
            }
            if (subscribe != 0 || income != 0) {
                result.add(new int[] { subscribe, income });
            }
        } else {
            for (int i = 0; i < salePercent.length; i++) {
                sales[depth] = salePercent[i];
                checkCustomer(users, emoticons, sales, depth + 1);
            }
        }
    }

}