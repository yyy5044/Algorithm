import java.io.*;
import java.util.*;

public class Solution {
	static int D, W, K;
	static int[][] map;
	static int minCnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[D][W];
			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
				// System.out.println(Arrays.toString(map[i]));
			}
			
			minCnt = Integer.MAX_VALUE;
			
			dfs(0, 0);
			
			sb.append("#").append(t).append(" ");
			sb.append(minCnt).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void dfs(int floor, int count) { // floor: 몇층인지, count: 약품 몇 번 넣었는지, currentMap: 전 선택으로 변경된 map
		
		// 호출되면 검사부터
		int checkCnt = 0;
		for (int i = 0; i < W; i++) {
			if (!check(i)) {
				break;
			} else {
				checkCnt++;
			}
		}
		
		if (checkCnt == W) { // 모든 열이 검사 통과하면 최소값 갱신 시도
			minCnt = Math.min(count, minCnt);
			return;
		}
		
		if (count > K) { // 약품 K번 넣으면 그만 봐도 됨
			minCnt = Math.min(K, minCnt);
			return;
		}
		
		if (floor == D) { // 최고층 도달했는데 위에 검사 실패시 걍 리턴
			return;
		}
		
		// 맵 원상복구 필수
		int[] copy = map[floor].clone();
		
		// 현재 층에 약품은 안 넣는 경우
		dfs(floor+1, count);
		
		// 현재 층에 0번 약품은 넣는 경우
		Arrays.fill(map[floor], 0);
		dfs(floor+1, count+1);
		
		// 현재 층에 1번 약품을 넣는 경우
		Arrays.fill(map[floor], 1);
		dfs(floor+1, count+1);
		
		for (int i = 0; i < W; i++) { // 값을 하나씩 다 바꿔야 함
		    map[floor][i] = copy[i];
		}
	}
	
	static boolean check(int c) { // column 인덱스 알려주면 해당 열 모든 rows 쭉 따라가며 같은 수가 K개 연속으로 있는지 확인
		int count = 1;
		
		boolean result = false;
		
		int digit = map[0][c];
		for (int i = 1; i < D; i++) {
			if (map[i][c] == digit) count++;
			else {
				digit = map[i][c];
				count = 1;
			}
			
			if (count >= K) {
				result = true;
				break;
			}
		}
		
		return result;
	}
	
}
