class Solution {
    public int solution(String t, String p) {
        
        int answer = 0;
        int len = p.length();
        
        long pNum = Long.parseLong(p);
        
        for(int i = 0; i <= t.length() - len; i++) {
            
            String str = t.substring(i, i + len);
            long num = Long.parseLong(str);
            
            if(num <= pNum) {
                answer++;
            }
        }
        return answer;
    }
}