//Student Number: 2610237
public class Lab extends Classes {
	//Inheriting from Classes
	public Lab(String name, String day, String startTime, String endTime, String weekPattern, String location, int roomSize,
			int classSize, String staff, String department) 
	{
		super(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staff, department);
	}
	
	//The output when the method is called
	public String getEntry()
	{
		String details;
		String warning;
		
		if ((getRoomSize()) < (getClassSize()) ) //Sends additional message if room size is less than class size				
		{
				warning = "  - Warning: Class size bigger than room size".toLowerCase();
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

}
