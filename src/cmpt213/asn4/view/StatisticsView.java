package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.Course;
import cmpt213.asn4.model.HistogramIcon;

/**
 * Responsible for creating and updating histogram statistics graph
 * on top right of the window based on the course button selected
 * from course grid in the middle.
 * @author Hisashi
 *
 */
public class StatisticsView extends ABCCoursePlanerPanel{
	private Course selectedCourse;
	private int[] semesterOfferedCounts;
	private int[] campusOfferedCounts;
	
	private JPanel courseNamePanel;
	private JLabel courseNameLabel;
	private JLabel campusHistogramLabel;
	private JLabel semesterHistogramLabel;

	/*
	 * Constructor
	 */
	
	public StatisticsView(Object model) {
		super(model, "Statistics");
//		super.setMinimumSize(new Dimension(200, 600));
		super.getContentPanel().setPreferredSize(new Dimension(200, 400));
		this.createHistogramsPanel();
	}

	/*
	 * Public Method
	 */
	
	public void updateHistograms(Course selectedCourse) {
		this.selectedCourse = selectedCourse;
		this.updateSemesterHistogram();
		this.updateCampusHistorgram();
	}

	/*
	 * Private Method
	 */
	
	private void updateCampusHistorgram() {
		this.campusOfferedCounts = this.selectedCourse.getNumTimesOfferedByCampus();
		
		this.campusHistogramLabel.setIcon(new HistogramIcon(this.campusOfferedCounts, 4, 200, 130));
	}

	private void updateSemesterHistogram() {
		this.semesterOfferedCounts = this.selectedCourse.getNumTimesOfferedBySemester();
		
		this.courseNameLabel.setText(this.selectedCourse.getCourseName());
		this.semesterHistogramLabel.setIcon(new HistogramIcon(this.semesterOfferedCounts, 3, 200, 130));
	}
	
	private void createHistogramsPanel() {
		JPanel panel = (JPanel) super.getContentPanel();
//		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(this.createCourseNamePanel());
		panel.add(this.createSemesterHistogramPanel());
		panel.add(this.createCampusHistogramPanel());
	}
	
	private Component createCampusHistogramPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		
		panel.add(new JLabel("Campus Offerings: "));
		
		this.campusOfferedCounts = new int[0];
		this.campusHistogramLabel = new JLabel(new HistogramIcon(this.campusOfferedCounts, 0, 200, 130));
		this.campusHistogramLabel.setPreferredSize(new Dimension(210, 150));
		panel.add(this.campusHistogramLabel);
		
		panel.add(new JLabel("(0=Bby, 1=Sry, 2=Van, 3=Other)"));
		return panel;
	}

	private Component createSemesterHistogramPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Semester Offerings: "));
		
		this.semesterOfferedCounts = new int[0];
		this.semesterHistogramLabel = new JLabel(new HistogramIcon(this.semesterOfferedCounts, 0, 200, 130));
		this.semesterHistogramLabel.setPreferredSize(new Dimension(210, 150));
		panel.add(this.semesterHistogramLabel);
		
		panel.add(new JLabel("(0=Spring, 1=Summer, 2=Fall)"));
		return panel;
	}

	private Component createCourseNamePanel() {
		this.courseNamePanel = new JPanel();
		this.courseNamePanel.setLayout(new BorderLayout());
		this.courseNamePanel.add(new JLabel("Course: "), BorderLayout.WEST);
		this.courseNamePanel.add(Box.createHorizontalGlue());
		
		this.courseNameLabel = new JLabel();
		this.courseNamePanel.add(this.courseNameLabel, BorderLayout.CENTER);
		return this.courseNamePanel;
	}

}
