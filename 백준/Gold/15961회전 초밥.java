import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * * 메모리 170780KB 시간 560ms
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int sum = 0, max = 0;
        int[] s = new int[N];
        int[] cnt = new int[D + 1];
        for (int i = 0; i < N; i++) {
            s[i] = Integer.parseInt(br.readLine());
            if (i < K && cnt[s[i]]++ == 0)
                sum++;
        }
        for (int i = 0; i < N; i++) {
            if (--cnt[s[i]] == 0)
                sum--;
            if (++cnt[s[(i + K) % N]] == 1)
                sum++;
            if (cnt[C] == 0)
                max = sum + 1 > max ? sum + 1 : max;
            else
                max = sum > max ? sum : max;
        }
        System.out.println(max);

    }

}

/**
 * 아래는 처음 제출했던 코드
 * 메모리 328540KB 시간 1220ms
 */

// public class Main {
// public static void main(String[] args) throws IOException {
// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
// String[] input = br.readLine().split(" ");
// // 접시의 수 N, 초밥의 가짓수 d, 연속해서 먹는 접시의 수 k, 쿠폰 번호 c
// // 2 ≤ N ≤ 3,000,000, 2 ≤ d ≤ 3,000, 2 ≤ k ≤ 3,000 (k ≤ N), 1 ≤ c ≤ d
// int N = Integer.parseInt(input[0]);
// int D = Integer.parseInt(input[1]);
// int K = Integer.parseInt(input[2]);
// int C = Integer.parseInt(input[3]);

// int[] sushis = new int[N];
// for (int i = 0; i < sushis.length; i++) {
// sushis[i] = Integer.parseInt(br.readLine());
// }
// // data input end
// int answer = 0;
// int startIdx = 0;
// int endIdx = (startIdx + K - 1) % N;
// HashSet<Integer> answerSet = new HashSet<>();
// HashMap<Integer, Integer> answerMap = new HashMap<>();
// for (int i = startIdx; i <= endIdx; i++) {
// answerSet.add(sushis[i]);
// answerMap.put(sushis[i], answerMap.getOrDefault(sushis[i], 0) + 1);
// }

// for (int i = 0; i < sushis.length; i++) {
// endIdx = (endIdx + 1) % N;
// answerSet.add(sushis[endIdx]);
// answerMap.put(sushis[endIdx], answerMap.getOrDefault(sushis[endIdx], 0) + 1);
// answerMap.put(sushis[startIdx], answerMap.get(sushis[startIdx]) - 1);
// if (answerMap.get(sushis[startIdx]) == 0) {
// answerSet.remove(sushis[startIdx]);
// }
// startIdx = (startIdx + 1) % N;

// if (answerSet.contains(C)) {
// answer = Math.max(answer, answerSet.size());
// } else {
// answer = Math.max(answer, answerSet.size() + 1);
// }
// }

// System.out.println(answer);
// }
// }