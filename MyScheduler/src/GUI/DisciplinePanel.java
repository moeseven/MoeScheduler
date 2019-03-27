package GUI;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class DisciplinePanel extends JPanel{
	JTextField dp;
	public DisciplinePanel(ProgramFrame pf) {
		int truncatedNumber =(int) (pf.scheduler.getDiscipline()*100);
		double truncatedDouble = truncatedNumber/100.0;
		dp= new JTextField("your discipline: "+truncatedDouble);
		dp.setEditable(false);
		add(dp);
	}
}
