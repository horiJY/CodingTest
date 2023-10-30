import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        Map<String, Seller> network = new HashMap<>();

        network.put("-", new Seller("-", "-", 0));
        for (int i = 0; i < enroll.length; i++) {
            network.put(enroll[i], new Seller(enroll[i], referral[i], i));
        }

        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            Seller salesman = network.get(seller[i]);

            while (profit > 0) {
                if (profit / 10 > 0) {
                    answer[salesman.idx] += profit - (profit / 10);
                    profit /= 10;

                    if (salesman.recommender.equals("-")) {
                        break;
                    }
                    salesman = network.get(salesman.recommender);
                } else {
                    answer[salesman.idx] += profit;
                    break;
                }
            }
        }

        return answer;
    }
}

class Seller {
    String name;
    String recommender;
    int idx;

    public Seller(String name, String recommender, int idx) {
        this.name = name;
        this.recommender = recommender;
        this.idx = idx;
    }

}