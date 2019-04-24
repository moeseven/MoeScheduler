package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class SchedulerPanel extends JPanel{
	AddEntryPanel addEntryPanel;
	JPanel jpCenter;
	JPanel jpEast;
	ProgramFrame pf;
	public SchedulerPanel(ProgramFrame pf) {
		//setSize(new Dimension(pf.getHeight(), pf.getWidth()));
		setLayout(new BorderLayout());
		addEntryPanel=new AddEntryPanel(pf);
		//jpNorth.setSize(new Dimension(300, 300));
		jpCenter= new TaskListPanel(pf);
		add(addEntryPanel, BorderLayout.NORTH);
		add(jpCenter, BorderLayout.CENTER);
		jpEast = new DisciplinePanel(pf);
		add(jpEast, BorderLayout.EAST);
	}
}
