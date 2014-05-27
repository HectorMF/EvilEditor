package com.perfectplay.org.desktop;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.CardLayout;

import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.GridLayout;

import javax.swing.JLabel;

import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.SwingConstants;
import javax.swing.JTabbedPane;

public class ComponentView extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ComponentView() {
		setLayout(new MigLayout("", "[grow,center]", "[grow]"));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane, "cell 0 0,grow");
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Entity Inspector", null, scrollPane, null);
		
		JPanel panel = new JPanel();
		scrollPane.setColumnHeaderView(panel);
		panel.setLayout(new MigLayout("", "[63px][166px]", "[23px]"));
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Enabled");
		panel.add(chckbxNewCheckBox, "cell 0 0,alignx left,aligny top");
		
		textField = new JTextField();
		panel.add(textField, "cell 1 0,alignx left,aligny center");
		textField.setColumns(20);
		
		//this.setMinimumSize(new Dimension(400,100));
		//setMinimumLayout

	}

}
