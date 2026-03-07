import java.io.*;
import java.util.*;

public class Solution {
	static int H, W;
	static char[][] map;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int[] cur_pos = new int[] {-1, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();	
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken()); // 높이
			W = Integer.parseInt(st.nextToken()); // 너비
			
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
			
			int N = Integer.parseInt(br.readLine()); // 사용자 입력 수
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				char action = line.charAt(i);
				move(action);
			}
			
			
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
			
			cur_pos = new int[] {-1, -1};
		}
		
		System.out.println(sb);
	}
	
	private static void shoot() {
		int d = -1;
		
		if (map[cur_pos[0]][cur_pos[1]] == '^') d = 0;
		else if (map[cur_pos[0]][cur_pos[1]] == 'v') d = 1;
		else if (map[cur_pos[0]][cur_pos[1]] == '<') d = 2;
		else if (map[cur_pos[0]][cur_pos[1]] == '>') d = 3;
		
		int nr = cur_pos[0];
		int nc = cur_pos[1];
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			
			if (nr<0 || nc<0 || nr>H-1 || nc>W-1) break;
			
			if (map[nr][nc] == '*') {
				map[nr][nc] = '.';
				break;
			} else if (map[nr][nc] == '#') break;
		}
	}
	
	private static void turn(char action) {
		if (action == 'U') {
			map[cur_pos[0]][cur_pos[1]] = '^';
		} else if (action == 'D') {
			map[cur_pos[0]][cur_pos[1]] = 'v';
		} else if (action == 'L') {
			map[cur_pos[0]][cur_pos[1]] = '<';
		} else if (action == 'R') {
			map[cur_pos[0]][cur_pos[1]] = '>';
		}
	}
	
	private static void move(char action) {
		// action이 'S'면 shoot() 호출
		if (action == 'S') {
			shoot();
			return;
		}
		
		// action에 맞는 턴 먼저
		turn(action);
		
		int d = -1; // 방향
		
		// 실제 이동 처리
		if (action == 'U') d = 0;
		else if (action == 'D') d = 1;
		else if (action == 'L') d = 2;
		else if (action == 'R') d = 3;
		
		int nr = cur_pos[0] + dr[d];
		int nc = cur_pos[1] + dc[d];
		
		if (nr>=0&&nc>=0&&nr<H&&nc<W) {
			if (map[nr][nc] == '.') {
				map[nr][nc] = map[cur_pos[0]][cur_pos[1]]; // 다음 위치에 현재 탱크 그림 그리기
				map[cur_pos[0]][cur_pos[1]] = '.'; // 현재 위치는 평지로 다시 그리기
				
				// 현재 위치는 다음 위치로 업데이트
				cur_pos[0] = nr;
				cur_pos[1] = nc;
			}
		}
		
	}

}

