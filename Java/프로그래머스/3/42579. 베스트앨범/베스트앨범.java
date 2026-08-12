import java.util.*;

class Solution {

    public int[] solution(String[] genres, int[] plays) {

        Map<String, Integer> genreTotal = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            genreTotal.put(
                genres[i],
                genreTotal.getOrDefault(genres[i], 0) + plays[i]
            );
        }

        List<String> genreList = new ArrayList<>(genreTotal.keySet());

        genreList.sort((a, b) ->
            genreTotal.get(b) - genreTotal.get(a)
        );

        List<Integer> answerList = new ArrayList<>();

        for (String genre : genreList) {

            List<Integer> songs = new ArrayList<>();

            for (int i = 0; i < genres.length; i++) {
                if (genres[i].equals(genre)) {
                    songs.add(i);
                }
            }

            songs.sort((a, b) -> {
                if (plays[a] != plays[b]) {
                    return plays[b] - plays[a];
                }

                return a - b;
            });

            answerList.add(songs.get(0));

            if (songs.size() >= 2) {
                answerList.add(songs.get(1));
            }
        }

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }
}