
public class Lecture extends Classes {

	public Lecture(String name, String day, String startTime, String endTime, String weekPattern, String location, int roomSize,
			int classSize, String staff, String department) {
		super(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staff, department);
		
	}
	
	public String getEntry()
	{
			//The output when the method is called
			String details = "name: ".toUpperCase() + getName() + "\n" + "day: ".toUpperCase() + getDay() + "\n" + "start time: ".toUpperCase() +  getStartTime() + "\n" + "end time: ".toUpperCase() 
			+ getEndTime() + "\n" + "week pattern: ".toUpperCase() + getWeekPattern() + "\n" + "location: ".toUpperCase() + getLocation() + "\n" + "room size: ".toUpperCase() + getRoomSize() 
			+ "\n" + "classes size: ".toUpperCase() + getClassSize() + "\n" + "staff: ".toUpperCase() + getStaff() + "\n" + "department: ".toUpperCase() 
			+ getDepartment() + "\n" + "\n";
				
		return details;
	}
	
}
