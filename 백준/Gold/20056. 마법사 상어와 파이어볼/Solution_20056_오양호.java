import java.io.*;
import java.util.*;

public class Main {
	static int[] dr = {-1,-1,0,1,1,1,0,-1};
	static int[] dc = {0,1,1,1,0,-1,-1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 격자 크기 N*N
		int M = Integer.parseInt(st.nextToken()); // 초기 파이어볼 개수 M
		int K = Integer.parseInt(st.nextToken()); // 이동 명령 횟수
		
		ArrayDeque<Fireball> dq = new ArrayDeque<>(); // 큐
		// 초기 큐 삽입
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			
			dq.addLast(new Fireball(r, c, m, s, d));
		}
		
		for (int k = 0; k < K; k++) { // 명령 횟수만큼 반복
			int step = dq.size();
			
			ArrayList<Fireball>[][] temp = new ArrayList[N][N]; // 도화지 준비
			
			for (int i = 0; i < step; i++) { // 큐 사이즈만큼 반복
				Fireball cur = dq.pollFirst();
				
				// 다음 위치 계산: 속력*방향
				// nr = (현재위치 + 이동거리 + 보정값) % N
				int nr = (cur.r + (cur.s % N) * dr[cur.d] + N) % N;
				int nc = (cur.c + (cur.s % N) * dc[cur.d] + N) % N;
				
				if (temp[nr][nc] == null) { // null이면 ArrayList 채워주고
					temp[nr][nc] = new ArrayList<>();
				}
				temp[nr][nc].add(cur);
				
			}
			
			// 이동 마쳤으면 도화지 확인
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (temp[r][c] == null) continue; // null이면 스킵
					
					int size = temp[r][c].size(); // 같은 위치에 들어있는 파이어볼 개수
					if (size > 1) { // 파이어볼이 두 개 이상이라면
						// 합쳐진 파이어볼 질량의 합
						int totalM = 0;
						for (int i = 0; i < size; i++) {
							totalM += temp[r][c].get(i).m;
						}
						
						// 나누어진 파이어볼의 질량
						int fragM = (totalM / 5);
						
						if (fragM <= 0) continue; // 질량이 0이면 소멸되어 없어진다.
						
						// 합쳐진 파이어볼 속력의 합
						int totalS = 0;
						for (int i = 0; i < size; i++) {
							totalS += temp[r][c].get(i).s;
						}
						
						// 나누어진 파이어볼의 속력
						int fragS = (totalS / size);
						
						
						// 홀수인지 짝수인지 체크용
						boolean same = true;
						int mod = (temp[r][c].get(0).d)%2 ; 
						for (int i = 1; i < size; i++) {
							if ((temp[r][c].get(i).d)%2 != mod) {
								same = false;
							}
						}
						
						if (same) { // 모두 짝수 또는 홀수면: 이동방향 0, 2, 4, 6
							dq.addLast(new Fireball(r,c,fragM,fragS, 0));
							dq.addLast(new Fireball(r,c,fragM,fragS, 2));
							dq.addLast(new Fireball(r,c,fragM,fragS, 4));
							dq.addLast(new Fireball(r,c,fragM,fragS, 6));
						} else { // 그렇지 않으면:  이동방향 1, 3, 5, 7
							dq.addLast(new Fireball(r,c,fragM,fragS, 1));
							dq.addLast(new Fireball(r,c,fragM,fragS, 3));
							dq.addLast(new Fireball(r,c,fragM,fragS, 5));
							dq.addLast(new Fireball(r,c,fragM,fragS, 7));
						}
					} else { // 개별적인 파이어볼도 처리
						int m = temp[r][c].get(0).m;
						int s = temp[r][c].get(0).s;
						int d = temp[r][c].get(0).d;
						dq.addLast(new Fireball(r,c,m,s,d));
					}	
				}
			}
		}
		
		int ans = 0;
		while(!dq.isEmpty()) {
			Fireball cur = dq.pollFirst();
			ans += cur.m;
		}
		
		System.out.println(ans);
		
		// System.out.println(sb);
	}
	
}

class Fireball{
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
