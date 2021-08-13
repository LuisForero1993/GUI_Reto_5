package co.edu.utp.misiontic2022.c2.lforero.controller;

import java.sql.SQLException;
import java.util.List;

import co.edu.utp.misiontic2022.c2.lforero.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.lforero.model.dao.ProyectoDao;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoVo;

public class ReportesController {

    private ProyectoDao proyectoDao;
    private ProyectoBancoDao proyectoBancoDao;
    
    public ReportesController(){
        proyectoDao = new ProyectoDao();
        proyectoBancoDao = new ProyectoBancoDao();
    }

    public List<ProyectoVo> listarProyectosExcluyendoClasificaciones(String clasificacion1, String clasificacion2) throws SQLException{
        return proyectoDao.listarProyectos(clasificacion1, clasificacion2);
    }

    public List<ProyectoBancoVo> listarProyectosFinanciadosPorBanco(String banco) throws SQLException{
        return proyectoBancoDao.listarProyectosFinanciadosPorBanco(banco);
    }

    
}
