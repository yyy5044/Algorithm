import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[] S = new int[N+1]; // 1번 인덱스부터
		
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int x = Integer.parseInt(st.nextToken());
			S[i] = x + S[i-1];
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			
			sb.append(S[x2] - S[x1-1]).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
}
