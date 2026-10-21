import java.io.*;
import java.util.*;

class Solution {
    
    public int[] solution(String[] genres, int[] plays) {
        HashMap<String, Integer> genreTotal = new HashMap<>();
        // 장르별로 묶어서 {i, j}와 같이 정리
        // 장르별로 한 번 정렬해서 넣고, 그 다음 안에 있는 i, j 정렬
        
        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            if (!genreTotal.containsKey(g)) genreTotal.put(g, 0);
            genreTotal.put(g, genreTotal.get(g) + plays[i]);
        }
        
        HashMap<String, ArrayList<Integer>> songs = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String g = genres[i];
            if (!songs.containsKey(g)) songs.put(g, new ArrayList<>());
            songs.get(g).add(i);
        }
        
        for (String g : songs.keySet()) {
            ArrayList<Integer> tmp = songs.get(g);
            
            tmp.sort((a, b) -> {
                if (plays[b] != plays[a]) return plays[b] - plays[a];
                else return a - b;
            });
        }
        
        ArrayList<String> genreRank = new ArrayList<>(genreTotal.keySet());
        genreRank.sort((a, b) -> genreTotal.get(b) - genreTotal.get(a));
        
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = 0; i < genreRank.size(); i++) {
            String g = genreRank.get(i);
            result.add(songs.get(g).get(0));
            if (songs.get(g).size() > 1)
            result.add(songs.get(g).get(1));
        }
        
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        
        return ans;
    }
}