import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

/*
 * this class stores the backEnd of the app and uses serialization to store the state of the tasks arraylist
 * a better version would be storing the information of the arraylist manually as a string in a file for speed
 * that should be faster than serialization, but needs custom implementation so it can be done once project is finished if desired
 */
public class Memory {

	private ArrayList<Task> tasks;
	File file;

	
	public Memory() {
		tasks = new ArrayList<>();
		
		file = new File("src/Memory/memory.txt");
		
		// we only read the file if it has something to be read
		if(file.length() != 0) {
			try {
				ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
				tasks = (ArrayList<Task>) objectInputStream.readObject();
				objectInputStream.close();
				
			// if for some reason there is an exception:
			// we empty the file so we can use it later on by printing an empty string to it
			} catch (Exception e) {
				e.printStackTrace();
				PrintWriter writer;
				try {
					writer = new PrintWriter(file);
					writer.print("");
					writer.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}			
	}
	
	public ArrayList<Task> getTasks() {
		return tasks;
	}
	
	public void addTask(String text) {
		tasks.add(new Task(text));
		update();
	}
	
	public void removeTask(Task t) {
		tasks.remove(t);
		update();
	}
	
	// called when the data is modified
	public void update() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
			objectOutputStream.writeObject(tasks);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
