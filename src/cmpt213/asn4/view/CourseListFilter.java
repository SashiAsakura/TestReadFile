package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.Course;

public class CourseListFilter extends ABCCoursePlanerPanel{
	private static final int THICKNESS = 3;
	private List<String> departmentList;
	
	public static void main(String[] args) {
	}
	
	public CourseListFilter(Object model) {
		super(model, "Course List Filter");
		
//		this.createBorderLayoutPanel();
	}
	
//	private void createBorderLayout() {
//		JFrame frame = new JFrame();
//		frame.add(this.createBorderLayoutPanel());
//		
//		frame.setTitle("Test BorderLayout");
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);
//	}
	
	private JPanel createBorderLayoutPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		
		jPanel.add(new JLabel("Your Name: "), BorderLayout.WEST);
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, THICKNESS));
		return jPanel;
	}


}
