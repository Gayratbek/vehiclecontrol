package uz.coding.vehiclemovementcontrol.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import uz.coding.vehiclemovementcontrol.entity.Owner;
import uz.coding.vehiclemovementcontrol.entity.mapper.OwnerMapper;

@Repository
public class OwnerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    public Owner findbyName(String ownerName) {
        Owner owner = (Owner) jdbcTemplate.queryForObject("SELECT * FROM owner where name = ?",
                new Object[]{ownerName},new OwnerMapper());
        return owner;

    }
}
