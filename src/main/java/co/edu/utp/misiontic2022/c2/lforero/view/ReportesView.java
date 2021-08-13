package co.edu.utp.misiontic2022.c2.lforero.view;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.lforero.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;

public class ReportesView {
    private ReportesController controller;

    public ReportesView (){
        controller = new ReportesController();
    }


    private String repitaCaracter(Character caracter, Integer veces) { 
        var respuesta = ""; 
        for (int i = 0; i < veces; i++) { 
            respuesta += caracter; 
        } return respuesta; 
    }
    

    public void imprimirProyectosSinCasaCampestreNiCondominio() throws SQLException{
        
        try{
            System.out.println(repitaCaracter('=', 5) + " LISTADO DE PROYECTOS SIN CASA CAMPESTRE NI CONDOMINIO " + repitaCaracter('=', 5));
            System.out.println(String.format("%-20s %-15s %-7s %-10s %-10s", 
            "CIUDAD", "CLASIFICACION", "TOTAL", "VIEJO", "RECIENTE")); 
            System.out.println(repitaCaracter('-', 66));
            var lista = controller.listarProyectosExcluyendoClasificaciones("Casa Campestre", "Condominio");
            for (ProyectoBancoVo proyecto : lista){
                System.out.printf("%-20s %-15s %7d %-10s %-10s %n", proyecto.getCiudad(), proyecto.getClasificacion(), proyecto.getTotal(), proyecto.getViejo(), proyecto.getReciente());
            }
        }catch (SQLException e){
        System.err.println("Error: " + e);
        e.printStackTrace();
        }
    }
}
