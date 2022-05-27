package homework_one;
// this class includes all the methods that allow administrator to perform tasks and they are called in course class;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.Serializable;
public class Admin extends User implements adminInterface{

	static ArrayList<Student> adminRegister = new ArrayList<>();
	String userName;
	String password;
	
	public Admin() {
		super();
		this.userName = "Admin";
		this.password = "Admin001";
		
	}
	
	public void setUserName(ArrayList<Student> adminRegister) {
		this.adminRegister = adminRegister;
	}
	public ArrayList<Student> getadminRegister() {
		return adminRegister;
	}
	// Create a new course
	public void createNew() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter the course name: ");
		String courseName = input.nextLine();
		
		System.out.println("Enter the course ID: ");
		String courseId = input.next();
		
		System.out.println("Enter the maximum number of students in this course: ");
		int maximumStu = input.nextInt();
		
		System.out.println("Enter the current number of students that are in the course ");
		int currentStu = input.nextInt();
		
		ArrayList<Student> studentName = new ArrayList<>();
		System.out.println("Enter name of the instructor: ");
		String instructor = input.nextLine();
	    input.nextLine();
		System.out.println("Enter the section number: ");
		int courseSection = input.nextInt();
		
		System.out.println("Enter the location: ");
		String location = input.nextLine();
		input.nextLine();
		
		course newC = new course(courseName, courseId, maximumStu,currentStu,studentName, instructor,courseSection,location);
		newCourse.add(newC);
		
		System.out.println("A new course " + courseName + " has been created! ");	
		newC.display();

	}
	
	// delete a course
	public void delete() throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course name you want to delete: ");
		String courseName = input.nextLine();
		System.out.println("Enter the course section you want to delete: ");
		int section = input.nextInt();
		
		
		for(course c : newCourse) {
			if(c.getCourseName().equals(courseName) && c.getCourseSection() == section) {
				newCourse.remove(c);
				System.out.println(courseName + " has been successfully removed! ");
				break;
		}
		}

	}
	
	//(this will allow the admin to edit any information on the course except for course ID and name)
	public void edit() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter the name of the course that you want to edit: ");
		String courseName = input.nextLine();
		System.out.println("Enter the course section you want to delete: ");
		int section = input.nextInt();
		System.out.println("Please enter the information that you wnat to edit(instructor/location/section number/maximum_Stu): ");
		String answer = input.nextLine();
		
		if (answer.equals("instructor")) {
			System.out.println("Enter the new instructor: ");
			String instructor = input.nextLine();
			for(course c : newCourse) {
				if (c.getCourseName().equals(courseName) && c.getCourseSection() == section) {
					c.setInstructor(instructor);
					System.out.println("Course instructor name has been successfully changed to: " + instructor);
				}
			}
		}else if (answer.equals("location")) {
			System.out.println("Enter the new location: ");
			String instructor = input.nextLine();
			for(course c : newCourse) {
				if (c.getCourseName().equals(courseName) &&c.getCourseSection() == section) {
					c.setLocation(instructor);
					System.out.println("Course location has been successfully changed to: " + location);
				}
			}
		}else if (answer.equals("section number")) {
			System.out.println("Enter the new section number: ");
			int sectionNumber = input.nextInt();
			for(course c : newCourse) {
				if (c.getCourseName().equals(courseName) && c.getCourseSection() == section) {
					c.setCourseSection(sectionNumber);
					System.out.println("Course section number has been successfully changed to: " + sectionNumber);
				}
			}
		}else if (answer.equals("maximum_Stu")) {
			System.out.println("Enter the new maximum number of students: ");
			int maximum = input.nextInt();
			for(course c : newCourse) {
				if (c.getCourseName().equals(courseName) && c.getCourseSection() == section) {
					c.setMaximumStu(maximum);
					System.out.println("the maximum number of students has been successfully changed to: " + maximum);
				}
			}
		}
	}
	// Display information for a given course (by course ID)
	public void adminView() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the ID of the course that you want to view: ");
		String ID = input.nextLine();
		
		for(course c : newCourse) {
			if (c.getCourseId().equals(ID)) {
				c.display();
			}
		}
	}
	// (this option will allow the admin to add a student without assigning to a course check Req 11 for student’s information 
	public void registerStu() {
		Scanner input = new Scanner(System.in);
	
		System.out.println("Enter the first name of the student: ");
		String firstname = input.next();
		System.out.println("Enter the last name of the student: ");
		String lastname = input.next();
		String name = firstname + " " + lastname;
		
		Student addstu = new Student( firstname, lastname);
		adminRegister.add(addstu);
		System.out.println("New student " + name +" has been successfully added!");

		
		
	}
	
	// Reports
	//for every course the admin should be able to see the list of course name, course id, number of students registered, and the maximum number of students allowed to be registered)
	public void viewAllCourses() {
		for (course c : newCourse) {
			c.adView();
		}
	}
	
	// (reached the maximum number of students)
	public void viewFullCourses() {
		for(course c : newCourse) {
			if(c.getMaximumStu()== c.getCurrentStu()) {
				c.display();
			}
		}
		
	}
	
	public void writeToFile() {
		ArrayList<String> fullCourse = new ArrayList<>();
		for(course c : newCourse) {
			if (c.getMaximumStu()== c.getCurrentStu()) {
				String str = c.toString();
				fullCourse.add(str);
			}
		}
		
		try {
		      FileWriter myWriter = new FileWriter("fullCourse.txt");
		      for (String a: fullCourse) {
		    	  myWriter.write(a);
		      }
		      myWriter.close();
		      System.out.println("Successfully written to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	// View the names of the students being registered in a specific course
	public void viewRegisteredStu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course name in which you want to view the student names: ");
		String courseName = input.nextLine();
		System.out.println("Enter the course section in which you want to view the student names: ");
		int s = input.nextInt();
	
		for (course c : newCourse) {
			if (c.getCourseName().equals(courseName) && c.getCourseSection()== s) {
				for (int i = 0; i < c.studentName.size(); i ++) {
					String first = c.getStudentName().get(i).getFirstName();
					String last = c.getStudentName().get(i).getLastName();
					System.out.println(first + " " + last);
				}
			}
		}
		
	}
	
	// (given a student first name and last name the system shall display all the courses that students is being registered in)
	public void viewStudentCourse() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the first name of the student : ");
		String f = input.next();
		System.out.println("Enter the last name of the student : ");
		String l = input.next();
		
		for (course c : newCourse) {
			for (int i = 0; i < c.studentName.size(); i ++) {
				if (c.getStudentName().get(i).getFirstName().equals(f) && c.getStudentName().get(i).getLastName().equals(l)) {
					String course = c.getCourseName();
					System.out.println(course);
				}
		
			}
		}
	}
	
	// Sort courses based on the current number of student registers
	public void sort() {
		for (int i = 0 ; i < newCourse.size(); i ++) {
			for (int j = i + 1 ; j < newCourse.size(); j++) {
				if(newCourse.get(i).getCurrentStu() < newCourse.get(j).getCurrentStu())
					Collections.swap(newCourse, i, j);
					
			}
		}
		for(course c : newCourse) {
			c.display();
		}
	}

}
