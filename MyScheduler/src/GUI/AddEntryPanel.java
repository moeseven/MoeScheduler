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
import program.ToolFunctions;

public class AddEntryPanel extends JPanel{
	private JpWest jpWest;
	private JPanel jpEast;
	private ProgramFrame pf;
	public AddEntryPanel(ProgramFrame pf) {
		this.pf=pf;
		setLayout(new BorderLayout());
		jpWest= new JpWest();
		setVisible(true);
		add(jpWest, BorderLayout.NORTH);
	}
	public void setDate(Integer[] d) {
		jpWest.setDate(d);
	}
	public void setDescription(String s) {
		jpWest.setDescription(s);
	}
	public void setRecursion(int r) {
		jpWest.setRecursion(r);
	}
	private class JpWest extends JPanel{
		JTextPane descriptionField;
		JTextField year;
		JTextField month;
		JTextField day;
		JTextField hour;
		JTextField minute;
		JTextField recursion;
		JTextField yearName;
		JTextField monthName;
		JTextField dayName;
		JTextField hourName;
		JTextField minuteName;
		JTextField recursionName;
		JPanel date;
		JPanel description;
		JPanel createEntry;
		JButton create;
		private JpWest() {
			this.setVisible(true);
			setLayout(new BorderLayout());
			//date panel
			Integer[] d = ToolFunctions.getCurrentDate();
			date= new JPanel();
			year=new JTextField(""+d[0]);
			month= new JTextField(""+d[1]);
			day= new JTextField(""+d[2]);
			hour= new JTextField(""+d[3]);
			minute= new JTextField(""+d[4]);
			recursion= new JTextField("0");
			yearName=new JTextField("year");
			yearName.setEditable(false);
			monthName= new JTextField("month");
			monthName.setEditable(false);
			dayName= new JTextField("day");
			dayName.setEditable(false);
			hourName= new JTextField("hour");
			hourName.setEditable(false);
			minuteName= new JTextField("minute");
			minuteName.setEditable(false);
			recursionName= new JTextField("cycle time");
			recursionName.setEditable(false);
			date.setLayout(new GridLayout(2, 6));
			date.add(yearName);
			date.add(monthName);
			date.add(dayName);
			date.add(hourName);
			date.add(minuteName);
			date.add(recursionName);
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
			create.addMouseListener(new MyMouseListener());
			createEntry.add(create);
			add(createEntry, BorderLayout.EAST);
		}
		public void setDate(Integer[] d) {
			year.setText(""+d[0]);
			month.setText(""+d[1]);
			day.setText(""+d[2]);
			hour.setText(""+d[3]);
			minute.setText(""+d[4]);
		}
		public void setDescription(String s) {
			descriptionField.setText(s);
		}
		public void setRecursion(int r) {
			recursion.setText(r+"");
		}
		private class MyMouseListener extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					pf.scheduler.addNewTask(new TaskObject(pf.scheduler, descriptionField.getText(), recursion.getText(), year.getText(), month.getText(), day.getText(), hour.getText(), minute.getText()));
					pf.renew();
					pf.revalidate();
					pf.repaint();
				}else{
					if (e.getButton()==3){
					}
				}
			} 
		}
	}	
}
