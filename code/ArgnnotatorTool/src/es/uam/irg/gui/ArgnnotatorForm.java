/**
 * Copyright 2022
 * Andrés Segura-Tinoco
 * Information Retrieval Group at Universidad Autonoma de Madrid
 *
 * This is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This software is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * the current software. If not, see <http://www.gnu.org/licenses/>.
 */
package es.uam.irg.gui;

import es.uam.irg.io.IOManager;
import es.uam.irg.utils.FileUtils;
import es.uam.irg.utils.FunctionUtils;
import es.uam.irg.utils.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Main GUI class.
 */
public class ArgnnotatorForm extends javax.swing.JFrame {

    // GUI constants
    private static final String HTML_CONTENT_TYPE = "text/html";
    private static final int PROPOSITION_MIN_SIZE = 3;
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    // GUI variables
    private String currDirectory;
    private String currEntity;
    private boolean isDirty;
    private final DataModel model;
    private final Queue<Integer> acuSelected;
    private String fileExtension;
    private String userName;

    /**
     * Creates new form ArgnnotatorForm
     */
    public ArgnnotatorForm() {
        initComponents();

        this.currDirectory = "";
        this.currEntity = "";
        this.isDirty = false;
        this.model = new DataModel();
        this.acuSelected = new LinkedList<>();
        this.fileExtension = "";

        this.setTablesLookAndFeel();
        this.setComboBoxes();
        this.setVisible(true);
        this.setAnnotatorName();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollPane1 = new javax.swing.JScrollPane();
        lstFiles = new javax.swing.JList<>();
        scrollPane2 = new javax.swing.JScrollPane();
        textEditor = new javax.swing.JEditorPane();
        scrollPane3 = new javax.swing.JScrollPane();
        tblArgComponents = new javax.swing.JTable();
        scrollPane4 = new javax.swing.JScrollPane();
        tblArgRelations = new javax.swing.JTable();
        lblFileList = new javax.swing.JLabel();
        lblAnnotation = new javax.swing.JLabel();
        cmbArgCompType = new javax.swing.JComboBox<>();
        btnAddArgument = new javax.swing.JButton();
        lblAddRelation = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        cmbIntent = new javax.swing.JComboBox<>();
        btnAddRelation = new javax.swing.JButton();
        lblDelete = new javax.swing.JLabel();
        btnDeleteComponent = new javax.swing.JButton();
        btnDeleteRelation = new javax.swing.JButton();
        lblNumberArguments = new javax.swing.JLabel();
        lblNumberRelations = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        mItemImportJsonl = new javax.swing.JMenuItem();
        mItemImportText = new javax.swing.JMenuItem();
        mItemExport = new javax.swing.JMenuItem();
        menuHorzSeparator = new javax.swing.JPopupMenu.Separator();
        mItemClose = new javax.swing.JMenuItem();
        menuAnnotation = new javax.swing.JMenu();
        mItemAnnoSave = new javax.swing.JMenuItem();
        menuHelp = new javax.swing.JMenu();
        mItemAbout = new javax.swing.JMenuItem();
        menuAnnotator = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Argument Annotator Tool v0.9");
        setMinimumSize(new java.awt.Dimension(1060, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        lstFiles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstFiles.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstFilesValueChanged(evt);
            }
        });
        scrollPane1.setViewportView(lstFiles);

        textEditor.setContentType(HTML_CONTENT_TYPE);
        scrollPane2.setViewportView(textEditor);

