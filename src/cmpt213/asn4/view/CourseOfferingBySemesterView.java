package cmpt213.asn4.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class CourseOfferingBySemesterView extends ABCCoursePlanerPanel {
	private final int NUM_GRID_COLS = 3;
	private final int NUM_GRID_ROWS = 3;

	public CourseOfferingBySemesterView(Object model) {
		super(model, "Course Offering by Semester");
		this.createCourseOfferingBySemesterPanel();
	}

	private void createCourseOfferingBySemesterPanel() {
		JPanel panel = (JPanel) super.getContentPanel();
		panel.setLayout(new GridLayout(3, 3));
		panel.setBorder(BorderFactory.createBevelBorder(
				BevelBorder.RAISED));
		
		for (int i = 0; i < NUM_GRID_COLS; i++) {
			GridBagConstraints gbc = new GridBagConstraints();
//			gbc.fill = GridBagConstraints.HORIZONTAL;
			for (int j = 0; j < NUM_GRID_ROWS; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
//				gbc.ipadx = 90;
//				gbc.ipady = 90;
				
				panel.add(new JLabel("CMPT 213 - Surrey"));
			}
 		}
		
	}
}
