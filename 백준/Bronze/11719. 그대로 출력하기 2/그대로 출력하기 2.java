import java.io.IOException;
import java.util.Scanner;
public class Main {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		
		String str = "";
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            System.out.println(s);
        }
	}
}
