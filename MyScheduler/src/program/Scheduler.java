package program;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;


public class Scheduler {
private LinkedList<TaskObject> taskList;
	public Scheduler() {
		this.loadEntries();
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
		for (int i = 0; i < taskList.size(); i++) {
			if (task.getTime()< taskList.get(i).getTime()) {
				taskList.add(i,task);
			}
		}
		
	}
	public LinkedList<TaskObject> getTodaysTasks() {
		//TODO
		return taskList;
		
	}
	public void saveEntries() {
		ObjectOutputStream oos=null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("./src/resources/scheduler.dat"));
			oos.writeObject(taskList);
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
	public void loadEntries() {
		//load from file;
		ObjectInputStream ois=null;
		try {
			ois=new ObjectInputStream(new FileInputStream("./src/resources/scheduler.dat"));
			taskList=(LinkedList<TaskObject>)ois.readObject();
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
	}
	
	// getters and setters
	public LinkedList<TaskObject> getTaskList() {
		return taskList;
	}
	public void setTaskList(LinkedList<TaskObject> taskList) {
		taskList = taskList;
	}
	
}
