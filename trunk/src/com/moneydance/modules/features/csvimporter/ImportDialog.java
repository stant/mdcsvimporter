/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the Lesser GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.moneydance.modules.features.csvimporter;

import com.moneydance.apps.md.model.Account;
import com.moneydance.apps.md.model.CurrencyType;
import com.moneydance.apps.md.model.OnlineTxn;
import com.moneydance.apps.md.model.OnlineTxnList;
import com.moneydance.apps.md.model.RootAccount;
import com.moneydance.util.CustomDateFormat;
import com.moneydance.util.StringUtils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author miki
 */
public class ImportDialog
   extends javax.swing.JDialog
{
   private File selectedFile;
   private Main main;
   private String dateFormat;

   public ImportDialog( java.awt.Frame parent, Main main )
   {
      super( parent, true );
      initComponents();

      textFilename.getDocument().addDocumentListener( new DocumentListener()
      {
         public void insertUpdate( DocumentEvent e )
         {
            textFilenameChanged();
         }

         public void removeUpdate( DocumentEvent e )
         {
            textFilenameChanged();
         }

         public void changedUpdate( DocumentEvent e )
         {
            textFilenameChanged();
         }
      } );

      this.main = main;
      RootAccount rootAccount = main.getRootAccount();
      comboAccount.removeAllItems();
      for ( int i = 0; i < rootAccount.getSubAccountCount(); ++i ) {
         Account account = rootAccount.getSubAccount( i );
         if ( account.isOnlineBankingCandidate() ) {
            comboAccount.addItem( account );
         }
      }
      if ( comboAccount.getItemCount() > 0 ) {
         comboAccount.setSelectedIndex( 0 );
      }
      
      updateActions();
   }

   private String guessDateFormat( String s )
   {
      throw new UnsupportedOperationException( "Not yet implemented" );
   }

   /** This method is called from within the constructor to
    * initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is
    * always regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      jLabel3 = new javax.swing.JLabel();
      jLabel1 = new javax.swing.JLabel();
      textFilename = new javax.swing.JTextField();
      btnBrowse = new javax.swing.JButton();
      checkDeleteFile = new javax.swing.JCheckBox();
      btnClose = new javax.swing.JButton();
      btnProcess = new javax.swing.JButton();
      jLabel2 = new javax.swing.JLabel();
      comboAccount = new javax.swing.JComboBox();
      lblMessage = new javax.swing.JLabel();

      jLabel3.setText("jLabel3");

      setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
      setTitle("Import CSV File");
      setName("importDialog"); // NOI18N
      setResizable(false);

      jLabel1.setText("Select File:");

      btnBrowse.setText("...");
      btnBrowse.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnBrowseActionPerformed(evt);
         }
      });

      checkDeleteFile.setText("Securely erase file after processing.");
      checkDeleteFile.setToolTipText("If checked, the specified file will be securely erased (first overwritten, then deleted) after successful processing.");

      btnClose.setText("Close");
      btnClose.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnCloseActionPerformed(evt);
         }
      });

      btnProcess.setText("Process");
      btnProcess.setEnabled(false);
      btnProcess.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            btnProcessActionPerformed(evt);
         }
      });

      jLabel2.setText("Select Account:");

      comboAccount.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

      lblMessage.setForeground(new java.awt.Color(255, 0, 51));
      lblMessage.setText("jLabel4");
      lblMessage.setOpaque(true);

      org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
      getContentPane().setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
               .add(layout.createSequentialGroup()
                  .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(jLabel2)
                     .add(jLabel1))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                     .add(comboAccount, 0, 255, Short.MAX_VALUE)
                     .add(textFilename, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                  .add(btnBrowse, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 40, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
               .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                  .add(btnProcess)
                  .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                  .add(btnClose))
               .add(checkDeleteFile)
               .add(lblMessage, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE))
            .addContainerGap())
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
         .add(layout.createSequentialGroup()
            .addContainerGap()
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jLabel1)
               .add(textFilename, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
               .add(btnBrowse))
            .add(18, 18, 18)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(jLabel2)
               .add(comboAccount, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(18, 18, 18)
            .add(checkDeleteFile, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(18, 18, 18)
            .add(lblMessage, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 16, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
               .add(btnClose)
               .add(btnProcess))
            .addContainerGap())
      );

      pack();
   }// </editor-fold>//GEN-END:initComponents

    private void btnBrowseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnBrowseActionPerformed
    {//GEN-HEADEREND:event_btnBrowseActionPerformed
       JFileChooser dialog = new JFileChooser();
       dialog.setFileHidingEnabled( true );
       dialog.setDialogTitle( "Select text file" );
       dialog.addChoosableFileFilter( new FileFilter()
       {
          @Override
          public boolean accept( File f )
          {
             return f.isDirectory() || f.getName().endsWith( ".csv" );
          }

          @Override
          public String getDescription()
          {
             return "Formatted Text File";
          }
       } );
       if ( dialog.showDialog( this, "Select" ) == JFileChooser.APPROVE_OPTION ) {
          selectedFile = dialog.getSelectedFile();
          textFilename.setText( selectedFile.getPath() );
          updateActions();
       }
}//GEN-LAST:event_btnBrowseActionPerformed

    private void btnCloseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnCloseActionPerformed
    {//GEN-HEADEREND:event_btnCloseActionPerformed
       this.setVisible( false );
    }//GEN-LAST:event_btnCloseActionPerformed

    private void btnProcessActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnProcessActionPerformed
    {//GEN-HEADEREND:event_btnProcessActionPerformed
      FileReader file;
      try {
         file = new FileReader( selectedFile );
      }
      catch ( FileNotFoundException x ) {
         JOptionPane.showMessageDialog( rootPane, "Could not open file.",
         "Error Reading File", JOptionPane.ERROR_MESSAGE );
         return;
      }
      try {
         CSVReader reader = new CSVReader( file );
         processFile( reader, (Account) comboAccount.getSelectedItem() );
         reader.close();
      }
      catch ( IOException x ) {
         try {
            file.close();
         }
         catch ( Exception ex ) {
         }
         JOptionPane.showMessageDialog( rootPane, "Error reading from the file.",
         "Error Reading File", JOptionPane.ERROR_MESSAGE );
         return;
      }

      if ( checkDeleteFile.isSelected() ) {
         try {
            SecureFileDeleter.delete( selectedFile );
         }
         catch ( IOException x ) {
            JOptionPane.showMessageDialog( rootPane, x.getMessage(), "Cannot Delete File",
               JOptionPane.ERROR_MESSAGE );
            return;
         }
      }

      setVisible( false );
    }//GEN-LAST:event_btnProcessActionPerformed

   protected void processFile( CSVReader reader, Account account )
      throws IOException
   {
      if ( !reader.nextLine() ) {
         JOptionPane.showMessageDialog( rootPane, "Specified file is empty.",
            "Finished", JOptionPane.INFORMATION_MESSAGE );
         return;
      }
      if ( !reader.nextField().equals( "Transaction Date" )
         || !reader.nextField().equals( "Posting Date" )
         || !reader.nextField().equals( "Description" )
         || !reader.nextField().equals( "Amount" ) )
      {
         JOptionPane.showMessageDialog( rootPane, "Format of this file is not supported.",
            "Invalid File Format", JOptionPane.ERROR_MESSAGE );
         return;
      }

      OnlineTxnList transactionList = account.getDownloadedTxns();
      CurrencyType currency = account.getCurrencyType();
      CustomDateFormat dateFormat = new CustomDateFormat( this.dateFormat );

      while ( reader.nextLine() ) {
         String transactionDateString = reader.nextField();
         if ( transactionDateString == null ) { // empty line
            continue;
         }
         String postingDateString = reader.nextField();
         String description = reader.nextField();
         String amountString = reader.nextField();
         if ( amountString == null ) { // invalid line
            continue;
         }

         long amount;
         try {
            double amountDouble = StringUtils.parseDoubleWithException( amountString, '.' );
            amount = currency.getLongValue( amountDouble );
         }
         catch ( Exception x ) { // invalid value
            continue;
         }

         int transactionDate = dateFormat.parseInt( transactionDateString );
         int postingDate = dateFormat.parseInt( postingDateString );

         OnlineTxn transaction = transactionList.newTxn();
         transaction.setAmount( amount );
         transaction.setTotalAmount( amount );
         transaction.setMemo( description );
         transaction.setFITxnId( postingDate + ":" + amountString + ":" + description );
         transaction.setDatePostedInt( postingDate );
         transaction.setDateInitiatedInt( transactionDate );
         transaction.setDateAvailableInt( postingDate );
         // transaction.setTxnType( OnlineTxn.INVEST_TXN_BANK );
         transaction.setProtocolType( OnlineTxn.PROTO_TYPE_OFX );
         // transaction.setLocalStatus( OnlineTxn.STATUS_NEW );

         transactionList.addNewTxn( transaction );
      }
   }
   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnBrowse;
   private javax.swing.JButton btnClose;
   private javax.swing.JButton btnProcess;
   private javax.swing.JCheckBox checkDeleteFile;
   private javax.swing.JComboBox comboAccount;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JLabel lblMessage;
   private javax.swing.JTextField textFilename;
   // End of variables declaration//GEN-END:variables

   protected void updateActions()
   {
      boolean enabled = true;
      String message = null;

      if ( selectedFile == null || !selectedFile.exists() || !selectedFile.isFile() ) {
         enabled = false;
         message = "Please select a valid file.";
      }
      else {
         try {
            FileReader file = new FileReader( selectedFile );
            try {
               CSVReader reader = new CSVReader( file );
               DateGuesser guesser = new DateGuesser();

               while ( reader.nextLine() ) {
                  for ( String s = reader.nextField(); s != null; s = reader.nextField() ) {
                     guesser.checkDateString( s );
                  }
               }

               if ( guesser.getBestFormatProbability() < 0.9999 ) {
                  enabled = false;
                  message = "Could not detect proper date format for this file.";
               }
               else {
                  dateFormat = guesser.getBestFormat();
               }
            }
            finally {
               file.close();
            }
         }
         catch ( Exception x ) {
            enabled = false;
            message = "This file does not seem to be in correct format.";
         }
      }

      Account account = (Account) comboAccount.getSelectedItem();
      if ( account == null || !account.isOnlineBankingCandidate() ) {
         enabled = false;
      }

      btnProcess.setEnabled( enabled );
      if ( message != null ) {
         lblMessage.setText( message );
         lblMessage.setVisible( true );
      }
      else {
         lblMessage.setVisible( false );
      }
   }

   private void textFilenameChanged()
   {
      File newFile = new File( textFilename.getText() );

      if ( !newFile.equals( selectedFile ) ) {
         selectedFile = newFile;
         updateActions();
      }
   }
}
