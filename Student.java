package homework_one;

// this class includes all the methods that allow students to perform tasks and they are called in course class;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.Serializable;
public class Student extends User implements stuInterface{
	
	private static final long serialVersionUID = -2322401915571279695L;
	public Student(String firstName, String lastName) {
		super(firstName, lastName);
	}
    public Student(String userName, String password, String firstName, String lastName) {
    	super(userName, password, firstName, lastName);
    }
	public Student() {
		super();
	}
	public void viewAllCourses() {
		for (course x : newCourse) {
			x.studentView();
		}
		
	}
	

	public void viewNotFullCourses() {
		for(int i = 0; i < newCourse.size(); i ++) {
			if (newCourse.get(i).getMaximumStu() != newCourse.get(i).getCurrentStu())
				System.out.println(newCourse.get(i));
		}
	}
	
	//(in this case the student must enter the course name, section, and student full name, the name will be added to the appropriate course)
	public void register() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the course name: ");
		String courseName = input.nextLine();	
		System.out.println("Enter the course section number: ");
		int sectionNumber = input.nextInt();
		System.out.println("Enter your first name: ");
		String firstName = input.next();	
		System.out.println("Enter your last name: ");
		String lastName = input.next();
		
		for (int i = 0; i < newCourse.size(); i ++) {
			
			if ((newCourse.get(i).getCourseName().equals(courseName)) && newCourse.get(i).getCourseSection()==(sectionNumber)) {
				
				Student stu = new Student(firstName, lastName);
				newCourse.get(i).studentName.add(stu);
				newCourse.get(i).currentStu ++;
				System.out.println("You have been added to the course successfuly!");
				break;
			}
	}
	
	}
		//(in this case the student will be asked to enter her/his student name and the course, then the name of the student will be taken off from the given course’ list)
	public void withdraw() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter the course name: ");
		String c = input.nextLine();
		System.out.println("Enter the course section number: ");
		int sectionNumber = input.nextInt();
		System.out.println("Please enter your first name: ");
		String f = input.next();
		System.out.println("Please enter your last name: ");
		String l = input.next();


		
		for (int i = 0; i <newCourse.size(); i ++) {
				if(newCourse.get(i).getCourseName().equals(c) && newCourse.get(i).getCourseSection()==(sectionNumber) ) {
					for(int j = 0; j < newCourse.get(i).studentName.size(); j ++) {
						if (newCourse.get(i).studentName.get(j).getFirstName().equals(f) && newCourse.get(i).studentName.get(j).getLastName().equals(l)) {
							newCourse.get(i).studentName.remove(j);
							newCourse.get(i).currentStu --;
				
							System.out.println("You have successfully removed from the course.");
						}
						}
				}
					
		}
	
	}
	// View all courses that the current student is being registered in
	public void registeredCourses() {
		Scanner input = new Scanner(System.in);
		
		System.out.println("Please enter your first name: ");
		String firstName = input.next();
		System.out.println("Please enter your last name: ");
		String lastName = input.next();
		
		for (course c : newCourse) {
			for(int i =0; i < c.studentName.size(); i ++)
				if(c.getStudentName().get(i).getFirstName().equals(firstName) && c.getStudentName().get(i).getLastName().equals(lastName)) {
					c.display();
				}
		
		}
	}
	

}

