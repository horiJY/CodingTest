import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static HashMap<String, HashSet<String>> folders;
    static HashMap<String, HashSet<String>> files;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // 폴더의 총 개수 N (1 <= N <= 1000)
        int N = Integer.parseInt(input[0]);
        // 파일의 총 개수 M (1 <= M <= 1000)
        int M = Integer.parseInt(input[1]);
        // 상위 폴더 이름 P, 폴더 또는 파일의 이름 F(1<= P,F <= 10),
        // 폴더인지 아닌지 알려주는 C (1 <= C <= 1)
        folders = new HashMap<>();
        folders.put("main", new HashSet<>());
        files = new HashMap<>();
        files.put("main", new HashSet<>());
        for (int i = 0; i < N + M; i++) {
            input = br.readLine().split(" ");
            if (input[2].equals("1")) { // 받은 입력이 폴더일 경우
                if (!folders.containsKey(input[0])) {
                    folders.put(input[0], new HashSet<>());
                }
                if (!folders.containsKey(input[1])) {
                    folders.put(input[1], new HashSet<>());
                }
                folders.get(input[0]).add(input[1]);
            } else {
                if (!files.containsKey(input[0])) {
                    files.put(input[0], new HashSet<>());
                }
                files.get(input[0]).add(input[1]);
            }
        }
        // data input end

        // move File
        // 옮기는 횟수 K (0 <= K <= 1000)
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            input = br.readLine().split(" ");
            moveFiles(input);
        }

        // print file count
        // 쿼리의 개수 Q(1 <= Q <= 1000)
        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            input = br.readLine().split("/");
            printFiles(input);
        }
        System.out.println(sb);
    }

    /**
     * 입력받은 path부터 하위까지 파일 개수 출력
     * 
     * @param path 파일 개수 출력할 루트 패스
     */
    private static void printFiles(String[] path) {
        int fileCount = 0;
        HashSet<String> fileKind = new HashSet<>();
        Deque<String> que = new ArrayDeque<>();

        que.add(path[path.length - 1]);
        while (!que.isEmpty()) {
            if (folders.containsKey(que.peekFirst())) {
                for (String folder : folders.get(que.peekFirst())) {
                    que.add(folder);
                }
            }

            String curFolder = que.pollFirst();
            if (files.containsKey(curFolder)) {
                for (String file : files.get(curFolder)) {
                    fileKind.add(file);
                    fileCount++;
                }
            }
        }

        sb.append(fileKind.size()).append(' ').append(fileCount).append('\n');
    }

    /**
     * white space로 구분된 2개의 path를 받아 좌측 path부터 하위 경로전체를 우측path로 옮긴다.
     * 
     * e.g. main/FolderB main/FolderA
     * main/FolderB -> main/FolderA
     * 
     * @param input [PathA, PathB]
     */
    private static void moveFiles(String[] input) {
        String[] targetPath = input[0].split("/");
        String[] destPath = input[1].split("/");
        String target = targetPath[targetPath.length - 1];
        String dest = destPath[destPath.length - 1];

        if (!folders.containsKey(dest)) {
            folders.put(dest, new HashSet<>());
        }
        if (!files.containsKey(dest)) {
            files.put(dest, new HashSet<>());
        }
        if (!folders.containsKey(target)) {
            folders.put(target, new HashSet<>());
        }
        if (!files.containsKey(target)) {
            files.put(target, new HashSet<>());
        }

        // target 하위 폴더를 dest로 옮김
        folders.get(dest).addAll(folders.get(target));
        // target 파일을 dest로 옮김
        files.get(dest).addAll(files.get(target));
        // target의 상위 경로 연결 제거
        folders.get(targetPath[targetPath.length - 2]).remove(target);
    }
}