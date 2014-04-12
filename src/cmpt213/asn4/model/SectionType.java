package cmpt213.asn4.model;

/**
 * Enum class for section type.
 * @author Hisashi
 *
 */
public enum SectionType {
	LEC, TUT, OPL, SEC, OTHERS;

	public static SectionType getSectionType(String stringSectionType) {
		SectionType sectionType;
		if (stringSectionType.equalsIgnoreCase("LEC")) {
			sectionType = SectionType.LEC;
		}
		else if (stringSectionType.equalsIgnoreCase("TUT")) {
			sectionType = SectionType.TUT;
		}
		else if (stringSectionType.equalsIgnoreCase("SEC")) {
			sectionType = SectionType.SEC;
		}
		else if (stringSectionType.equalsIgnoreCase("OPL")) {
			sectionType = SectionType.OPL;
		}
		else {
			sectionType = SectionType.OTHERS;
		}
		return sectionType;
	}
}
