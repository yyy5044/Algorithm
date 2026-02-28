import java.io.*;
import java.util.*;

public class Solution {
	// 입력
	static int N, M, K;
	
	// 방향 벡터: 상하좌우
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 격자 크기 N*N
			M = Integer.parseInt(st.nextToken()); // 격리 시간
			K = Integer.parseInt(st.nextToken()); // 미생물 군집 개수
			
			ArrayDeque<Microb> dq = new ArrayDeque<>();
			
			for (int k = 0; k < K; k++) { // 초기 큐 삽입
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int n = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken())-1;
				
				dq.addLast(new Microb(r, c, n, d));
			}
			
			for (int time = 0; time < M; time++) { // 격리 시간만큼 반복
				int[][][] temp = new int[N][N][3]; // [0]: 미생물 수, [1]: 가장 큰 군집, [2]: 이동방향
				
				while(!dq.isEmpty()) { // 큐에서 하나씩 꺼낸 후 이동을 temp 기록
					Microb cur = dq.pollFirst();
					int r = cur.r;
					int c = cur.c;
					int n = cur.n;
					int d = cur.d;
					
					int nr = r + dr[d];
					int nc = c + dc[d];
					
					if (nr < 0 || nc < 0 || nr > N-1 || nc > N-1) continue;
					
					temp[nr][nc][0] += n; // 미생물 누적
					
					if (temp[nr][nc][1] < n) { // 지금껏 이 위치에 왔던 미생물보다 내 미생물 수가 더 크면
						temp[nr][nc][1] = n; // 제일 큰 놈 기록
						temp[nr][nc][2] = d; // 제일 큰 놈의 이동방향으로 갱신
					}
				}
				
				for (int r = 0; r < N; r++) { // 기록된 temp로 일괄 처리
					for (int c = 0; c < N; c++) {
						int n = temp[r][c][0];
						int d = temp[r][c][2];
						
						if (r == 0 || c == 0 || r == N-1 || c == N-1) { // 가장자리에 있는 애들은 약품 처리
							n = n / 2; // 절반 감소
							
							if (d==0) d=1; // 상 -> 하
							else if (d==1) d=0; // 하 -> 상
							else if (d==2) d=3; // 좌 -> 우
							else if (d==3) d=2; // 우 -> 좌
						}
						
						dq.addLast(new Microb(r, c, n, d));
					}
				}
			}
			
			int ans = 0;
			while(!dq.isEmpty()) { // 큐에 남아 있는 미생물 수 세기
				Microb cur = dq.pollFirst();
				ans += cur.n;
			}
			
			sb.append("#").append(t).append(" ")
				.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
	
}

class Microb {
	int r, c, n, d;
	public Microb(int r, int c, int n, int d) {
		super();
		this.r = r; this.c = c; this.n = n; this.d = d;
	}
}
