package uz.coding.vehiclemovementcontrol.entity.mapper;

import org.springframework.jdbc.core.RowMapper;
import uz.coding.vehiclemovementcontrol.entity.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OwnerMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Owner owner = new Owner();
        owner.setId(rs.getInt("id"));
        owner.setName(rs.getString("name"));
        return owner;
    }
}
