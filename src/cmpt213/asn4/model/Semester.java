package cmpt213.asn4.model;

public enum Semester {
	Spring, Summer, Fall, Others;

	public static Semester getSemester(int semesterNum) {
		Semester semester;
		if (semesterNum == 1) {
			semester = Semester.Spring;
		}
		else if (semesterNum == 4) {
			semester = Semester.Summer;
		}
		else if (semesterNum == 7) {
			semester = Semester.Fall;
		}
		else {
			semester = Semester.Others;
		}
		return semester;
	}
}
