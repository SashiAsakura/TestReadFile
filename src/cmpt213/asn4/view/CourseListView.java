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

import cmpt213.asn4.model.Course;
import cmpt213.asn4.model.CoursePlanner;

public class CourseListView extends ABCCoursePlanerPanel {
	private int verticalWrap;
	private CoursePlannerView coursePlannerView;
	private Vector<String> courses;
	private JList courseNameList;
	private JPanel courseListPanel;

	public CourseListView(Object model) {
		super(model, "Course List");
		this.createCourseListPanel();
	}

	private void createCourseListPanel() {
		this.courseListPanel = (JPanel) super.getContentPanel();
		this.courseListPanel.setLayout(new BoxLayout(this.courseListPanel, BoxLayout.PAGE_AXIS));
		
		this.courses = new Vector<String>();
		this.courseNameList = new JList(this.courses);
		
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

	public void updateCoursesInCourseListView(String selectedDepartment,
			boolean includeUndergrad, boolean includeGrad) {
		System.out.println("CourseListView:: updateCoursesInCourseListView() - includeUndergrad=" 
				+ includeUndergrad + ", includeGrad=" + includeGrad);
		
		CoursePlanner cp = (CoursePlanner) super.getModel();
		HashMap<String, List<Course>> sortedDepartmentHM = cp.getSortedDepartmentHM();
		this.courses = new Vector<String>();
		
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
				this.courses.add(course.getCourseName());
//				System.out.println(course);
			}
		}
		this.courseNameList.setListData(this.courses);
	}
	
}
