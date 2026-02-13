import java.io.*;
import java.util.*;

public class Solution {
	static int N, nC;
	static int[][] map, status;
	static ArrayList<Core> list = new ArrayList<>();
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	static int maxCore = Integer.MIN_VALUE;
	static int minLine = Integer.MAX_VALUE;
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			status = new int[N][N];
			for(int r = 0; r < N; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					int tmp = Integer.parseInt(st.nextToken());
					map[r][c] = tmp;
					status[r][c] = tmp;
					if(r == 0 || c == 0 || r == N-1 || c == N-1) continue; // 가장자리 프로세서 skip
					if(map[r][c] == 1) {
						list.add(new Core(r, c));
					}
				}
			}
			
			nC = list.size();
			
			setPower(0,0,0);
			
			sb.append("#").append(t+1).append(" ")
				.append(minLine).append("\n");
			
			maxCore = Integer.MIN_VALUE;
			minLine = Integer.MAX_VALUE;
			list.clear();
		}
		
		System.out.println(sb);
	}
	
	public static void setPower(int index, int coreCnt, int lenLine) {
		if(index == nC) { // 종료
			if (coreCnt > maxCore) {
				maxCore = coreCnt;
				minLine = lenLine;
			} else if (coreCnt == maxCore) {
				minLine = Math.min(minLine, lenLine);
			}
			
			return;
		}
		
		Core core = list.get(index);
		int r = core.r;
		int c = core.c;
		
		for (int d = 0; d < 4; d++) { // 4방향 전선 깔기 시도
			if(!isAvailable(r, c, d)) continue; // 불가능하면 다음 방향 시도
			
			// 가능하면 전선 설치
			int addLenLine = setLine(r,c,d,2);
			// 다음 코어 호출
			setPower(index+1, coreCnt+1, lenLine+addLenLine);
			// 백트래킹 했으면 전선 설치 취소
			setLine(r,c,d,0);
		}
		
		// 아무 방향도 안 되면 설치 포기하고 다음 코어 호출
		setPower(index+1, coreCnt, lenLine);
	}
	
	public static boolean isAvailable(int r, int c, int dir) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			if(nr >= 0 && nc>= 0 && nr < N && nc < N) { // 인덱스 범위 안에 있을 때만
				if (status[nr][nc] != 0) return false;
			} else {
				break;
			}
		}
		
		return true;
	}
	
	public static int setLine(int r, int c, int dir, int setting) {
		int nr = r;
		int nc = c;
		
		int lenLine = 0;
		while(true) {
			nr += dr[dir];
			nc += dc[dir];
			if(nr >= 0 && nc>= 0 && nr < N && nc < N) { // 인덱스 범위 안에 있을 때만
				status[nr][nc] = setting;
				lenLine++;
			} else {
				break;
			}
		}
		
		return lenLine;
	}

}

class Core{
	int r, c;
	public Core(int r, int c) {
		super();
		this.r = r;
		this.c = c;
	}
}