package homework_one;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.Serializable;
// this class stores information of each course and turn them into an arraylist;
// this class also shows the main menu of the system: log in as administrator/studentm --- manage courses or view reports 

public class course implements java.io.Serializable{
	private static final long serialVersionUID = 1057884876467423394L;
	public String courseName;
	public String courseId;
	public int maximumStu;
	public int currentStu;
	public ArrayList<Student> studentName = new ArrayList<>();
	public String instructor;
	public int courseSection;
    public String location;
    static ArrayList<course> newCourse = readFromCSV();
	
	
	public course() {
		
	}
	public course(String courseName, String courseId, int maximumStu, int currentStu,
			ArrayList<Student> studentName,String instructor, int courseSection, String location){
		this.courseName = courseName;
		this.courseId = courseId;
		this.maximumStu = maximumStu;
		this.currentStu = currentStu;
		this.studentName = studentName;
		this.instructor = instructor;
		this.courseSection = courseSection;
		this.location = location;
		
		
	}
	
	// getters and setters
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName  = courseName;
	}
	
	public String getCourseId() {
		return courseId;
	}
	public void setCourseId(String courseId) {
		this.courseId  = courseId;
	}
	
	public int getMaximumStu() {
		return maximumStu;
	}
	
	public void setMaximumStu(int maximumStu) {
		this.maximumStu  = maximumStu;
	}
	
	public int getCurrentStu() {
		return currentStu;
	}
	
	public void setCurrentStu(int currentStu) {
		this.currentStu  = currentStu;
	}
	
	public ArrayList <Student> getStudentName(){
		return studentName;
	}
	
	public void setStudentName(ArrayList <Student> studentName){
		this.studentName = studentName;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setInstructor(String instructor) {
		this.instructor  = instructor;
	}
	public int getCourseSection() {
		return courseSection;
	}
	public void setCourseSection(int courseSection){
		this.courseSection = courseSection;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String toString() {
		return "Course_name: "+ courseName + ", Course_Id: "
				+ courseId + ", Maximum_Stu: " + maximumStu + ", Current_Stu :" 
				+ currentStu + ", List_of_Name: " + studentName + ", Instructor: "
				+ instructor + ", Section_Number: " + courseSection + ", Course_Location: " + location;
	}
	
	// read information from csv file and make them a course arraylist
	public static ArrayList<course> readFromCSV(){
		ArrayList<course> newCourse = new ArrayList <>();
		String filename = "/Users/ichisei/Desktop/MyUniversityCourses.csv";// path of csv file 
		String line = " ";
		
		
		try {	
			BufferedReader br = new BufferedReader(new FileReader(filename));
			br.readLine();// skip the first line
			
			while((line = br.readLine()) != null ) {
				String[] values = line.split(",");
				String[] stuName = values[4].split(".");
				ArrayList<Student> studentName = new ArrayList<>();
				for (int i = 0; i < stuName.length; i++) {
					String[]name = stuName[i].split(" ");
					Student adds = new Student(name[0], name[1]);
					studentName.add(adds);
				}
				course newC = new course(values[0], values[1], Integer.parseInt(values[2]), Integer.parseInt(values[3]), studentName, values[5], Integer.parseInt(values[6]), values[7]);
				newCourse.add(newC);

			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		}  
		return newCourse;
	}
	
	// print the arraylist 
	public void display() {
	   String name = " ";
	   
	   for (int s = 0; s < studentName.size();s ++) {
		   String first = studentName.get(s).getFirstName();
		   String last = studentName.get(s).getLastName();

		   name = name + first +" "+ last + " ";
	   }
	   System.out.println("Course_name: "+ courseName + ", Course_Id: "
			   + courseId + ", Maximum_Stu: " + maximumStu + ", Current_Stu :" 
			   + currentStu + ", List_of_Name: " + name + ", Instructor: "
			   + instructor + ", Section_Number: " + courseSection + ", Course_Location: " + location);
	}
	
	// print courses for an administrator
	public void adView() {
		String name = " ";
		  
		for (int s = 0; s < studentName.size();s ++) {
			String first = studentName.get(s).getFirstName();
			String last = studentName.get(s).getLastName();

			name = name + first +" "+ last + " ";
		}
		System.out.println("Course_name: "+ courseName + ", Course_Id: "
				+ courseId + ", Maximum_Stu: " + maximumStu + ", Current_Stu :" 
				+ currentStu + ", List_of_Name: " + name );
	}
	
	// print course information for a student
	public void studentView() {
		String name = " ";
		  
		for (int s = 0; s < studentName.size();s ++) {
			String first = studentName.get(s).getFirstName();
			String last = studentName.get(s).getLastName();

			name = name + first +" "+ last + " ";
		}
		System.out.println("Course_name: "+ courseName + ", Course_Id: "
				+ courseId + ", Maximum_Stu: " + maximumStu + ", Current_Stu :" 
				+ currentStu + ", Instructor: "
				+ instructor + ", Section_Number: " + courseSection + ", Course_Location: " + location  );
	}
	
    
	// serialization and deserialization
	public static void serialization(String filename, Object o) {
		try {
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream oos = new ObjectOutputStream(file);
			oos.writeObject(o);
			oos.close();
			file.close();
			//System.out.println("Serialization complete! ");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	// deserialize course arraylists
	public static void deserialization() {
		try {
			 FileInputStream fis = new FileInputStream("Ser_MyUniversityCourses.ser");
			 ObjectInputStream ois = new ObjectInputStream(fis);
			 //arraylist.clear();
			 ArrayList<course> d=  (ArrayList) ois.readObject();
			 ois.close();
			 fis.close();
			 newCourse.clear();
			 newCourse = d;
			 //System.out.println("Deserialization complete! ");
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return;
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return;
		}
	}
	// deserialize student arraylists
	public static ArrayList<Student> deserialization(String filename) {
		try {
			 FileInputStream fis = new FileInputStream(filename);
			 ObjectInputStream ois = new ObjectInputStream(fis);
			 ArrayList<Student> d=  (ArrayList) ois.readObject();
			
			 ois.close();
			 fis.close();
			 //System.out.println("Deserialization complete! ");
			 return d;
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) throws IOException {
		 Admin a = new Admin();
		 // check if the file exists, and if existing, deserialization.
		 File f = new File("Ser_MyUniversityCourses.ser");
		 
		 if(f.isFile()) {
			 deserialization();
			 a.setUserName(deserialization("Ser_StudentAccount.ser"));
		 }

		 // the main menu
		 Scanner input = new Scanner (System.in);
		 System.out.println("Hi! Welcome to the Course Registration System!");
		 System.out.println("Are you a student or an administrator?(Type in administrator or student)");
		 String answer = input.next();
		 
		 // check if the user type in the appropriate answer.
		 while(!answer.equals("administrator")&& !answer.equals("student")) {
			 System.out.println(" Please type in the correct choice(administrator or student): ");
			 answer = input.next();
		 }
		 
		 // log in as an administrator
		 if (answer.equals("administrator")) {
			 System.out.println("Please enter the username: ");
			 String username = input.next();
			 System.out.println("Please enter the password");
			 String password = input.next();
			 // check username and password
			 while (!username.equals("Admin")|| !password.equals("Admon001")) {
				 if(!username.equals("Admin")) {
					 System.out.println("Your user name is wrong. Please try again!");
					 System.out.println("Please enter the username: ");
					 username = input.next();
					 System.out.println("Please enter the password");
					 password = input.next();
				 }
				 if(!password.equals("Admin001")) {
					 System.out.println("Your password is wrong. Please try again!");
					 System.out.println("Please enter the username: ");
					 username = input.next();
					 System.out.println("Please enter the password:");
					 password = input.next();
				 }else {
					 break;
				 }
			 }
			 // choose to manage courses or view reports 
			 System.out.println("You have successfully logged in as an administrator:");
			 System.out.println("Please enter 1 to manage courses or 2 to see reports or 3 to exit: ");
			 int choice = input.nextInt();
			 while(choice != 1 && choice != 2 && choice != 3) {
				 System.out.println(" No such option. Please enter again!(1/2/3) : ");
				 choice = input.nextInt();
			 }
			 
			 // options in management
			 boolean loginin = true;
			 while(loginin) {
				 if (choice == 1) {
					 System.out.println("You are now in course management: ");
					 System.out.println("Please enter 1 to create a new course: ");
					 System.out.println("Please enter 2 to delete a course: ");
					 System.out.println("Please enter 3 to edit course: ");
					 System.out.println("Please enter 4 to display information for a given course: ");
					 System.out.println("Please enter 5 to register a student: ");
					 System.out.println("Please enter 6 to exit ");
					 int choice2 = input.nextInt();
					 System.out.println();
					 
					 while(choice2 != 1 && choice2 != 2 && choice2 != 3 && choice2 != 4 && choice2 != 5 && choice2 != 6 ) {
						 System.out.println(" No such option. Please enter again!(1/2/3/4/5/6) : ");
						 choice2 = input.nextInt();
					 }
					 if (choice2 == 1) {
						 a.createNew();
					 }else if(choice2 == 2) {
						 a.delete();
					 }else if (choice2 == 3) {
						 a.edit();
					 }else if (choice2 == 4) {
						 a.adminView();
					 }else if (choice2 == 5) {
						 a.registerStu();

					 }else if (choice2 == 6) {
						System.out.println(" You have successfully logged out! ");
						loginin = false;
					 
						serialization("Ser_MyUniversityCourses.ser",newCourse);
						serialization("Ser_StudentAccount.ser",Admin.adminRegister);
						 System.exit(0);

					 }
				 }
				 // options in reports 
				 else if(choice == 2) {
					 System.out.println("You are now in reports:");
					 System.out.println("Please enter 1 to view all courses:");
					 System.out.println("Please enter 2 to view all full courses:");
					 System.out.println("Please enter 3 to write a file the name of courses that are full:");
					 System.out.println("Please enter 4 to view the names of the students being registered in a specific course:");
					 System.out.println("Please enter 5 view the list of courses that a given student is being registered on:");
					 System.out.println("Please enter 6 to sort courses based on the current number of student registers:");
					 System.out.println("Please enter 7 to exit:");
					 int choice3 = input.nextInt();
					 System.out.println();
					 while(choice3 != 1 && choice3 != 2 && choice3 != 3 && choice3 != 4 && choice3 != 5 && choice3 != 6 && choice3 != 7) {
						 System.out.println(" No such option. Please enter again!(1/2/3/4/5/6/7) : ");
						 choice3 = input.nextInt();
					 }
					 
					 if (choice3 == 1) {
						 a.viewAllCourses();
					 }else if(choice3 == 2) {
						 a.viewFullCourses();
					 }else if (choice3 == 3) {
						 a.writeToFile();
					 }else if (choice3 == 4) {
						 a.viewRegisteredStu();	
					 }else if (choice3 == 5) {
						 a.viewStudentCourse() ;
					 }else if (choice3 == 6) {
						 a.sort();
					 }else if (choice3 == 7){
						 loginin = false;
						 System.out.println(" You have successfully logged out! ");
						 
						 serialization("Ser_MyUniversityCourses.ser",newCourse);
						 serialization("Ser_StudentAccount.ser",Admin.adminRegister);
						 System.exit(0);
			
					 }
				 }else if(choice == 3){
					 // log out
					 loginin = false;
					 System.out.println(" You have successfully logged out! ");
					 
					 serialization("Ser_MyUniversityCourses.ser",newCourse);
					 serialization("Ser_StudentAccount.ser",Admin.adminRegister);
					 System.exit(0);
				

				 }
			 }
				 
		// log in as a student	 
		 }else if (answer.equals("student")) {
			 System.out.println("Please enter your first name: ");
		     String firstName = input.next();
		     System.out.println("Please enter your last name: ");
		     String lastName = input.next();

		     Student newS = new Student(firstName, lastName);

		     // find if the student is in the studentlist that the administrator registers
		     boolean foundUser = false;
		     for(int i = 0; i < Admin.adminRegister.size();i ++) {
		    	 if ((Admin.adminRegister.get(i).getFirstName().equals (firstName))
							&& Admin.adminRegister.get(i).getLastName().equals(lastName)){
		    		 foundUser = true;
		    		 break;
				}
		     }
		     // if the student is in the list and he does not have an account, first create an account 
		     if(foundUser) {  
		    	 for(int i = 0; i < Admin.adminRegister.size(); i ++) {
	    			 if(Admin.adminRegister.get(i).getFirstName().equals (firstName) && Admin.adminRegister.get(i).getLastName().equals (lastName)) {
	    				 if(Admin.adminRegister.get(i).getUserName()==null || Admin.adminRegister.get(i).getPassWord()==null) {
	    					 System.out.println("Please set up an account.");
	    		    		 System.out.println("Please enter your username: ");
	    		    		 String u = input.next();
	    		    		 System.out.println("Please enter your password: ");
	    		    		 String p = input.next();
	    		    		
	    		    		 Admin.adminRegister.get(i).setUserName(u);
		    				 Admin.adminRegister.get(i).setPassWord(p);
	    		    		 
		    				 break;
	    				 }
	    			 }
		    	 }
		   
		    	// when the student has set up an account, check his username and password 
	    		boolean correct = true;
		    	 while(correct) {
		    		 System.out.println("Please enter your username: ");
		    		 String user = input.next();
		    		 System.out.println("Please enter your password: ");
		    		 String pass = input.next();
		    		 
		    		 for(int i = 0; i < Admin.adminRegister.size(); i ++) {
		    			 if (Admin.adminRegister.get(i).getFirstName().equals(firstName) && Admin.adminRegister.get(i).getLastName().equals(lastName)) { 				 
		    				 if(Admin.adminRegister.get(i).getUserName().equals(user) && Admin.adminRegister.get(i).getPassWord().equals(pass)) {
		    				 	correct = false;
		    				 	break;
		    				 }else {
		    					 System.out.println("Username or password incorrect! Please try again!");
		    				 }
		    	 }
		     }
		    	 }
		    	 System.out.println("You have successfully logged in as student" + firstName + " " + lastName);
		    	 boolean log = true;
		    	 while (log) {
		    		 Student s = new Student(firstName, lastName);
		    		 // options to manage courses / view reports 
		    		 System.out.println("Please enter 1 to view all courses:");
		    		 System.out.println("Please enter 2 to view all courses that are not full:");
		    		 System.out.println("Please enter 3 to register on a course:");
		    		 System.out.println("Please enter 4 to withdraw from a course:");
		    		 System.out.println("Please enter 5 view all courses that you are registered in:");
		    		 System.out.println("Please enter 6 to exit:");
		    		 int stuChoice = input.nextInt();
		    		 
		    		 while(stuChoice != 1 && stuChoice != 2 && stuChoice!= 3 && stuChoice!= 4 && stuChoice!= 5 && stuChoice!= 6) {
						 System.out.println(" No such option. Please enter again!(1/2/3/4/5/6) : ");
						 stuChoice = input.nextInt();
					 }

		    		 if (stuChoice == 1) {
		    			 s.viewAllCourses();
		    		 }else if (stuChoice == 2) {
		    			 s.viewNotFullCourses();
		    		 }else if (stuChoice == 3) {
		    			 s.register();
		    		 }else if (stuChoice == 4) {
		    			 s.withdraw();
		    		 }else if (stuChoice == 5) {
		    			 s.registeredCourses();
		    		 }else if (stuChoice == 6) {
		    			 log = false;
		    			 System.out.println(" You have successfully logged out! ");

		    			 serialization("Ser_MyUniversityCourses.ser",newCourse);
		    			 serialization("Ser_StudentAccount.ser",Admin.adminRegister);
		    			 System.exit(0);
		    		 }
		    	  }
		    	    
		    	   // if the student is not in the student list that the administrator register them in;
		    	   // they need first to let the administrator to register them in 
		           }else{
		    	    	System.out.println("Sorry, you are not in the student lists.");
						System.exit(0);
		           }
		  

				 
	}
		    	 
		    	 
		    	 
		 } 
		    	 
		    	 }
		 
		 	 

				
		
	
	
	