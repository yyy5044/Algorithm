import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 배열 크기
		int M = Integer.parseInt(st.nextToken()); // 구간합 개수
		
		int[][] S = new int[N+1][N+1]; // 패딩 전략 -> N+1
		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= N; y++) {
				int xy = Integer.parseInt(st.nextToken());
				S[x][y] = xy + S[x][y-1] + S[x-1][y] - S[x-1][y-1];
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sb.append(S[x2][y2] - S[x2][y1-1] - S[x1-1][y2] + S[x1-1][y1-1]).append("\n");
		}
		
		System.out.println(sb);
	}
	
}
