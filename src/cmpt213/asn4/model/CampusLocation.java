package cmpt213.asn4.model;

/**
 * Enum class holding campus locations.
 * @author Hisashi
 *
 */
public enum CampusLocation {
	Burnaby, Surrey, Vancouver, Others;
	
	public static CampusLocation getCampusLocation(String stringCampusLocation) {
		CampusLocation campusLocation;
		if (stringCampusLocation.equalsIgnoreCase("BURNABY")) {
			campusLocation = CampusLocation.Burnaby;
		}
		else if (stringCampusLocation.equalsIgnoreCase("SURREY")) {
			campusLocation = CampusLocation.Surrey;
		}
		else if (stringCampusLocation.equalsIgnoreCase("VAN")) {
			campusLocation = CampusLocation.Vancouver;
		}
		else {
			campusLocation = CampusLocation.Others;
		}
		return campusLocation;
	}
}
