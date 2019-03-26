package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import program.TaskObject;

public class AddEntryPanel extends JPanel{
	private JPanel jpWest;
	private JPanel jpEast;
	private ProgramFrame pf;
	public AddEntryPanel(ProgramFrame pf) {
		this.pf=pf;
		setLayout(new BorderLayout());
		jpWest= new JpWest();
		setVisible(true);
		add(jpWest, BorderLayout.NORTH);
	}
	private class JpWest extends JPanel{
		JTextPane descriptionField;
		JTextField year;
		JTextField month;
		JTextField day;
		JTextField hour;
		JTextField minute;
		JTextField recursion;
		JPanel date;
		JPanel description;
		JPanel createEntry;
		JButton create;
		private JpWest() {
			this.setVisible(true);
			setLayout(new BorderLayout());
			//date panel
			date= new JPanel();
			year=new JTextField();
			month= new JTextField();
			day= new JTextField();
			hour= new JTextField();
			minute= new JTextField();
			recursion= new JTextField("0");
			date.setLayout(new GridLayout(1, 6));
			date.add(year);
			date.add(month);
			date.add(day);
			date.add(hour);
			date.add(minute);
			date.add(recursion);
			add(date, BorderLayout.NORTH);
			//
			//description Panel
			descriptionField = new JTextPane();
			description = new JPanel();
			description.add(descriptionField);
			add(description, BorderLayout.CENTER);
			//
			createEntry= new JPanel();
			create= new JButton("new entry");
			createEntry.add(create);
			add(createEntry, BorderLayout.EAST);
		}
		private class MyMouseListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					pf.scheduler.addNewTask(new TaskObject(pf.scheduler, descriptionField.getText(), recursion.getText(), year.getText(), month.getText(), day.getText(), hour.getText(), minute.getText()));
				}else{
					if (e.getButton()==3){
						//new CardView(card);
					}
				}
			} 
		}
	}	
}
