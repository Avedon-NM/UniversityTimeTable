
//Student Number: 2610237
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

//import org.omg.CORBA.SystemException;

public class ClassRecords {

	private List<Classes> classEntry;
	//Global variables used to gather feedback and send it into the ClassesGUI methods
	private String userEntryFeedBack = "";
	private String fileEntryFeedBack ="";
	private String fileErrors = "Errors Found ...\n";
	private String userErrors = "";

	public ClassRecords() 
	{
		classEntry = new ArrayList<Classes>();
	} //Constructor


	//Add user inputs
	public void addUserEntry(Classes entry) 
	{
		ListIterator<Classes> iter = classEntry.listIterator();
		String result = "";
		//Checks if class exists first by comparing it to every class in the array list
		while (iter.hasNext()) 
		{
			Classes current = iter.next();
			if (current.getName().toLowerCase().equals(entry.getName().toLowerCase()) && current.getDay().equals(entry.getDay())
					&& current.getStartTime().equals(entry.getStartTime()) && current.getEndTime().equals(entry.getEndTime()) 
					&& current.weekPattern.equals(entry.getWeekPattern()))
			{
				//Error messages for when a match is found
				result = result + "ERROR:  " + entry.getName() + "  "+ entry.getDay() + "  " + entry.getStartTime() + "-" + 
						entry.getEndTime() + "  already exists \n";	
			}

		}//addUserEntry


		if (result.equals("")) 
		{
			//Sends feedback when class is successfully added
			classEntry.add(entry);
			userEntryFeedBack = entry.getName() + "  "+ entry.getDay() + "  " + entry.getStartTime() + "-" + 
					entry.getEndTime() + " successfully added\n";
		}

		else
		{
			//Sends error messages when class has not been added
			userErrors = userErrors + result;
		}
	}

	//Method used to add uploaded files
	public void addFileEntry(Classes entry) 
	{
		ListIterator<Classes> iter = classEntry.listIterator();
		String result = "";
		//Checks if class exists first by comparing it to every class in the array list
		while (iter.hasNext()) 
		{
			Classes current = iter.next();
			if (current.getName().toLowerCase().equals(entry.getName().toLowerCase()) && current.getDay().equals(entry.getDay())
					&& current.getStartTime().equals(entry.getStartTime()) && current.getEndTime().equals(entry.getEndTime()) 
					&& current.weekPattern.equals(entry.getWeekPattern()))
			{
				//Error messages for when a match is found
				result = result + " " + entry.getName() + "  "+ entry.getDay() + "  " + entry.getStartTime() + "-" + 
						entry.getEndTime() + "  already exists \n";	
			}

		}

		if (result.equals("")) 
		{
			//Sends feedback when class is successfully added
			classEntry.add(entry);
			fileEntryFeedBack = "File Uploaded \n\n";
		}

		else
		{
			//Sends error messages when class has not been added
			fileErrors = fileErrors + result;
		}

	} // addFileEntry


	//Searching for name using substrings
	public List<Classes> searchName(String n) {
		ListIterator<Classes> iter = classEntry.listIterator();
		List<Classes> result = new ArrayList<Classes>();
		while (iter.hasNext()) 
		{
			Classes current = iter.next();
			if (current.getName().toLowerCase().contains(n.toLowerCase()))
			{
				result.add(current);
			}
		}
		return result;
	} //Search name


	//Search for a specific class
	public List<Classes> searchClass(String name, String day, String start, String end, String pattern) 
	{
		ListIterator<Classes> iter = classEntry.listIterator();
		List<Classes> results = new ArrayList<Classes>();
		while (iter.hasNext()) 
		{
			Classes current = iter.next();
			if (current.getName().toLowerCase().contains(name.toLowerCase()) && current.getDay().toLowerCase().equals(day.toLowerCase()) 
					&& current.getStartTime().equals(start) && current.getEndTime().equals(end) && current.getWeekPattern().equals(pattern))
			{
				results.add(current);
			}
		}
		return results;
	} //Search class


	//Verification check for added inputs
	public String checkInput(int start, int end, String rSize, String cSize, String pattern )
	{
		//Validating user input before adding their class
		String result = "";
		if (!pattern.matches("[\\s\\d,-]+$"))
		{
			result = " Error: " + pattern + " invalid input for Week Pattern. "  + "Only numbers, comas  and dashes permitted\n";
		}

		if (start >= end)					
		{
			result = result + " Error: Start time cannot be the same or greater than end time \n";
		}		

		if (!rSize.matches("[\\d]+"))
		{
			result = result + " Error: Room Size: Invalid input\n";
		}

		if (!cSize.matches("[\\d]+"))
		{
			result = result + " Error: Class Size: Invalid input. \n";
		}

		return result;

	} //checkInput


