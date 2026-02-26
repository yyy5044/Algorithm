import java.io.*;
import java.util.*;

public class Solution {
	static int[] dr = {0,-1,1,0,0}, dc = {0,0,0,-1,1}; // 상(1), 하(2), 좌(3), 우(4)
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken()); // 맵 크기: N*N
			int M = Integer.parseInt(st.nextToken()); // 격리 시간: 정답은 M시간 후의 남은 미생물 수
			int K = Integer.parseInt(st.nextToken()); // 군집 수
			
			map = new int[N][N]; // 초기 맵
			
			ArrayDeque<Microb> dq = new ArrayDeque<>(); // 미생물 큐
			
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				map[r][c] = num; // 맵에 미생물 그리기
				dq.addLast(new Microb(r, c, num, d)); // 큐에 미생물 넣기
			}

			int total_microbs = 0;
			for (int time = 0; time < M; time++) {
				int step = dq.size(); // 현재 큐에 있는 애들이 다 처리되면 1시간이 지남
				int[][][] temp = new int[N][N][3]; // [0]: 미생물 수, [1]: 가장 큰 군집, [2]: 우세 군집의 이동방향
				
				for (int i = 0; i < step; i++) {
					Microb cur = dq.pollFirst();
					int r = cur.r;
					int c = cur.c;
					int num = cur.num;
					int d = cur.d;
					
					// 1. 다음 위치 계산
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (nr>=0&&nc>=0&&nr<N&&nc<N) { // 인덱스 방어
						// I. 다음 위치가 가장자리인지 확인
						if (nr == 0 || nc == 0 || nr == N-1 || nc == N-1) {
							// I-I. 가장자리라면? 미생물 수 절반 감소 시키고 이동방향 수정
							num = num / 2;
							if (d==1) d = 2; // 상(1) -> 하(2) 
							else if (d==2) d=1; // 하(2) -> 상(1) 
							else if (d==3) d=4; // 좌(3) -> 우(4)
							else if (d==4) d=3; // 우(4) -> 좌(3)
						}
						
						// I-II. 가장자리가 아니라면? temp[][]에 미생물 이동 기록
						temp[nr][nc][0] += num;
						if (temp[nr][nc][1] < num) { // 가장 큰 놈이라면
							temp[nr][nc][1] = num; // 큰 놈이 누구였는지
							temp[nr][nc][2] = d; // 큰 놈의 이동방향
						}
					}	
				}
				// 모든 미생물 이동 처리 후,
				// temp에 저장된 대로 map 변경 및 큐 삽입
				for (int y = 0; y < N; y++) {
					for (int x = 0; x < N; x++) {
						if (temp[y][x][0] > 0) {
							dq.addLast(new Microb(y, x, temp[y][x][0], temp[y][x][2]));
						}
					}
				}
				
				if (time == M-1) { // 마지막에 큐에 남은 미생물 수를 다 더하기
					while(!dq.isEmpty()) {
						Microb cur = dq.pollFirst();
						total_microbs += cur.num;
					}
				}
			}
			
			sb.append("#").append(t).append(" ")
				.append(total_microbs).append("\n");
		}
		
		System.out.println(sb);
	}
	
}

class Microb {
	int r, c, num, d;
	public Microb(int r, int c, int num, int d) {
		this.r = r;
		this.c = c;
		this.num = num;
		this.d = d;
	}
}

