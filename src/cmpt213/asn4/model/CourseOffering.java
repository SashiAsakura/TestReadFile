package cmpt213.asn4.model;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class holds information about course offerings: semester,
 * enrolled size, enrollment capacity, section type, etc.
 * @author Hisashi
 *
 */
public class CourseOffering implements Comparator<CourseOffering>{
	private String courseName;
	private int semesterCode;
	private Semester semester;
	private int year;
	private String subject;
	private String catalogNumber;
	private CampusLocation campusLocation;
	private int enrollmentCapacity;
	private int enrollmentSize;
	private SectionType sectionType;
	private List<String> instructors;
	private List<Section> sections;

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
		this.enrollmentSize = enrollmentSize;
		this.sectionType = SectionType.getSectionType(sectionType);
		this.enrollmentCapacity = enrollmentCapacity;
		this.instructors = instructors;
		this.courseName = this.subject + " " + this.catalogNumber;
		
		this.sections = new ArrayList<Section>();
		this.sections.add(new Section(this.sectionType, this.enrollmentCapacity, this.enrollmentSize));
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
		return this.enrollmentSize;
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
	
	public SectionType getSectionType() {
		return this.sectionType;
	}
	
	public List<String> getInstructors() {
		return this.instructors;
	}
	
	public List<Section> getSections() {
		return this.sections;
	}
	
	/**
	 * Public Method
	 */
	
	public void appendSection(Section section) {
		if (this.getSections().contains(section)) {
			Section currSection = this.getSections().get(this.getSections().indexOf(section));
			currSection.addEnrolledSize(section.getEnrollmentSize());
			currSection.addEnrollmentCapacity(section.getEnrollmentCapacity());
		}
		else {
			this.getSections().add(section);
		}
	}
	
	@Override
	public String toString() {
		StringBuffer stringSections = new StringBuffer();
		for (Section section : this.sections) {
			stringSections.append(section);
		}
		return "\n\t\t " + this.semesterCode + " in " + this.campusLocation + " by " + this.instructors
				+ stringSections;
	}

	/*
	 * Required method when checking if specific courseOffering exists 
	 * in the list of CourseOfferings (1141, CMPT120, BURNABY).
	 * Two courseOfferins are the same if semesterCode & campusLocation are the same
	 */
	@Override
	public boolean equals(Object that) {
		if (that == null || this.getClass() != that.getClass()) {
			return false;
		}
		else if (that == this) {
			return true;
		}
		return this.getSemesterCode() == ((CourseOffering) that).getSemesterCode()
				&& this.getCampusLocation().equals(((CourseOffering) that).getCampusLocation());
//				&& this.getSectionType().equals(((CourseOffering) that).getSectionType());
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
