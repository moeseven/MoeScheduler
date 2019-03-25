package GUI;

import javax.swing.JFrame;

public class ProgramFrame extends JFrame{
	public ProgramFrame() {
		initialize();
	}
	private void initialize() {
		setVisible(true);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(new AddEntryPanel());
	}
}
