import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class Main{

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder builder = new StringBuilder();
		while(true){
		    String str = br.readLine();
		    if (str == null || str.isEmpty()) {
		        break;
		    }
		    builder.append(str).append("\n");
		}
		br.close();
		System.out.println(builder);
	}
}
