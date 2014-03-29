
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

import junit.framework.TestCase;

/**
 * JUnit Test class for the Course class. 
 */
public class CourseTest extends TestCase {

//	public void assertBarCountEqual(Iterator<Histogram.Bar> given, int[] expectedCounts) {
//		assertNotNull(given);
//		assertNotNull(expectedCounts);
//		int index = 0;
//		while (given.hasNext()) {
//			Histogram.Bar bar = given.next();
//
//			assertTrue(index < expectedCounts.length);
//			assertNotNull(bar);
//			assertEquals(bar.getCount(), expectedCounts[index]);
//			
//			index++;
//		}
//		
//		// In case no asserts were checked above (empty set).
//		assertTrue(true);
//	}
//	
//	/**
//	 * Custom test function which validates the ranges are correct on the bars.
//	 * @param given An iterator to the Bars of the Histogram.
//	 * @param expectedRange The expected size of the range (i.e., span) of each bar.
//	 */
//	public void assertBarsRange(Iterator<Histogram.Bar> given, int expectedRange) {
//		assertNotNull(given);
//		while (given.hasNext()) {
//			Histogram.Bar bar = given.next();
//
//			assertNotNull(bar);
//			int rangeWidth = bar.getRangeMax() - bar.getRangeMin() + 1;
//			assertEquals(rangeWidth, expectedRange);
//		}
//		
//		// In case no asserts were checked above (empty set).
//		assertTrue(true);
//	}
	
	public void testSubject() {
		String courseName = "CMPT 370";
		String subject = "CMPT";
		String catalogNum = "370";
		Course course = new Course(courseName, subject, catalogNum);
		
		assertEquals(subject, course.getSubject());
	}
	public void testCatalogNum() {
		String courseName = "CMPT 370";
		String subject = "CMPT";
		String catalogNum = "370";
		Course course = new Course(courseName, subject, catalogNum);
		
		assertEquals(catalogNum, course.getCatalogNum());
	}
	
	public void testIsUndergdrad() {
		Course course1 = new Course("CMPT XXX", "CMPT", "XXX");
		assertEquals(true, course1.getIsUndergrad());
		
		Course course2 = new Course("CMPT 1XX", "CMPT", "1XX");
		assertEquals(true, course2.getIsUndergrad());
		
		Course course3 = new Course("CMPT XX1", "CMPT", "XX1");
		assertEquals(true, course3.getIsUndergrad());
		
		Course course4 = new Course("CMPT 100", "CMPT", "100");
		assertEquals(true, course4.getIsUndergrad());
		
		Course course5 = new Course("CMPT 225", "CMPT", "225");
		assertEquals(true, course5.getIsUndergrad());
		
		Course course6 = new Course("CMPT 320W", "CMPT", "320W");
		assertEquals(true, course6.getIsUndergrad());
		
		Course course7 = new Course("CMPT 499", "CMPT", "499");
		assertEquals(true, course7.getIsUndergrad());
		
		Course course8 = new Course("CMPT 555", "CMPT", "555");
		assertEquals(false, course8.getIsUndergrad());
		
		Course course9 = new Course("CMPT 6XX", "CMPT", "6XX");
		assertEquals(false, course9.getIsUndergrad());
		
		Course course10 = new Course("CMPT 700", "CMPT", "700");
		assertEquals(false, course10.getIsUndergrad());
		
		Course course11 = new Course("CMPT 899", "CMPT", "899");
		assertEquals(false, course11.getIsUndergrad());
	}
	
	public void testCourseOffering() {
		CourseOffering co1 = new CourseOffering(1044, "MACM", "101", "Burnaby", 89, "LEC", 160, new ArrayList<String>(Arrays.asList("Frank Burton")));
		CourseOffering co2 = new CourseOffering(1044, "MACM", "101", "Surrey", 18, "LEC", 72, new ArrayList<String>(Arrays.asList("Anthony Dixon", " Daniela Marinescu")));
		Course course1 = new Course("MACM 101", "MACM", "101");
		course1.appendCourseOffering(co1);
		course1.appendCourseOffering(co2);
		
		List<CourseOffering> cos1 = new ArrayList<CourseOffering>();
		cos1.add(co1);
		cos1.add(co2);
		System.out.println("expected: " + cos1 + ", \nactual: " + course1.getCourseOfferings());
		assertEquals(cos1, course1.getCourseOfferings());
		
		
		CourseOffering co3 = new CourseOffering(1044, "MACM", "101", "Burnaby", 89, "LEC", 160, new ArrayList<String>(Arrays.asList("Frank Burton")));
		CourseOffering co4 = new CourseOffering(1044, "MACM", "101", "Burnaby", 18, "LEC", 72, new ArrayList<String>(Arrays.asList("Anthony Dixon", " Daniela Marinescu")));
		Course course2 = new Course("MACM 101", "MACM", "101");
		course2.appendCourseOffering(co3);
		course2.appendCourseOffering(co4);
		
		List<CourseOffering> cos2 = new ArrayList<CourseOffering>();
		cos2.add(new CourseOffering(1044, "MACM", "101", "Burnaby", 107, "LEC", 232, new ArrayList<String>(Arrays.asList("Frank Burton", "Anthony Dixon", " Daniela Marinescu"))));
		System.out.println("expected: " + cos2 + ", \nactual: " + course2.getCourseOfferings());
		assertEquals(cos2, course2.getCourseOfferings());
		
		
		CourseOffering co5 = new CourseOffering(1044, "MACM", "101", "Burnaby", 89, "LEC", 160, new ArrayList<String>(Arrays.asList("Frank Burton")));
		CourseOffering co6 = new CourseOffering(1044, "MACM", "101", "Burnaby", 18, "TUT", 72, new ArrayList<String>(Arrays.asList("Frank Burton")));
		Course course3 = new Course("MACM 101", "MACM", "101");
		course3.appendCourseOffering(co5);
		course3.appendCourseOffering(co6);
		
		List<CourseOffering> cos3 = new ArrayList<CourseOffering>();
		CourseOffering co7 = new CourseOffering(1044, "MACM", "101", "Burnaby", 89, "LEC", 160, new ArrayList<String>(Arrays.asList("Frank Burton")));
		co7.getSections().add(new Section(SectionType.TUT, 72, 18));
		cos3.add(co7);
		System.out.println("expected: " + cos3 + ", \nactual: " + course3.getCourseOfferings());
		assertEquals(cos3, course3.getCourseOfferings());
	}
	
