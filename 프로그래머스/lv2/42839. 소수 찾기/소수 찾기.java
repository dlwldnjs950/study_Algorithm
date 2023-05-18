import java.util.HashSet;
class Solution {
	public static HashSet<Integer> result = new HashSet<>();
	public static boolean[] chk;
    
    public int solution(String numbers) {
        int answer = 0;
        chk = new boolean[numbers.length()];
		makeNum(numbers, "", 0);
        answer = result.size();
        return answer;
    }
	
	public static void makeNum(String numbers, String tmp, int idx) {
		if(tmp!="") {
			int num = Integer.parseInt(tmp);
			if(chkPrime(num)) result.add(num);
		}
		
		if(idx==numbers.length()) return;
		
		for(int i=0; i<numbers.length(); i++){
            if(chk[i]) continue;
            chk[i] = true;
            makeNum(numbers, tmp+numbers.charAt(i), idx+1);
            chk[i] = false;
        }
	}
	
	public static boolean chkPrime(int num) {
		if (num < 2) return false;
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) return false;
		}
		return true;
	}

}
