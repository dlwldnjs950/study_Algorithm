import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
class Solution {
    public int[] solution(String[] genres, int[] plays) {
        HashMap<Integer, String> gr = new HashMap<>();
		HashMap<String, Integer> p = new HashMap<>();
		Integer[] idx = new Integer[genres.length];
		for (int i = 0; i < genres.length; i++) {
			idx[i] = i;
			gr.put(i, genres[i]);
		}
		for (int i = 0; i < genres.length; i++) {
			p.put(genres[i], p.getOrDefault(genres[i], 0) + plays[i]);
		}
		Arrays.sort(idx, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if (!gr.get(o1).equals(gr.get(o2))) { // 장르가 다르면
					return p.get(gr.get(o2)) - p.get(gr.get(o1));
				} else { // 장르가 같을 때
					if (plays[o1] != plays[o2]) { // 재생수가 다르면
						return plays[o2] - plays[o1];
					} else { // 재생수가 같으면
						return o1 - o2;
					}
				}
			}
		});
        
		List<Integer> ans = new ArrayList<>();
		int cnt = 0;
		String chk = "";
		int a = 0; // answer의 index
		for (int i = 0; i < idx.length; i++) {
			if (!gr.get(idx[i]).equals(chk)) {
				cnt = 0;
				chk = gr.get(idx[i]);
				System.out.println(chk);
			}
			if (cnt == 2) {
				continue;
			}
			ans.add(idx[i]) ; // 기본 동작
			a++;
			cnt++;
		}
		int[] answer = new int[ans.size()];
		for (int i = 0; i < ans.size(); i++) {
			answer[i]=ans.get(i);
		}
        return answer;
    }
}