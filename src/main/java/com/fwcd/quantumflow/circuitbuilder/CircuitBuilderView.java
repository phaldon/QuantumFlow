package com.fwcd.quantumflow.circuitbuilder;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

import com.fwcd.fructose.swing.Viewable;

public class CircuitBuilderView implements Viewable {
	private final JPanel view;
	
	private final CircuitToolBar toolBar;
	private final QuantumCircuitView circuit;
	private final JTextPane output;
	
	public CircuitBuilderView() {
		view = new JPanel();
		view.setLayout(new BorderLayout());
		
		circuit = new QuantumCircuitView();
		circuit.addListener(this::onChange);
		view.add(circuit.getView(), BorderLayout.CENTER);
		
		toolBar = new CircuitToolBar(circuit);
		view.add(toolBar.getView(), BorderLayout.SOUTH);
		
		output = new JTextPane();
		output.setPreferredSize(new Dimension(180, 180));
		view.add(new JScrollPane(output), BorderLayout.EAST);
		
		onChange();
	}
	
	private void onChange() {
		output.setText(circuit.getModel().compute().toString());
		output.repaint();
	}

	@Override
	public JComponent getView() {
		return view;
	}
}