	//method to add entries from a file
	public void sortFileEntries(String name, String day, String startTime, String endTime, String weekPattern,
			String location, int roomSize2, int classSize2, String staff, String department) 
	{
		//Adds entry to the proper class based on class type
		if (name.contains(".L")) {

			name = name.toUpperCase();
			day = day.toUpperCase();
			location = location.toUpperCase();
			staff = staff.toUpperCase();
			department = department.toUpperCase();

			Lecture lecture1 = new Lecture(name, day, startTime, endTime, weekPattern, location, roomSize2,
					classSize2, staff, department);
			addFileEntry(lecture1);
		}

		//Adds entry to the proper class based on class type
		else if (name.contains(".CL"))

		{
			name = name.toLowerCase();
			day = day.toLowerCase();
			location = location.toLowerCase();
			staff = staff.toLowerCase();
			department = department.toLowerCase();

			Lab lab1 = new Lab(name, day, startTime, endTime, weekPattern, location, roomSize2, classSize2, staff,
					department);
			addFileEntry(lab1);
		}

		//Adds entry to the proper class based on class type
		else if (name.contains(".S"))

		{
			name = name.toLowerCase();
			day = day.toLowerCase();
			location = location.toLowerCase();
			staff = staff.toLowerCase();
			department = department.toLowerCase();

			Seminar seminar1 = new Seminar(name, day, startTime, endTime, weekPattern, location, roomSize2,
					classSize2, staff, department);
			addFileEntry(seminar1);
		}

		else 
		{
			fileErrors = fileErrors + name + " " + " "+ day + " not added, missing class type \n";
		}


	}//sortEntries


	//Sorts user entries
	public void sortUserEntries(String name, String day, String startTime, String endTime, String weekPattern,
			String location, int roomSize2, int classSize2, String staff, String department) 
	{
		//Adds entry to the proper class based on class type
		if (name.contains(".L")) {

			name = name.toUpperCase();
			day = day.toUpperCase();
			location = location.toUpperCase();
			staff = staff.toUpperCase();
			department = department.toUpperCase();

			Lecture lecture1 = new Lecture(name, day, startTime, endTime, weekPattern, location, roomSize2,
					classSize2, staff, department);
			addUserEntry(lecture1);
		}

		//Adds entry to the proper class based on class type
		else if (name.contains(".CL"))

		{
			name = name.toLowerCase();
			day = day.toLowerCase();
			location = location.toLowerCase();
			staff = staff.toLowerCase();
			department = department.toLowerCase();

			Lab lab1 = new Lab(name, day, startTime, endTime, weekPattern, location, roomSize2, classSize2, staff,
					department);
			addUserEntry(lab1);
		}

		//Adds entry to the proper class based on class type
		else if (name.contains(".S"))

		{
			name = name.toLowerCase();
			day = day.toLowerCase();
			location = location.toLowerCase();
			staff = staff.toLowerCase();
			department = department.toLowerCase();

			Seminar seminar1 = new Seminar(name, day, startTime, endTime, weekPattern, location, roomSize2,
					classSize2, staff, department);
			addUserEntry(seminar1);
		}

	}//sortUserEntries




	public String upLoadFeedback(String feedback)
	{
		//Global feedback variables are transfered when method is called and variables reset 
		feedback = fileEntryFeedBack + fileErrors;
		fileEntryFeedBack = "";
		fileErrors = "";
		return feedback;
	}//loadFeedback


	public String userInputFeedback(String feedback)
	{
		//Global variables are transfered when method is called and then reset 
		feedback = userEntryFeedBack + userErrors;
		userEntryFeedBack = "";
		userErrors = "";
		return feedback;
		
	}//userInputFeedback


	//Creating a HTML body text
	public String makeHTML (String name, String day, String startTime, String endTime, String weekPattern,
			String location, int roomSize, int classSize, String staff, String department)
	{

		String htmlTxt = 

				"<b>Name:&nbsp</b>" + name + "<br>"
						+"<b>Day:&nbsp</b>" + day + "<br>"
						+"<b>Start Time:&nbsp</b>" + startTime + "<br>"
						+"<b>End Time:&nbsp</b>" + endTime + "<br>"
						+"<b>Week Pattern:&nbsp</b>" + weekPattern + "<br>"
						+"<b>Location:&nbsp</b>" + location + "<br>"
						+"<b>Room Size:&nbsp</b>" + roomSize + "<br>"
						+"<b>Class Size:&nbsp</b>" + classSize + "<br>"
						+"<b>Staff:&nbsp</b>" + staff + "<br>"
						+"<b>Department:&nbsp</b>" + department + "<br>"
						+"<br>"										
						+"</body>"
						+"</html>";	


		return htmlTxt;
	}

} //ClassRecords