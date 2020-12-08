package cieyie;

import java.io.FileInputStream;
import java.io.IOException;

public class ObjectCIEYIE {
	public static void main(String[] args) {
		FileInputStream in= null;
		try {
			in = new FileInputStream("XMLCIEYIE1.json");
			JSONTokener tokener = new JSONTokener(in);
		}catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
