package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class SchedulerPanel extends JPanel{
	JPanel jpNorth;
	JPanel jpCenter;
	JPanel jpEast;
	ProgramFrame pf;
	public SchedulerPanel(ProgramFrame pf) {
		setLayout(new BorderLayout());
		jpNorth=new AddEntryPanel(pf);
		jpCenter= new TaskListPanel();
		add(jpNorth, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
	}
}
