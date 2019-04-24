package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import program.Scheduler;
import program.ToolFunctions;


public class ProgramFrame extends JFrame{
	JFrame frame;
	Scheduler scheduler;
	SchedulerPanel mainPanel;
	Integer[] date;
	public ProgramFrame(Scheduler scheduler) {
		frame =this;
		this.scheduler= scheduler;
		setVisible(true);
		initialize();
	}
	public void renew() {
		this.remove(mainPanel);
		mainPanel= new SchedulerPanel(this);
		add(mainPanel);
		revalidate();
	}
	private void initialize() {
		date =ToolFunctions.getCurrentDate();
		this.setTitle(date[0]+"."+date[1]+"."+date[2]);
		mainPanel= new SchedulerPanel(this);		
		setBounds(230, 100, 650, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(mainPanel);
		revalidate();
		addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    	scheduler.setLastUpdate(System.currentTimeMillis()/1000);
	    	scheduler.saveEntries(scheduler);
	    	System.exit(0);
	    }
	});
	}
	
}
