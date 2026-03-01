import java.io.*;
import java.util.*;

// 54분 21초
public class Main {
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 맵 크기
		int M = Integer.parseInt(st.nextToken()); // 초기 파이어볼 개수
		int K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		
		ArrayDeque<Fireball> dq = new ArrayDeque<>();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine()); // 각 파이어볼 객체 정보 읽기
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			dq.add(new Fireball(r, c, m, s, d));
		}
		
		
		
		for (int k = 0; k < K; k++) {
			ArrayList<Fireball>[][] temp = new ArrayList[N][N]; // 초기에는 모든 칸에 null
			while(!dq.isEmpty()) { // 큐에서 꺼내서 이동 처리
				Fireball cur = dq.poll();
				
				int r = cur.r;
				int c = cur.c;
				int m = cur.m;
				int s = cur.s;
				int d = cur.d;
				
				int nr = (r + (s%N)*dr[d] + N) % N;
				int nc = (c + (s%N)*dc[d] + N) % N;
				
				if (temp[nr][nc] == null) {
					temp[nr][nc] = new ArrayList<>();
				}
				
				temp[nr][nc].add(new Fireball(nr, nc, m, s, d));
			}
			
			// 이동 완료된 정보를 temp에서 읽으면서 이동 완료 후 처리를 여기서 진행
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (temp[r][c] == null) continue; // null인 건 스킵
					
					if (temp[r][c].size() == 1) {
						dq.add(temp[r][c].get(0)); // 파이어볼 한 개면 그냥 큐에 넣기
						continue;
					}
					
					// 파이어볼이 2개 이상인 경우는 여기서 처리
					int totalN = temp[r][c].size(); // 모여있는 파이어볼 개수
					
					int totalM = 0; // 합쳐진 파이어볼 질량 합
					for (int i = 0; i < totalN; i++) {
						totalM += temp[r][c].get(i).m;
					}
					
					int fragM = totalM / 5; // 나누어진 파이어볼 질량
					if (fragM <= 0) continue; // 질량이 0이면 소멸
					
					int totalS = 0; // 합쳐진 파이어볼 속력 합
					for (int i = 0; i < totalN; i++) {
						totalS += temp[r][c].get(i).s;
					}
					
					int fragS = totalS / totalN; // 나누어진 파이어볼 속력
					
					boolean allSame = true;
					int re = (temp[r][c].get(0).d) % 2; // 홀수면 1, 짝수면 0
					for (int i = 1; i < totalN; i++) {
						if ((temp[r][c].get(i).d) % 2 != re) { // 다르면
							allSame = false;
							break;
						}
					}
					

					// 다 되면 다시 큐에 넣는 작업까지!
					if (allSame) {
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
				}
			}
		}
	
		int ans = 0;
		while (!dq.isEmpty()) {
			Fireball cur = dq.poll();
			ans += cur.m;
		}
		
		System.out.println(ans);
		
	}

}

class Fireball {
	int r, c, m, s, d;
	public Fireball(int r, int c, int m, int s, int d) {
		super();
		this.r = r;
		this.c = c;
		this.m = m;
		this.s = s;
		this.d = d;
	}
}