package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class TaskListPanel extends JPanel{
	private JScrollPane sp;
	private JPanel gridEntries;
	public TaskListPanel() {	
		setLayout(new BorderLayout());
		sp= new JScrollPane();
		sp.setVisible(true);
		gridEntries= new JPanel();
		gridEntries.setLayout(new GridLayout(0,1));
		for (int i = 0; i < 3; i++) {
			gridEntries.add(new GridEntry());
		}
		sp.add(gridEntries);
		setSize(new Dimension(300, 200));
		add(sp, BorderLayout.CENTER);
		setVisible(true);
		revalidate();
		repaint();
	}
	private class GridEntry extends JPanel{
		private JButton buttonPush;
		private JButton buttonCancle;
		private JButton buttonFullfill;
		private JTextField description;
		private GridEntry() {
			
			//setSize(new Dimension(300, 50));
			setLayout(new GridLayout(4, 1));
			description= new JTextField();
			description.setEditable(false);
			buttonPush= new JButton("Push");
			buttonCancle= new JButton("Cancle");
			buttonFullfill= new JButton("Fullfill");
			add(description);
			add(buttonFullfill);
			add(buttonPush);
			add(buttonCancle);
			setVisible(true);
		}
	}
}
