package co.edu.utp.misiontic2022.c2.lforero.view;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.lforero.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoVo;

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
            for (ProyectoVo proyecto : lista){
                System.out.printf("%-20s %-15s %7d %-10s %-10s %n", proyecto.getCiudad(), proyecto.getClasificacion(), proyecto.getTotal(), proyecto.getViejo(), proyecto.getReciente());
            }
        }catch (SQLException e){
            System.err.println("Error: " + e);
            e.printStackTrace();
            }
    }

    public void proyectosFinanciadosPorBanco(String banco) throws SQLException { 
        try{
            System.out.println(repitaCaracter('=', 36) 
            + " LISTADO DE PROYECTOS POR BANCO " 
            + repitaCaracter('=', 37)); 
            if (banco != null && !banco.isBlank()) { 
                System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", 
                "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER")); 
                System.out.println(repitaCaracter('-', 105)); 
                // TODO Imprimir en pantalla la información del proyecto 
                var lista = controller.listarProyectosFinanciadosPorBanco(banco);
                for (ProyectoBancoVo proyecto : lista){
                    System.out.printf("%3d %-25s %-20s %-15s %7d %-30s %n", proyecto.getId(), proyecto.getConstructora(), proyecto.getCiudad(), proyecto.getClasificacion(), proyecto.getEstrato(), proyecto.getLider());
                }
            }
        }catch(SQLException e){
            System.err.println("Error: " + e);
            e.printStackTrace();
            }
    }
}
