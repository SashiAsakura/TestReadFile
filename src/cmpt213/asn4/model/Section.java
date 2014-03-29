package cmpt213.asn4.model;

/**
 * Immutable class holding section information.
 * @author Hisashi
 *
 */
public class Section {
	private SectionType sectionType;
	private int enrollmentCapacity;
	private int enrollmentSize;
	
	/*
	 * Constructor
	 */
	public Section(SectionType sectionType, int enrollmentCapacity, int enrollmentSize) {
		this.sectionType = sectionType;
		this.enrollmentCapacity = enrollmentCapacity;
		this.enrollmentSize = enrollmentSize;
	}
	
	/*
	 * Public Getter
	 */
	public SectionType getSectionType() {
		return this.sectionType;
	}
	
	public int getEnrollmentCapacity() {
		return this.enrollmentCapacity;
	}
	
	public int getEnrollmentSize() {
		return this.enrollmentSize;
	}
	
	/*
	 * Public Method
	 */
	public void addEnrolledSize(int num) {
		this.enrollmentSize += num;
	}
	
	public void addEnrollmentCapacity(int num) {
		this.enrollmentCapacity += num;
	}

	/*
	 * Required method for comparing if sections are the same
	 * when appending section to the courseOffering.
	 * Two sections are the same if SectionTypes are the same
	 */
	@Override
	public boolean equals(Object that) {
		return this.getSectionType().equals(((Section) that).getSectionType());
	}
	
	@Override
	public String toString() {
		return "\n\t\t\t\t Type=" + this.sectionType + ", Enrollment=" + this.enrollmentSize + "/" + this.enrollmentCapacity;
	}
}