        tblArgComponents.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ACU Id", "Text", "Type"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArgComponents.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tblArgComponents.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblArgComponentsMouseClicked(evt);
            }
        });
        scrollPane3.setViewportView(tblArgComponents);

        tblArgRelations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ACU 1", "ACU 2", "Relation Type", "Intent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblArgRelations.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrollPane4.setViewportView(tblArgRelations);

        lblFileList.setText("File list:");

        lblAnnotation.setText("Annotation:");

        cmbArgCompType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Major claim", "Claim", "Premise" }));

        btnAddArgument.setText("Add");
        btnAddArgument.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddArgumentActionPerformed(evt);
            }
        });

        lblAddRelation.setText("Add relation:");

        cmbCategory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-" }));

        cmbIntent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "support", "attack" }));

        btnAddRelation.setText("Add");
        btnAddRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddRelationActionPerformed(evt);
            }
        });

        lblDelete.setText("Delete:");

        btnDeleteComponent.setText("ACU");
        btnDeleteComponent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteComponentActionPerformed(evt);
            }
        });

        btnDeleteRelation.setText("Relation");
        btnDeleteRelation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteRelationActionPerformed(evt);
            }
        });

        lblNumberArguments.setText("Number of argument component units (ACU): 0");

        lblNumberRelations.setText("Number of relations: 0");

        menuFile.setText("File");

        mItemImportJsonl.setText("Import from Jsonl");
        mItemImportJsonl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemImportJsonlActionPerformed(evt);
            }
        });
        menuFile.add(mItemImportJsonl);

        mItemImportText.setText("Import from Text");
        mItemImportText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemImportTextActionPerformed(evt);
            }
        });
        menuFile.add(mItemImportText);

        mItemExport.setText("Export files");
        mItemExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemExportActionPerformed(evt);
            }
        });
        menuFile.add(mItemExport);
        menuFile.add(menuHorzSeparator);

        mItemClose.setText("Close");
        mItemClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemCloseActionPerformed(evt);
            }
        });
        menuFile.add(mItemClose);

        menuBar.add(menuFile);

        menuAnnotation.setText("Annotation");

        mItemAnnoSave.setText("Save");
        mItemAnnoSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemAnnoSaveActionPerformed(evt);
            }
        });
        menuAnnotation.add(mItemAnnoSave);

        menuBar.add(menuAnnotation);

        menuHelp.setText("Help");

        mItemAbout.setText("About");
        mItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mItemAboutActionPerformed(evt);
            }
        });
        menuHelp.add(mItemAbout);

        menuBar.add(menuHelp);

        menuAnnotator.setText("| Annotator:");
        menuBar.add(menuAnnotator);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFileList))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAnnotation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbArgCompType, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddArgument)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(scrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAddRelation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbIntent, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddRelation))
                    .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumberArguments))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnDeleteComponent, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDeleteRelation))
                    .addComponent(lblNumberRelations))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAddRelation)
                    .addComponent(lblDelete)
                    .addComponent(lblFileList)
                    .addComponent(lblAnnotation)
                    .addComponent(cmbArgCompType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddArgument)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddRelation)
                    .addComponent(cmbIntent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteRelation)
                    .addComponent(btnDeleteComponent))
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1)
                    .addComponent(scrollPane2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                            .addComponent(scrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNumberRelations)
                            .addComponent(lblNumberArguments))))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemAboutActionPerformed
        // TODO add your handling code here:
        String aboutMsg = """
                          Argument Annotator Tool
                          
                          Version: 0.9.2
                          Date: 07/21/2022
                          Created by: Andr\u00e9s Segura-Tinoco & Iv\u00e1n Cantador 
                          License: Apache License 2.0
                          Web site: https://argrecsys.github.io/arg-nnotator-tool 
                          """;

        JOptionPane.showMessageDialog(this, aboutMsg, "About", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_mItemAboutActionPerformed

    private void mItemCloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemCloseActionPerformed
        // TODO add your handling code here:
        closeForm();
    }//GEN-LAST:event_mItemCloseActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        closeForm();
    }//GEN-LAST:event_formWindowClosing

    private void lstFilesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstFilesValueChanged
        // TODO add your handling code here:
        if (!lstFiles.isSelectionEmpty() && !evt.getValueIsAdjusting()) {
            if (isDirty) {
                String msg = "There are unsaved changes made to the file: " + currEntity + ".\nDo you want to save the changes?";
                int result = JOptionPane.showConfirmDialog(this, msg, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    saveResultFiles(currEntity);
                }
            }

            // Get current entity (file, proposal, etc.)
            acuSelected.clear();
            currEntity = lstFiles.getSelectedValue();
            System.out.println(">> Selectd file: " + currEntity);

            // Display result data
            Map<String, List<String[]>> data = getSavedData();
            displayResultData(data);

            // Display HTML report
            updateHtmlReport();
            isDirty = false;
        }
    }//GEN-LAST:event_lstFilesValueChanged

    private void btnAddArgumentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddArgumentActionPerformed
        // TODO add your handling code here:
        String propText = this.textEditor.getSelectedText();

        if (propText != null && propText.length() > PROPOSITION_MIN_SIZE) {

            // Add new argument component
            int propId = getNextPropositionId();
            String propType = this.cmbArgCompType.getSelectedItem().toString();
            DefaultTableModel tblModel = (DefaultTableModel) this.tblArgComponents.getModel();
            tblModel.addRow(new Object[]{propId, propText, propType});
            lblNumberArguments.setText("Number of argument component units: " + tblModel.getRowCount());

            // Display HTML report
            updateHtmlReport();
            isDirty = true;
        }
    }//GEN-LAST:event_btnAddArgumentActionPerformed

    private void btnAddRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRelationActionPerformed
        // TODO add your handling code here:
        if (acuSelected.size() == 2) {
            System.out.println(acuSelected);
            Integer[] selected = new Integer[2];
            selected = acuSelected.toArray(selected);

            TableModel acuModel = tblArgComponents.getModel();
            int acuId1 = Integer.parseInt(acuModel.getValueAt(selected[0], 0).toString());
            int acuId2 = Integer.parseInt(acuModel.getValueAt(selected[1], 0).toString());
            String category = cmbCategory.getSelectedItem().toString();
            String intent = cmbIntent.getSelectedItem().toString();

            DefaultTableModel relModel = (DefaultTableModel) tblArgRelations.getModel();
            relModel.addRow(new Object[]{acuId1, acuId2, category, intent});
            lblNumberRelations.setText("Number of relations: " + tblArgRelations.getRowCount());

            tblArgComponents.clearSelection();
            tblArgRelations.clearSelection();
            acuSelected.clear();
            isDirty = true;
        }
    }//GEN-LAST:event_btnAddRelationActionPerformed

    private void btnDeleteRelationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteRelationActionPerformed
        // TODO add your handling code here:
        if (tblArgRelations.getRowCount() > 0) {
            int row = tblArgRelations.getSelectedRow();

            if (row >= 0) {
                ((DefaultTableModel) tblArgRelations.getModel()).removeRow(row);
                lblNumberRelations.setText("Number of relations: " + tblArgRelations.getRowCount());
                isDirty = true;
            }
        }
    }//GEN-LAST:event_btnDeleteRelationActionPerformed

    private void tblArgComponentsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblArgComponentsMouseClicked
        // TODO add your handling code here:
        int row = tblArgComponents.rowAtPoint(evt.getPoint());

        if (row >= 0) {
            acuSelected.add(row);
            if (acuSelected.size() > 2) {
                acuSelected.poll();
            }
        }
    }//GEN-LAST:event_tblArgComponentsMouseClicked

    private void mItemImportJsonlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemImportJsonlActionPerformed
        // TODO add your handling code here:
        this.fileExtension = "jsonl";
        importFilesFromDirectory();
    }//GEN-LAST:event_mItemImportJsonlActionPerformed

    private void mItemImportTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemImportTextActionPerformed
        // TODO add your handling code here:
        this.fileExtension = "txt";
        importFilesFromDirectory();
    }//GEN-LAST:event_mItemImportTextActionPerformed

    private void mItemExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemExportActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mItemExportActionPerformed

    private void btnDeleteComponentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteComponentActionPerformed
        // TODO add your handling code here:
        if (tblArgComponents.getRowCount() > 0) {
            int row = tblArgComponents.getSelectedRow();

            if (row >= 0) {
                DefaultTableModel acuModel = (DefaultTableModel) tblArgComponents.getModel();
                int acuId = Integer.parseInt(acuModel.getValueAt(row, 0).toString());

                if (!isAcuInRelation(acuId)) {

                    // Remove argument component
                    acuModel.removeRow(row);
                    lblNumberArguments.setText("Number of arguments: " + acuModel.getRowCount());

                    // Display HTML report
                    updateHtmlReport();
                    isDirty = true;

                } else {
                    JOptionPane.showMessageDialog(this, "This ACU cannot be eliminated, because it is part of an argumentative relation", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnDeleteComponentActionPerformed

    private void mItemAnnoSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mItemAnnoSaveActionPerformed
        // TODO add your handling code here:
        if (!StringUtils.isEmpty(currEntity)) {
            saveResultFiles(currEntity);
            isDirty = false;
        }
    }//GEN-LAST:event_mItemAnnoSaveActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddArgument;
    private javax.swing.JButton btnAddRelation;
    private javax.swing.JButton btnDeleteComponent;
    private javax.swing.JButton btnDeleteRelation;
    private javax.swing.JComboBox<String> cmbArgCompType;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbIntent;
    private javax.swing.JLabel lblAddRelation;
    private javax.swing.JLabel lblAnnotation;
    private javax.swing.JLabel lblDelete;
    private javax.swing.JLabel lblFileList;
    private javax.swing.JLabel lblNumberArguments;
    private javax.swing.JLabel lblNumberRelations;
    private javax.swing.JList<String> lstFiles;
    private javax.swing.JMenuItem mItemAbout;
    private javax.swing.JMenuItem mItemAnnoSave;
    private javax.swing.JMenuItem mItemClose;
    private javax.swing.JMenuItem mItemExport;
    private javax.swing.JMenuItem mItemImportJsonl;
    private javax.swing.JMenuItem mItemImportText;
    private javax.swing.JMenu menuAnnotation;
    private javax.swing.JMenu menuAnnotator;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuHelp;
    private javax.swing.JPopupMenu.Separator menuHorzSeparator;
    private javax.swing.JScrollPane scrollPane1;
    private javax.swing.JScrollPane scrollPane2;
    private javax.swing.JScrollPane scrollPane3;
    private javax.swing.JScrollPane scrollPane4;
    private javax.swing.JTable tblArgComponents;
    private javax.swing.JTable tblArgRelations;
    private javax.swing.JEditorPane textEditor;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @param acuId
     * @return
     */
    private boolean isAcuInRelation(int acuId) {
        TableModel relModel = tblArgRelations.getModel();
        for (int i = 0; i < relModel.getRowCount(); i++) {
            if ((acuId == Integer.parseInt(relModel.getValueAt(i, 0).toString()))
                    || (acuId == Integer.parseInt(relModel.getValueAt(i, 1).toString()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Closes winform.
     */
    private void closeForm() {
        this.setVisible(false);
        this.dispose();
        System.exit(0);
    }

    /**
     *
     * @param data
     */
    private void displayResultData(Map<String, List<String[]>> data) {
        List<String[]> argCompUnits = data.get("acu");
        List<String[]> relationList = data.get("rel");

        // Update arguments table
        lblNumberArguments.setText("Number of argument component units: " + (argCompUnits.size() - 1));
        DefaultTableModel acuModel = (DefaultTableModel) tblArgComponents.getModel();
        acuModel.setRowCount(0);
        for (int i = 1; i < argCompUnits.size(); i++) {
            String[] rowData = argCompUnits.get(i);
            try {
                acuModel.addRow(FunctionUtils.getSubArray(rowData, 0, 3));
            } catch (Exception ex) {
                Logger.getLogger(ArgnnotatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // Update relations table
        lblNumberRelations.setText("Number of relations: " + (relationList.size() - 1));
        DefaultTableModel relModel = (DefaultTableModel) tblArgRelations.getModel();
        relModel.setRowCount(0);
        for (int i = 1; i < relationList.size(); i++) {
            String[] rowData = relationList.get(i);
            try {
                relModel.addRow(FunctionUtils.getSubArray(rowData, 0, 4));
            } catch (Exception ex) {
                Logger.getLogger(ArgnnotatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    /**
     *
     * @return
     */
    private int getNextPropositionId() {
        int propNextId = 1;
        int nRows = this.tblArgComponents.getRowCount();
        if (nRows > 0) {
            propNextId = Integer.parseInt(this.tblArgComponents.getModel().getValueAt(nRows - 1, 0).toString()) + 1;
        }
        return propNextId;
    }

    /**
     *
     * @return
     */
    private Map<String, List<String[]>> getSavedData() {
        String directory = currDirectory + "\\..\\results\\";
        String currFile = currEntity;
        return IOManager.readAnnotationData(directory, currFile);
    }

    /**
     *
     * @return
     */
    private String getSelectedReport() {
        String filepath = currDirectory + "\\" + currEntity + "." + fileExtension;
        String report = this.model.getFileReport(filepath, fileExtension);
        return report;
    }

    /**
     *
     * @param fileExt
     */
    private void importFilesFromDirectory() {
        JFileChooser jfc = new JFileChooser();
        jfc.setCurrentDirectory(new java.io.File("."));
        jfc.setDialogTitle("Select folder");
        jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);

        if (jfc.showOpenDialog(ArgnnotatorForm.this) == JFileChooser.APPROVE_OPTION) {
            currDirectory = jfc.getSelectedFile().toString();
            List<String> files = model.readFilenamesInFolder(currDirectory, fileExtension);
            System.out.println(String.format(">> Directory: '%s' and number of uploaded files: %d", currDirectory, files.size()));

            lstFiles.removeAll();
            if (files.size() > 0) {
                DefaultListModel listModel = new DefaultListModel();
                listModel.addAll(files);
                lstFiles.setModel(listModel);
            }
        }
    }

    /**
     *
     * @param fileName
     */
    private void saveResultFiles(String fileName) {

        if (!lstFiles.isSelectionEmpty()) {
            List<String> header;
            List<String[]> argCompUnits = new ArrayList<>();
            List<String[]> relationList = new ArrayList<>();

            // Loop through the rows
            TableModel acuModel = tblArgComponents.getModel();
            for (int i = 0; i < acuModel.getRowCount(); i++) {
                String acuId = acuModel.getValueAt(i, 0).toString();
                String acuText = acuModel.getValueAt(i, 1).toString();
                String acuType = acuModel.getValueAt(i, 2).toString();
                String dateStamp = dateFormat.format(new Date());
                argCompUnits.add(new String[]{acuId, acuText, acuType, userName, dateStamp});
            }

            // Loop through the rows
            TableModel relModel = tblArgRelations.getModel();
            for (int i = 0; i < relModel.getRowCount(); i++) {
                String acuId1 = relModel.getValueAt(i, 0).toString();
                String acuId2 = relModel.getValueAt(i, 1).toString();
                String relType = relModel.getValueAt(i, 2).toString();
                String relIntent = relModel.getValueAt(i, 3).toString();
                String dateStamp = dateFormat.format(new Date());
                relationList.add(new String[]{acuId1, acuId2, relType, relIntent, userName, dateStamp});
            }

            header = new ArrayList<>(Arrays.asList("acu_id", "acu_text", "acu_type", "annotator", "timespam"));
            saveResults(fileName, "acu", header, argCompUnits);

            header = new ArrayList<>(Arrays.asList("acu_id1", "acu_id2", "rel_type", "rel_intent", "annotator", "timespam"));
            saveResults(fileName, "rel", header, relationList);
        }

    }

    /**
     *
     * @param fileName
     * @param fileType
     * @param data
     * @return
     */
    private boolean saveResults(String fileName, String fileType, List<String> header, List<String[]> rows) {
        String filepath = currDirectory + "\\..\\results\\" + fileName + "_" + fileType + ".csv";
        List<String[]> data = new ArrayList<>();
        data.add(header.toArray(new String[header.size()]));
        data.addAll(rows);
        return FileUtils.saveCsvFile(filepath, data);
    }

    /**
     *
     */
    private void setComboBoxes() {
        List<String> subCategories = model.getSubCategories(true);
        cmbCategory.setModel(new DefaultComboBoxModel<>(subCategories.toArray(new String[0])));
    }

    /**
     *
     */
    private void setTablesLookAndFeel() {

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        // Table 1: Argument Component Units
        tblArgComponents.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblArgComponents.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblArgComponents.getColumnModel().getColumn(1).setPreferredWidth(210);
        tblArgComponents.getColumnModel().getColumn(2).setPreferredWidth(90);
        tblArgComponents.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Table 2: Argument Component Units
        tblArgRelations.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblArgRelations.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblArgRelations.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(2).setPreferredWidth(110);
        tblArgRelations.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tblArgRelations.getColumnModel().getColumn(3).setPreferredWidth(90);
        tblArgRelations.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
    }

    /**
     *
     */
    private void setAnnotatorName() {
        String[] annotators = model.getAnnotatorList();
        String result = (String) JOptionPane.showInputDialog(this, "Please, enter annotator name:", "Annotator Name", JOptionPane.PLAIN_MESSAGE, null, annotators, "");

        if (result != null && result.length() > 0) {
            userName = result.replace("\n", "");
        } else {
            userName = "admin";
        }

        this.menuAnnotator.setText("| Annotator: " + userName);
    }

    /**
     * Display and update html report.
     */
    private void updateHtmlReport() {
        String report = getSelectedReport();
        int caretPosition = this.textEditor.getCaretPosition();

        // Update report
        TableModel acuModel = tblArgComponents.getModel();
        String hlText;
        String acuText;
        String acuType;

        for (int i = 0; i < acuModel.getRowCount(); i++) {
            acuText = acuModel.getValueAt(i, 1).toString();
            acuType = acuModel.getValueAt(i, 2).toString();

            switch (acuType.toLowerCase()) {
                case "major claim":
                    hlText = model.getFormatter().highlightMajorClaim(acuText);
                    break;
                case "claim":
                    hlText = model.getFormatter().highlightClaim(acuText);
                    break;
                default:
                    hlText = model.getFormatter().highlightPremise(acuText);
                    break;
            }
            report = report.replace(acuText, hlText);
        }

        // Display report
        this.textEditor.setText(report);
        this.textEditor.setCaretPosition(caretPosition);
    }

}
