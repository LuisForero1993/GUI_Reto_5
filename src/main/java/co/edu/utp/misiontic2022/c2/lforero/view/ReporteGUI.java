package co.edu.utp.misiontic2022.c2.lforero.view;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import co.edu.utp.misiontic2022.c2.lforero.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoVo;

public class ReporteGUI extends JFrame{
    private ReportesController controller;
    private JTable tabla;
    private JTable tablaBanco;
    private JTable tablaLimiteInferior;
    private JTable tablaTop;
    
    public ReporteGUI() {
        controller = new ReportesController();
        initUI();
        setLocationRelativeTo(null);
    }

    private void initUI() {
        setTitle("Reto 5");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);

        var tbd = new JTabbedPane();
        getContentPane().add(tbd, BorderLayout.CENTER);

        var panel = new JPanel();
        panel.setLayout(new BorderLayout());
        tbd.addTab("Consulta Proyectos por Clasificaciones", panel);

        var panel2 = new JPanel();
        panel2.setLayout(new BorderLayout());
        tbd.addTab("Proyectos financiados por banco", panel2);

        var panel3 = new JPanel();
        panel3.setLayout(new BorderLayout());
        tbd.addTab("Total adeudado por proyecto según límite inferior", panel3);

        var panel4 = new JPanel();
        panel4.setLayout(new BorderLayout());
        tbd.addTab("Top de líderes que mas gastan", panel4);




        var panelEntrada = new JPanel();
        panelEntrada.add(new JLabel("Clasificacion 1"));
        var txtClasificacion1 = new JTextField(15);
        panelEntrada.add(txtClasificacion1);
        panelEntrada.add(new JLabel("Clasificacion 2"));
        var txtClasificacion2 = new JTextField(15);
        panelEntrada.add(txtClasificacion2);
        var btnConsulta = new JButton("Consultar");
        btnConsulta.addActionListener(e -> consultarProyectosPorClasificacion(txtClasificacion1.getText().trim(),
                txtClasificacion2.getText().trim()));
        panelEntrada.add(btnConsulta);
        panel.add(panelEntrada, BorderLayout.PAGE_START);

        var panelEntrada2 = new JPanel();
        panelEntrada2.add(new JLabel("Nombre de Banco"));
        var txtNombreBanco = new JTextField(15);
        panelEntrada2.add(txtNombreBanco);
        var btnConsultaBanco = new JButton("Consultar");
        btnConsultaBanco.addActionListener(e -> consultarProyectoPorBanco(txtNombreBanco.getText().trim()));
        panelEntrada2.add(btnConsultaBanco);
        panel2.add(panelEntrada2, BorderLayout.PAGE_START);
        
        var panelEntrada3 = new JPanel();
        panelEntrada3.add(new JLabel("Valor límite inferior"));
        var txtLimiteInferior = new JTextField(15);
        panelEntrada3.add(txtLimiteInferior);
        var btnLimiteInferior = new JButton("Consultar");
        btnLimiteInferior.addActionListener(e -> consultarDeudasPorProyecto(Double.parseDouble(txtLimiteInferior.getText().trim())));
        panelEntrada3.add(btnLimiteInferior);
        panel3.add(panelEntrada3, BorderLayout.PAGE_START);
        
        var panelEntrada4 = new JPanel();
        panelEntrada4.add(new JLabel("Indice del TOP de líderes"));
        var txtValorTop = new JTextField(15);
        panelEntrada4.add(txtValorTop);
        var btnValorTop = new JButton("Consultar");
        btnValorTop.addActionListener(e -> consultarComprasDeLider(Integer.valueOf(txtValorTop.getText().trim())));
        panelEntrada4.add(btnValorTop);
        panel4.add(panelEntrada4, BorderLayout.PAGE_START);
        
