import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreTotal = new HashMap<>();
        Map<String, List<int[]>> genreSongs = new HashMap<>();
        
        for (int i = 0; i < genres.length; i++) {
            genreTotal.put(genres[i], genreTotal.getOrDefault(genres[i], 0) + plays[i]);
            if (!genreSongs.containsKey(genres[i])) {
                genreSongs.put(genres[i], new ArrayList<int[]>());
            }
            genreSongs.get(genres[i]).add(new int[]{plays[i], i});
        }
        
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(genreTotal.entrySet());
        entries.sort((a, b) -> b.getValue() - a.getValue());
    
        
        List<Integer> list = new ArrayList<>();
        for (Map.Entry<String, Integer> e : entries) {
            String genre = e.getKey();
            
            List<int[]> songs = genreSongs.get(genre);
            
            songs.sort((a, b) -> {
                if (a[0] != b[0]) return b[0] - a[0];
                else return a[1] - b[1];
            });
            
            for (int i = 0; i < Math.min(songs.size(), 2); i++) {
                int[] song = songs.get(i);
                list.add(song[1]);
            }
        }
        
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) ans[i] = list.get(i);
        
        return ans;
    }
}