package co.edu.utp.misiontic2022.c2.lforero.view;

import java.sql.SQLException;

import co.edu.utp.misiontic2022.c2.lforero.controller.ReportesController;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.DeudasPorProyectoVo;
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
    
    /*public void imprimirProyectosSinCasaCampestreNiCondominio() throws SQLException{
        
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
    }*/

    public void proyectosFinanciadosPorBanco(String banco) { 
        try{
            System.out.println(repitaCaracter('=', 36) 
            + " LISTADO DE PROYECTOS POR BANCO " 
            + repitaCaracter('=', 37)); 
            if (banco != null && !banco.isBlank()) { 
                System.out.println(String.format("%3s %-25s %-20s %-15s %-7s %-30s", 
                "ID", "CONSTRUCTORA", "CIUDAD", "CLASIFICACION", "ESTRATO", "LIDER")); 
                System.out.println(repitaCaracter('-', 105)); 
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

    public void totalAdeudadoPorProyectosSuperioresALimite(Double limiteInferior) { 
        try{
            System.out.println(repitaCaracter('=', 1) 
            + " TOTAL DEUDAS POR PROYECTO " 
            + repitaCaracter('=', 1)); 
            if (limiteInferior != null) { 
                System.out.println(String.format("%3s %15s", "ID", "VALOR  ")); 
                System.out.println(repitaCaracter('-', 29)); 
                
                var lista = controller.listarDeudasPorProyecto(limiteInferior);
                for (DeudasPorProyectoVo proyecto : lista){
                    System.out.printf("%3d %,15.1f %n", proyecto.getId(), proyecto.getValor());
                    }
                }
            }catch (SQLException e){
                System.err.println("Error: " + e);
                e.printStackTrace();
                }
        
    }
    
    public void lideresQueMasGastan(Integer top) { 
        try{
        System.out.println(repitaCaracter('=', 6) 
        + " 10 LIDERES MAS COMPRADORES " 
        + repitaCaracter('=', 7)); 
        System.out.println(String.format("%-25s %15s", "LIDER", "VALOR  ")); 
        System.out.println(repitaCaracter('-', 41));
        var lista = controller.listarComprasDeLider(top);
                for (ComprasDeLiderVo proyecto : lista){
                    System.out.printf("%-25s %,15.1f %n", proyecto.getLider(), proyecto.getValor());
                }            
        }catch(SQLException e){
            System.err.println("Error: " + e);
            e.printStackTrace();
            }
    } 


}
