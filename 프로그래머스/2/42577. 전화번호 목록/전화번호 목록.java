import java.util.*;
import java.io.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> set = new HashSet<>(Arrays.asList(phone_book));
        
        for (String p : phone_book) {
            for (int i = 0; i < p.length(); i++) {
                if (set.contains(p.substring(0, i))) return false;
            }
        }
        
        return true;
    }
}