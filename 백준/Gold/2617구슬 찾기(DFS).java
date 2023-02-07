import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] checkedBeads;
    static int[][] dp;
    static HashSet<Integer>[] beadsWeightList;

    static void DFS(int beadsNumber, int startBeadsNumber) {

        checkedBeads[beadsNumber] = true;

        for (int next : beadsWeightList[beadsNumber]) {
            if (!checkedBeads[next]) {
                dp[startBeadsNumber][0]++; // 현재 구슬보다 가벼운 구슬 수
                dp[next][1]++; // 현재 구슬보다 무거운 구슬 수
                DFS(next, startBeadsNumber);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        beadsWeightList = new HashSet[N + 1];
        for (int i = 1; i <= N; i++) {
            beadsWeightList[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            beadsWeightList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
        // data input end

        dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            checkedBeads = new boolean[N + 1];
            DFS(i, i);
        }

        int answer = 0;
        for (int i = 1; i <= N; i++)
            if (dp[i][0] > N / 2 || dp[i][1] > N / 2)
                answer++;

        System.out.println(answer);
    }
}