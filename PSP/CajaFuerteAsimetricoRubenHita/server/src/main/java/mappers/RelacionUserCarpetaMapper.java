package mappers;

import org.springframework.jdbc.core.RowMapper;
import serverModel.RelacionUserCarpeta;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RelacionUserCarpetaMapper implements RowMapper<RelacionUserCarpeta> {
    @Override
    public RelacionUserCarpeta mapRow(ResultSet rs, int rowNum) throws SQLException {
        RelacionUserCarpeta r = new RelacionUserCarpeta();
        r.setUser(rs.getString("user"));
        r.setIdCarpeta(rs.getInt("id_carpeta"));
        return r;
    }
}
