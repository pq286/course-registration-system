package homework_one;
import java.util.ArrayList;
import java.io.IOException;

public interface adminInterface {
	public abstract void createNew();
	public abstract void delete() throws IOException;
	public abstract void edit();
	public abstract void adminView();
	public abstract void registerStu();
	public abstract void viewAllCourses();
	public abstract void viewFullCourses();
	public abstract void writeToFile();
	public abstract void viewRegisteredStu();
	public abstract void viewStudentCourse();
	public abstract void sort();
}
