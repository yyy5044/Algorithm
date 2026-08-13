import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 재생수 합을 담고 있는 맵
        Map<String, Integer> genreTotal = new HashMap<>();
        // 2. 장르별, 노래별 재생수와 고유번호를 배열로 담고 있는 맵
        Map<String, List<int[]>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genreTotal.put(genres[i], genreTotal.getOrDefault(genres[i], 0) + plays[i]);
            if (!genreSongs.containsKey(genres[i])) {
                genreSongs.put(genres[i], new ArrayList<>());
            }
            genreSongs.get(genres[i]).add(new int[]{plays[i], i});
        }
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(genreTotal.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
        
        List<Integer> answers = new ArrayList<>();
        for (Map.Entry<String, Integer> e : entries) {
            String genre = e.getKey();
            genreSongs.get(genre).sort((a, b) -> {
                if (a[0] != b[0]) {
                    return b[0] - a[0];
                } else {
                    return a[1] - b[1];
                }
            });
            
            // 정렬된 int[]에서 고유 번호 두 개 뽑아서 수록
            if (genreSongs.get(genre).size() == 1) {
                answers.add(genreSongs.get(genre).get(0)[1]);
            } else {
                answers.add(genreSongs.get(genre).get(0)[1]);
                answers.add(genreSongs.get(genre).get(1)[1]);
            }
            
        }
        
        // List -> int[]
        int[] ans = new int[answers.size()];
        for (int i = 0; i < answers.size(); i++) {
            ans[i] = answers.get(i);
        }
        
        return ans;
    }
}