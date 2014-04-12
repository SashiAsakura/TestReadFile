package cmpt213.asn4.model;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.Iterator;

/**
 * Responsible for reading input file and process data and create
 * list of Department objects that contain list of Course objects.
 * @author Hisashi
 *
 */
public class CoursePlanner {
	public String sourceFile = "data/course_data_2014.csv.txt";
	public String ToFile = "data/output_dump.txt";
	// HashMap: <departmentName(CMPT), HashMap<courseName(CMPT 213), Course(CMPT 213)>>
	private HashMap<String, HashMap<String, Course>> departmentList;
//	private List<List<Course>> sortedDepartmentList;
	private HashMap<String, List<Course>> sortedDepartmentHM;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CoursePlanner cp = new CoursePlanner();
	}
	
	/*
	 * Constructor
	 */
	
	public CoursePlanner() {
		this.dumpModel();
	}
	
	/*
	 * Public Getter
	 */
	
	public HashMap<String, List<Course>> getSortedDepartmentHM() {
		return this.sortedDepartmentHM;
	}
	
	/*
	 * Private Method
	 */
	
	private void dumpModel() {
		File sourceFile = new File(this.sourceFile);
		this.departmentList = new HashMap<String, HashMap<String, Course>>();
		
		try {
			Scanner scanner = new Scanner(sourceFile);
			
			// Skip the first line (header)
			if (scanner.hasNextLine()) {
				scanner.nextLine();
			}
			while (scanner.hasNextLine()) {
				String text = scanner.nextLine();
				System.out.println("Read: " + text);
				
				// Instantiate new CourseOffering
				CourseOffering courseOffering = this.parseToCourseOfferingFrom(text);
				
				// Create department "CMPT" in outer HashMap of deparments
				String departmentName = courseOffering.getSubject();
				if (!this.departmentList.containsKey(departmentName)) {
					this.departmentList.put(departmentName, new HashMap<String, Course>());
				}
				
				// Create course "CMPT 213" in inner HashMap of courses
				String courseName = courseOffering.getCourseName();
				if (!this.departmentList.get(departmentName).containsKey(courseName)) {
					Course course = new Course(courseName, courseOffering.getSubject(), courseOffering.getCatalogNum());
					this.departmentList.get(departmentName).put(courseName, course);
				}
				
				// Append courseOffering to Course, which is now in course list inside department list
				this.departmentList.get(departmentName).get(courseName).appendCourseOffering(courseOffering);
			}
			
			scanner.close();

			// Dump all the courses in HashMap to file
			this.printDepartmentList();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void printDepartmentList() {
		System.out.println("printDepartmentList()");
		File dumpFile = new File(this.ToFile);
		try {
			PrintWriter printWriter = new PrintWriter(dumpFile);
			
			this.sortDepartmentAndCourse();
			for (String department : this.sortedDepartmentHM.keySet()) {
				for (Course course : this.sortedDepartmentHM.get(department)) {
					printWriter.println(course);
//					System.out.println(course);
				}
			}
			
			printWriter.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sortDepartmentAndCourse() {
		this.sortedDepartmentHM = new HashMap<String, List<Course>>();
		for (String department : this.departmentList.keySet()) {
			HashMap<String, Course> courses = this.departmentList.get(department);
			List<Course> sortedCourses = new ArrayList<Course>(courses.values());
			Collections.sort(sortedCourses, new SortedCourseByName());
			this.sortedDepartmentHM.put(department, sortedCourses);
		}
	}

	/**
	 * Parse text and instantiate CourseOffering object.
	 * @param text: "1021,PHYS,100,BURNABY,0,OPL,0"
	 */
	private CourseOffering parseToCourseOfferingFrom(String text) {
		if (text == null || text.isEmpty()) {
			return null;
		}
		
		String[] tokens = text.split(",");
		System.out.println("tokenSize: " + tokens.length);
		if (tokens != null || tokens.length >= 7) {
			int semesterCode = Integer.parseInt(tokens[0]);
			String subject = tokens[1];
			String catalogNum = tokens[2];
			String campusLocation = tokens[3];
			int enrollmentCapacity = Integer.parseInt(tokens[4]);
			String sectionType = tokens[5];
			int enrollmentSize = Integer.parseInt(tokens[6]);
			List<String> instructors = this.getInstructors(tokens);
			CourseOffering courseOffering = new CourseOffering(semesterCode, subject, catalogNum, campusLocation, enrollmentSize, sectionType, enrollmentCapacity, instructors);
			
//			System.out.println("     " + courseOffering);
			return courseOffering;
		}
		return null;
	}

	/**
	 * Get a list of instructors out of varying number of instructors in array
	 * @param tokens:
	 * 			1. null
	 * 			2. Oliver Pitts
	 * 			3. "Gwen Litchfield, Natalie Erickson"
	 * 			4. ". Sessional, Reza Sabzehgar"
	 * @return List of String instructor name
	 */
	private List<String> getInstructors(String[] tokens) {
		List<String> instructors = new ArrayList<String>();
		
		if (tokens.length > 7) {
			for (int i = 7; i < tokens.length; i++) {
				if (tokens.length == 8) {
					instructors.add(tokens[i]);
				}
				else {
					// Trim '"' at first index of the first token
					if (i == 7) {
						instructors.add(tokens[i].substring(1));
					}
					// Trim '"' at last index of the last token
					else if (i == tokens.length - 1) {
						instructors.add(tokens[i].substring(0, tokens[i].length() -1));
					}
					else {
						instructors.add(tokens[i]);
					}
				}
			}
		}
		return instructors;
	}

}