	public void testSort1() {
		Course course1 = new Course("CMPT 705", "CMPT", "705");
		Course course2 = new Course("CMPT 101", "CMPT", "101");
		Course course3 = new Course("CMPT 102", "CMPT", "102");
		List<Course> unsortedCourses = new ArrayList<Course>();
		unsortedCourses.add(course1);
		unsortedCourses.add(course2);
		unsortedCourses.add(course3);
		Collections.sort(unsortedCourses, new SortedCourseByName());
		
		List<Course> sortedCourses = new ArrayList<Course>();
		sortedCourses.add(course2);
		sortedCourses.add(course3);
		sortedCourses.add(course1);
		System.out.println("expected: " + sortedCourses + ", actual: " + unsortedCourses);
		assertEquals(sortedCourses, unsortedCourses);
	}
	
	public void testSort2() {
		Course course1 = new Course("CMPT 1XX", "CMPT", "1XX");
		Course course2 = new Course("CMPT 878", "CMPT", "878");
		Course course3 = new Course("CMPT 100", "CMPT", "100");
		List<Course> unsortedCourses = new ArrayList<Course>();
		unsortedCourses.add(course1);
		unsortedCourses.add(course2);
		unsortedCourses.add(course3);
		Collections.sort(unsortedCourses, new SortedCourseByName());
		
		List<Course> sortedCourses = new ArrayList<Course>();
		sortedCourses.add(course1);
		sortedCourses.add(course3);
		sortedCourses.add(course2);
		System.out.println("expected: " + sortedCourses + ", actual: " + unsortedCourses);
		assertEquals(sortedCourses, unsortedCourses);
	}
	
	public void testSort3() {
		Course course1 = new Course("CMPT 1XX", "CMPT", "1XX");
		Course course2 = new Course("CMPT 878", "CMPT", "878");
		Course course3 = new Course("CMPT XX1", "CMPT", "XX1");
		List<Course> unsortedCourses = new ArrayList<Course>();
		unsortedCourses.add(course1);
		unsortedCourses.add(course2);
		unsortedCourses.add(course3);
		Collections.sort(unsortedCourses, new SortedCourseByName());
		
		List<Course> sortedCourses = new ArrayList<Course>();
		sortedCourses.add(course3);
		sortedCourses.add(course1);
		sortedCourses.add(course2);
		System.out.println("expected: " + sortedCourses + ", actual: " + unsortedCourses);
		assertEquals(sortedCourses, unsortedCourses);
	}
	
	public void testSortLarge1() {
		Course course1 = new Course("CMPT 705", "CMPT", "705");
		Course course2 = new Course("CMPT 101", "CMPT", "101");
		Course course3 = new Course("CMPT 102", "CMPT", "102");
		Course course4 = new Course("CMPT 201", "CMPT", "201");
		Course course5 = new Course("CMPT 307", "CMPT", "307");
		Course course6 = new Course("CMPT 300", "CMPT", "300");
		List<Course> unsortedCourses = new ArrayList<Course>();
		unsortedCourses.add(course1);
		unsortedCourses.add(course2);
		unsortedCourses.add(course3);
		unsortedCourses.add(course4);
		unsortedCourses.add(course5);
		unsortedCourses.add(course6);
		Collections.sort(unsortedCourses, new SortedCourseByName());
		
		List<Course> sortedCourses = new ArrayList<Course>();
		sortedCourses.add(course2);
		sortedCourses.add(course3);
		sortedCourses.add(course4);
		sortedCourses.add(course6);
		sortedCourses.add(course5);
		sortedCourses.add(course1);
		System.out.println("expected: " + sortedCourses + ", actual: " + unsortedCourses);
		assertEquals(sortedCourses, unsortedCourses);
	}
	
	public void testSortLarge2() {
		Course course1 = new Course("CMPT 7XX", "CMPT", "7XX");
		Course course2 = new Course("CMPT XXX", "CMPT", "XXX");
		Course course3 = new Course("CMPT 300", "CMPT", "300");
		Course course4 = new Course("CMPT 1XX", "CMPT", "201");
		Course course5 = new Course("CMPT 320W", "CMPT", "320W");
		Course course6 = new Course("CMPT X11", "CMPT", "X11");
		List<Course> unsortedCourses = new ArrayList<Course>();
		unsortedCourses.add(course1);
		unsortedCourses.add(course2);
		unsortedCourses.add(course3);
		unsortedCourses.add(course4);
		unsortedCourses.add(course5);
		unsortedCourses.add(course6);
		Collections.sort(unsortedCourses, new SortedCourseByName());
		
		List<Course> sortedCourses = new ArrayList<Course>();
		sortedCourses.add(course2);
		sortedCourses.add(course6);
		sortedCourses.add(course4);
		sortedCourses.add(course3);
		sortedCourses.add(course5);
		sortedCourses.add(course1);
		System.out.println("expected: " + sortedCourses + ", actual: " + unsortedCourses);
		assertEquals(sortedCourses, unsortedCourses);
	}


}
