package program;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.LinkedList;

import javax.tools.Tool;


public class Scheduler implements Serializable{
private LinkedList<TaskObject> taskList;
private double discipline;
private long lastUpdate;
	public Scheduler() {
		discipline=0;
		taskList=new LinkedList<TaskObject>();
		taskList.add(new TaskObject(this, "1 Kniebeuge", 1, 3000, 4, 1, 8, 22));
		
	}
	public void update() {
		long checkTime= System.currentTimeMillis()/1000;
		//Dayly downtick
		int i= (int) ((lastUpdate-checkTime)/ToolFunctions.aDaysSeconds());
		for (int j = 0; j < i; j++) {
			daylyDecayTick();
		}
		lastUpdate=System.currentTimeMillis()/1000;
	}
	public void sortTaskList() {
		//TODO
	}
	public void addNewTask(TaskObject task) {
		sortInNewTask(task);
	}
	public void removeTask(TaskObject task) {
		if (taskList.contains(task)) {
			taskList.remove(task);
		}
	}
	public void sortInNewTask(TaskObject task) {
		int index=taskList.size(); boolean positionReached=false;
		for (int i = 0; i < taskList.size()&&!positionReached; i++) {
			if (task.getTime()< taskList.get(i).getTime()) {
				index=i; positionReached=true;				
			}
		}
		taskList.add(index,task);
		System.out.println("actually inserted");
	}
	public LinkedList<TaskObject> getTodaysTasks() {
		//TODO
		return taskList;
		
	}
	public void saveEntries(Scheduler scheduler) {
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("./src/resources/scheduler.dat"));
			oos.writeObject(scheduler);
			System.out.println("tasks saved");
		} catch (FileNotFoundException e1) {			
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		finally{
			try {
				oos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	public Scheduler loadEntries() {
		//load from file;
		Scheduler s=null;
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream("./src/resources/scheduler.dat"));
			s=(Scheduler)ois.readObject();
			System.out.println("tasks loaded");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		finally{
			try {
				ois.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		return s;
	}
	public void push(TaskObject task) {
		normalPushPunish();
		removeTask(task);
		task.setTime(task.getTime()+1*ToolFunctions.aDaysSeconds());
		addNewTask(task);	
	}
	public void cancle(TaskObject task) {
		normalCanclePunish();
		task.delete();
	}
	public void fullFill(TaskObject task) {
		if (getTaskList().contains(task)) {
			if (task.getRecursion()>0) {				
				removeTask(task);
				task.setTime(task.getTime()+task.getRecursion()*ToolFunctions.aDaysSeconds());
				addNewTask(task);				
			}else {
				removeTask(task);
			}
			//gain discipline points
			giveNormalTaskReward();
			//write to output file
			Integer[] scheduledDate = ToolFunctions.convertFromSeconds(task.getTime());
			Integer[] timeOfChecking = ToolFunctions.convertFromSeconds(System.currentTimeMillis()/1000);
			String logEntry=ToolFunctions.getPrettyDateString(scheduledDate)+"("+ToolFunctions.getPrettyDateString(timeOfChecking)+")"+" "+task.getDescription()+"\n";
			try(
					FileWriter fw = new FileWriter("./src/resources/log.txt", true);
				    BufferedWriter bw = new BufferedWriter(fw);
				    PrintWriter out = new PrintWriter(bw))
				{
				    out.println(logEntry);
				} catch (IOException e) {
				    //exception handling left as an exercise for the reader
				}
		}
	}
	public void daylyDecayTick() {
		gainDiscipline(-(discipline)/250);
	}
	public void normalPushPunish() {
		gainDiscipline(-(discipline)/35);
	}
	public void normalCanclePunish() {
		gainDiscipline(-(discipline)/10);
	}
	public void giveNormalTaskReward() {
		gainDiscipline((100-discipline)/50);
	}
	public void gainDiscipline(double d) {
		discipline+=d;
		if (discipline<0) {
			discipline=0;
		}
		if (discipline>100) {
			discipline=100;
		}
	}
	
	// getters and setters
	public LinkedList<TaskObject> getTaskList() {
		return taskList;
	}
	public void setTaskList(LinkedList<TaskObject> taskList) {
		taskList = taskList;
	}
	public double getDiscipline() {
		return discipline;
	}
	public void setDiscipline(double discipline) {
		this.discipline = discipline;
	}
	public long getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(long lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	
}
