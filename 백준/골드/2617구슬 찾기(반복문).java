import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * N개의 구슬과 (1~(N-1))
 * M개의 구슬 간 무게 비교에 대한 데이터를 받아
 * 중간의 무게를 가질 가능성이 없는 구슬의 개수는?
 * 
 * 반복문
 * 메모리 22540KB, 308ms 소요
 * DFS
 * 메모리 16772KB, 188ms 소요
*/

public class Main {
    static int N;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 구슬개수 홀수 N(1 ≤ N ≤ 99), 구슬간 무게 비교 M(1 ≤ M ≤ N(N-1)/2)
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<Integer>[] beadsWeightList = new HashSet[N + 1];
        for (int i = 0; i < N + 1; i++) {
            beadsWeightList[i] = new HashSet<Integer>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            beadsWeightList[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
        }
        // data input end

        // 구슬별(i) 더 가볍거나 무거운 구슬 수 체크
        int answer = 0;
        for (int i = 1; i < beadsWeightList.length; i++) {
            if (validWeight(beadsWeightList, i) > N / 2) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    private static int validWeight(HashSet<Integer>[] beadsWeightList, int beadsNumber) {
        boolean[] checkedBeads = new boolean[N + 1];
        Queue<Integer> checkQue = new ArrayDeque<>();

        // beadsNumber 보다 가벼운 개수
        int lessWeightCount = 0;
        checkQue.add(beadsNumber);
        while (!checkQue.isEmpty()) {
            int target = checkQue.poll();
            if (checkedBeads[target] == false) {
                if (target != beadsNumber) {
                    lessWeightCount++;
                }
                for (Integer i : beadsWeightList[target]) {
                    if (checkedBeads[i] == false) {
                        checkQue.add(i);
                    }
                }
            }
            checkedBeads[target] = true;
        }

        if (lessWeightCount > N / 2) {
            return lessWeightCount;
        }

        // beadsNumber 보다 무거운 개수
        checkedBeads = new boolean[N + 1];
        int moreWeightCount = 0;
        for (int i = 1; i < beadsWeightList.length; i++) {
            if (checkedBeads[i] == false && beadsWeightList[i].contains(beadsNumber)) {
                checkQue.add(i);
                while (!checkQue.isEmpty()) {
                    int target = checkQue.poll();
                    if (checkedBeads[target] == false) {
                        moreWeightCount++;
                        for (int j = 1; j < checkedBeads.length; j++) {
                            if (beadsWeightList[j].contains(target)) {
                                checkQue.add(j);
                            }
                        }
                    }
                    checkedBeads[target] = true;
                }
            }
        }

        return Math.max(lessWeightCount, moreWeightCount);
    }
}