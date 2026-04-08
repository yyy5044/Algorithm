import java.io.*;
import java.util.*;

public class Solution {
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 2차원 배열 만들기
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			
			// [전처리]: 무게가 0일 때랑 고려하는 아이템이 0번째부터 0번째면 아무것도 안 넣는 거니까 다 0으로 채워준다.
			int[][] K = new int[N+1][W+1];
			int[][] items = new int[N+1][2];
			
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				int weight = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				items[i][0] = weight;
				items[i][1] = value;
			}
			
			for (int i = 1; i <= N; i++) {
				for (int w = 1; w <= W; w++) {
					int w_i = items[i][0];
					int v_i = items[i][1];
					
					if (w_i > w) { // 1. 현재 고려하고 있는 아이템의 무게 wi가 현재 배낭의 무게 한도 w보다 큰지 확인 
						K[i][w] = K[i-1][w]; // -> wi>w이면 i번째 아이템은 못 넣음 -> K[i,w] = K[i-1,w]
					} else {
						// -> wi<=w이면 i번째 아이템을 넣는 것과 넣지 않는 것 중에 따져볼 수 있음 -> K[i,w] = max(vi + K[i-1, w-wi], K[i-1,w])
						K[i][w] = Math.max(v_i + K[i-1][w-w_i], K[i-1][w]); // * i번째 물건을 넣기로 했으면 w-wi 무게 내에서 고려해야 하는게 킥임.
					}
				}
			}
			
			// 2. 다 채운 테이블의 맨 끝이 답 (무게 W인 배낭에서 0부터 N번까지 모든 아이템들을 고려했을 때의 최적 K[N, W])
			sb.append("#").append(t).append(" ");
			sb.append(K[N][W]).append("\n");
		}
		
		System.out.println(sb);
	}
	
	
}
