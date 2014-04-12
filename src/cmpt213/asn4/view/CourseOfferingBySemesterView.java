package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cmpt213.asn4.model.Course;
import cmpt213.asn4.model.CourseOffering;
import cmpt213.asn4.model.Semester;

/**
 * Responsible for creating and updating course grid in the middle
 * of the window.
 * @author Hisashi
 *
 */

public class CourseOfferingBySemesterView extends ABCCoursePlanerPanel {
	private final int NUM_GRID_COLS = 4;
	private CoursePlannerView coursePlannerView;
	
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
		int oldestYear = selectedCourse.getOldestYearTaught();
		
		JPanel panel = (JPanel) super.getContentPanel();
		panel.removeAll();
		panel.setLayout(new BorderLayout());
		panel.setSize(new Dimension(400, 400));

		this.gridPanel = new JPanel();
		this.gridPanel.setLayout(new GridBagLayout());
		this.gridPanel.setMinimumSize(new Dimension(200, 200));

		this.createYearsTitle(numYears, oldestYear);
		this.createSemestersTitle();
		this.drawGrid(numYears, selectedCourse);
		
		this.gridPanel.revalidate();
		this.gridPanel.repaint();
		
		panel.add(this.gridPanel, BorderLayout.CENTER);
		panel.revalidate();
		panel.repaint();
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

	private void drawGrid(int numYears, Course selectedCourse) {
		for (int i = 1; i < NUM_GRID_COLS; i++) {
			GridBagConstraints gbc = new GridBagConstraints();
			gbc.fill = GridBagConstraints.BOTH;
			
			for (int j = 1; j <= numYears; j++) {
				gbc.gridx = i;
				gbc.gridy = j;
				gbc.weightx = 90;
				gbc.weighty = 50;
				
				JPanel grid = new JPanel();
				grid.setLayout(new BoxLayout(grid, BoxLayout.PAGE_AXIS));
				
				this.tryCreateCourseButton(grid, selectedCourse, i, j - 1);
				
				grid.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				this.gridPanel.add(grid, gbc);
			}
 		}
	}

	private void tryCreateCourseButton(JPanel grid, Course selectedCourse, int semesterCode, int yearOffset) {
		Semester semester = convertToSemester(semesterCode);
		int yearTaught = selectedCourse.getOldestYearTaught() + yearOffset;
		System.out.println("CourseOfferingBySemesterView:: tryCreateCourseButton() - "
				+ "selectedCourse=" + selectedCourse.getCourseName() + ", semester="
				+ semester + ", year=" + yearTaught);
		
		List<CourseOffering> courseOfferings = selectedCourse.getCoursesOfferredIn(semester, yearTaught);
		for (CourseOffering co : courseOfferings) {
			createCourseButton(grid, co);
		}
	}

	private Semester convertToSemester(int semesterCode) {
		Semester semester;
		if (semesterCode == 1) {
			semester = Semester.Spring;
		}
		else if (semesterCode == 2) {
			semester = Semester.Summer;
		}
		else {
			semester = Semester.Fall;
		}
		return semester;
	}

	private void createCourseButton(JPanel grid, final CourseOffering courseOffering) {
		JButton button = new JButton(courseOffering.getCourseName() + " - " + courseOffering.getCampusLocation());
		button.setPreferredSize(new Dimension(grid.getWidth(), 40));
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				coursePlannerView.getDetailsOfCourseOfferingView().updateCourse(courseOffering);
			}
		});
		
		grid.add(button);
	}

	private void createYearsTitle(int numYears, int oldestYear) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 0;
		
		for (int i = 1; i <= numYears; i++) {
			gbc.gridy = i;
			gbc.ipadx = 10;
			int year = oldestYear + i - 1;
			JLabel yearLabel = new JLabel(Integer.toString(year));
			yearLabel.setBackground(Color.WHITE);
			this.gridPanel.add(yearLabel, gbc);
		}
	}
	
	private void createSemestersTitle() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.ipady = 4;
		JLabel sprSemester = new JLabel("Spring");
		this.gridPanel.add(sprSemester, gbc);
		
		gbc.gridx = 2;
		JLabel summerSemester = new JLabel("Summer");
		this.gridPanel.add(summerSemester, gbc);
		
		gbc.gridx = 3;
		JLabel fallSemester = new JLabel("Fall");
		this.gridPanel.add(fallSemester, gbc);
	}

	private void createCourseOfferingBySemesterPanel() {
		JPanel panel = (JPanel) super.getContentPanel();
		panel.setBackground(Color.WHITE);
	}

}
