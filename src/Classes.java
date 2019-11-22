import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.Number;
import java.util.Scanner;
//Student Number: 2610237
/**
 * 
 * 
 *
 */
public class Classes {

	String name, day, startTime, endTime, weekPattern, location, staff, department;
	int roomSize, classSize;

	//Constructor
	public Classes(String name, String day, String startTime, String endTime, String weekPattern, String location,
			int roomSize2, int classSize2, String staff, String department) {
		this.name = name;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.weekPattern = weekPattern;
		this.location = location;
		this.roomSize = roomSize2;
		this.classSize = classSize2;
		this.staff = staff;
		this.department = department;
	}

	public String getName() {
		return name;
	}

	public String getDay() {
		return day;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getWeekPattern() {
		return weekPattern;
	}

	public String getLocation() {
		return location;
	}

	public int getRoomSize() {
		return roomSize;
	}

	public int getClassSize() {
		return classSize;
	}

	public String getStaff() {
		return staff;
	}

	public String getDepartment() {
		return department;
	}

	//Not requires as data can only be saved as either Lecture, Lab or Seminar. 
	public String getEntry() 
	{		
		String details = "";
		return details;
	}
}