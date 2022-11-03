import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] homes;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int homeCount = Integer.parseInt(st.nextToken());
        int wifiCount = Integer.parseInt(st.nextToken());

        homes = new int[homeCount];
        for (int i = 0; i < homeCount; i++) {
            homes[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(homes);

        int minDistance = 1;
        int maxDistance = homes[homeCount - 1] - homes[0];
        if (wifiCount == 2) {
            System.out.println(maxDistance);
            return;
        } else {
            while (minDistance < maxDistance) {
                int curDistance = (minDistance + maxDistance) / 2;

                if (checkWifi(curDistance) < wifiCount) {
                    // 필요 개수 < 요구 개수 -> 거리가 김-> 상한값 내림
                    maxDistance = curDistance;
                } else {
                    // 필요 개수 > 요구 개수 -> 거리가 짧음 -> 하한값 올림
                    minDistance = curDistance + 1;
                }
            }
        }

        System.out.println(minDistance - 1);
    }

    private static int checkWifi(int distance) {
        int count = 1;
        int lastLocation = homes[0];

        for (int i = 1; i < homes.length; i++) {
            if (homes[i] - lastLocation >= distance) {
                count++;
                lastLocation = homes[i];
            }
        }

        return count;
    }

}
