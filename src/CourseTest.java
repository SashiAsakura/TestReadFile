
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
