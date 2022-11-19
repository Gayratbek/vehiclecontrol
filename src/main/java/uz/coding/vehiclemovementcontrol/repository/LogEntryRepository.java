package uz.coding.vehiclemovementcontrol.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import uz.coding.vehiclemovementcontrol.entity.LogEntry;
import uz.coding.vehiclemovementcontrol.entity.mapper.LogEntryMapper;

import java.sql.Types;
import java.util.*;

@Repository
public class LogEntryRepository  {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public LogEntry editLogEntry(Integer id, LogEntry logEntry) {
//        LogEntry editedlogEntry = jdbcTemplate.query("select * from vehicleLogEntry;",LogEntry.class);
        String sql = "update vehiclelogentry set ";
        Object[] objects = new Object[]{};

        if (logEntry.getDate() != null){
            sql += " date = ? ";
            objects =appendValue(objects, logEntry.getDate());
        }
        if (logEntry.getDescription() != null){
            sql += " , description = ? ";
            objects =appendValue(objects, logEntry.getDescription());
        }
        if (logEntry.getEndingodometr() != null){
            sql += " , endingodometr = ? ";
            objects =appendValue(objects, logEntry.getEndingodometr());
        }
        if (logEntry.getStartingodometr() != null){
            sql += " , startingodometr = ? ";
            objects =appendValue(objects, logEntry.getStartingodometr());
        }
        if (logEntry.getOwner() != null){
            sql += " , owner = ? ";
            objects =appendValue(objects, logEntry.getOwner());
        }
        if (logEntry.getVehicle() != null){
            sql += " , vehicle = ? ";
            objects =appendValue(objects, logEntry.getVehicle());
        }
        sql += " where id = ?";
        objects =appendValue(objects, id);

        jdbcTemplate.update(sql,objects);
        sql = "SELECT vhle.date,veh.name vehicle, ow.name owner, vhle.startingodometr, vhle.endingodometr,vhle.route, vhle.description  " +
                "FROM vehiclelogentry vhle, vehicle veh, owner ow where vhle.vehicle = veh.id and vhle.owner = ow.id  and vhle.id = ?";
        logEntry = (LogEntry) jdbcTemplate.queryForObject(sql,
                new Object[]{id} , new LogEntryMapper());

        return logEntry;
    }

    public List<LogEntry> fetchEntries(Date startDate, Date endDate, Integer owner, Integer vehicle)  {
        List<LogEntry> logs = new ArrayList<LogEntry>();
        String sql = "SELECT * FROM vehiclelogentry ";
        Object[] objects = new Object[]{};
        if (startDate != null && startDate != null ){
            sql += " where date between ? and ?";
            objects =appendValue(objects, startDate);
            objects =appendValue(objects, endDate);
        }
        if (owner != null){
            sql  += sqlString("owner",objects.length);
            objects =appendValue(objects, owner);
        }
        if (vehicle != null){
            sql  += sqlString("vehicle",objects.length);
            objects =appendValue(objects, vehicle);
        }


        logs.addAll(
                jdbcTemplate.query(sql, objects ,
                        new LogEntryMapper()
                        )
        );


        return logs;
    }

    private String sqlString(String parametr,Integer length) {

           String sql = "";
          if ( length > 0){
                sql += " and " + parametr +"  = ?";
            }
            else {
                sql += " where " + parametr + "  = ?";
            }

        return sql;

    }
    private Object[] appendValue(Object[] obj, Object newObj) {

        ArrayList<Object> temp = new ArrayList<Object>(Arrays.asList(obj));
        temp.add(newObj);
        return temp.toArray();

    }

    public String  deleteLogEntry(Integer id) {

        String sql  = "delete from vehiclelogentry where id = ?";
        jdbcTemplate.update(sql,new Object[]{id});

        return "" + id + ": deleted succesfully";
    }

    public String saveLogEntry(LogEntry logEntry) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO public.vehiclelogentry (regno, date,vehicle, owner, startingodometr,endingodometr,route,description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatementCreatorFactory factory = new PreparedStatementCreatorFactory(
                sql, new int[] {
                Types.VARCHAR, Types.DATE,
                Types.INTEGER, Types.INTEGER,
                Types.BIGINT, Types.BIGINT,Types.VARCHAR,Types.VARCHAR } );

        jdbcTemplate.update(factory.newPreparedStatementCreator(new Object[]{logEntry.getRegno(), logEntry.getDate(), logEntry.getVehicle(), logEntry.getOwner(), logEntry.getStartingodometr(), logEntry.getEndingodometr(), logEntry.getRoute(), logEntry.getDescription()})
                 , keyHolder);


        return "Successfully added";
    }
}
