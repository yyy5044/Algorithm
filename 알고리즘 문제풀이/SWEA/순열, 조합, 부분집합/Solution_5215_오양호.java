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
	static int N, L;
	static List<Ingredient> ingredients = new ArrayList<Ingredient>();
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
			
			subset(0, 0, 0);

			sb.append("#").append(t+1).append(" ")
				.append(max).append("\n");
			
			ingredients.clear();
			max = 0;
		}
		
		System.out.println(sb);
	}
	
	public static void subset(int depth, int sumScore, int sumKcal) {
		if (sumKcal > L) return; // 가지치기
		
		if (depth == N) {
			if (max < sumScore) max = sumScore;
			return;
		}
		
		subset(depth + 1, sumScore + ingredients.get(depth).score, 
				sumKcal + ingredients.get(depth).kcal);
		
		subset(depth + 1, sumScore, sumKcal);
	}
}
