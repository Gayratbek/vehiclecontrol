package uz.coding.vehiclemovementcontrol.entity.mapper;


import org.springframework.jdbc.core.RowMapper;
import uz.coding.vehiclemovementcontrol.entity.LogEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LogEntryMapper implements RowMapper {


    @Override
    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
        LogEntry logEntry = new LogEntry();
        logEntry.setId(rs.getInt("id"));
        logEntry.setRegno(rs.getString("regno"));
        logEntry.setDate(rs.getDate("date"));
        logEntry.setVehicle(rs.getInt("vehicle"));
        logEntry.setOwner(rs.getInt("owner"));
        logEntry.setDescription(rs.getString("description"));
        logEntry.setRoute(rs.getString("route"));
        logEntry.setEndingodometr(rs.getLong("endingOdometr"));
        logEntry.setStartingodometr(rs.getLong("startingOdometr"));

        return logEntry;
    }
}
