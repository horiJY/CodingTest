import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int treeCount = Integer.parseInt(st.nextToken());
        int needMeter = Integer.parseInt(st.nextToken());

        int[] trees = new int[treeCount];
        int minHeight = 0;
        int maxHeight = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < trees.length; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            if (maxHeight < trees[i]) {
                maxHeight = trees[i];
            }
        }
        // data input end

        while (minHeight < maxHeight) {
            int cutPoint = (minHeight + maxHeight) / 2;
            long cutMeter = 0;

            for (int treeHeight : trees) {
                if (treeHeight > cutPoint) {
                    cutMeter += treeHeight - cutPoint;
                }
            }

            // min과 max 중간에서 잘랐을 때
            if (cutMeter == needMeter) {
                System.out.println(cutPoint);
                return;
            } else if (cutMeter < needMeter) { // 적음(위에서 잘랐음)-> 상한선 낮춤
                maxHeight = cutPoint;
            } else { // cut > need -> 많음(아래에서 잘랐음) -> 하한선 높임
                minHeight = cutPoint + 1;
            }
        }
        System.out.println(minHeight - 1);
    }

}