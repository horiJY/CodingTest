import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] answer = new int[N][M];
		Queue<int[]> queue = new ArrayDeque<>();
		for(int i=0;i<N;i++){
			st = new StringTokenizer(in.readLine());
			for(int j=0;j<M;j++){
				int num = Integer.parseInt(st.nextToken());
				if(num!=1) answer[i][j] = 0;
				else answer[i][j] = -1;
				if(num==2) queue.offer(new int[]{i,j});
			}
		}

		int[][] deltas = {{1,0},{-1,0},{0,1},{0,-1}};
		int[] node;
		int ni,nj;
		while(!queue.isEmpty()){
			node = queue.poll();
			for(int[] d:deltas){
				ni = node[0]+d[0];
				nj = node[1]+d[1];
				if(ni<0||nj<0||ni>=N||nj>=M||answer[ni][nj]!=-1) continue;
				answer[ni][nj] = answer[node[0]][node[1]]+1;
				queue.offer(new int[]{ni,nj});
			}
		}

		StringBuilder sb = new StringBuilder();
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				sb.append(answer[i][j]).append(' ');
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}
}