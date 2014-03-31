package cmpt213.asn4.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public abstract class ABCCoursePlanerPanel extends JPanel{
	private Object model;
	private String title;
	private Dimension maximumSize;
	private Dimension preferredSize;
	private Component titlePanel;
	private Component contentPanel;
	

	/*
	 * Constructors
	 */
//	public ABCCoursePlanerPanel(Object model) {
//		this.model = model;
//		setLayout(new BorderLayout());
//		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.GRAY));
//	}
	
	public ABCCoursePlanerPanel(Object model, String title) {
		this.model = model;
		this.title = title;
		setLayout(new BorderLayout());
//		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.GRAY));
		add(this.getTitleLabel(title), BorderLayout.NORTH);
		add(this.setContentPanel("Content"), BorderLayout.CENTER);
		
	}
	
	private Component setContentPanel(String string) {
		JLabel label = new JLabel(title);
		label.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.GRAY));
		label.setBackground(Color.WHITE);
		return label;
	}

	public Component getContentPanel(String string) {
		return this.contentPanel;
	}

	private Component getTitleLabel(String title2) {
		JLabel label = new JLabel(title);
		label.setForeground(Color.BLUE);
		return label;
	}

	/*
	 * Public Getter
	 */
	public Object getModel() {
		return this.model;
	}
	
//	public Dimension getMaximumSize() {
//		return this.maximumSize;
//	}
//	
//	public Dimension getPreferredSize() {
//		return this.preferredSize;
//	}
	
	/*
	 * Public Method
	 */
	
	public void fixHeight() {
	}
	
	
}
