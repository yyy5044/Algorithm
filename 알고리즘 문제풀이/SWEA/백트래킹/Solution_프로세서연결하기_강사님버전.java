import java.io.*;
import java.util.*;

public class Solution_프로세서연결하기 {

	static int N, max, totalCnt, min;
	static int[][] map;
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static ArrayList<int[]> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = 0;
			min = Integer.MAX_VALUE;
			totalCnt = 0; // 가장자리가 아닌 코어 수
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if((i==0 || j==0 || i==N-1 || j==N-1) && map[i][j] == 1) continue;
					if(map[i][j] == 1) {
						list.add(new int[] {i, j});
					}
				}
			}
			totalCnt = list.size();
			
			setPower(0, 0, 0);
			System.out.println("#"+(t+1)+" "+min);
		}
	}
	
	static void setPower(int index, int coreCnt, int lineCnt) {
		
		if(totalCnt-index+coreCnt<max) return;
		if(index == totalCnt) {
			if(max < coreCnt) {
				max = coreCnt;
				min = lineCnt;
			} else if(max == coreCnt) {
				min = Integer.min(min, lineCnt);
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		// 해당 코어를 4방향으로 전선 놓기 시도
		for (int d = 0; d < 4; d++) {
			// 해당 코어를 d 방향으로 놓는 것이 가능한지 체크
			if(!isAvailable(r,c,d)) continue;
			
			// 해당 코어를 d방향으로 전선 놓기
			int cnt = setStatus(r, c, d, 2); // 놓아진 전선의 길이
			// 다음 코어로 넘어가기
			setPower(index+1, coreCnt+1, lineCnt+cnt);
			// 해당 코어를 d방향으로 전선 지우기
			setStatus(r, c, d, 0);
			
		}
		// 해당 코어를 전선 놓기 하지 않기
		setPower(index+1, coreCnt, lineCnt);
	}

	
	private static boolean isAvailable(int r, int c, int d) {
		int nr=r, nc=c;
		while(true) {
			nr += dr[d];
			nc += dc[d]; 
			if (nr<0 || nr>=N || nc<0 || nc>=N) break;
			if (map[nr][nc]!=0) return false;
		}
		return true;
	}
	
	
	private static int setStatus(int r, int c, int d, int s) {
		int nr=r, nc=c, cnt=0;
		while(true) {
			nr += dr[d];
			nc += dc[d]; 
			if (nr<0 || nr>=N || nc<0 || nc>=N) break;
			map[nr][nc] = s;
			++cnt;
		}
		return cnt;
	}
}
