import java.util.ArrayList;
import java.util.List;


public class Course implements Comparable<Course>{
	private String courseName;
	private String subject;
	private String catalogNum;
	private boolean isUndergradCourse = true;
	private List<CourseOffering> courseOfferings;
	private int oldestYearTaught;
	private int latestYearTaught;
	private int rangeOfYearsTaught;

	/*
	 * Constructor
	 */
	public Course(String courseName, String subject, String catalogNum) {
		this.courseName = courseName;
		this.subject = subject;
		this.catalogNum = catalogNum;
		if (catalogNum.charAt(0) == '5' || catalogNum.charAt(0) == '6'
				|| catalogNum.charAt(0) == '7' || catalogNum.charAt(0) == '8') {
			this.isUndergradCourse = false;
		}
		this.courseOfferings = new ArrayList<CourseOffering>();
	}
	
	/*
	 * Public Getter
	 */
	
	public String getSubject() {
		return this.getSubject();
	}
	public String getCatalogNum() {
		return this.catalogNum;
	}
	
	/*
	 * Public Method
	 */
	public void appendCourseOffering(CourseOffering courseOffering) {
		if (courseOffering == null) {
			return;
		}
		if (this.courseOfferings.contains(courseOffering)) {
			CourseOffering co = this.courseOfferings.get(this.courseOfferings.indexOf(courseOffering));
			co.addEnrolledSize(courseOffering.getEnrolledSize());
			co.addEnrollmentCapacity(courseOffering.getEnrollmentCapacity());
		}
		else {
			this.courseOfferings.add(courseOffering);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer stringCourseOffering = new StringBuffer();
		for (CourseOffering courseOffering : this.courseOfferings) {
			stringCourseOffering.append(courseOffering);
		}
		return this.courseName + stringCourseOffering.toString();
	}

	/*
	 * Required method to implement for sorting course.
	 * Compare Course by course name (eg. subject + catalogNum).
	 * Compare by subject in alphabetical order, then compare by catalog number.
	 * As for catalog number, 'X' is smaller than any numbers, so (XXX, XX1, X11) is smaller than 100
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Course that) {
		// e.g. CMPT 100 vs CMPT 1xx
		if (this.getSubject().equals(that.getSubject())) {
			// X.. vs X..
			if (this.getCatalogNum().charAt(0) == 'X' && that.getCatalogNum().charAt(0) == 'X') {
				
				// XXX vs XXX 
				// XXX vs XX1
				// XX1 vs XXX
				if (this.getCatalogNum().charAt(1) == 'X' && that.getCatalogNum().charAt(1) == 'X') {
					if (this.getCatalogNum().charAt(2) == 'X' && that.getCatalogNum().charAt(2) == 'X') {
						return 0;
					}
					return this.getCatalogNum().charAt(2) - that.getCatalogNum().charAt(2);
				}
				// XX. vs X..
				else if (this.getCatalogNum().charAt(1) == 'X') {
					return -1;
				}
				// X.. vs XX.
				else if (that.getCatalogNum().charAt(1) == 'X') {
					return 1;
				}
				// X11 vs X10
				else {
					return Integer.parseInt(this.getCatalogNum().substring(1, 2)) - Integer.parseInt(that.getCatalogNum().substring(1, 2));
				}
			}
			// X.. vs 1..
			else if (this.getCatalogNum().charAt(0) == 'X') {
				return -1;
			}
			// 1.. vs X..
			else if (that.getCatalogNum().charAt(0) == 'X') {
				return 1;
			}
			// 1.. vs 1XX
			else {
				// 11X vs 1X1
				if (this.getCatalogNum().contains("X") && that.getCatalogNum().contains("X")) {
					return 0;
				}
				// 11X vs 100
				else if (this.getCatalogNum().contains("X")) {
					return -1;
				}
				// 100 vs 1XX
				else if (that.getCatalogNum().contains("X")) {
					return 1;
				}
				// 1.. vs 1..
				else {
					return Integer.parseInt(this.getCatalogNum().substring(1, 2)) - Integer.parseInt(that.getCatalogNum().substring(1, 2));
				}
			}
		}
		// CMPT 100 vs ENSC 100
		else {
			return this.getSubject().compareTo(that.getSubject());
		}
	}
	
	/*
	 * Required method to properly search the same Course from list
	 */
	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		return this.getSubject().equals(((Course) that).getSubject())
				&& this.getCatalogNum().equals(((Course) that).getCatalogNum());
	}
}
