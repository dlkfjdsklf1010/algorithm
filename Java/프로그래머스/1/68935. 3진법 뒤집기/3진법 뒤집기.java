class Solution {
    public int solution(int n) {
        
        String ternary = Integer.toString(n, 3);
        
        String reversed = new StringBuilder(ternary).reverse().toString();
        
        int answer = Integer.parseInt(reversed, 3);
        
        return answer;
    }
}