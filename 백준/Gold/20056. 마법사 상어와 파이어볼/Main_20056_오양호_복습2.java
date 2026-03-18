import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static int[][] deltas = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
	
	static class Fireball {
		int r, c, m, s, d;

		public Fireball(int r, int c, int m, int s, int d) {
			super();
			this.r = r;
			this.c = c;
			this.m = m;
			this.d = d;
			this.s = s;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 배열 크기
		M = Integer.parseInt(st.nextToken()); // 파이어볼 개수
		K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		
		ArrayDeque<Fireball> dq = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			dq.add(new Fireball(r, c, m, s, d));
		}
		
		for (int i = 0; i < K; i++) { // 이동 명령만큼 수행
			ArrayList<Fireball>[][] temp = new ArrayList[N][N];
			
			while(!dq.isEmpty()) {
				Fireball cur = dq.poll();
				int r = cur.r, c = cur.c, m = cur.m, s = cur.s, d = cur.d;
				
				int nr = (r + (s % N)*deltas[d][0] + N) % N;
				int nc = (c + (s % N)*deltas[d][1] + N) % N;
				
				if (temp[nr][nc] == null) {
					temp[nr][nc] = new ArrayList<>();
				}
				
				temp[nr][nc].add(new Fireball(nr, nc, m, s, d));
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (temp[r][c] == null) continue;
					
					int numOfFB = temp[r][c].size();
					
					if (numOfFB > 1) { // 여러 파이어볼이 겹쳤을 때 처리
						int totalM = 0;
						for (int j = 0; j < numOfFB; j++) {
							totalM += temp[r][c].get(j).m;
						}
						
						int totalS = 0;
						for (int j = 0; j < numOfFB; j++) {
							totalS += temp[r][c].get(j).s;
						}
						
						int fragM = totalM / 5;
						
						if (fragM == 0) continue; // 질량이 0이면 소멸
						
						int fragS = totalS / numOfFB;
						
						boolean isAllSame = true;
						int remain = temp[r][c].get(0).d % 2;
						for (int j = 1; j < numOfFB; j++) {
							if (temp[r][c].get(j).d % 2 != remain) {
								isAllSame = false;
							}
						}
						
						if (isAllSame) {
							dq.add(new Fireball(r, c, fragM, fragS, 0));
							dq.add(new Fireball(r, c, fragM, fragS, 2));
							dq.add(new Fireball(r, c, fragM, fragS, 4));
							dq.add(new Fireball(r, c, fragM, fragS, 6));
						} else {
							dq.add(new Fireball(r, c, fragM, fragS, 1));
							dq.add(new Fireball(r, c, fragM, fragS, 3));
							dq.add(new Fireball(r, c, fragM, fragS, 5));
							dq.add(new Fireball(r, c, fragM, fragS, 7));
						}
						
					} else { // 파이어볼이 하나일 때는 그냥 그대로 큐에 다시 넣기
						dq.add(temp[r][c].get(0));
					}
				}
			}
			
		}
		
		int ans = 0;
		while(!dq.isEmpty()) {
			Fireball cur = dq.poll();
			ans += cur.m;
		}
		
		System.out.println(ans);
	}
}
