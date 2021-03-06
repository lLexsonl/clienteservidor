package agenciaviajes.presentacion;

import agenciaviajes.negocio.GestorClientes;
import agenciaviajes.utils.Utilidades;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import mvcf.AModel;
import mvcf.AView;

// Imports de JFreeChart para el diagrama de Barras
import org.jfree.chart.ChartFactory;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Gráfico que muestra la cantidad de hombres y mujeres que hay en forma de diagrama de barras.
 * @author Yerson
 */
public class GUIClientesGrafico extends javax.swing.JFrame implements AView{

    /**
     * Creates new form GUIClientesGrafico
     */
    public GUIClientesGrafico() {
        initComponents();
        this.setTitle("Gráfico");
        setSize(new java.awt.Dimension( 400 , 350));
        setLocation(930, 20);
        createJFreeChart(0,0);
    }
    
    private void createJFreeChart(int hombres, int mujeres){
        JFreeChart barChart = ChartFactory.createBarChart(
                "CANTIDAD H0MBRES Y MUJERES",
                "",
                "Cantidad",
                createDataset(hombres, mujeres),
                PlotOrientation.VERTICAL,
                true, true, false);
        
        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 380 , 280));
        
        pnPanel1.removeAll();
        pnPanel1.add(chartPanel);
        pnPanel1.updateUI();
        //setContentPane( chartPanel );
    }

    private CategoryDataset createDataset(int hombres, int mujeres) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(hombres, "Masculino", "Sexo");
        dataset.addValue(mujeres, "Femenino", "Sexo");
        
        return dataset;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    @Override
    public void actualizar(AModel amodel) {
        System.out.println("Grafico");
        try{
            GestorClientes gestor = (GestorClientes) amodel;
            createJFreeChart(gestor.getTotalHombres(), gestor.getTotalMujeres());
        }catch(ClassNotFoundException | SQLException ex) {
            Logger.getLogger(GUIClientesVer.class.getName()).log(Level.SEVERE, null, ex);
            Utilidades.mensajeError("Error al consultar clientes", "Atención");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnPanel1;
    // End of variables declaration//GEN-END:variables

}
