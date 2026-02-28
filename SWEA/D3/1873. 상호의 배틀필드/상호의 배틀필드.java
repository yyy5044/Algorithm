import java.io.*;
import java.util.*;

public class Solution {
	static int H, W; // 맵 크기
	static char[][] map; // 맵
	static int N; // 사용자 입력 수
	static int[] cur_pos = new int[2]; // 전차의 현재 위치 (h, w)
	
	static int[] dh = {-1,1,0,0};
	static int[] dw = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			map = new char[H][W];
			for (int h = 0; h < H; h++) {
				String line = br.readLine();
				for (int w = 0; w < W; w++) {
					map[h][w] = line.charAt(w);
					if (map[h][w] == '^' || map[h][w] == 'v' || 
							map[h][w] == '<' || map[h][w] == '>') {
						cur_pos[0] = h; cur_pos[1] = w;
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			char[] input = new char[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				input[i] = line.charAt(i);
			}
			
			for (int i = 0; i < N; i++) {
				move(input[i]);
			}
			
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			
			
		}
		
		System.out.println(sb);
	}
	
	private static void shoot() {
		int h = cur_pos[0];
		int w = cur_pos[1];
		
		int nh = h; int nw = w; int d = -1;
		
		// 현재 전차의 기호를 보고 방향 결정
		if (map[h][w] == '^') d = 0; 
		else if (map[h][w] == 'v') d = 1;
		else if (map[h][w] == '<') d = 2;
		else if (map[h][w] == '>') d = 3;
		
		while(true) { // 포탄이 맵 밖으로 나갈 때까지 반복
			nh += dh[d]; nw += dw[d];
			
			if (nh < 0 || nw < 0 || nh > H-1 || nw > W-1) break;
			
			if (map[nh][nw] == '*') {
				map[nh][nw] = '.'; // 벽돌은 부숴져서 평지가 됨
				break;
			} else if (map[nh][nw] == '#') {
				break;
			}
		}
		
	}
	
	private static void turn(char action) {
		int h = cur_pos[0];
		int w = cur_pos[1];
		
		if (action == 'U') {
			map[h][w] = '^';
		} else if (action == 'D') {
			map[h][w] = 'v';
		} else if (action == 'L') {
			map[h][w] = '<';
		} else if (action == 'R') {
			map[h][w] = '>';
		} else if (action == 'S') {
			// nothing happen
		} else {
			// error
		}
	}
	
	private static void move(char action) {
		if (action == 'S') {
			shoot(); // 발사
			return;
		} 
		
		turn(action); // 먼저 전차를 턴 시키고 이동 수행
		
		int h = cur_pos[0];
		int w = cur_pos[1];
		
		int nh = h; int nw = w; int d = -1;
 		
		if (action == 'U') d = 0; 
		else if (action == 'D') d = 1;
		else if (action == 'L') d = 2;
		else if (action == 'R') d = 3;
		
		nh += dh[d]; nw += dw[d];
		
		if (nh >= 0 && nw >= 0 && nh < H && nw < W) { // 인덱스 방어
			if (map[nh][nw] == '.') { // 가려는 곳이 평지일 때만 이동
				map[nh][nw] = map[h][w]; // 현재 전차의 기호를 그대로 다음 위치에 그리기
				map[h][w] = '.'; // 현재 위치는 평지가 됨
				
				// 실제 위치까지 업데이트! -> 이동 완료
				cur_pos[0] = nh;
				cur_pos[1] = nw;
			}
		}
		
		
	}
}

