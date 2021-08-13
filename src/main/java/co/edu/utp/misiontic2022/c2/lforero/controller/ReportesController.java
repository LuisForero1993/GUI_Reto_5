package co.edu.utp.misiontic2022.c2.lforero.controller;

import java.sql.SQLException;
import java.util.List;
import co.edu.utp.misiontic2022.c2.lforero.model.dao.ProyectoBancoDao;
import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;

public class ReportesController {

    private ProyectoBancoDao proyectoBancoDao;
    
    public ReportesController(){
        proyectoBancoDao = new ProyectoBancoDao();
    }

    public List<ProyectoBancoVo> listarProyectosExcluyendoClasificaciones(String clasificacion1, String clasificacion2) throws SQLException{
        return proyectoBancoDao.listarProyectos(clasificacion1, clasificacion2);
    }
    
}
