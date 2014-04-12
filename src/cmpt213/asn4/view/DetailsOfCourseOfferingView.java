package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import cmpt213.asn4.model.CourseOffering;

public class DetailsOfCourseOfferingView extends ABCCoursePlanerPanel{
	private JLabel courseLabel;
	private JLabel locationLabel;
	private JLabel semesterLabel;
	private JLabel instructorsLabel;
	private JLabel sectionTypeLabel;
	private JLabel enrollmentLabel;
	private JPanel sectionTypePanel;
	private JPanel valuesPanel;

	/*
	 * Constructor
	 */
	
	public DetailsOfCourseOfferingView(Object model) {
		super(model, "Details of Course Offering");
		this.createDetailPanel();
	}
	
	/*
	 * Public Method
	 */
	
	public void updateCourse(CourseOffering courseOffering) {
		System.out.println("DetailsOfCourseOfferingView:: updateCourse() - "
				+ "courseOffering=" + courseOffering);
		
		this.courseLabel.setText(courseOffering.getCourseName());
		this.locationLabel.setText(courseOffering.getCampusLocation().toString());
		this.semesterLabel.setText(Integer.toString(courseOffering.getSemesterCode()));
		this.instructorsLabel.setText(courseOffering.getInstructors().toString());
		this.sectionTypeLabel.setText(courseOffering.getSectionType().toString());
		String enrollment = courseOffering.getEnrolledSize() + " / " + courseOffering.getEnrollmentCapacity();
		this.enrollmentLabel.setText(enrollment);
	}

	/*
	 * Private Method
	 */
	
	private void createDetailPanel() {
		JPanel detailPanel = (JPanel) super.getContentPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.PAGE_AXIS));
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout());
		top.add(this.createTitlesPanel(), BorderLayout.WEST);
		top.add(this.createValuesPanel(), BorderLayout.CENTER);
		
		detailPanel.add(top);
		detailPanel.add(Box.createVerticalGlue());
	}

	private Component createValuesPanel() {
		this.valuesPanel = new JPanel();
		this.valuesPanel.setLayout(new BoxLayout(this.valuesPanel, BoxLayout.PAGE_AXIS));
		
		this.courseLabel = new JLabel("                                            ");
		this.courseLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.valuesPanel.add(this.courseLabel);
		
		this.locationLabel = new JLabel("                                            ");
		this.locationLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.valuesPanel.add(this.locationLabel);
		
		this.semesterLabel = new JLabel("                                            ");
		this.semesterLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.valuesPanel.add(this.semesterLabel);
		
		this.instructorsLabel = new JLabel("                                            ");
		this.instructorsLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		this.valuesPanel.add(this.instructorsLabel);
		
		this.valuesPanel.add(new JLabel("Enrollment (filled/cap)"));
		
		this.enrollmentLabel = new JLabel(" ");
		this.valuesPanel.add(this.enrollmentLabel);
		
		return this.valuesPanel;
	}

	private Component createTitlesPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Course Name:    "));
		panel.add(new JLabel("Location: "));
		panel.add(new JLabel("Semester: "));
		panel.add(new JLabel("Instructors: "));
		panel.add(new JLabel("Selection Type"));
		this.sectionTypeLabel = new JLabel("");
		panel.add(this.sectionTypeLabel);
		return panel;
	}

}
