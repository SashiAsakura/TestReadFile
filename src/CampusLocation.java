
/**
 * Enum class holding campus locations.
 * @author Hisashi
 *
 */
public enum CampusLocation {
	Burnaby, Surrey, Vancouver, Others;
	
	public static CampusLocation getCampusLocation(String stringCampusLocation) {
		CampusLocation campusLocation;
		if (stringCampusLocation.equals("BURNABY")) {
			campusLocation = CampusLocation.Burnaby;
		}
		else if (stringCampusLocation.equals("SURREY")) {
			campusLocation = CampusLocation.Surrey;
		}
		else if (stringCampusLocation.equals("VAN")) {
			campusLocation = CampusLocation.Vancouver;
		}
		else {
			campusLocation = CampusLocation.Others;
		}
		return campusLocation;
	}
}