        tabla = new JTable();
        panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        tablaBanco = new JTable();
        panel2.add(new JScrollPane(tablaBanco), BorderLayout.CENTER);
        tablaLimiteInferior = new JTable();
        panel3.add(new JScrollPane(tablaLimiteInferior), BorderLayout.CENTER);
        tablaTop = new JTable();
        panel4.add(new JScrollPane(tablaTop), BorderLayout.CENTER);
    }

    private void consultarProyectosPorClasificacion(String opcion1, String opcion2) {
        try {
            var lista = controller.listarProyectosExcluyendoClasificaciones(opcion1, opcion2);
            var tableModel = new ProyectosTableModel();
            tableModel.setData(lista);

            tabla.setModel(tableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarProyectoPorBanco(String banco) {
        try {
            var lista = controller.listarProyectosFinanciadosPorBanco(banco);
            var tableModel = new ProyectosTableBanco();
            tableModel.setData(lista);

            tablaBanco.setModel(tableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    private void consultarDeudasPorProyecto(Double limiteInferior) {
        try {
            var lista = controller.listarDeudasPorProyecto(limiteInferior);
            var tableDeudas = new ProyectosTableDeudas();
            tableDeudas.setData(lista);

            tablaLimiteInferior.setModel(tableDeudas);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }
   
    private void consultarComprasDeLider(Integer top) {
        try {
            var lista = controller.listarComprasDeLider(top);
            var tableModel = new ProyectosTableTop();
            tableModel.setData(lista);

            tablaTop.setModel(tableModel);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), getTitle(), JOptionPane.ERROR_MESSAGE);
        }
    }

    


    private class ProyectosTableModel extends AbstractTableModel {
        private List<ProyectoVo> data;

        public void setData(List<ProyectoVo> data) {
            this.data = data;
            fireTableDataChanged();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                case 1:
                case 3:
                case 4:
                    return String.class;
                case 2:
                    return Integer.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "Ciudad";
                case 1:
                    return "Clasificación";
                case 2:
                    return "Total";
                case 3:
                    return "Viejo";
                case 4:
                    return "Reciente";
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return project.getCiudad();
                case 1:
                    return project.getClasificacion();
                case 2:
                    return project.getTotal();
                case 3:
                    return project.getViejo();
                case 4:
                    return project.getReciente();
            }
            return null;
        }

    }

    private class ProyectosTableBanco extends AbstractTableModel{
        private List<ProyectoBancoVo> proyectos;

        public ProyectosTableBanco(){
            proyectos = new ArrayList<>();
            fireTableDataChanged();
        }

        public void setData(List<ProyectoBancoVo> data) {
            proyectos = data;
            fireTableDataChanged();
        }

         @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                case 4:
                    return Integer.class;
                default:
                    return String.class;
            }
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID";
                case 1:
                    return "CONSTRUCTORA";
                case 2:
                    return "CIUDAD";
                case 3:
                    return "CLASIFICACION";
                case 4:
                    return "ESTRATO";
                case 5:
                    return "LIDER";
            }

            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return proyectos.size();
        }

        @Override
        public int getColumnCount() {
            return 6;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var proyecto = proyectos.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return proyecto.getId();
                case 1:
                    return proyecto.getConstructora();
                case 2:
                    return proyecto.getCiudad();
                case 3:
                    return proyecto.getClasificacion();
                case 4:
                    return proyecto.getEstrato();
                case 5:
                    return proyecto.getLider();
            }
            return null;
        }
    }

    private class ProyectosTableDeudas extends AbstractTableModel{

        private List<DeudasPorProyectoVo> data;

        public void setData(List<DeudasPorProyectoVo> data) {
            this.data = data;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return Integer.class;
                case 1:
                    return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "ID";
                case 1:
                    return "VALOR";                
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return project.getId();
                case 1:
                    return project.getValor();                
            }
            return null;
        }

    }

    private class ProyectosTableTop extends AbstractTableModel{

        private List<ComprasDeLiderVo> data;

        public void setData(List<ComprasDeLiderVo> data) {
            this.data = data;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return String.class;
                case 1:
                    return Double.class;
            }
            return super.getColumnClass(columnIndex);
        }

        @Override
        public String getColumnName(int column) {
            switch (column) {
                case 0:
                    return "LIDER";
                case 1:
                    return "VALOR";                
            }
            return super.getColumnName(column);
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            var project = data.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return project.getLider();
                case 1:
                    return project.getValor();                
            }
            return null;
        }

    }

}