import java.util.*;
import java.io.*;

public class Solution {
	static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};
	static ArrayDeque<Atom> dq = new ArrayDeque<Atom>();
	static int[][] map = new int[4001][4001];
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {		
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)*2;
				int y = (Integer.parseInt(st.nextToken())+1000)*2;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				dq.addLast(new Atom(x,y,dir,e));
				map[y][x] = e;
			}
			
			int totalEnergy = 0;
			
			while(!dq.isEmpty()) {
				Atom atom = dq.pollFirst();
				int x = atom.x;
				int y = atom.y;
				int dir = atom.dir;
				int e = atom.e;
				
				// System.out.println(x + " " + y + " " + dir + " " + e);
				
				// 충돌 검사
				if (map[y][x] != e) { // 충돌이 났다면
					totalEnergy += map[y][x];
					map[y][x] = 0; // 소멸
					continue;
				}
				
				// 충돌이 안 났다면
				// 진행 방향의 다음 위치
				map[y][x] = 0; // 현재 위치는 0으로
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				
				// 다음 위치에 에너지 옮기기
				if (nx >= 0 && ny >= 0 && nx <= 4000 && ny <= 4000) {
					map[ny][nx] += e; // 누적으로 해야 충돌 감지 가능
					dq.addLast(new Atom(nx, ny, dir, e));
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(totalEnergy).append("\n");
		}
		
		System.out.println(sb);
	}
	
}

class Atom {
	int x,y,dir,e;

	public Atom(int x, int y, int dir, int e) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.e = e;
	}
}
