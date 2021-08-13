package co.edu.utp.misiontic2022.c2.lforero.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.lforero.model.vo.ProyectoBancoVo;
import co.edu.utp.misiontic2022.c2.lforero.util.JDBCUtilities;

public class ProyectoBancoDao {

    public List<ProyectoBancoVo> listarProyectos(String clasificacion1, String clasificacion2) throws SQLException{
        List<ProyectoBancoVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;

        try {
            var query = "SELECT ciudad, Clasificacion, COUNT(*) TOTAL, MIN(Fecha_Inicio) VIEJO, MAX(Fecha_Inicio) RECIENTE"
                + " FROM Proyecto p"
                + " WHERE Clasificacion NOT IN (?, ?)"
                + " GROUP BY Ciudad, Clasificacion"
                + " ORDER BY Ciudad, Clasificacion";
            //'Casa Campestre', 'Condominio'
            stmt = conn.prepareStatement(query);
            stmt.setString(1, clasificacion1);
            stmt.setString(2, clasificacion2);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ProyectoBancoVo();
                vo.setCiudad(rset.getString("CIUDAD"));
                vo.setClasificacion(rset.getString("CLASIFICACION"));
                vo.setTotal(rset.getInt("TOTAL"));
                vo.setViejo(rset.getString("VIEJO"));
                vo.setReciente(rset.getString("RECIENTE"));

                respuesta.add(vo);                
            }
        
        }finally{
            if(rset != null){
                rset.close();
            }
            if(stmt != null){
                stmt.close();
            }
            if(conn != null){
                conn.close();
            }
        }
        return respuesta;
    }
    
}  
