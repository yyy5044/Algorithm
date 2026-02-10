import java.util.*;
import java.io.*;

public class Solution {
	static int N, numP; // 가로세로 길이, 가장자리 코어 개수
	static int[][] map; // 원본 맵
	static int[][] status; // 맵의 상태
	static ArrayList<int[]> list = new ArrayList<int[]>(); // 가장자리 코어 좌표(r, c)
	
	static int[] dr= {-1,1,0,0}, dc = {0,0,-1,1}; // 4방향 델타
	
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			status = new int[N][N];
			
			for(int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = status[r][c] = Integer.parseInt(st.nextToken());
					
					if (r == 0 || c == 0 || r == N-1 || c == N-1) continue;  // 가장 자리인 경우 skip
					if (map[r][c] == 1) {
						list.add(new int[] {r, c});
					}
				}
			}
			
			numP = list.size();
			
			setPower(0,0,0);
			
			sb.append("#").append(t+1).append(" ")
				.append(min).append("\n");
			max = Integer.MIN_VALUE;
			min = Integer.MAX_VALUE;
			list.clear();
					
		}
		
		System.out.println(sb);
	}
	
	public static void setPower(int index, int poweredCores, int lenLine) {
		
		// base case
		if (index == numP) {
			if (max < poweredCores) {
				max = poweredCores;
				min = lenLine;
			} else if (max == poweredCores){
				min = Integer.min(min, lenLine);
			}
			return; 
		}
		
		int[] position = list.get(index); // 리스트에서 코어 좌표 꺼내오기
		int r = position[0];
		int c = position[1];
		
		// 4방향 전선 설치 시도
		for (int d = 0; d < 4; d++) {
			if (!isAvailable(r, c, d)) continue; // 불가능하면 다음 방향 시도
			
			int addLenLine = setLine(r,c,d,2); // 전선 설치
			setPower(index+1, poweredCores+1, lenLine+addLenLine); // 코어에 전원을 넣는 선택지
			setLine(r,c,d,0); // 전선 설치 취소
		}
		
		setPower(index+1, poweredCores, lenLine); // 코어에 전원을 안 넣는 선택지
	}
	
	public static boolean isAvailable(int r, int c, int d) {
		int nr = r;
		int nc = c;
		
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			if (status[nr][nc] != 0) return false;
		}
		
		return true;
	}
	
	public static int setLine(int r, int c, int d, int set) {
		int nr = r;
		int nc = c;
		int lenLine = 0;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nc < 0 || nr >= N || nc >= N) break;
			status[nr][nc] = set;
			lenLine++;
		}
		
		return lenLine;
	}
	

}
