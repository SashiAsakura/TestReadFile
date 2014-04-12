package cmpt213.asn4.model;
import java.util.ArrayList;
import java.util.List;

/**
 * Class holds information about Course.
 * @author Hisashi
 *
 */
public class Course {
	private String courseName;
	private String subject;
	private String catalogNum;
	private boolean isUndergradCourse = true;
	private List<CourseOffering> courseOfferings;
	private int oldestYearTaught = 2020;
	private int latestYearTaught = 0;
	private int rangeOfYearsTaught;
	private int[] countsBySemester;
	private int[] countsByCampus;

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
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getSubject() {
		return this.subject;
	}
	public String getCatalogNum() {
		return this.catalogNum;
	}
	
	public boolean getIsUndergrad() {
		return this.isUndergradCourse;
	}
	
	public List<CourseOffering> getCourseOfferings() {
		return this.courseOfferings;
	}
	
	public int getOldestYearTaught() {
		return this.oldestYearTaught;
	}
	
	public int getLatestYearTaught() {
		return this.latestYearTaught;
	}
	
	public int getRangeOfYearsTaught() {
		return this.latestYearTaught - this.oldestYearTaught + 1;
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
			Section section = new Section(courseOffering.getSectionType(),
					courseOffering.getEnrollmentCapacity(), courseOffering.getEnrolledSize());
			co.appendSection(section);
		}
		else {
			this.courseOfferings.add(courseOffering);
			updateOldestLatestYearTaught(courseOffering);
		}
	}
	
	public List<CourseOffering> getCoursesOfferredIn(Semester semester, int yearTaught) {
		List<CourseOffering> cos = new ArrayList<CourseOffering>();
		for (CourseOffering co : this.courseOfferings) {
			if (co.getSemester().equals(semester) && co.getYear() == yearTaught) {
				cos.add(co);
			}
		}
		return cos;
	}
	
	/*
	 * Return number of times this course has been offered in
	 * various semesters. Array is of size 3.
	 * Array content is [springCounts, summerCounts, fallCounts]
	 */
	public int[] getNumTimesOfferedBySemester() {
		this.countsBySemester = new int[this.courseOfferings.size()];
		int count = 0;
		
		for (CourseOffering co : this.courseOfferings) {
			if (co.getSemester().equals(Semester.Spring)) {
				countsBySemester[count++] = 0; 
			}
			else if (co.getSemester().equals(Semester.Summer)) {
				countsBySemester[count++] = 1; 
			}
			else if (co.getSemester().equals(Semester.Fall)) {
				countsBySemester[count++] = 2; 
			}
		}
		
		return this.countsBySemester;
	}
	
	/*
	 * Return number of times this course has been offered in
	 * various campuses. Array is of size 4.
	 * Array content is [BurnabyCounts, SurreyCounts, VancouverCounts, OthersCounts]
	 */
	public int[] getNumTimesOfferedByCampus() {
		this.countsByCampus = new int[this.courseOfferings.size()];
		int count = 0;
		
		for (CourseOffering co : this.courseOfferings) {
			if (co.getCampusLocation().equals(CampusLocation.Burnaby)) {
				countsByCampus[count++] = 0; 
			}
			else if (co.getCampusLocation().equals(CampusLocation.Surrey)) {
				countsByCampus[count++] = 1; 
			}
			else if (co.getCampusLocation().equals(CampusLocation.Vancouver)) {
				countsByCampus[count++] = 2; 
			}
			else {
				countsByCampus[count++] = 3; 
			}
		}
		return this.countsByCampus;
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
		if (that == null || this.getClass() != that.getClass()) {
			return false;
		}
		else if (that == this) {
			return true;
		}
		return this.getSubject().equals(((Course) that).getSubject())
				&& this.getCatalogNum().equals(((Course) that).getCatalogNum());
	}
	
	/*
	 * Private Method
	 */
	
	private void updateOldestLatestYearTaught(CourseOffering courseOffering) {
		if (courseOffering.getYear() < this.oldestYearTaught) {
			this.oldestYearTaught = courseOffering.getYear();
		}
		if (courseOffering.getYear() > this.latestYearTaught) {
			this.latestYearTaught = courseOffering.getYear();
		}
	}

}
