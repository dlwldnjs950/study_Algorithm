import java.io.*;
import java.util.*;

public class Main {
    static List<Integer> result = new ArrayList<>();
    static boolean[] used = new boolean[10];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        precompute();

        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;
            sb.append(result.get(n - 1)).append("\n");
        }

        System.out.print(sb);
    }

    static void precompute() {
        int num = 1;
        while (result.size() < 1000000) {
            if (isUnique(num)) {
                result.add(num);
            }
            num++;
        }
    }

    static boolean isUnique(int n) {
    	Arrays.fill(used, false);
        
        while (n > 0) {
            int digit = n % 10;
            if (used[digit]) return false;
            used[digit] = true;
            n /= 10;
        }
        return true;
    }
}