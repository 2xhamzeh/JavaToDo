
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddTaskUI extends JPanel{
	
	// main parts
	private JTextField textField;
	private JButton button;

	// backEnd item, add tasks to it when add is pressed
	private Memory memory;
	// used to redisplay after adding a new item to memory
	private UserInterface ui;

	// constructor
	public AddTaskUI(UserInterface ui, Memory m) {
		textField = new JTextField();
		button = new JButton("Add");

		memory = m;

		this.ui = ui;
		ui.redisplay();
		
		setup();
	}
	
	// setup main parts of adding new tasks
	private void setup() {
		// layout left to right
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		this.add(Box.createHorizontalStrut(5)); // space on left of TextField
		this.add(textField);
		this.add(button);
		// MouseListener when add is pressed
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				action();
			}
		});
		
		// key listener when enter is pressed in TextField to add new task
		textField.addKeyListener(new KeyAdapter() {
			  public void keyPressed(KeyEvent e)
			  {
				  if(e.getKeyCode() == 10 || e.getKeyCode() == 13) {
					  action();					  
				 }
			  }
			});
	}
	
	// called when add or enter is pressed
	private void action() {
		if(!textField.getText().equals("")) {
			memory.addTask(textField.getText());
			textField.setText("");
			ui.redisplay();
		}
	}
}
