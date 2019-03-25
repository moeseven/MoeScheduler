
public class TaskObject {
private int recursion;
private int day;
private int hour;
private int minute;
private long time;
public TaskObject(int recursion, int year, int month,int day, int hour, int minute) {
	super();
	this.recursion = recursion;
	time=ToolFunctions.convertToSeconds(year, month, day, hour, minute);
}

}
