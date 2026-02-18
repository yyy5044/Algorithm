import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Solution {
	static int N, K;
	static int[] dr = {-1,1,0,0}, dc = {0,0,-1,1};
	static int[][] map;
	static boolean[][] visited;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());	
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			visited = new boolean[N][N];
			int maxHeight = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					maxHeight = Math.max(maxHeight, map[i][j]);
				}
			}
			
			ArrayList<int[]> startPoints = new ArrayList<>();
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == maxHeight) {
						startPoints.add(new int[] {i, j});
					}
				}
			}
			
			int maxPath = 0;
			for (int i = 0; i < startPoints.size(); i++) {
				int r = startPoints.get(i)[0];
				int c = startPoints.get(i)[1];
				maxPath = Math.max(dfs(r,c,false), maxPath);
			}

			sb.append("#").append(t+1).append(" ")
				.append(maxPath).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static int dfs(int r, int c, boolean isUsed) {
		visited[r][c] = true;
		int myCnt = 1;
		
		for (int d = 0; d < 4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if (nr<0||nc<0||nr>N-1||nc>N-1||visited[nr][nc]) continue;
			
			if(map[nr][nc] < map[r][c]) {
				myCnt = Math.max(myCnt, dfs(nr,nc,isUsed)+1); // 실수: false가 아닌 isUsed를 넘겨줘야 공사권 초기화가 안 됨!!
			} else if (!isUsed && (map[nr][nc] - K < map[r][c])) {
				int tmp = map[nr][nc];
				map[nr][nc] = map[r][c] - 1;
				myCnt = Math.max(myCnt, dfs(nr,nc,true)+1);
				map[nr][nc] = tmp;
			}
		}
		
		visited[r][c] = false;
		return myCnt;
	}
}

