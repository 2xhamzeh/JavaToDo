
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskUI extends JPanel{
	
	// main parts of a ToDo object
	private JCheckBox checkbox;
	private JLabel text;
	private JButton removeButton;
	
	// the texts we use when the box is checked or unchecked
	private String normalText;
	private String crossedText;
	
	private UserInterface ui;
	private Task task; // stored a copy of its backEnd self so it can modify state based on ui interactions
	private Memory memory; // only need memory to access tasks arraylist and remove self when remove is clicked
	
	// constructor takes the text as string and the tasks panel
	public TaskUI(Task task, Memory memory, UserInterface ui) {
		this.task = task;
		this.memory = memory;
		this.ui = ui;

		checkbox = new JCheckBox();
		checkbox.setSelected(task.getIsDone()); // set checkbox state based on task state

		removeButton = new JButton("Remove");
		
		normalText = task.getText(); // normal text
		crossedText = "<HTML><s>" + task.getText() + "</s></HTML>"; // this text is crossed version of parameter
		
		// set the initial text based on the task state (crossed or normal)
		if(task.getIsDone()) {
			text = new JLabel(crossedText);
		} else {
			text = new JLabel(normalText);
		}
		
		setup(); // setup main parts of a ToDo item
	}
	
	private void setup() {
		// layout left to right
		this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		this.add(checkbox);
		this.add(text);
		this.add(Box.createHorizontalGlue()); // space between text and remove button
		this.add(removeButton);
		
		// to remove item from tasks when remove is pressed
		removeButton.addActionListener(e -> action());
		// to change text style when CheckBox is clicked
		checkbox.addActionListener(e -> checked());

		
	}
	
	// called when remove button is clicked
	private void action() {
		// remove the first task in memory with the same text as this object
		memory.removeTask(task);
		ui.redisplay();
	}
	
	// called when CheckBox is checked
	private void checked() {
		if(checkbox.isSelected()) {
			text.setText(crossedText);		
		} else {
			text.setText(normalText);
		}
		task.isDone();
		memory.update();
	}
}
