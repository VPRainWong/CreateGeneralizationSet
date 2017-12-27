package com.vp.plugin.sample.genset.actions;

import java.awt.Point;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.IDiagramTypeConstants;
import com.vp.plugin.diagram.connector.IGeneralizationUIModel;
import com.vp.plugin.diagram.shape.IClassUIModel;
import com.vp.plugin.diagram.shape.IGeneralizationSetUIModel;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IGeneralization;
import com.vp.plugin.model.IGeneralizationSet;
import com.vp.plugin.model.factory.IModelElementFactory;

public class GeneralizationSetActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// Create Class Diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IClassDiagramUIModel diagram = (IClassDiagramUIModel) diagramManager.createDiagram(IDiagramTypeConstants.DIAGRAM_TYPE_CLASS_DIAGRAM);		
		
		// Create Super Class model and shape
		IClass superClass = IModelElementFactory.instance().createClass();
		superClass.setName("SuperClass");
		IClassUIModel superClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, superClass);
		superClassShape.setBounds(150, 50, 80, 40);
		superClassShape.setRequestResetCaption(true);
		
		// Create model and shape for sub-classes
		IClass subClass1 = IModelElementFactory.instance().createClass();
		subClass1.setName("Sub Class 1");
		IClassUIModel subClass1Shape = (IClassUIModel) diagramManager.createDiagramElement(diagram, subClass1);
		subClass1Shape.setBounds(50, 150, 80, 40);
		subClass1Shape.setRequestResetCaption(true);
		
		IClass subClass2 = IModelElementFactory.instance().createClass();
		subClass2.setName("Sub Class 2");
		IClassUIModel subClass2Shape = (IClassUIModel) diagramManager.createDiagramElement(diagram, subClass2);
		subClass2Shape.setBounds(250, 150, 80, 40);
		subClass2Shape.setRequestResetCaption(true);
		
		// Create generalization model and connector between super class and sub-classes
		IGeneralization generalization1 = IModelElementFactory.instance().createGeneralization();
		generalization1.setFrom(superClass);
		generalization1.setTo(subClass1);
		IGeneralizationUIModel generalization1Shape = (IGeneralizationUIModel) diagramManager.createConnector(
														diagram, generalization1, superClassShape, subClass1Shape, 
														new Point[] {	new Point(190, 90), 
																		new Point(190, 120), 
																		new Point(90, 120), 
																		new Point(90, 150)});
		generalization1Shape.setRequestResetCaption(true);
		
		IGeneralization generalization2 = IModelElementFactory.instance().createGeneralization();
		generalization2.setFrom(superClass);
		generalization2.setTo(subClass2);
		IGeneralizationUIModel generalization2Shape = (IGeneralizationUIModel) diagramManager.createConnector(
														diagram, generalization2, superClassShape, subClass2Shape, 
														new Point[] {	new Point(190, 90), 
																		new Point(190, 120),
																		new Point(290, 120),
																		new Point(290, 150)});
		generalization2Shape.setRequestResetCaption(true);
				
		// Create generalization set model
		IGeneralizationSet generalizationSet = IModelElementFactory.instance().createGeneralizationSet();
		
		// Add generalizations to generalization set
		generalizationSet.addGeneralization(generalization1);
		generalizationSet.addGeneralization(generalization2);
		
		// Create generalization UI model
		IGeneralizationSetUIModel generalizationSetUI = (IGeneralizationSetUIModel) diagramManager.createDiagramElement(diagram, generalizationSet);
		
		// Set notation for generalization set to common arrow head
		generalizationSetUI.setNotation(IClassDiagramUIModel.GENERALIZATION_SET_NOTATION_COMMON_GENERALIZATION_ARROWHEAD);
		
		// Show up the diagram
		diagramManager.openDiagram(diagram);
	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
