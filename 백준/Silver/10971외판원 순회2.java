import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int cityCount;
    static boolean[] visitedCity;
    static int[][] travelCost;
    static int answer;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        cityCount = Integer.parseInt(br.readLine());
        travelCost = new int[cityCount][cityCount];
        answer = Integer.MAX_VALUE;

        visitedCity = new boolean[cityCount];
        for (int i = 0, j = 0; i < cityCount; i++) {
            for (String s : br.readLine().split(" ")) {
                travelCost[i][j++] = Integer.parseInt(s);
            }
            j = 0;
        }
        Arrays.fill(visitedCity, false);
        // data input end

        for (int i = 0; i < cityCount; i++) {
            recursion(i, i, 0, 0);
        }

        System.out.println(answer);
    }

    static void recursion(int startCity, int visitCity, int visitCount, int cost) {
        if (visitCount >= cityCount && visitCity == startCity) {
            answer = Math.min(answer, cost);
            return;
        }

        for (int i = 0; i < cityCount; i++) {
            if (travelCost[visitCity][i] == 0) {
                continue;
            }

            if (!visitedCity[visitCity] && travelCost[visitCity][i] != 0) {
                visitedCity[visitCity] = true;
                cost += travelCost[visitCity][i];
                if (cost < answer) {
                    recursion(startCity, i, visitCount + 1, cost);
                }
                visitedCity[visitCity] = false;
                cost -= travelCost[visitCity][i];
            }
        }
    }
}
