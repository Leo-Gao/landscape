package start;

import java.text.DecimalFormat;

public class Test2 {
	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("00000");
		for (int i = 0; i < 10000; i++) {
			System.out.println(df.format(i));
		}
	}

}
