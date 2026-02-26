import java.io.*;
import java.util.*;

public class Solution {
	static int[][] magnets = new int[4][8];
	static ArrayList<int[]> list = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnets[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int index = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());
				checkMagnet(index-1,rotateDir,-1);
				for (int j = 0; j < list.size(); j++) {
					int[] unbox = list.get(j);
					int idx = unbox[0];
					int dir = unbox[1];
					rotateMagnet(idx, dir);
				}
				list.clear();
			}

			int score = 0;
			for(int i = 0; i < 4; i++) {
				if(magnets[i][0] == 1) {
					score += 1<< i;
				}
			}
			
			sb.append("#").append(t+1).append(" ")
				.append(score).append("\n");
		}
		
		System.out.println(sb);
	}
	
	public static void checkMagnet(int curIdx, int rotateDir, int fromIdx) {
		if (curIdx+1<=3 && curIdx+1 != fromIdx) { // 오른쪽이 있고, 나를 호출한 것이 오른쪽이 아니면 오른쪽으로 전파
			if(magnets[curIdx][2] != magnets[curIdx+1][6]) {
				checkMagnet(curIdx+1, -rotateDir, curIdx);
			}
		}
		
		if (curIdx-1>=0 && curIdx-1 != fromIdx) {
			if(magnets[curIdx][6] != magnets[curIdx-1][2]) {
				checkMagnet(curIdx-1, -rotateDir, curIdx);
			}
		}
		
		list.add(new int[] {curIdx, rotateDir});
	}
	
	public static void rotateMagnet(int index, int rotateDir) {
		if (rotateDir == 1) {
			int tmp = magnets[index][7];
			for (int i = 6; i >= 0; i--) {
				magnets[index][i+1] = magnets[index][i];
			}
			magnets[index][0] = tmp; 
		} else if (rotateDir == -1) {
			int tmp = magnets[index][0];
			for (int i = 1; i < 8; i++) {
				magnets[index][i-1] = magnets[index][i];
			}
			magnets[index][7] = tmp;
		}	
	}
	
}
