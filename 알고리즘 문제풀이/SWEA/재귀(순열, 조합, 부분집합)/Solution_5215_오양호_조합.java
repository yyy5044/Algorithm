import java.util.*;
import java.io.*;

class Ingredient{
	public int score;
	public int kcal;
	public Ingredient(int score, int kcal) {
		super();
		this.score = score;
		this.kcal = kcal;
	}
}

public class Solution {
	static int N, L, M;
	static List<Ingredient> ingredients = new ArrayList<Ingredient>();
	static int[] numbers;
	static int max = 0;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int score = Integer.parseInt(st.nextToken());
				int kcal = Integer.parseInt(st.nextToken());
				ingredients.add(new Ingredient(score, kcal));
			}
			
			for (int i = 1; i <= N; i++) {
				M = i;
				numbers = new int[M];
				comb(0, 0);
			}

			sb.append("#").append(t+1).append(" ")
				.append(max).append("\n");
			
			ingredients.clear();
			max = 0;
		}
		
		System.out.println(sb);
	}
	
	public static void comb(int depth, int start) {
		if (depth == M) {
			int sumScore = 0, sumKcal = 0;
			for (int i = 0; i < M; i++) {
				sumScore += ingredients.get(numbers[i]).score;
				sumKcal += ingredients.get(numbers[i]).kcal;
				if(sumKcal < L && sumScore > max) max = sumScore;
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			numbers[depth] = i;
			comb(depth+1, i + 1);
		}
	}

}
