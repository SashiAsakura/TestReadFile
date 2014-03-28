
/**
 * Immutable class holding section information.
 * @author Hisashi
 *
 */
public class Section {
	private SectionType sectionType;
	private int enrollmentCapacity;
	private int enrollmentSize;
	
	public Section(SectionType sectionType, int enrollmentCapacity, int enrollmentSize) {
		this.sectionType = sectionType;
		this.enrollmentCapacity = enrollmentCapacity;
		this.enrollmentSize = enrollmentSize;
	}
	
	public SectionType getSectionType() {
		return this.sectionType;
	}
	
	public int getEnrollmentCapacity() {
		return this.enrollmentCapacity;
	}
	
	public int getEnrollmentSize() {
		return this.enrollmentSize;
	}

}
