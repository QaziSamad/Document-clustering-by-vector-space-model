
import java.io.File;
import java.util.Collections;
import java.util.Comparator;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Evolutionary_Clustering.java
 *
 * Created on Nov 23, 2011, 12:18:59 PM
 */

/**
 *
 * @author santu
 */





public class Document_Clustering extends javax.swing.JFrame {


    String inputFolder;
    String outputFolder;
    File_Reader myFile_Reader;
    
    


    
    /** Creates new form Evolutionary_Clustering */
    public Document_Clustering() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        clearStatusjButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        inputFolderjTextField = new javax.swing.JTextField();
        ClusterjButton = new javax.swing.JButton();
        thetajTextField = new javax.swing.JTextField();
        outputFolderjTextField = new javax.swing.JTextField();
        CurrentStatusjLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        ReadButton = new javax.swing.JButton();
        removeOutputjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Document Clustering");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));

        clearStatusjButton.setText("Clear status");
        clearStatusjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearStatusjButtonActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel4.setText("DOCUMENT CLUSTERING");

        ClusterjButton.setText("Cluster");
        ClusterjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClusterjButtonActionPerformed(evt);
            }
        });

        jLabel2.setText("Output Folder:");

        jLabel3.setText("Theta:");

        jLabel1.setText("Input Folder:");

        ReadButton.setText("Read Documents");
        ReadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReadButtonActionPerformed(evt);
            }
        });

        removeOutputjButton.setText("Remove Output");
        removeOutputjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeOutputjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(CurrentStatusjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ReadButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(outputFolderjTextField)
                            .addComponent(inputFolderjTextField)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(clearStatusjButton)
                                    .addComponent(ClusterjButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeOutputjButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(thetajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ClusterjButton, ReadButton, clearStatusjButton, removeOutputjButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inputFolderjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputFolderjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(thetajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 123, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ReadButton)
                    .addComponent(removeOutputjButton)
                    .addComponent(ClusterjButton))
                .addGap(18, 18, 18)
                .addComponent(clearStatusjButton)
                .addGap(18, 18, 18)
                .addComponent(CurrentStatusjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ReadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReadButtonActionPerformed
        // TODO add your handling code here:
        this.inputFolder=this.inputFolderjTextField.getText();
        this.outputFolder=this.outputFolderjTextField.getText();
        this.myFile_Reader=new File_Reader(this.inputFolder);
        this.myFile_Reader.ReadFiles();
        //int populaitonSize=(int)Math.pow(myFile_Reader.wordCountArrayList.size(),2);
        this.CurrentStatusjLabel.setText("Input files read and processed");
    }//GEN-LAST:event_ReadButtonActionPerformed

    private void removeOutputjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeOutputjButtonActionPerformed
        // TODO add your handling code here:
        File f1 = new File(this.outputFolderjTextField.getText());
        if(f1.exists()==true)
        {
            deleteDir(f1);
        }
        this.CurrentStatusjLabel.setText("Output folder deleted");
    }//GEN-LAST:event_removeOutputjButtonActionPerformed

    private void ClusterjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClusterjButtonActionPerformed
        // TODO add your handling code here:
        double theta=Double.parseDouble(this.thetajTextField.getText());
        int populaitonSize=this.myFile_Reader.wordCountArrayList.size()*10;
        Population myPopulation=new Population(populaitonSize,2,this.myFile_Reader.wordCountArrayList.size()/4);
        //myPopulation.printPopulation();

        KMAsimulator myKMAsimulator=new KMAsimulator(this.myFile_Reader,this.myFile_Reader.wordCountArrayList.size()/4,theta);

        for(int i=0; i<myPopulation.chromosomes.size(); i++)
        {
            myPopulation.chromosomes.get(i).PBM=myKMAsimulator.performCosineKMA(myPopulation.chromosomes.get(i));
        }

        Collections.sort(myPopulation.chromosomes, new Comparator(){

            public int compare(Object o1, Object o2) {
               Chromosome c1 = (Chromosome) o1;
               Chromosome c2 = (Chromosome) o2;
               if(c1.PBM<c2.PBM)
               {
                   return 1;
               }
               else if(c1.PBM>c2.PBM)
               {
                 return -1;
               }
               return 0;
            }

        });


        for(int i=0; i<myPopulation.chromosomes.size(); i++)
        {
            System.out.println("Chromosome :"+i+"\tNo of Clusters: "+myPopulation.chromosomes.get(i).NoOfOnes()+"\tPBM="+myPopulation.chromosomes.get(i).PBM);
        }
        Genetic_Algorithm GA=new Genetic_Algorithm(myPopulation,myKMAsimulator,2);
        Chromosome result=GA.ExexuteGA();
        int[] clusters=myKMAsimulator.performCosineKMAwithResults(result);
        /*
        for(int i=0; i<clusters.length; i++)
        {
            System.out.print(clusters[i]+",");
        }
        System.out.println();*/
        OutputGenerator myOutputGenerator=new OutputGenerator(clusters,this.inputFolder,this.outputFolder,result.NoOfOnes());
        myOutputGenerator.GenerateOutput();
        this.CurrentStatusjLabel.setText("Clustering performed and output generated");
    }//GEN-LAST:event_ClusterjButtonActionPerformed

    private void clearStatusjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearStatusjButtonActionPerformed
        // TODO add your handling code here:
        this.CurrentStatusjLabel.setText("");
    }//GEN-LAST:event_clearStatusjButtonActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Document_Clustering().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ClusterjButton;
    private javax.swing.JLabel CurrentStatusjLabel;
    private javax.swing.JButton ReadButton;
    private javax.swing.JButton clearStatusjButton;
    private javax.swing.JTextField inputFolderjTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField outputFolderjTextField;
    private javax.swing.JButton removeOutputjButton;
    private javax.swing.JTextField thetajTextField;
    // End of variables declaration//GEN-END:variables










    public static boolean deleteDir(File dir)
    {
        if (dir.isDirectory())
        {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
}