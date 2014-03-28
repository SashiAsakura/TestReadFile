import java.util.Comparator;

/*
 * Required method to implement for sorting course.
 * Compare Course by course name (eg. subject + catalogNum).
 * Compare by subject in alphabetical order, then compare by catalog number.
 * As for catalog number, 'X' is smaller than any numbers, so (XXX, XX1, X11) is smaller than 100
 * @see java.lang.Comparable#compareTo(java.lang.Object)
 */
public class SortedCourseByName implements Comparator<Course>{

	@Override
//	public int compare(Course c1, Course c2) {
//		// e.g. CMPT 100 vs CMPT 1xx
//		String c1Subject = c1.getSubject();
//		String c2Subject = c2.getSubject();
//		String c1CatalogNum = c1.getCatalogNum();
//		String c2CatalogNum = c2.getCatalogNum();
//		System.out.println(c1Subject + " " + c1CatalogNum + ", " + c2Subject + " " + c2CatalogNum);
//		if (c1Subject.equals(c2Subject)) {
//			// X.. vs X..
//			if (c1CatalogNum.charAt(0) == 'X' && c2CatalogNum.charAt(0) == 'X') {
//				
//				// XXX vs XXX 
//				// XXX vs XX1
//				// XX1 vs XXX
//				if (c1CatalogNum.charAt(1) == 'X' && c2CatalogNum.charAt(1) == 'X') {
//					if (c1CatalogNum.charAt(2) == 'X' && c2CatalogNum.charAt(2) == 'X') {
//						return 0;
//					}
//					return c1CatalogNum.charAt(2) - c2CatalogNum.charAt(2);
//				}
//				// XX. vs X..
//				else if (c1CatalogNum.charAt(1) == 'X') {
//					return -1;
//				}
//				// X.. vs XX.
//				else if (c2CatalogNum.charAt(1) == 'X') {
//					return 1;
//				}
//				// X11 vs X10
//				else {
//					return Integer.parseInt(c1CatalogNum.substring(1, 2)) - Integer.parseInt(c2CatalogNum.substring(1, 2));
//				}
//			}
//			// X.. vs 1..
//			else if (c1CatalogNum.charAt(0) == 'X') {
//				return -1;
//			}
//			// 1.. vs X..
//			else if (c2CatalogNum.charAt(0) == 'X') {
//				return 1;
//			}
//			// 1.. vs 1XX
//			else {
//				// 1XX vs 100
//				// 3XX vs 300W
//				if (c1CatalogNum.charAt(0) == c2CatalogNum.charAt(0)) {
//					if (c1CatalogNum.matches("[a-zA-Z]+") && c2CatalogNum.contains("[a-zA-Z]+")) {
//						if (c1CatalogNum.charAt(1) == 'X' && c2CatalogNum.charAt(1) == 'X') {
//							if (c1CatalogNum.charAt(2) == 'X' && c2CatalogNum.charAt(2) == 'X') {
//								return 0;
//							}
//							return c1CatalogNum.charAt(2) - c2CatalogNum.charAt(2);
//						}
//						else if (c1CatalogNum.charAt(1) == 'X') {
//							return -1;
//						}
//						else if (c2CatalogNum.charAt(1) == 'X') {
//							return 1;
//						}
//						return c1CatalogNum.charAt(1) - c2CatalogNum.charAt(1);
//					}
//					// 11X vs 100
//					else if (c1CatalogNum.matches("[a-zA-Z]+")) {
//						return -1;
//					}
//					// 100 vs 1XX
//					else if (c2CatalogNum.matches("[a-zA-Z]+")) {
//						return 1;
//					}
//					// 1.. vs 1..
//					else {
//						return Integer.parseInt(c1CatalogNum) - Integer.parseInt(c2CatalogNum);
//					}
//				}
//				// 7XX vs 300
//				else {
//					return c1CatalogNum.charAt(0) - c2CatalogNum.charAt(0);
//				}
//			}
//		}
//		// CMPT 100 vs ENSC 100
//		else {
//			return c1Subject.compareTo(c2Subject);
//		}
//	}
	
	public int compare(Course c1, Course c2) {
		// e.g. CMPT 100 vs CMPT 1xx
		String c1Subject = c1.getSubject();
		String c2Subject = c2.getSubject();
		String c1CatalogNum = c1.getCatalogNum();
		String c2CatalogNum = c2.getCatalogNum();
		System.out.println(c1Subject + " " + c1CatalogNum + ", " + c2Subject + " " + c2CatalogNum);
		if (c1Subject.equals(c2Subject)) {
			// 1.. vs 1.. OR X.. vs X..
			if (this.compare(c1CatalogNum.charAt(0), c2CatalogNum.charAt(0)) == 0) {
				// 10. vs 10. OR XX. vs XX.
				if (this.compare(c1CatalogNum.charAt(1), c2CatalogNum.charAt(1)) == 0) {
					// 100 vs 100 OR XX1 vs XX1
					if (this.compare(c1CatalogNum.charAt(2), c2CatalogNum.charAt(2)) == 0) {
						return 0;
					}
					// 100 vs 101 OR XXX vs XX1
					return this.compare(c1CatalogNum.charAt(2), c2CatalogNum.charAt(2));
				}
				return this.compare(c1CatalogNum.charAt(1), c2CatalogNum.charAt(1));
			}
		}
		return this.compare(c1CatalogNum.charAt(0), c2CatalogNum.charAt(0));
	}
	
	private int compare(char c1, char c2) {
		if ((c1 == 'X' || c1 == 'W') && (c2 == 'X' || c2 == 'W')) {
			return 0;
		}
		else if (c1 == 'X' || c1 == 'W') {
			return -1;
		}
		else if (c2 == 'X' || c2 == 'W') {
			return 1;
		}else {
			return c1 - c2;
		}
	}

}
