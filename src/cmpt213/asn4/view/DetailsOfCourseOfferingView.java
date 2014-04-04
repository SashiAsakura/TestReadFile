package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class DetailsOfCourseOfferingView extends ABCCoursePlanerPanel{

	public DetailsOfCourseOfferingView(Object model) {
		super(model, "Details of Course Offering");
		this.createDetailPanel();
	}

	private void createDetailPanel() {
		JPanel detailPanel = (JPanel) super.getContentPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.PAGE_AXIS));
		
		detailPanel.add(this.createPanel("Course Name: ", ""));
		detailPanel.add(this.createPanel("Location: ", ""));
		detailPanel.add(this.createPanel("Semester: ", ""));
		detailPanel.add(this.createPanel("Instructors: ", ""));
		detailPanel.add(this.createSectionAndEnrollmentPanel());
//		detailPanel.add(this.createSectionAndEnrollmentValuePanel());
//		detailPanel.add(this.createSectionTypePanel());
//		detailPanel.add(this.createEnrollmentPanel());
	}

//	private Component createSectionAndEnrollmentValuePanel() {
//		JPanel panel = new JPanel();
//		
//		panel.setLayout(new BorderLayout());
//		panel.add(new JLabel("LEC"), BorderLayout.WEST);
//		panel.add(new JLabel("47/50"), BorderLayout.EAST);
//		return panel;
//	}

	private Component createSectionAndEnrollmentPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BorderLayout());
		panel.add(this.createSectionPanel(), BorderLayout.WEST);
		panel.add(this.createEnrollmentPanel(), BorderLayout.EAST);
		return panel;
	}

	private Component createEnrollmentPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Enrollment (filled/cap"));
		panel.add(new JLabel("47/50"));
		return panel;
	}

	private Component createSectionPanel() {
		JPanel panel = new JPanel();
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
		panel.add(new JLabel("Section Type"));
		panel.add(new JLabel("LEC"));
		return panel;
	}

	private Component createPanel(String labelName, String textContent) {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		panel.add(new JLabel(labelName));
		panel.add(new JTextArea(textContent));
		return panel;
	}

}
