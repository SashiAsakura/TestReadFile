package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.CoursePlanner;

/***
 * Responsible for creating a window for course planner view.
 * This displays ComboBox for departments, list of courses of selected department,
 * course grid, stats, and course details.
 * @author Hisashi
 *
 */
public class CoursePlannerView {
	private CoursePlanner coursePlanner;
	private CourseListFilterView courseListFilterView;
	private CourseListView courseListView;
	private CourseOfferingBySemesterView courseOfferingBySemesterView;	
	private StatisticsView statisticsView;
	private DetailsOfCourseOfferingView detailsOfCourseOfferingView;

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
	
	/*
	 * Public Getter
	 */
	
	public CourseListFilterView getCourseListFilterView() {
		return this.courseListFilterView;
	}
	
	public CourseListView getCourseListView() {
		return this.courseListView;
	}
	
	public CourseOfferingBySemesterView getCourseOfferingBySemester() {
		return this.courseOfferingBySemesterView;
	}
	
	public StatisticsView getStatisticsView() {
		return this.statisticsView;
	}
	
	public DetailsOfCourseOfferingView getDetailsOfCourseOfferingView() {
		return this.detailsOfCourseOfferingView;
	}

	/*
	 * Private Method
	 */
	
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
		
		this.courseListFilterView = new CourseListFilterView(this.coursePlanner);
		this.courseListView = new CourseListView(this.coursePlanner);
		this.courseListFilterView.setCoursePlannerView(this);
		this.courseListView.setCoursePlannerView(this);
		jPanel.add(this.courseListFilterView, BorderLayout.NORTH);
		jPanel.add(this.courseListView, BorderLayout.CENTER);
		
		return jPanel;
	}
	
	private Component createCenterPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		this.courseOfferingBySemesterView = new CourseOfferingBySemesterView(this.coursePlanner);
		this.courseOfferingBySemesterView.setCoursePlannerView(this);
		jPanel.add(this.courseOfferingBySemesterView, BorderLayout.CENTER);
		
		return jPanel;
	}

	private Component createRightPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		this.statisticsView = new StatisticsView(this.coursePlanner);
		this.detailsOfCourseOfferingView = new DetailsOfCourseOfferingView(this.coursePlanner);
		jPanel.add(this.statisticsView, BorderLayout.NORTH);
		jPanel.add(this.detailsOfCourseOfferingView, BorderLayout.CENTER);
		
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
