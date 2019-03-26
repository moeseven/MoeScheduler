package program;
import java.io.File;

import GUI.ProgramFrame;

public class Main {

	public static void main(String[] args) {
		//System.out.println(ToolFunctions.getPrettyDateString(ToolFunctions.getCurrentDate()));
		long time= System.currentTimeMillis();
		Integer[] date = ToolFunctions.getDateFromMillis(time);
		System.out.println(ToolFunctions.getPrettyDateString(date));
		time = ToolFunctions.convertToSeconds(date[0], date[1], date[2], date[3], date[4]);
		date = ToolFunctions.convertFromSeconds(time);
		System.out.println("transfomed: "+ToolFunctions.getPrettyDateString(date));
		System.out.println(new File(".").getAbsoluteFile());
		new ProgramFrame(new Scheduler());
	}

}
