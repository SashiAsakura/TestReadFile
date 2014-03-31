package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.CoursePlanner;

public class CoursePlannerView {
	private CoursePlanner coursePlanner;

	public static void main(String[] args) {
		CoursePlannerView cpv = new CoursePlannerView();
//		cpv.coursePlanner = new CoursePlanner();
	}
	
	public CoursePlannerView() {
		this.coursePlanner = new CoursePlanner();
		
		JFrame frame = new JFrame("FAS Course Planner");
		frame.add(this.createCoursePlannerPanel());
		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);	
	}

	private Component createCoursePlannerPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(this.createLeftPanel(), BorderLayout.WEST);
		jPanel.add(this.createCenterPanel(), BorderLayout.CENTER);
		jPanel.add(this.createRightPanel(), BorderLayout.EAST);
		
		return jPanel;
	}

	private Component createLeftPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		
//		jPanel.add(this.createCourseListFilterPanel(), BorderLayout.NORTH);
		jPanel.add(new CourseListFilter(this.coursePlanner), BorderLayout.NORTH);
		jPanel.add(this.createCourseListPanel(), BorderLayout.CENTER);
		
		return jPanel;
	}
	
	private Component createCenterPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(this.createCourseOfferingBySemesterPanel(), BorderLayout.CENTER);
		
		return jPanel;
	}

	private Component createRightPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		jPanel.add(this.createStatsPanel(), BorderLayout.NORTH);
		jPanel.add(this.createDetailsOfCourseOfferingPanel(), BorderLayout.CENTER);
		
		return jPanel;
	}
	

	private Component createDetailsOfCourseOfferingPanel() {
		return new JLabel("Details of Course Offering");
	}

	private Component createStatsPanel() {
		return new JLabel("Statistics");
	}

	private Component createCourseOfferingBySemesterPanel() {
		return new JLabel("Course Offering by Semester");
	}

	private Component createCourseListFilterPanel() {
		return new JLabel("Course List Filter");
	}
	
	private Component createCourseListPanel() {
		return new JLabel("Course List");
	}

}
