import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> card = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            card.add(Integer.parseInt(br.readLine()));
        }

        int answer = 0;
        while (card.size() >= 2) {
            int temp = card.poll() + card.poll();
            answer += temp;
            card.add(temp);
        }

        System.out.println(answer);
    }
}
