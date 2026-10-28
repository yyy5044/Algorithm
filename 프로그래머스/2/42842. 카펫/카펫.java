import java.util.*;

class Solution {
    public int[] solution(int brown, int yellow) {
        int sum = brown + yellow; // sum = 12
        ArrayList<Integer> candi = new ArrayList<>();
        for (int i = 3; i < sum; i++) {
            if (sum % i == 0) candi.add(i);
        }
        
        ArrayList<int[]> tuples = new ArrayList<>();
        for (int i = 0; i < candi.size(); i++) {
            tuples.add(new int[]{candi.get(i), sum / candi.get(i)});
        }
        
        ArrayList<int[]> ans = new ArrayList<>();
        int[] answer = new int[2];
        for (int i = 0; i < tuples.size(); i++) {
            int x = tuples.get(i)[0];
            int y = tuples.get(i)[1];
            int Y = (x-2)*(y-2);
            if (Y == yellow && x*y - Y == brown) {
                answer[0] = Math.max(x, y);
                answer[1] = Math.min(x, y);
                break;
            }
        }
        
        return answer;
    }
}