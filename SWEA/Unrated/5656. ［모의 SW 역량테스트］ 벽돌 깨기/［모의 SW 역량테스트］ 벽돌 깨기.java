import java.io.*;
import java.util.*;

public class Solution {
	static int N, W, H;
	static int[][] map, copy;
	static int[] dh = {-1,1,0,0}, dw = {0,0,-1,1};
	static int[] perm;
	static int min_blocks = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			map = new int[H][W];
			perm = new int[N];
			
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					map[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			copy = new int[H][W];
			for (int i = 0; i < H; i++) {
				copy[i] = map[i].clone();
			}
			
			dfs(0);
			
			sb.append("#").append(t).append(" ")
				.append(min_blocks).append("\n");
            min_blocks = Integer.MAX_VALUE;
		}
		
		System.out.println(sb);
	}
	
	private static void dfs(int depth) {
		if(depth==N) { // N개의 숫자를 선택하면 종료
			for (int w:perm) { // 순서대로 터뜨리기
				int[] start = new int[2];
				for (int h = 0; h < H; h++) {
					if (map[h][w] != 0) {
						start[0] = h;
						start[1] = w;
						break;
					}
				}
				
				bfs(start);
				gravity();
			}
			
			// 남은 벽돌의 개수를 세어보고
			int count = 0;
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					if (map[h][w]!=0) count++;
				}
			}
			
			for (int i = 0; i < H; i++) {
				map[i] = copy[i].clone();
			}
			
			// 지금까지중에서 가장 적은 벽돌이면 업데이트
			min_blocks = Math.min(min_blocks, count);
			
			return;
		}
		
		for(int i = 0; i < W; i++) { // 고를 수 있는 숫자는 0부터 W까지
			perm[depth] = i;
			dfs(depth+1);
		}
	}
	
	private static void gravity() {
		for (int i = 0; i < W; i++) {
			int tmp = 0;
			int index = H-1;
			for (int j = H-1; j >= 0; j--) {
				if(map[j][i] != 0) { // 현재 확인하고 있는 위치가 0이 아니면
					tmp = map[j][i]; // 벽돌 번호 잠시 적어두고
					map[j][i] = 0; // 현재 위치 지우기
					map[index][i] = tmp; // index 위치에 쓰기
					index--;
				}
			}
		}
	}
	
	private static void bfs(int[] start) {
		int start_h = start[0];
		int start_w = start[1];
		
		ArrayDeque<int[]> dq = new ArrayDeque<>();
		dq.addLast(new int[] {start_h, start_w}); // 시작 좌표 삽입
		
		while(!dq.isEmpty()) {
			int[] cur = dq.pollFirst();
			int cur_h = cur[0];
			int cur_w = cur[1];
			
			int range = map[cur_h][cur_w]-1; // 2의 좌표에는 값이 2이므로 범위를 알 수 있다.
			for (int d = 0; d < 4; d++) { // 상 하 좌 우 동일하게 적용
				int nh = cur_h;
				int nw = cur_w;
				// 해당 방향으로 범위만큼 탐색
				for (int i = 0; i < range; i++) {
					nh += dh[d]; // nh, hw에 delta 적용
					nw += dw[d]; // 2에서 상 하 좌 우 중 한 칸 나아간 좌표 획득			
					if (nh < 0 || nw < 0 || nh > H-1 || nw > W-1) continue; // 인덱스 방어
					if (map[nh][nw] == 0) continue; // 범위에 포함되어도 0이면 큐에 넣지 않는다
					dq.addLast(new int[] {nh, nw}); // 자기 범위 내의 상 하 좌 우 중 0이 아닌 것들의 좌표 큐에 삽입
				}
			}
			
			map[cur_h][cur_w] = 0; // 다 끝났으면 자기 위치는 0으로 만들기
		}
	}
}
