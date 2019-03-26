package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import program.Scheduler;


public class ProgramFrame extends JFrame{
	JFrame frame;
	Scheduler scheduler;
	public ProgramFrame(Scheduler scheduler) {
		frame =this;
		this.scheduler= scheduler;
		initialize();
	}
	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new SchedulerPanel(this));
		revalidate();
		addWindowListener(new java.awt.event.WindowAdapter() {
	    @Override
	    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	    	scheduler.saveEntries();
	        if (JOptionPane.showConfirmDialog(frame, 
	            "Are you sure you want to close this window?", "Close Window?", 
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
	            System.exit(0);
	        }
	    }
	});
	}
	
}
