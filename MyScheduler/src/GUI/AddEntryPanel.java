package GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class AddEntryPanel extends JPanel{
	private JPanel jpWest;
	private JPanel jpEast;
	public AddEntryPanel() {
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
			date.setLayout(new GridLayout(1, 5));
			date.add(year);
			date.add(month);
			date.add(day);
			date.add(hour);
			date.add(minute);
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
	}	
}
