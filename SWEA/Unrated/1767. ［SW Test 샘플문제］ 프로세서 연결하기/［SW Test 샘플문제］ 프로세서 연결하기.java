import java.io.*;
import java.util.*;

public class Solution {
	static int N, nP;
	static int[][] map, status;
	static ArrayList<int[]> corePosition = new ArrayList<>(); // 가장자리가 아닌 코어의 위치
	
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	
	static int maxCore = Integer.MIN_VALUE;
	static int minLine = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = status = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = status[i][j] = Integer.parseInt(st.nextToken());
					if (i == 0 || j == 0 || i == N-1 || j == N-1) continue; // 가장자리 스킵
					if(map[i][j] == 1) { 
						corePosition.add(new int[] {i, j}); // 가장자리가 아닌 코어의 위치 저장
						nP++; // 가장자리가 아닌 코어 개수 +1 
					}
				}
			}
			
			getPower(0,0,0);
			
			sb.append("#").append(t+1).append(" ")
				.append(minLine).append("\n");
			
			corePosition.clear();;
			maxCore = Integer.MIN_VALUE;
			minLine = Integer.MAX_VALUE;
			N = 0; 
			nP = 0;
			
		}
		
		System.out.println(sb);
	}
	
	public static void getPower(int index, int coreCnt, int lenLine) {
		
		// base case
		if (index == nP) {
			if (maxCore < coreCnt) {
				maxCore = coreCnt;
				minLine = lenLine;
			} else if (maxCore == coreCnt) {
				minLine = Integer.min(minLine, lenLine);
			}
			return;
		}
		
		int[] position = corePosition.get(index);
		int r = position[0];
		int c = position[1];
		
		// 4방향 시도
		for (int d = 0; d < 4; d++) {
			if(!isAvailable(r, c, d)) continue; // 불가능하면 스킵
			
			int addLenLine = setLine(r, c, d, 2); // 2: line
			getPower(index+1, coreCnt+1, lenLine+addLenLine);
			setLine(r, c, d, 0); // 전선 설치 취소
		}
		
		getPower(index+1, coreCnt, lenLine);
	}
	
	public static boolean isAvailable(int r, int c, int d) {
		int nr = r, nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			if (status[nr][nc] != 0) return false;
		}
		
		return true;
	}
	
	public static int setLine(int r, int c, int d, int setting) {
		int nr = r, nc = c;
		int lenLine = 0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			status[nr][nc] = setting;
			lenLine++;
		}
		
		return lenLine;
	}

}