package co.edu.utp.misiontic2022.c2.lforero.model.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.utp.misiontic2022.c2.lforero.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.c2.lforero.util.JDBCUtilities;

public class ComprasDeLiderDao {
    public List<ComprasDeLiderVo> listarComprasDeLider(Integer top) throws SQLException{
        List<ComprasDeLiderVo> respuesta = new ArrayList<>();
        var conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            /*var query = "SELECT p.ID_Proyecto ID, p.Constructora, p.Ciudad, p.Clasificacion, t.Estrato, l.Nombre ||' '|| l.Primer_Apellido ||' '|| l.Segundo_Apellido LIDER"
                + " FROM Proyecto p "
                + " join Tipo t ON (P.ID_Tipo=t.ID_Tipo)"
                + " join Lider l ON (p.ID_Lider=l.ID_Lider)"
                + " WHERE p.Banco_Vinculado = (?)"
                + " ORDER BY p.Fecha_Inicio DESC, Ciudad ASC, Constructora";
            */
            var query = "SELECT l.Nombre ||' '|| l.Primer_Apellido ||' '|| l.Segundo_Apellido LIDER, sum(c.Cantidad*mc.Precio_Unidad) VALOR"
            + " FROM Lider l"
            + " join Proyecto p on (l.ID_Lider = p.ID_Lider)"
            + " JOIN Compra c on (p.ID_Proyecto = c.ID_Proyecto)"
            + " JOIN MaterialConstruccion mc on (c.ID_MaterialConstruccion = mc.ID_MaterialConstruccion)"
            + " GROUP BY Lider"
            + " ORDER BY VALOR DESC"
            + " LIMIT (?)";

            stmt = conn.prepareStatement(query);
            stmt.setInt(1, top);
            rset = stmt.executeQuery();

            while(rset.next()){
                var vo = new ComprasDeLiderVo();
                vo.setValor(rset.getDouble("VALOR"));
                vo.setLider(rset.getString("LIDER"));
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
