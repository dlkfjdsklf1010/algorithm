class Solution {
    public String solution(String s) {
        
        StringBuilder answer = new StringBuilder();
        int index = 0;
        
        for (char c : s.toCharArray()) {
            
            if (c == ' ') {
                answer.append(c);
                index = 0;
            } else {
                
                if (index % 2 == 0) {
                    answer.append(Character.toUpperCase(c));
                } 
                else {
                    answer.append(Character.toLowerCase(c));
                }
                
                index++;
            }
        }
        
        return answer.toString();
    }
}