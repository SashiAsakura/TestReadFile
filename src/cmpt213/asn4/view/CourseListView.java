package cmpt213.asn4.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;

public class CourseListView extends ABCCoursePlanerPanel {

	private int verticalWrap;

	public CourseListView(Object model) {
		super(model, "Course List");
		this.createCourseListPanel();
	}

	private void createCourseListPanel() {
		JPanel courseListPanel = (JPanel) super.getContentPanel();
		
		Vector<String> courseList = new Vector<String>();
		courseList.add("CMPT 110");
		courseList.add("CMPT 120");
		courseList.add("CMPT 213");
		courseList.add("CMPT 225");
		courseList.add("CMPT 300");
		JList courseNameList = new JList(courseList);
		courseNameList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		courseNameList.setSelectedIndex(0);
		courseNameList.setVisibleRowCount(3);
		JScrollPane courseListScrollPane = new JScrollPane(courseNameList);
		courseListPanel.add(courseListScrollPane);
	}
	
}
