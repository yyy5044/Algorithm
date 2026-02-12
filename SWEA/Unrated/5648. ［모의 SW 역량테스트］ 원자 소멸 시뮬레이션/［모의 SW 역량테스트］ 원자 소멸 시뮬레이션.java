import java.io.*;
import java.util.*;

public class Solution {
	static int[][] map = new int[4001][4001];
	static ArrayDeque<Atom> dq = new ArrayDeque<>(); // 원자를 넣을 DQ
	static int totalEnergy = 0;
	static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine()); // 원자 개수
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)<<1;
				int y = (Integer.parseInt(st.nextToken())+1000)<<1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				map[y][x] = e;
				dq.add(new Atom(x,y,dir,e));
			}
			
			while(!dq.isEmpty()) {
				Atom cur = dq.pollFirst(); // 원자 하나 빼기
				
				// 충돌 감지: 맵의 기록된 에너지와 내 에너지가 다르다 -> 충돌
				if (map[cur.y][cur.x] != cur.e) { 
					totalEnergy += map[cur.y][cur.x]; // 에너지 기록
					map[cur.y][cur.x] = 0; // 소멸
					continue;
				}
				
				// 충돌 미감지: 다음 위치로 이동
				map[cur.y][cur.x] = 0; // 현재 위치 0으로 기록
				int nx = cur.x + dx[cur.dir];
				int ny = cur.y + dy[cur.dir];
				
				if(nx>=0&&ny>=0&&nx<4001&&ny<4001) { // 범위 안에 있을 때만
					// 다음 위치로 업데이트
					cur.x = nx;
					cur.y = ny;
					map[ny][nx] += cur.e; // 다음 위치에 에너지 누적
					dq.addLast(cur);
				}
			}
			sb.append("#").append(t+1).append(" ")
				.append(totalEnergy).append("\n");
			
			totalEnergy = 0;
		}
		
		System.out.println(sb);
	}
	
	
}

class Atom{
	int x,y,dir,e;
	public Atom(int x, int y, int dir, int e) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.e = e;
	}
}