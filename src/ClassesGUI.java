import java.awt.*;
import java.util.List;
import java.awt.event.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import javax.swing.*;
import javax.swing.event.AncestorListener;

import org.omg.CORBA.portable.ApplicationException;

import java.util.regex.*;

import java.lang.Number;
//Student Number: 2610237
public class ClassesGUI extends JFrame implements ActionListener {
	String sHours[] = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
	String sMins[] = { "00", "05", "30"};
	String eHours[] = {"8", "9", "10", "11", "12", "13", "14", "15", "16", "17" };
	String eMins[] = {"00", "30", "55" };
	String cTyp[] = { ".L", ".S", ".CL" };
	String cDy[] = { "Monday", "Tuesday", "Wednesday", "Thursday", "Friday" };

	//Adding buttons, text fields and labels.
	private JTextField name = new JTextField(20);
	private JComboBox<String> sHour = new JComboBox<String>(sHours);
	private JComboBox<String> sMin = new JComboBox<String>(sMins);
	private JComboBox<String> eHour = new JComboBox<String>(eHours);
	private JComboBox<String> eMin = new JComboBox<String>(eMins);
	private JComboBox<String> cType = new JComboBox<String>(cTyp);
	private JComboBox<String> cday = new JComboBox<String>(cDy);
	private JTextField weekPattern = new JTextField(15);
	private JTextField location = new JTextField(15);
	private JTextField roomSize = new JTextField(7);
	private JTextField classSize = new JTextField(7);
	private JTextField staff = new JTextField(20);
	private JTextField department = new JTextField(20);

	private JLabel labn = new JLabel(" Name:");
	private JLabel labday = new JLabel(" Day:");
	private JLabel labstart = new JLabel(" Start Time:");
	private JLabel labend = new JLabel(" End Time:");
	private JLabel labpattern = new JLabel(" Week Pattern:");
	private JLabel labloc = new JLabel(" Location:");
	private JLabel labrsize = new JLabel(" Room Size:");
	private JLabel labcsize = new JLabel(" Classes Size ");
	private JLabel labstaff = new JLabel(" Staff");
	private JLabel labdep = new JLabel(" Department");
	private JLabel ctype = new JLabel(" Class Type");

	private JButton searchClass = new JButton("Search Class");
	private JButton searchName = new JButton("Search Name");
	private JButton download = new JButton("Download");
	private JButton print = new JButton("Print");
	private JButton add = new JButton("Add");
	private JButton clear = new JButton("Clear");
	private JButton uploadFile = new JButton("Upload File");

	//Creating an object of ClassRecords class
	private ClassRecords myClasses = new ClassRecords();

