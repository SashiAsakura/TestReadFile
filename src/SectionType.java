
public enum SectionType {
	LEC, TUT, OPL, SEC, OTHERS;

	public static SectionType getSectionType(String stringSectionType) {
		SectionType sectionType;
		if (stringSectionType.equals("LEC")) {
			sectionType = SectionType.LEC;
		}
		else if (stringSectionType.equals("TUT")) {
			sectionType = SectionType.TUT;
		}
		else if (stringSectionType.equals("SEC")) {
			sectionType = SectionType.SEC;
		}
		else if (stringSectionType.equals("OPL")) {
			sectionType = SectionType.OPL;
		}
		else {
			sectionType = SectionType.OTHERS;
		}
		return sectionType;
	}
}
