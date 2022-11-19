package uz.coding.vehiclemovementcontrol.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.coding.vehiclemovementcontrol.entity.Vehicle;
import uz.coding.vehiclemovementcontrol.entity.mapper.VehicleMapper;

@Repository
public class VehicleRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Vehicle findbyName(String vehicleName) {

        Vehicle vehicle = (Vehicle) jdbcTemplate.queryForObject("SELECT * FROM vehicle where name = ?",
                new Object[]{vehicleName},new VehicleMapper());
        return vehicle;
    }
}
