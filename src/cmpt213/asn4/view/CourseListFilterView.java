package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Arrays;
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
import cmpt213.asn4.model.CoursePlanner;

/**
 * Responsible for creating department selecting combobox, checkbox 
 * for undergrad and grad, and update course list button on top left.
 * It will update course list on bottom left once button is clicked.
 * @author Hisashi
 *
 */
public class CourseListFilterView extends ABCCoursePlanerPanel{
	private static final int THICKNESS = 3;
	private List<String> departmentList;
	
	private JComboBox departmentComboBox;
	private boolean includeUndergrad = false;
	private boolean includeGrad = false;
	private CoursePlannerView coursePlannerView;
	
	/*
	 * Constructor
	 */
	
	public CourseListFilterView(Object model) {
		super(model, "Course List Filter");
		
		this.createComboBoxInContentPanel();
	}
	
	/*
	 * Public Setter
	 */
	public void setCoursePlannerView(CoursePlannerView cpv) {
		this.coursePlannerView = cpv;
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
		buttonPanel.add(this.createUpdateCourseListButton(), BorderLayout.EAST);
		return buttonPanel;
	}

	private Component createUpdateCourseListButton() {
		JButton button = new JButton("Update Course List");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selectedDepartment = "";
				if (departmentComboBox.getSelectedIndex() != -1) {
					selectedDepartment = (String) departmentComboBox.getItemAt(departmentComboBox.getSelectedIndex());
					System.out.println(selectedDepartment + " selected at ComboBox");
					
					
					coursePlannerView.getCourseListView().updateCoursesInCourseListView(selectedDepartment, includeUndergrad, includeGrad);
				}
			}
		});
		
		return button;
	}

	private Component createCheckBoxePanel() {
		JPanel checkPanel = new JPanel();
		checkPanel.setLayout(new BorderLayout());
		
		checkPanel.add(this.createUndergradCheckBox(), BorderLayout.NORTH);
		checkPanel.add(this.createGradCheckBox(),BorderLayout.SOUTH);
		
		return checkPanel;
	}

	private Component createGradCheckBox() {
		final JCheckBox chkGrad = new JCheckBox("Include grad courses");
		chkGrad.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					includeGrad = true;
					System.out.println("grad checkbox selected");
				}
				else {
					includeGrad = false;
					System.out.println("grad checkbox unselected");
				}
			}
		});
		return chkGrad;
	}

	private Component createUndergradCheckBox() {
		final JCheckBox chkUndergrad = new JCheckBox("Include undergrad courses");
		chkUndergrad.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == 1) {
					includeUndergrad = true;
					System.out.println("undergrad checkbox selected");
				}
				else {
					includeUndergrad = false;
					System.out.println("undergrad checkbox unselected");
				}
			}
		});
		
		return chkUndergrad;
	}

	private Component createDepartmentComboBoxPanel() {
		JPanel departmentComboBoxPanel = new JPanel();
		departmentComboBoxPanel.setLayout(new BoxLayout(departmentComboBoxPanel, BoxLayout.LINE_AXIS));
		departmentComboBoxPanel.add(new JLabel("Department"));
		departmentComboBoxPanel.add(this.createDepartmentComboBox());
		return departmentComboBoxPanel;
	}

	private Component createDepartmentComboBox() {
		String[] departments = this.parseAndCreateDepartmentsArrayFromHM();
		this.departmentComboBox = new JComboBox(departments);
		return this.departmentComboBox;
	}

	private String[] parseAndCreateDepartmentsArrayFromHM() {
		CoursePlanner cp = (CoursePlanner) super.getModel();
		HashMap<String, List<Course>> sortedDepartmentHM = cp.getSortedDepartmentHM();
		String[] departments = new String[sortedDepartmentHM.size()];
		int count = 0;
		
		for (String department : sortedDepartmentHM.keySet()) {
			departments[count++] = department;
		}
		
		// Sort departments by alphabetical order
		Arrays.sort(departments);
		return departments;
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
