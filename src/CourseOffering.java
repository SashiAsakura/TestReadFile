import java.util.Comparator;
import java.util.List;


public class CourseOffering implements Comparator<CourseOffering>{
	private String courseName;
	private int semesterCode;
	private Semester semester;
	private int year;
	private String subject;
	private String catalogNumber;
	private CampusLocation campusLocation;
	private int enrollmentCapacity;
	private int enrolledSize;
	private SectionType sectionType;
	private List<String> instructors;

	/*
	 * Constructor
	 */
	public CourseOffering(int semesterCode, String subject, String catalogNum, 
			String campusLocation, int enrollmentSize, String sectionType, int enrollmentCapacity, List<String> instructors) {
		this.semesterCode = semesterCode;
		this.processSemesterCodeIntoYearAndSemester(semesterCode);
		this.subject = subject;
		this.catalogNumber = catalogNum;
		this.campusLocation = CampusLocation.getCampusLocation(campusLocation);
		this.enrolledSize = enrollmentSize;
		this.sectionType = SectionType.getSectionType(sectionType);
		this.enrollmentCapacity = enrollmentCapacity;
		this.instructors = instructors;
		this.courseName = this.subject + " " + this.catalogNumber;
	}
	
	/*
	 * Public Getter Method
	 */
	public int getYear() {
		return this.year;
	}
	
	public Semester getSemester() {
		return this.semester;
	}
	
	public int getSemesterCode() {
		return this.semesterCode;
	}
	
	public int getEnrolledSize() {
		return this.enrolledSize;
	}
	
	public int getEnrollmentCapacity() {
		return this.enrollmentCapacity;
	}
	
	public String getCourseName() {
		return this.courseName;
	}
	
	public String getSubject() {
		return this.subject;
	}
	
	public String getCatalogNum() {
		return this.catalogNumber;
	}
	
	public CampusLocation getCampusLocation() {
		return this.campusLocation;
	}
	
	public List<String> getInstructors() {
		return this.instructors;
	}
	
	/**
	 * Public Method
	 */
	
	public void addEnrolledSize(int num) {
		this.enrolledSize += num;
	}
	
	public void addEnrollmentCapacity(int num) {
		this.enrollmentCapacity += num;
	}
	
	@Override
	public String toString() {
		return "\n\t\t " + this.semesterCode + " in " + this.campusLocation + " by " + this.instructors
				+ "\n\t\t\t\t Type=" + this.sectionType + ", Enrollment=" + this.enrolledSize + "/" + this.enrollmentCapacity;
	}

	@Override
	public boolean equals(Object that) {
		return this.getSemesterCode() == ((CourseOffering) that).getSemesterCode()
				&& this.getCampusLocation() == ((CourseOffering) that).getCampusLocation();
	}
	
	@Override
	public int compare(CourseOffering co1, CourseOffering co2) {
		if (co1.getSemesterCode() == co2.getSemesterCode()) {
			return co1.getCampusLocation().compareTo(co2.getCampusLocation());
		}
		return co1.getSemesterCode() - co2.getSemesterCode();
	}
	
	/*
	 * Private Method
	 */
	
	private void processSemesterCodeIntoYearAndSemester(int semesterCode) {
		this.semester = Semester.getSemester(semesterCode % 10);
		this.year = this.getYear(semesterCode / 10);
	}
	
	/**
	 * Convert 3 digit year code to 4 digit year
	 * If 1st digit is 1, year is 20xx
	 * Else if 0, year is 19xx
	 * 
	 * @param yearCode is 3 digit number
	 * @return 4 digit year
	 */
	private int getYear(int yearCode) {
		int year = 0;
		if (yearCode / 100 == 0) {
			year += 19;
		}
		else if (yearCode / 100 == 1) {
			year += 20;
		}
		
		year *= 100;
		year += yearCode % 100;
		return year;
	}

}
