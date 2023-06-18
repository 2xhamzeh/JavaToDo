import java.io.Serializable;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String text;
	private boolean isDone;
	
	public Task(String text) {
		this.text = text;
		isDone = false;
	}

	public Task() {
		isDone = false;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean getIsDone() {
		return isDone;
	}
	
	public void isDone() {
		isDone = !isDone;
	}
}