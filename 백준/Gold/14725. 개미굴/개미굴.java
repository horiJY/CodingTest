import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        /**
         * TreeMap is a raw type. References to generic type TreeMap<K,V> should be
         * parameterized 어떻게 처리해야 할까..
         */
        Map<String, TreeMap> answer = new TreeMap<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int n = 0; n < N; n++) {
            String[] data = br.readLine().split(" ");
            if (answer.get(data[1]) == null) {
                answer.put(data[1], new TreeMap<>());
            }

            if (data.length > 2) {
                setChildren(answer.get(data[1]), Arrays.copyOfRange(data, 2, data.length));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Entry<String, TreeMap> entry : answer.entrySet()) {
            sb.append(entry.getKey()).append(System.lineSeparator());
            getChildren(sb, entry.getValue(), 1);
        }
        System.out.println(sb);
    }

    private static void getChildren(StringBuilder sb, TreeMap<String, TreeMap> child, int depth) {

        for (Entry<String, TreeMap> entry : child.entrySet()) {
            for (int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(entry.getKey()).append(System.lineSeparator());
            if (entry.getValue().size() > 0) {
                getChildren(sb, entry.getValue(), depth + 1);
            }
        }
    }

    private static void setChildren(TreeMap<String, TreeMap> answer, String[] data) {
        if (data.length > 0) {
            if (answer.get(data[0]) == null) {
                answer.put(data[0], new TreeMap<>());
            }
            setChildren(answer.get(data[0]), Arrays.copyOfRange(data, 1, data.length));
        }
    }
}
