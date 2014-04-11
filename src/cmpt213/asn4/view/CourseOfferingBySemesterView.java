package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

import cmpt213.asn4.model.Course;
import cmpt213.asn4.model.CoursePlanner;

public class CourseOfferingBySemesterView extends ABCCoursePlanerPanel {
	private final int NUM_GRID_COLS = 3;
	private final int NUM_GRID_ROWS = 11;
	
	private JPanel gridPanel;

	public CourseOfferingBySemesterView(Object model) {
		super(model, "Course Offering by Semester");
		this.createCourseOfferingBySemesterPanel();
	}
	
	/*
	 * Public Method
	 */
	
	public void updateGrid(Course selectedCourse) {
		System.out.println("CourseOfferingBySemester:: updateGrid() - selectedCourse="
				+ selectedCourse + ",\n numYears= " + selectedCourse.getRangeOfYearsTaught());
		
		int numYears = selectedCourse.getRangeOfYearsTaught();
		
		JPanel panel = (JPanel) super.getContentPanel();
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.setSize(new Dimension(400, 400));
		
		this.gridPanel.removeAll();
		this.gridPanel.setLayout(new GridBagLayout());
//		this.gridPanel.setSize(new Dimension(200, 200));
		this.gridPanel.setMinimumSize(new Dimension(400, 400));
		
		for (int i = 0; i < NUM_GRID_COLS; i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			
			for (int j = 0; j < numYears; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
//				gbc.ipadx = 90;
//				gbc.ipady = 90;
				gbc.weightx = 90;
				gbc.weightx = 50;
				
				JLabel label = new JLabel("Test");
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.gridPanel.add(label, gbc);
			}
 		}
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
		
		panel.add(this.gridPanel, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
	}
	
	/*
	 * Private Method
	 */

	private void createCourseOfferingBySemesterPanel() {
		JPanel panel = (JPanel) super.getContentPanel();
		panel.setLayout(new BorderLayout());
		
		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridBagLayout());
//		this.gridPanel.setMaximumSize(new Dimension(200, 200));
//		this.gridPanel.setMinimumSize(new Dimension(400, 400));
		
		for (int i = 0; i < NUM_GRID_COLS; i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;

			for (int j = 0; j < NUM_GRID_ROWS; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
				gbc.ipadx = 90;
				gbc.ipady = 90;
				gbc.weightx = 90;
				gbc.weightx = 50;
				
				JLabel label = new JLabel("");
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.gridPanel.add(label, gbc);
			}
 		}
		
		panel.add(this.gridPanel, BorderLayout.CENTER);
	}

}
