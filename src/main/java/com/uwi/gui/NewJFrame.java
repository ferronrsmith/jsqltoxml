/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.uwi.gui;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import net.sf.jsqlparser.JSQLParserException;

import com.uwi.enums.ResultType;

// TODO: Auto-generated Javadoc
/**
 * The Class NewJFrame.
 *
 * @author ferron
 */
public class NewJFrame extends javax.swing.JFrame {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				new NewJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	/** The btn submit. */
	private javax.swing.JButton btnSubmit;

	/** The j scroll pane1. */
	private javax.swing.JScrollPane jScrollPane1;

	/** The lbl file name. */
	private javax.swing.JLabel lblFileName;

	/** The lbl sql. */
	private javax.swing.JLabel lblSql;

	/** The txt file name. */
	private javax.swing.JTextField txtFileName;

	/** The txt output. */
	private javax.swing.JTextArea txtOutput;

	/** The txt sql. */
	private javax.swing.JTextField txtSql;

	// End of variables declaration//GEN-END:variables

	/**
	 * Creates new form NewJFrame.
	 */
	public NewJFrame() {
		initComponents();
	}

	/**
	 * Btn submit action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void btnSubmitActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSubmitActionPerformed
		try {
			// TODO add your handling code here:
			// JOptionPane.showMessageDialog(this, "tesung");
			List<String> records = com.uwi.config.JSql.parseXML(
					txtFileName.getText(), txtSql.getText(), ResultType.XML,
					null);
			if (records != null && records.size() > 0) {
				String output = "";
				for (String string : records) {
					output += string;
				}
				txtOutput.setText(output);
			} else {
				txtOutput.setText("[ cannot find elements at current level ]");
			}

		} catch (JSQLParserException ex) {
			String err = ex.getMessage();
			System.err.println(">>>" + err);
			JOptionPane.showMessageDialog(this, ex.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			Logger.getLogger(NewJFrame.class.getName()).log(Level.SEVERE, null,
					ex);
		}
	}// GEN-LAST:event_btnSubmitActionPerformed

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		txtOutput = new javax.swing.JTextArea();
		txtSql = new javax.swing.JTextField();
		lblSql = new javax.swing.JLabel();
		btnSubmit = new javax.swing.JButton();
		lblFileName = new javax.swing.JLabel();
		txtFileName = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		txtOutput.setColumns(20);
		txtOutput.setRows(5);
		jScrollPane1.setViewportView(txtOutput);

		txtSql.setToolTipText("Enter a valid SQL String... <select name, drink from countries>");

		lblSql.setText("Enter SQL Query");

		btnSubmit.setText("Submit");
		btnSubmit.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSubmitActionPerformed(evt);
			}
		});

		lblFileName.setText("Enter XML File Name");
		lblFileName
				.setToolTipText("Please notte that the file must be in the same folder as the application");

		txtFileName
				.setToolTipText("Enter a valid SQL String... <select name, drink from countries>");
		txtFileName.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtFileNameActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												false)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblFileName)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						btnSubmit,
																						javax.swing.GroupLayout.Alignment.TRAILING)
																				.addComponent(
																						txtFileName,
																						javax.swing.GroupLayout.Alignment.TRAILING,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						350,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addComponent(
														jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														494,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		lblSql)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addComponent(
																		txtSql,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		350,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(44, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(20, 20, 20)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														txtSql,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														31,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(lblSql))
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblFileName)
												.addComponent(
														txtFileName,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														31,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(btnSubmit)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										37, Short.MAX_VALUE)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										203,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	/**
	 * Txt file name action performed.
	 *
	 * @param evt
	 *            the evt
	 */
	private void txtFileNameActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtFileNameActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtFileNameActionPerformed
}
