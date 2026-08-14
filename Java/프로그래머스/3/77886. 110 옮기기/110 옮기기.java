import java.util.*;

class Solution {

    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for (int i = 0; i < s.length; i++) {
            String str = s[i];
            StringBuilder sb = new StringBuilder();
            int count = 0;

            for (int j = 0; j < str.length(); j++) {
                sb.append(str.charAt(j));

                int size = sb.length();

                if (size >= 3 &&
                    sb.charAt(size - 3) == '1' &&
                    sb.charAt(size - 2) == '1' &&
                    sb.charAt(size - 1) == '0') {

                    sb.delete(size - 3, size);
                    count++;
                }
            }

            int insertIndex = sb.lastIndexOf("0");

            if (insertIndex == -1) {
                insertIndex = 0;
            } else {
                insertIndex++;
            }

            sb.insert(insertIndex, "110".repeat(count));

            answer[i] = sb.toString();
        }

        return answer;
    }
}