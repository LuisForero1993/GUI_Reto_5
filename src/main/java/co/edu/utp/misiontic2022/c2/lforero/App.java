package co.edu.utp.misiontic2022.c2.lforero;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.lforero.view.ReporteGUI;
import co.edu.utp.misiontic2022.c2.lforero.view.ReportesView;

public class App 
{
    public static void main( String[] args ) throws SQLException
    {   
        //var view = new ReportesView();
        //view.imprimirProyectosSinCasaCampestreNiCondominio();
        
        //var ReportesView = new ReportesView();
        //var banco = "Conavi";
        //ReportesView.proyectosFinanciadosPorBanco(banco);

        //var reportesView2 = new ReportesView();
        //reportesView2. lideresQueMasGastan();
        
        //var reportesView3 = new ReportesView();
        //var limiteInferior = 50_000d;
        //reportesView3.totalAdeudadoPorProyectosSuperioresALimite(limiteInferior);

        var view = new ReporteGUI();
        view.setVisible(true);
    }
}
