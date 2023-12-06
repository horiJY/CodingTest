import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] sequence, cache;
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sequence = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        cache = new int[N];
        Arrays.fill(cache, Integer.MAX_VALUE);
        cache[0] = sequence[0];
        for (int i = 0, cIdx = 0; i < N; i++) {
            if (cache[cIdx] < sequence[i]) {
                cache[++cIdx] = sequence[i];
            } else {
                int fixIdx = findIdx(0, cIdx, sequence[i]);
                cache[fixIdx] = sequence[i];
            }
        }

        for (int i = N - 1; i >= 0; i--) {
            if (cache[i] != Integer.MAX_VALUE) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static int findIdx(int lo, int hi, int target) {
        int mid = 0;
        while(lo < hi){
            mid = (lo + hi) / 2;
            if(cache[mid] < target){
                lo = mid+1;
            }else{
                hi = mid;
            }
        }
        
        return hi;
    }
}
