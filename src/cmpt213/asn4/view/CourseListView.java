package cmpt213.asn4.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import cmpt213.asn4.model.Course;
import cmpt213.asn4.model.CoursePlanner;

/**
 * Responsible for creating and updating course list on bottom left 
 * based on department selected by combobox.
 * @author Hisashi
 *
 */
public class CourseListView extends ABCCoursePlanerPanel {
	private Vector<String> courseNames;
	private List<Course> courses;
	private Course selectedCourse;
	
	private CoursePlannerView coursePlannerView;
	private JList courseNameList;
	private JPanel courseListPanel;

	public CourseListView(Object model) {
		super(model, "Course List");
		this.createCourseListPanel();
	}

	private void createCourseListPanel() {
		this.courseListPanel = (JPanel) super.getContentPanel();
		this.courseListPanel.setLayout(new BoxLayout(this.courseListPanel, BoxLayout.PAGE_AXIS));
		
		this.courseNames = new Vector<String>();
		this.courseNameList = new JList(this.courseNames);
		
		ListSelectionModel lsm = this.courseNameList.getSelectionModel();
		lsm.addListSelectionListener(new ListSelectionListener() {
			int selectedCourseIndex = -1;
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// Ignore the method firing twice when mouse clicked
				if (!e.getValueIsAdjusting()) {
					selectedCourseIndex = e.getLastIndex();
					if (courses.size() != 0) {
						Course selectedCourse = courses.get(selectedCourseIndex);
						System.out.println(selectedCourse.getCourseName() + " selected");
						coursePlannerView.getCourseOfferingBySemester().updateGrid(selectedCourse);
					}
				}
			}
		});
		
		this.courseNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.courseNameList.setSelectedIndex(0);
		this.courseNameList.setVisibleRowCount(-1);
		this.courseNameList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		JScrollPane courseListScrollPane = new JScrollPane(courseNameList);
		this.courseListPanel.add(courseListScrollPane);
	}
	
	/*
	 * Public Setter
	 */
	public void setCoursePlannerView(CoursePlannerView cpv) {
		this.coursePlannerView = cpv;
	}

	/*
	 * Public Method
	 */
	
	public void updateCoursesInCourseListView(String selectedDepartment,
			boolean includeUndergrad, boolean includeGrad) {
		System.out.println("CourseListView:: updateCoursesInCourseListView() - includeUndergrad=" 
				+ includeUndergrad + ", includeGrad=" + includeGrad);
		
		CoursePlanner cp = (CoursePlanner) super.getModel();
		HashMap<String, List<Course>> sortedDepartmentHM = cp.getSortedDepartmentHM();
		this.courseNames = new Vector<String>();
		this.courses = new ArrayList<Course>();
		
		for (Course course : sortedDepartmentHM.get(selectedDepartment)) {
			boolean addCourse = false;
			if (includeUndergrad) {
				if (course.getIsUndergrad()) {
					addCourse = true;
				}
			}
			if (includeGrad) {
				if (!course.getIsUndergrad()) {
					addCourse = true;
				}
			}
			
			if (addCourse) {
				this.courseNames.add(course.getCourseName());
				this.courses.add(course);
//				System.out.println(course);
			}
		}
		this.courseNameList.setListData(this.courseNames);
	}
	
}
