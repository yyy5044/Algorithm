import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N+1][N+1];
		for (int i = 1; i <= N; i++) { // N*N 행렬 받기
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[][] input = new int[M][4]; // x1, y1, x2, y2
		for (int i = 0; i < M; i++) { // 구간 합 좌표 받기
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] S = new int[N+1][N+1];
		
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				S[x][y] = map[x][y] + S[x-1][y] + S[x][y-1] - S[x-1][y-1];
			}
		}
		
		for (int i = 0; i < M; i++) {
			int x1 = input[i][0];
			int y1 = input[i][1];
			int x2 = input[i][2];
			int y2 = input[i][3];
			
			sb.append(S[x2][y2] - S[x1-1][y2] - S[x2][y1-1] + S[x1-1][y1-1]).append("\n");
		}
		
		System.out.println(sb);
		
	}
	
	
}
