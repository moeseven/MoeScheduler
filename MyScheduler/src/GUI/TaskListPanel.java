package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import program.TaskObject;
import program.ToolFunctions;

public class TaskListPanel extends JPanel{
	private JScrollPane sp;
	private JPanel gridEntries;
	private ProgramFrame pf;
	public TaskListPanel(ProgramFrame pf) {	
		this.pf=pf;
		setLayout(new BorderLayout());
		gridEntries= new JPanel();
		gridEntries.setLayout(new GridLayout(0,1));
		gridEntries.setVisible(true);
		for (int i = 0; i < pf.scheduler.getTaskList().size(); i++) {
			gridEntries.add(new GridEntry(pf.scheduler.getTaskList().get(i)));
		}
		sp= new JScrollPane(gridEntries);
		sp.setVisible(true);
		
		//setSize(new Dimension(300, 200));
		add(sp, BorderLayout.CENTER);
		setVisible(true);
		revalidate();
		repaint();
	}
	private class GridEntry extends JPanel{
		private JPanel buttons;
		private JPanel description;
		private JButton buttonPush;
		private JButton buttonCancle;
		private JButton buttonFullfill;
		private JTextArea text;
		private JTextArea timeStamp;
		private TaskObject to;
		private GridEntry(TaskObject to) {
			this.to=to;
			setPreferredSize(new Dimension(300, 60));
			//setSize(new );
			setLayout(new GridLayout(0, 2));
			text= new JTextArea(to.getDescription()+"");
			text.setEditable(false);
			timeStamp= new JTextArea(generateTimeStamp(to));
			timeStamp.setPreferredSize(new Dimension(100, 50));
			timeStamp.setEditable(false);

			description= new JPanel();
			description.setLayout(new BorderLayout());
			description.add(text, BorderLayout.CENTER);
			description.add(timeStamp, BorderLayout.WEST);
			buttonPush= new JButton("Push");
			buttonPush.addMouseListener(new ButtonPush());
			buttonCancle= new JButton("Cancle");
			buttonCancle.addMouseListener(new ButtonCancle());
			buttonFullfill= new JButton("Done");
			buttonFullfill.addMouseListener(new ButtonDone());
			add(description);
			buttons= new JPanel();
			buttons.setLayout(new GridLayout(1, 3));
			buttons.add(buttonFullfill);
			buttons.add(buttonPush);
			buttons.add(buttonCancle);
			add(buttons);
			setVisible(true);
		}
		private class ButtonDone extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					pf.scheduler.fullFill(to);
					pf.renew();
					pf.revalidate();
					pf.repaint();
				}else{
					if (e.getButton()==3){
					}
				}
			} 
		}
		private class ButtonPush extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					pf.scheduler.push(to);
					pf.renew();
					pf.revalidate();
					pf.repaint();
				}else{
					if (e.getButton()==3){
					}
				}
			} 
		}
		private class ButtonCancle extends MouseAdapter{
			public void mousePressed(MouseEvent e){
				if(e.getButton()==1){
					pf.scheduler.cancle(to);
					pf.renew();
					pf.revalidate();
					pf.repaint();
				}else{
					if (e.getButton()==3){
					}
				}
			} 
		}

		private String generateTimeStamp(TaskObject task) {//TODO make this pretty
			String retVal="";
			Integer[] date = ToolFunctions.convertFromSeconds(task.getTime()); 
			String prettyDate = ToolFunctions.getPrettyDateString(date);
			if (task.getTime()<System.currentTimeMillis()/1000) {
				retVal+= "in the past!"+"\n";
			}else {
				if (ToolFunctions.isThisToday(task.getTime())) {
					retVal+="Today"+"\n";
				}else {
					if (ToolFunctions.isThisTomorrow(task.getTime())) {
						retVal+="Tomorrow"+"\n";
					}
			}}
			if (!ToolFunctions.thisYear(task.getTime())) {							
				retVal+=date[0]+".";
			}
				//get day of week
				String dayOfTheWeek=ToolFunctions.getDayOfWeek(task.getTime());
				retVal+=dayOfTheWeek+"\n"+date[1]+"."+date[2]+". ";				
			
			if (ToolFunctions.thisYear(task.getTime())) {
				retVal+=" "+date[3]+":";
				if (date[4]<10) {
					retVal+="0"+date[4];
				}else {
					retVal+=date[4];
				}	
				retVal+=" Uhr";
			}			
			return retVal;
		}
	}
}
