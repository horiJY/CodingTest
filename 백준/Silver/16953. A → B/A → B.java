import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        long startNum = Long.parseLong(st.nextToken());
        long targetNum = Long.parseLong(st.nextToken());
        Deque<Simulation> que = new ArrayDeque<>();
        que.add(new Simulation(startNum, 1));
        while (!que.isEmpty()) {
            Simulation cur = que.pop();
            if (cur.num == targetNum) {
                System.out.println(cur.cycle);
                que.add(cur);
                break;
            }
            if (cur.num * 2 <= targetNum) {
                que.add(new Simulation(cur.num * 2, cur.cycle + 1));
            }

            if (Long.parseLong(cur.num + "1") <= targetNum) {
                que.add(new Simulation(Long.parseLong(cur.num + "1"), cur.cycle + 1));
            }
        }

        if (que.isEmpty()) {
            System.out.println(-1);
        }
    }
}

class Simulation {
    long num;
    int cycle;

    public Simulation(long num, int cycle) {
        this.num = num;
        this.cycle = cycle;
    }
}
