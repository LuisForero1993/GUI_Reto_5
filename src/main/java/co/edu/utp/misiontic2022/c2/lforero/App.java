package co.edu.utp.misiontic2022.c2.lforero;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.lforero.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.lforero.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.lforero.util.JDBCUtilities;

public class App 
{
    public static void main( String[] args )
    {
        try{
            var pd = new ReportesController();
            var lista = pd.listarProyectosExcluyendoClasificaciones("Casa Campestre", "Condominio");
            for (ProyectoBancoVo proyecto : lista){
                System.out.println(proyecto);
            }
        }catch (SQLException e){
            System.err.println("Error: " + e);
            //e.printStackTrace();
        }
        /*
        try{
            var pd = new ProyectoBancoDao();
            var lista = pd.listarProyectos("Casa Campestre", "Condominio");
            for (ProyectoBancoVo proyecto : lista){
                System.out.println(proyecto);
            }
        }catch (SQLException e){
            System.err.println("Error: " + e);
            //e.printStackTrace();
        }
        */
        /*
        try {
            var conn = JDBCUtilities.getConnection();
            System.out.println("Conexion exitosa");
            conn.close();
        }catch(SQLException e){
            System.err.println("Error: " + e);
        }
        */
    }
}
