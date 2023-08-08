import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Seller> network = new HashMap<>();
        network.put("-", new Seller("-", "-", 0));
        for (int i = 0; i < enroll.length; i++) {
            network.put(enroll[i], new Seller(enroll[i], referral[i], 0));
        }

        for (int i = 0; i < seller.length; i++) {
            int profit = amount[i] * 100;
            int recommenderProfit = 0;
            Seller salesman = network.get(seller[i]);
            while (profit > 0) {
                recommenderProfit = profit / 10;
                if (recommenderProfit > 0) {
                    salesman.profit += profit - recommenderProfit;
                    // salesman.profit += Math.round(profit * 0.9);
                    network.put(salesman.name, salesman);
                    profit = recommenderProfit;
                    if (!salesman.recommender.equals("-")) {
                        salesman = network.get(salesman.recommender);
                    } else {
                        Seller center = network.get(salesman.recommender);
                        center.profit += profit;
                        network.put(center.name, center);
                        break;
                    }
                } else {
                    salesman.profit += profit;
                    network.put(salesman.name, salesman);
                    break;
                }
            }
        }

        int[] answer = new int[enroll.length];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = network.get(enroll[i]).profit;
        }

        return answer;
    }
}

class Seller {
    String name;
    String recommender;
    int profit;

    public Seller(String name, String recommender, int profit) {
        this.name = name;
        this.recommender = recommender;
        this.profit = profit;
    }

}