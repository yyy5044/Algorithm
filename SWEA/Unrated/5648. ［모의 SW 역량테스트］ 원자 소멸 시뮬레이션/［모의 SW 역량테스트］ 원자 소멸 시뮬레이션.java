import java.util.*;

import java.io.*;
class Atom { // 원자클래스: 좌표(x,y), 이동방향(d), 에너지(e)
	int x, y, dir, e;
	public Atom(int x, int y, int dir, int e) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.e = e;
	}
}

public class Solution {
	// 상 하 좌 우 -> 0 1 2 3
	static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};
	
	static ArrayDeque<Atom> list = new ArrayDeque<Atom>();
	static int totalCnt = 0; // 소멸한 원자 개수
	static int N; // 원자 개수, 초기화 필수
	static int[][] map = new int[4001][4001]; // 0부터 2000에서 2배하면 0부터 4000 -> 총 4001칸 필요;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			// INPUT: +1000(음수 보정), *2(시간 보정)
			N = Integer.parseInt(br.readLine());
			
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken())+1000)<<1;
				int y = (Integer.parseInt(st.nextToken())+1000)<<1;
				int dir = Integer.parseInt(st.nextToken());
				int e = Integer.parseInt(st.nextToken());
				
				map[y][x] = e;
				list.add(new Atom(x,y,dir,e));
			}
			
			while(!list.isEmpty()) {
				// 원자 꺼내기
				Atom atom = list.pollFirst();
				// 충돌 검사
				if (atom.e != map[atom.y][atom.x]) {
					// 충돌했을 때 처리(totalCnt 1 올리고 0으로 덮어쓰기)
					totalCnt += atom.e;
					map[atom.y][atom.x] = 0;
					continue;
				}
				
				// 충돌 안 했을 때 처리 (다음 위치 기록 후 addLast)
				map[atom.y][atom.x] = 0; // 원래 자리는 0으로 바꾸기
				int nx = atom.x + dx[atom.dir];
				int ny = atom.y + dy[atom.dir];
				
				if (nx >= 0 && ny >= 0 && nx < 4001 && ny < 4001) { // 배열 범위 안일 때만
					map[ny][nx] += atom.e; // 다음 자리에 원자 옮기기
					// 위치 업데이트 후 addLast로 deque에 추가
					atom.x = nx;
					atom.y = ny;
					list.addLast(atom);
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(totalCnt).append("\n");
			
			totalCnt = 0;
			N = 0;
		}
		
		System.out.println(sb);
	}
	
}
