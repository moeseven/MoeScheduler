package program;

public class TaskObject {
private int recursion;
private boolean appointment;
private int day;
private int hour;
private int minute;
private long time;
private String description;
private Scheduler scheduler;
	public TaskObject(Scheduler scheduler,String description,String recursion, String year, String month,String day, String hour, String minute) {
		this(scheduler,description,Integer.parseInt(recursion), Integer.parseInt(year), Integer.parseInt(month),Integer.parseInt(day), Integer.parseInt(hour),Integer.parseInt(minute));
	}
	public TaskObject(Scheduler scheduler,String description,int recursion, int year, int month,int day, int hour, int minute) {
		super();
		this.recursion = recursion;//days
		this.scheduler=scheduler;
		this.description=description;
		time=ToolFunctions.convertToSeconds(year, month, day, hour, minute);
	}

	public void push() {//1 day costs dicipline points TODO
		scheduler.removeTask(this);
		time+=1*ToolFunctions.aDaysSeconds();
		scheduler.addNewTask(this);	
	}
	public void cancle() {//punish this TODO
		delete();
	}
	public void fullfill() {//give some dicipline points here TODO
		if (scheduler.getTaskList().contains(this)) {
			if (recursion>0) {				
				scheduler.removeTask(this);
				time+=recursion*ToolFunctions.aDaysSeconds();
				scheduler.addNewTask(this);				
			}else {
				scheduler.removeTask(this);
			}
		}
	}
	public void delete() {
		if (scheduler.getTaskList().contains(this)) {
			scheduler.getTaskList().remove(this);
		}
		
	}
	//getters and setters
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
