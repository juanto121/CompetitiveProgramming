// @EXPECTED_RESULTS@: CORRECT

import java.io.*;
import java.util.*;
import java.text.*;

public class tobi {
	public static void main(String args[]) throws Exception {
		GregorianCalendar c = new GregorianCalendar();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Date> skips = new ArrayList<>();
		for (int i = 16; i < 100; i++) if (i % 4 != 0) skips.add(new Date(i*100 - 1900, 2 - 1, 28));

		while (true) {
			String line = r.readLine();
			if (line == null) break;
			Date date = formatter.parse(line);
			String d[] = line.split("-");
			int skippedDays = 11;
			for (Date s : skips) if (date.after(s)) skippedDays++;
			if (d[0].equals("1582") && d[1].equals("10") && d[2].compareTo("15") < 0) skippedDays -= 10;
			if (d[1].equals("02") && d[2].equals("29")) {
				int y = Integer.parseInt(d[0]);
				if (y % 100 == 0 && y % 400 != 0) skippedDays--;
			}
			c.setTime(date);
			c.add(Calendar.DATE, skippedDays);
			date = c.getTime();
			System.out.printf("%02d-%02d-%02d\n", date.getYear() + 1900, date.getMonth() + 1, date.getDate());
		}
	}
}
