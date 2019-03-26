package program;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.LinkedList;


public class Scheduler implements Serializable{
private LinkedList<TaskObject> taskList;
	public Scheduler() {
		taskList=new LinkedList<TaskObject>();
		taskList.add(new TaskObject(this, "1 Kniebeuge", 1, 3000, 4, 1, 8, 22));
	}
	public void update() {
		//TODO
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
	
	// getters and setters
	public LinkedList<TaskObject> getTaskList() {
		return taskList;
	}
	public void setTaskList(LinkedList<TaskObject> taskList) {
		taskList = taskList;
	}
	
}