	//Setting up scroll bar
	private JTextArea outputArea = new JTextArea(45, 60);
	private JScrollPane scroll = new JScrollPane(outputArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
			JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

	//To take in parameters of the main method
	private File inputFile;
	private File outputFile;
	private PrintWriter pw;
	private FileWriter html;
	private String exceptions = "";//All exceptions are collected by this



	public static void main(String[] args) throws Throwable
	{

		//Taking in parameters
		String inputFileName = (args[0]);
		String outputFileName = (args[1]);
		String downloadName = (args[2]);

		try
		{
			//Passing parameters into new class
			ClassesGUI applic = new ClassesGUI(inputFileName, outputFileName, downloadName);
		}
		catch (Exception e) {
			System.err.print("Exception found: " + e);
		}

	} // main


	// set up the GUI and receiving parameters from the main method
	public ClassesGUI(String inputFileName, String outputFileName, String downloadFileName) throws IOException {
		super("Classes Record");
		setLayout(new FlowLayout());

		//Parameters being put in the global variable of the class we want to use
		inputFile = new File(inputFileName);
		outputFile = new File(outputFileName);
		try {
			html = new FileWriter(downloadFileName);
		}
		catch (Exception e) {
			System.err.print("Exception found: " + e);
		}


		//Addng labels and buttons to the GUI
		add(labn);
		add(name);

		add(labday);
		add(cday);

		add(labstart);
		add(sHour);
		add(sMin);

		add(labend);
		add(eHour);
		add(eMin);

		add(ctype);
		add(cType);

		add(labpattern);
		add(weekPattern);

		add(labloc);
		add(location);

		add(labrsize);
		add(roomSize);

		add(labcsize);
		add(classSize);

		add(labstaff);
		add(staff);

		add(labdep);
		add(department);

		add(uploadFile);
		uploadFile.addActionListener(this);

		add(searchName);
		searchName.addActionListener(this);

		add(searchClass);
		searchClass.addActionListener(this);		

		add(download);
		download.addActionListener(this);

		add(print);
		print.addActionListener(this);

		add(add);
		add.addActionListener(this);

		add(clear);
		clear.addActionListener(this);

		add(scroll);

		outputArea.setEditable(false);

		setSize(800, 1000);
		setVisible(true);
		clearText();

	}//Setting up GUI complete

	//Takes in all search results made
	List<Classes> results = new ArrayList<Classes>();
	// constructor

	// listen for and respond to GUI events
	public void actionPerformed(ActionEvent event)
	{
		if (event.getSource() == uploadFile)
		{
			//Try catch block incase user uses invalid file type
			try {
				Scanner inFile = new Scanner(inputFile);
				inFile.nextLine();

				//File is read one line at a time
				while (inFile.hasNextLine()) {

					//Boolean flag so invalid entries are not added
					boolean ok = true; 
					String line;
					String lineParts[];//Splitting line into array
					String name, day, weekPattern, location, staff, department, startTime, endTime;
					int roomSize = 0, classSize = 0;

					line = inFile.nextLine();
					lineParts = line.split("\\t");

					name = lineParts[0];
					day = lineParts[1];
					startTime = lineParts[2];
					endTime = lineParts[3];
					weekPattern = lineParts[4];
					location = lineParts[5];

					//Try catch block incase user uses invalid file type
					try 
					{	
						roomSize = Integer.parseInt(lineParts[6]);
						classSize = Integer.parseInt(lineParts[7]);
					} catch (NumberFormatException e) 

					{
						//Exceptions being held in a variable
						exceptions = exceptions + ("Exception found: " + e.getMessage() + " invalid entry. " + name + " not added \n");
						ok = false;
					}
					staff = lineParts[8];
					department = lineParts[9];

					if (ok)
					{	
						myClasses.sortFileEntries(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staff,
								department);
					}
				}

				inFile.close();						
				String fileEntryFeedback = "";
				fileEntryFeedback = myClasses.upLoadFeedback(fileEntryFeedback);//Feedback from methods processing data
				outputArea.setText(fileEntryFeedback + "\n" + exceptions);
				outputArea.setCaretPosition(0);
			}


			//Try catch block incase user uses invalid file type
			catch (FileNotFoundException e) 
			{
				exceptions = exceptions + ("IOException: " + e.getMessage() + ", not found \n");
			}
		} //Action load


		if (event.getSource() == add) 
		{	//Checks all fields are completed first
			if(name.getText().equals("") || weekPattern.getText().equals("") ||	location.getText().equals("") || roomSize.getText().equals("") 
					|| classSize.getText().equals("") || staff.getText().equals("") || department.getText().equals(""))
			{
				outputArea.setText("All fields must be filled to add class");
			}

			else 
			{
				//Sorting inputed information to make it easier to validate and compare data types
				String start = (String) sHour.getSelectedItem() + (String) sMin.getSelectedItem();
				String end = (String) eHour.getSelectedItem() + (String) eMin.getSelectedItem();
				int st = Integer.parseInt(start); //Converting to int so  they can be compared 
				int en = Integer.parseInt(end); //Converting to int so  they can be compared 				
				String rS = roomSize.getText();
				String cS = classSize.getText();
				String wPattern = weekPattern.getText();
				String classType = (String) cType.getSelectedItem();
				String inputCheck = myClasses.checkInput(st, en, rS, cS, wPattern);//Validating inputs

				if(inputCheck.equals(""))

				{
					String entryFeedback = "";
					//Sorting them to how they are supposed to be stored and how they will appear
					start = (String) sHour.getSelectedItem() + ":" + (String) sMin.getSelectedItem();
					end = (String) eHour.getSelectedItem() + ":" + (String) eMin.getSelectedItem();
					int rSize = Integer.parseInt(rS); //Needs to be int so they can be compared in the get entry method
					int cSize = Integer.parseInt(cS); //Needs to be int so they can be compared in the get entry method					
					String cName = name.getText();
					String cDay = (String) cday.getSelectedItem();
					String cLocation = location.getText();
					String cStaff = staff.getText();
					String cDepartment = department.getText();
					cName = cName + classType;
					myClasses.sortUserEntries(cName, cDay, start, end, wPattern, cLocation, rSize,cSize, cStaff, cDepartment);
					outputArea.setText(myClasses.userInputFeedback(entryFeedback));//Fetching feedback from data processing methods
				}

				else 

				{	//To be printed in in the event of failure
					outputArea.setText(inputCheck);
				}
			}


		} //Action add



		//only searches the name
		if (event.getSource() == searchName) 
		{  
			results.clear();
			String cName = name.getText();
			List<Classes>temp = myClasses.searchName(cName);
			results.addAll(temp);
			String result = "";
			if (results.size() == 0)
			{
				result = "No result found \n\n";
			} 
			else
			{ 
				//Copying classes from results list to be printed
				for(Classes cl : results)
					result = result + cl.getEntry();
			}
			outputArea.setText(result);
			outputArea.setCaretPosition(0);

		}//Action searchName


		if (event.getSource() == searchClass) 
		{	
			//Responds when the button is pressed with no info entered		
			if(name.getText().equals("") || weekPattern.getText().equals(""))
			{
				outputArea.setText("Please enter Class name, week pattern, day and time to uniquely identify a class \n\n");
			}

			else 
			{			
				//Declaring variables		
				String cName = name.getText();
				String cDay = (String) cday.getSelectedItem();
				String start = (String) sHour.getSelectedItem() + ":" + (String) sMin.getSelectedItem();
				String end = (String) eHour.getSelectedItem() + ":" + (String) eMin.getSelectedItem();
				String wPattern = weekPattern.getText();			

				//Searches for entry which matches the following parameters
				List<Classes>temp = myClasses.searchClass(cName, cDay, start, end, wPattern);
				results.addAll(temp);
				String result = "";
				if (results.size() == 0)
				{
					result = "No result found \n\n";
				} 
				else
				{ 
					for(Classes cl : results)
						result = result + cl.getEntry();
				}
				outputArea.setText(result);
				outputArea.setCaretPosition(0);
			}
		} //Action search class


		if (event.getSource() == print) 
		{	//Checking there is info in the outputArea to print 
			if(results.isEmpty())

			{
				outputArea.setText("No classes selected\n\n");
			}

			else
			{	
				try 
				{
					//Calls the global variable (The one which gets args from main class))
					File outputFile = this.outputFile;
					pw = new PrintWriter(outputFile);
				} catch (Exception e1) 

				{
					//Try and catch to detect invalid file types
					outputArea.setText("Exception found: " + e1.getMessage() + " invalid file type \n");
					System.exit(0);
				}
				String name, day, startTime, endTime, weekPattern, location, staff, department;
				int roomSize = 0, classSize = 0;
				ListIterator<Classes> iter = results.listIterator();
				String details = "";			

				//Creating the headers and setting file format
				name = "Name\t\t";
				day = "Day\t";
				startTime = "Start Time\t";
				endTime = "End Time\t";
				weekPattern = "Week Pattern\t\t";
				location = "Location\t";
				String room = "Room Size\t";
				String classS = "Class Size\t";
				staff = "Staff\t\t";
				department = "Department\t\t";

				//Printing the headers of the file 
				pw.println(name + day + startTime + endTime + weekPattern + location + room + classS + staff + department);
				while (iter.hasNext()) 
				{
					Classes current = iter.next();
					name = current.getName();
					day = current.getDay();
					startTime = current.getStartTime();
					endTime = current.getEndTime();
					weekPattern = current.getWeekPattern();
					location = current.getLocation();
					roomSize = current.getRoomSize();
					classSize = current.getClassSize();
					staff = current.getStaff();
					department = current.getDepartment();

					//Formatting body of the text
					details = name + "\t" + day + "\t" +  startTime + "\t\t" +  endTime + "\t\t"  + weekPattern + "\t\t" +  location + 
							"\t\t"	+ roomSize + "\t\t" + classSize 	+ "\t\t" +  staff + "\t\t" + department;

					pw.print(details + "\r\n");
					//Printing each line
				}
				pw.close();
				outputArea.setText("File Created\n\n");
			}

		} //Action print


		if (event.getSource() == download) 
		{
			if(results.isEmpty())

			{
				outputArea.setText("No classes selected\n\n");
			}
			//Maximum of 10 classes to download into HTML
			else if(results.size() >= 11)

			{
				outputArea.setText("Only 10 Classes can be downloaded at a time \n\n");
			}
			else
			{	//The input is passed from the global variable
				FileWriter html = this.html;
				try 
				{
					ListIterator<Classes> iter = results.listIterator();

					html.write("<html>");
					html.write("<h1> Time Table</h1>");	
					html.write("<br>");
					html.write("</html>");

					//Iterating through the list and assigning elements to variables
					while (iter.hasNext()) 
					{
						Classes current = iter.next();
						String name = current.getName();
						String day = current.getDay();
						String startTime = current.getStartTime();
						String endTime = current.getEndTime();
						String weekPattern = current.getWeekPattern();
						String location = current.getLocation();
						int roomSize = current.getRoomSize();
						int classSize = current.getClassSize();
						String staff = current.getStaff();
						String department = current.getDepartment();
						String htmlTxt = myClasses.makeHTML(name, day, startTime, endTime, weekPattern, location, roomSize, classSize, staff, department);
						html.write(htmlTxt);
					}
					html.close();

				}//Catching missing file type
				catch (IOException e) {
					outputArea.setText("IOException: " + e.getMessage() + "Name missing file type\n\n");
					e.printStackTrace();

				}

				outputArea.setText("HTML File Created\n\n");
			}
		}//Action Download


		if (event.getSource() == clear) 

		{
			//Clears output area and values stored in results
			outputArea.setText(null);
			results.clear();
		}

		clearText();

	} // actionPerformed


	//clears text fields after execution
	public void clearText()
	{
		name.setText(""); 
		location.setText(""); 
		roomSize.setText(""); 
		classSize.setText("");
		staff.setText(""); 
		department.setText("");
		weekPattern.setText("");
		sHour.setSelectedIndex(0);
		sMin.setSelectedIndex(0);
		eHour.setSelectedIndex(0);
		eMin.setSelectedIndex(0);
		cType.setSelectedIndex(0);
		cday.setSelectedIndex(0);
	}

} // TrainingRecordGUI