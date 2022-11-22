import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 시간 제한 2초
 * 메모리 제한 128 MB
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 멀티탭 구멍의 개수 N (1 ≤ N ≤ 100)
        int N = Integer.parseInt(st.nextToken());
        // 전기 용품의 총 사용횟수 K (1 ≤ K ≤ 100)
        int K = Integer.parseInt(st.nextToken());
        int[] useList = new int[K];

        ArrayList<Integer> multiTab = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            useList[i] = Integer.parseInt(st.nextToken());
        }
        // data input end

        int swapCount = 0;
        for (int i = 0; i < useList.length; i++) {
            if (multiTab.contains(useList[i])) { // 중복 사용방지
                continue;
            } else if (multiTab.size() < N) {
                multiTab.add(useList[i]);
            } else {
                // 사용중인 요소 중 다음 등장 차례가 제일 먼 요소 부터 제거
                int[] remove = new int[] { -1, -1 };
                for (Integer plugIn : multiTab) {
                    for (int j = i + 1; j < useList.length; j++) {
                        if (useList[j] == plugIn) {
                            if (remove[1] < j - i) {
                                remove[0] = plugIn;
                                remove[1] = j - i;
                            }
                            break;
                        } else if (j == useList.length - 1) {
                            remove[0] = plugIn;
                            remove[1] = K;
                        }
                    }
                }

                multiTab.remove((Object) remove[0]);
                multiTab.add(useList[i]);
                swapCount++;
            }
        }

        System.out.println(swapCount);
    }

}