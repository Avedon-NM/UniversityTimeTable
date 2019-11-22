Timetable Builder
The program allows user to search, build and print time table.

Object Model and Design
The data in the text files were split into 3 seperate types; Lectures, Seminars and Labs so three classes were used to 
for each entry type. They all inherited from the super class called "Classes" so that the data can be kept in one array
list. Another two classes used were the ClassesGUI and the ClassRecords; the first one 
was mainly used for the graphic interface and the second one managed the data. 

To help minimize errors and to have a more structured systems certain textfields were restricted through the use of non editable combo boxes for entries such
as "days", "start time" and "end time". This is convenient for the user as well as guaranteeing the entered information will always be the right data type. 
Class size and Room Size was strictly set to only accept numbers and Week Pattern only accepts comas, dashes and digits. 

Using a combination of try and catch blocks and if statements the program is fully equiped to handle any user input errors such as using. 
Program also makes sure only one entry exist. 
The object model used is saved as a file in this folder. 

Data
The assumptions which were made about the data were that it was mainly for students so as a result there were 
certain errors which were seen as acceptable where as others were not. Empty spaces in the staff section are
ignored by the program and the classes are added how ever errors such as "16*20" appearing in the week pattern section are 
not added because they are no one can figure out when they actually take place which makes them useless. 
A large portion of the data had dates in the week pattern section so the decision was made to just leave them in
as strings because using the entry a student knows where to go and when. All exceptions and errors are printed with
the name of the class to make it easier to identify them and edit them in txt form. 
Duplicates are also printed on the screen to make them easy to rectify. 

Instructions
User needs to input three arguments for the program to launch. Inputfile name should be first, outputfilename
second and html file name last.

Load Button: Loads file and lists any errors found.

Search Name: Mainly used during testing. Lists all entries when nothing is typed in the text box and lists classes
containing the string pattern of the textbox.  

Search Class: Search specific class. User needs to enter the date, start time, end time and week pattern to 
uniquely identify class. For more convenience to the user if they misspell something the program simply ignores 
the input and they can try again without having to start from the begining. 

Download: Create a html file, maximum of 10 classes. Even though the text area clears after download, the timetable does not clear
from the list. This is by design in case user wants to down load and print the same table. There is a clear button for
starting afresh.
 
Print: Creates a tab seperated txt file. Even though the text area clears after print, the timetable does not clear
from the list. This is by design in case user wants to download and print the same table.

Add: Adds a class, user notified when invalid data types are entered

Clear: Wipes output text area and well as the list containing the timetable. 

 



Author
Student Number: 2610237