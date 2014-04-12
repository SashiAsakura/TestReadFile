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
	
	public ABCCoursePlanerPanel(Object model, String title) {
		this.model = model;
		this.title = title;
		setLayout(new BorderLayout());
		
		this.titlePanel = this.createTitleLabel(title);
		this.contentPanel = this.createContentPanel();
		add(this.titlePanel, BorderLayout.NORTH);
		add(this.contentPanel, BorderLayout.CENTER);
	}
	
	/*
	 * Public Getter
	 */
	
	public Component getTitlePanel() {
		return this.titlePanel;
	}
	
	public Component getContentPanel() {
		return this.contentPanel;
	}
	
	public Object getModel() {
		return this.model;
	}
	
	
	/*
	 * Private Method
	 */
	
	private Component createTitleLabel(String title2) {
		JLabel label = new JLabel(title);
		label.setForeground(Color.BLUE);
		return label;
	}
	
	private Component createContentPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.GRAY));
		return panel;
	}
	
	/*
	 * Public Method
	 */
	
	public void fixHeight() {
	}
	
//	@Override
//	public Dimension getMaximumSize() {
//		return this.maximumSize;
//	}

	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension(230, 130);
//	}
	
	public void setPreferredSize(Dimension preferredSize) {
//		this.contentPanel = new ?JPanel();
		this.contentPanel.setPreferredSize(preferredSize);
	}
}
