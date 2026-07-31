import java.io.*;
import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Set<String> set = new HashSet<>(Arrays.asList(phone_book));
        
        for (String p : phone_book) {
            for (int i = 1; i < p.length(); i++) {
                if (set.contains(p.substring(0, i))) return false;
            }
        }
        
        return true;
    }
}