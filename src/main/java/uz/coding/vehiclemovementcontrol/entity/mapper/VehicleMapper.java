package uz.coding.vehiclemovementcontrol.entity.mapper;


import org.springframework.jdbc.core.RowMapper;
import uz.coding.vehiclemovementcontrol.entity.Vehicle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(rs.getInt("id"));
        vehicle.setName(rs.getString("name"));
        vehicle.setOwner(rs.getInt("owner"));
//        vehicle.setId(rs.getInt("id"));
////        logEntry.setOwner(rs.getObject("owner"));
////        logEntry.setVehicle(rs.getObject("vehicle"));
//        logEntry.setDescription(rs.getString("description"));
//        logEntry.setRoute(rs.getString("route"));
//        logEntry.setEndingOdometr(rs.getLong("endingOdometr"));
//        logEntry.setStartingOdometr(rs.getLong("startingOdometr"));

        return vehicle;
    }
}
