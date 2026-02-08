import java.util.*;
import java.io.*;

class Box {
	public int rotateDir;
	public int idx;
	public Box(int rotateDir, int idx) {
		super();
		this.rotateDir = rotateDir;
		this.idx = idx;
	}
}

public class Solution {
	static int K;
	static int[][] magnets = new int[4][8];
	static List<Box> list = new ArrayList<Box>();
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			
			for (int r = 0; r < 4; r++) {
				st = new StringTokenizer(br.readLine());
				for (int c = 0; c < 8; c++) {
					magnets[r][c] = Integer.parseInt(st.nextToken());
				}
			}

			
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int idx = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				addBox(idx - 1, dir, -1);
				for (int i = 0; i < list.size(); i++) {
					rotateMagnet(magnets[list.get(i).idx], list.get(i).rotateDir);
				}
				list.clear();
			}
			
			int score = 0;
			for (int r = 0; r < 4; r++) {
				if (magnets[r][0] == 1) {
					score += 1 << r;
				}
			}
			sb.append("#").append(t+1).append(" ")
				.append(score).append("\n");
		}
		
		
		System.out.println(sb);
	}

	public static void addBox(int curIdx, int rotateDir, int fromIdx) {
		if (curIdx+1 <= 3 && curIdx + 1 != fromIdx) {
			if (magnets[curIdx][2] != magnets[curIdx + 1][6]) { // 오른쪽 자석과 비교
				addBox(curIdx + 1, -rotateDir, curIdx); // 오른쪽으로 전파
			}
		}
		
		if (curIdx-1 >= 0 && curIdx - 1 != fromIdx) {
			if (magnets[curIdx][6] != magnets[curIdx - 1][2]) {
				addBox(curIdx - 1, -rotateDir, curIdx); // 왼쪽으로 전파
			}
		}
		
		list.add(new Box(rotateDir, curIdx));
	}
	
//	public static void rotateMagnet(int[] magnet, int rotateDir) {
//		ArrayList<Integer> tmp = new ArrayList<Integer>();
//		for (int i = 0; i < magnet.length; i++) {
//			tmp.add(magnet[i]);
//		}
//		Collections.rotate(tmp, rotateDir);
//		for (int i = 0; i < magnet.length; i++) {
//			magnet[i] = tmp.get(i);
//		}
//	}
	
	public static void rotateMagnet(int[] magnet, int dir) {
		if (dir == 1) {
			int tmp = magnet[magnet.length - 1];
			for (int i = magnet.length - 1; i > 0; i--) {
				magnet[i] = magnet[i - 1];
			}
			magnet[0] = tmp;
		} else if (dir == -1){
			int tmp = magnet[0];
			for (int i = 0; i < magnet.length - 1; i++) {
				magnet[i] = magnet[i + 1];
			}
			magnet[magnet.length - 1] = tmp;
		}

	}
}
