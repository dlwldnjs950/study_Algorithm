class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int denom = lcm(Math.max(denom1, denom2),Math.min(denom1, denom2));
		
		numer1 = numer1 * (denom / denom1);
		numer2 = numer2 * (denom / denom2);
		
		int numer = numer1 + numer2;
		
		int GCD = gcd(Math.max(numer, denom),Math.min(numer, denom));
		
		int[] answer = new int[2];
		answer[0]=numer/GCD;
		answer[1]=denom/GCD;
        return answer;
    }
	
	public int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
}