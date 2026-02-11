import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[] numbers;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		StringTokenizer st = new StringTokenizer(line);
		int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
		
		N = n; M = m;
		numbers = new int[M];
		visited = new boolean[N];
		
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) sb.append(numbers[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (visited[i]) continue;
			
			visited[i] = true;
			numbers[depth] = i + 1;
			dfs(depth+1);
			visited[i] = false;
		}
	}
}
