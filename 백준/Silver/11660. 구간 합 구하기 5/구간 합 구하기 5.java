import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] S = new int[N+1][N+1];
		for (int x = 1; x <= N; x++) {
			st = new StringTokenizer(br.readLine());
			for (int y = 1; y <= N; y++) {
				int xy = Integer.parseInt(st.nextToken());
				S[x][y] = S[x-1][y] + S[x][y-1] - S[x-1][y-1] + xy;
			}
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int result = S[x2][y2] - (S[x1-1][y2] + S[x2][y1-1] - S[x1-1][y1-1]);
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}
}
