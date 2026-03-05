import java.io.*;
import java.util.*;

public class Solution {
	static int N, X;
	static int[][] map;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int count = 0;
			for (int i = 0; i < N; i++) {
				int[] copy = new int[N];
				copy = map[i].clone();
				
				if (isValid(copy)) count++;
			}
			
			for (int i = 0; i < N; i++) {
				int[] copy = new int[N];
				for (int j = 0; j < N; j++) {
					copy[j] = map[j][i];
				}
				
				if (isValid(copy)) count++;
			}
			
			sb.append("#").append(t).append(" ")
				.append(count).append("\n");
		}
		
		System.out.println(sb);
	}
	
	private static boolean isValid(int[] line) {
		boolean result = true;
		
		boolean[] used = new boolean[N]; // 경사로 설치 영역에 true
		
		int cur = 0;
		while(true) {
			int next = cur + 1;
			if (cur >= N-1) { // cur이 끝에 도달한 경우
				break; // 조기 종료
			} else if (line[cur] == line[next]) { // 평평한 경우
				cur = next;
				continue;
			} else if (canDsc(line, cur) && line[cur] == line[next] + 1) { // 내리막을 설치할 수 있는 상황
				for (int i = next; i < next+X; i++) {
					if (used[i]) {
						result = false;
						break;
					}
				}
				
				if (result) {
					Arrays.fill(used, next, next+X, true);
					cur = next;
				}
				else break;
			} else if (canAsc(line, cur) && line[cur] + 1 == line[next]) { // 오르막을 설치할 수 있는 상황
				for (int i = cur; i >= cur-(X-1); i--) {
					if (used[i]) {
						result = false;
						break;
					}
				}
				

				if (result) {
					Arrays.fill(used, cur-(X-1), cur+1, true);
					cur = next;
				}
				else break;
			} else { // 높이 차이가 2 이상이면
				result = false;
				break;
			}
		}

		return result;
	}
	
	private static boolean canDsc(int[] line, int cur) {
		boolean result = true;
		if (cur + X < N) { // 공간이 있으면
			int next = cur + 1;
			for (int i = next+1; i < next+X; i++) { // X 길이만큼 값이 같은지 확인
				if (line[next] != line[i]) {
					result = false;
					break;
				}
			}
		} else { // 공간이 없으면
			result = false;
		}
		
		return result;
	}
	
	private static boolean canAsc(int[] line, int cur) {
		boolean result = true;
		if (cur - (X-1) >= 0) { // 공간이 있으면
			for (int i = cur-1; i >= cur-(X-1); i--) {
				if (line[cur] != line[i]) {
					result = false;
					break;
				}
			}
		} else {
			result = false;
		}
		
		return result;
	}
}

