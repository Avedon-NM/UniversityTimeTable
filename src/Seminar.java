//Student Number: 2610237
public class Seminar extends Classes {

	public Seminar(String name, String day, String startTime, String endTime, String weekPattern, String location, int roomSize,
			int classSize, String staff, String department) {
		super(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staff, department);

	}
	
	public String getEntry()
	{
		String details;
		String warning;
		int percentage = (getRoomSize() / 100) * 10;//Calculating 10%
		
		if ((getRoomSize()/2) > getClassSize())//if half of room size is greater than class size 					
		{
				warning = " - (Class size less than half the size of room size)";
				details = "name: " + getName() + "\n" + "day: " + getDay() + "\n" + "start time: " +  getStartTime() + "\n" + "end time: " 
				+ getEndTime() + "\n" + "week pattern: " + getWeekPattern() + "\n" + "location: " + getLocation() + "\n" + "room size: " + getRoomSize() 
				+ "\n" + "classes size: " + getClassSize() + warning.toLowerCase() + "\n" + "staff: " + getStaff() + "\n" + "department: " 
				+ getDepartment() + "\n" + "\n";
		}
		
		else if (getRoomSize()  < (getClassSize() - percentage)) //If class size is more than 10% above room size					
		{
				warning = " - (Warning: Class size more than 10% above room size)";
				details = "name: " + getName() + "\n" + "day: " + getDay() + "\n" + "start time: " +  getStartTime() + "\n" + "end time: " 
				+ getEndTime() + "\n" + "week pattern: " + getWeekPattern() + "\n" + "location: " + getLocation() + "\n" + "room size: " + getRoomSize() 
				+ "\n" + "classes size: " + getClassSize() + warning.toLowerCase() + "\n" + "staff: " + getStaff() + "\n" + "department: " 
				+ getDepartment() + "\n" + "\n";
		}
		
		else 
			
		{
			details = "name: " + getName() + "\n" + "day: " + getDay() + "\n" + "start time: " +  getStartTime() + "\n" + "end time: " 
			+ getEndTime() + "\n" + "week pattern: " + getWeekPattern() + "\n" + "location: " + getLocation() + "\n" + "room size: " + getRoomSize() 
			+ "\n" + "classes size: " + getClassSize() + "\n" + "staff: " + getStaff() + "\n" + "department: " 
			+ getDepartment() + "\n" + "\n";
		}
		
		return details;
	}

}//getEntry
