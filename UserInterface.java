import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserInterface {
	

	// main frame
	private JFrame frame;
	private JPanel tasks;
	
	// the backEnd of the app
	private Memory memory;

	// constructor of UserInterface
	public UserInterface(Memory memory) {

		this.memory = memory;
		
		// create the frame with a title
		frame = new JFrame("To Do");
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);//so the frame closes when we press x and not minimize or defocus
		frame.setSize(400, 600); // size of frame
		frame.setResizable(false); // disable frame resize so it always has this size
		setUp(); // to setup the main parts of the frame
		
		frame.setVisible(true); // so we can see the frame/window

		redisplay();
	}
	
	//set up the main frame
	private void setUp() {
		//the main panel in the frame
		JPanel pane = new JPanel();

		// layout of main panel 
		pane.setLayout(new BorderLayout());
		
		// panel for ToDo list
		tasks = new JPanel();
		// layout of ToDo items (tasks)
		tasks.setLayout(new BoxLayout(tasks, BoxLayout.Y_AXIS));
		
		// panel for adding new tasks
		JPanel addTask = new JPanel();
		// add the AddTaskUI object to the addTask panel (to allow users to add new ToDo items)
		addTask.add(new AddTaskUI(this, memory));
		// layout to stretch TextField
		addTask.setLayout(new BoxLayout(addTask, BoxLayout.X_AXIS));
		
		// add tasks part on top of main panel
		pane.add(tasks, BorderLayout.NORTH);
		
		// add adding tasks part on bottom of main panel
		pane.add(addTask, BorderLayout.SOUTH);
		
		// add the main panel to frame
		frame.add(pane);

	}
	
	// redisplays tasks based on backEnd
	public void redisplay() {
		tasks.removeAll();
		for(Task t: memory.getTasks()) {
			tasks.add(new TaskUI(t, memory, this));
		}
		frame.validate();
	}
}
