package Thread.Thread04;

public class Task implements Comparable<Task>{

	private int id ; 
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Task task) {
//		if(this.id > task.id){
//			return 1;
//		} else if (this.id == task.id){
//			return 0;
//		} else {
//			return -1;
//		}
		return this.id > task.id ? 1 : (this.id < task.id ? -1 : 0);
		
	}

}
