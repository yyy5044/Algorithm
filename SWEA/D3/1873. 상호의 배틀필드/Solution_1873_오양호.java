import java.util.*;
import java.io.*;

public class Solution {
	static int H, W, N;
	static char[][] map;
	static char[] input;
	static int[] cur_pos;
	static int[] dh = {-1,1,0,0}, dw = {0,0,-1,1}; // 순서대로: 상 하 좌 우
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			sb.append("#").append(t+1).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][W];
			
			for (int h = 0; h < H; h++) {
				String line = br.readLine();
				for (int w = 0; w < W; w++) {
					map[h][w] = line.charAt(w);
					if (map[h][w] == '^' || map[h][w] == 'v' || map[h][w] == '<' || map[h][w] == '>') {
						cur_pos = new int[] {h, w};
					}
				}
			}
			
			N = Integer.parseInt(br.readLine());
			input = new char[N];
			String line = br.readLine();
			for (int i = 0; i < N; i++) {
				input[i] = line.charAt(i);
			}
			
			for (int i = 0; i < N; i++) { // 입력 읽기
				char action = input[i];
				move(action);
			}
			
			// 결과 출력 (테스트)
			for (int h = 0; h < H; h++) {
				for (int w = 0; w < W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		
		System.out.println(sb);
	}
	
	private static void shoot() {
		int h = cur_pos[0];
		int w = cur_pos[1];
	
		int d = -1; // 방향 벡터
		// 보고 있는 방향에 따른 방향 벡터 설정
		if (map[h][w] == '^') { 
			d = 0;
		} else if (map[h][w] == 'v') {
			d = 1;
		} else if (map[h][w] == '<') {
			d = 2;
		} else if (map[h][w] == '>') {
			d = 3;
		}
		
		int nh = h;
		int nw = w;
		while(true) {
			nh += dh[d];
			nw += dw[d];
			
			if (!isIn(nh, nw)) break; // 맵 밖으로 나가면 계산 중지
			
			if (map[nh][nw] == '*') { // 벽돌 벽이면
				map[nh][nw] = '.'; // 평지가 됨
				break; // 포탄 소멸, 계산 중지
			} else if (map[nh][nw] == '#') { // 강철 벽이면
				// 아무 일도 일어나지 않음
				break; // 포탄 소멸, 계산 중지
			}
		}
	}
	
	private static void move(char c) {
		int h = cur_pos[0];
		int w = cur_pos[1];
		
		if (c == 'U') {
			int nh = h - 1;
			int nw = w;
			
			if (isIn(nh, nw)) {
				if (map[nh][nw] == '.') { // 평지라면, 위치 이동
					map[h][w] = '.'; 
					map[nh][nw] = '^';
					
					// 현재 위치 수정
					cur_pos[0] = nh;
					cur_pos[1] = nw;
				} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
					map[h][w] = '^';
					
					// 현재 위치 수정
					cur_pos[0] = h;
					cur_pos[1] = w;
				}
			} else {
				map[h][w] = '^';
				
				// 현재 위치 수정
				cur_pos[0] = h;
				cur_pos[1] = w;
			}
		} else if (c == 'D') {
			int nh = h + 1;
			int nw = w;
			
			if (isIn(nh, nw)) {
				if (map[nh][nw] == '.') { // 평지라면, 위치 이동
					map[h][w] = '.'; 
					map[nh][nw] = 'v';
					
					// 현재 위치 수정
					cur_pos[0] = nh;
					cur_pos[1] = nw;
				} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
					map[h][w] = 'v';
					
					// 현재 위치 수정
					cur_pos[0] = h;
					cur_pos[1] = w;
				}
			} else {
				map[h][w] = 'v';
				
				// 현재 위치 수정
				cur_pos[0] = h;
				cur_pos[1] = w;
			}
		} else if (c == 'L') {
			int nh = h;
			int nw = w - 1;
			
			if (isIn(nh, nw)) {
				if (map[nh][nw] == '.') { // 평지라면, 위치 이동
					map[h][w] = '.'; 
					map[nh][nw] = '<';
					
					// 현재 위치 수정
					cur_pos[0] = nh;
					cur_pos[1] = nw;
				} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
					map[h][w] = '<';
					
					// 현재 위치 수정
					cur_pos[0] = h;
					cur_pos[1] = w;
				}
			} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
				map[h][w] = '<';
				
				// 현재 위치 수정
				cur_pos[0] = h;
				cur_pos[1] = w;
			}
		} else if (c == 'R') {
			int nh = h;
			int nw = w + 1;
			if (isIn(nh, nw)) {
				if (map[nh][nw] == '.') { // 평지라면, 위치 이동
					map[h][w] = '.'; 
					map[nh][nw] = '>';
					
					// 현재 위치 수정
					cur_pos[0] = nh;
					cur_pos[1] = nw;
				} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
					map[h][w] = '>';
					
					// 현재 위치 수정
					cur_pos[0] = h;
					cur_pos[1] = w;
				}
			} else { // 평지가 아니면, 전차가 바라보는 방향만 수정
				map[h][w] = '>';
				
				// 현재 위치 수정
				cur_pos[0] = h;
				cur_pos[1] = w;
			}
		} else if (c == 'S') {
			shoot();
		}
	}
	
	private static boolean isIn(int h, int w) {
		return (h >= 0 && h < H && w >= 0 && w < W);
	}
}
