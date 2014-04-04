package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.Course;

public class CourseListFilterView extends ABCCoursePlanerPanel{
	private static final int THICKNESS = 3;
	private List<String> departmentList;
	
	public static void main(String[] args) {
	}
	
	/*
	 * Constructor
	 */
	
	public CourseListFilterView(Object model) {
		super(model, "Course List Filter");
		
		this.createComboBoxInContentPanel();
	}
	
	/*
	 * Private Method
	 */
	private void createComboBoxInContentPanel() {
		JPanel contentPanel = (JPanel) super.getContentPanel();	
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.PAGE_AXIS));
		
		contentPanel.add(this.createDepartmentComboBoxPanel());
		contentPanel.add(this.createCheckBoxePanel());
		contentPanel.add(this.createUpdateCourseListButtonPanel());
	}

	private Component createUpdateCourseListButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.add(Box.createHorizontalGlue(), BorderLayout.CENTER);
		buttonPanel.add(new JButton("Update Course List"), BorderLayout.EAST);
		return buttonPanel;
	}

	private Component createCheckBoxePanel() {
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new BorderLayout());
		final JCheckBox chkUndergrad = new JCheckBox("Include undergrad courses");
		final JCheckBox chkGrad = new JCheckBox("Include grad courses");
		checkPanel.add(chkUndergrad, BorderLayout.NORTH);
		checkPanel.add(chkGrad,BorderLayout.SOUTH);
		
		return checkPanel;
	}

	private Component createDepartmentComboBoxPanel() {
		JPanel departmentComboBoxPanel = new JPanel();
		departmentComboBoxPanel.setLayout(new BoxLayout(departmentComboBoxPanel, BoxLayout.LINE_AXIS));
		departmentComboBoxPanel.add(new JLabel("Department"));
		departmentComboBoxPanel.add(this.createDepartmentComboBox());
		return departmentComboBoxPanel;
	}

	private Component createDepartmentComboBox() {
		Vector<String> departments = new Vector<String>();
		departments.add("CMPT");
		departments.add("MACM");
		JComboBox departmentCombo = new JComboBox(departments);
		return departmentCombo;
	}

	private Component createComboBox() {
		Vector<String> options = new Vector<String>();
		options.add("First");
		options.add("Second");
		JComboBox myComboBox = new JComboBox(options);
		myComboBox.setSelectedIndex(0);
		return myComboBox;
	}

	private JPanel createBorderLayoutPanel() {
		JPanel jPanel = new JPanel();
		jPanel.setLayout(new BorderLayout());
		
		jPanel.add(new JLabel("Your Name: "), BorderLayout.WEST);
		jPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, THICKNESS));
		return jPanel;
	}


}
