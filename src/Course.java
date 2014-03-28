import java.util.ArrayList;
import java.util.List;


public class Course {
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
		return this.subject;
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
