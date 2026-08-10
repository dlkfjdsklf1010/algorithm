import java.util.*;

class Solution {
    public long solution(long n) {
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder(new String(arr));
        sb.reverse();
        return Long.parseLong(sb.toString());
    }
}