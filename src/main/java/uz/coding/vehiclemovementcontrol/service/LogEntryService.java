package uz.coding.vehiclemovementcontrol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.coding.vehiclemovementcontrol.entity.LogEntry;
import uz.coding.vehiclemovementcontrol.entity.Owner;
import uz.coding.vehiclemovementcontrol.entity.Vehicle;
import uz.coding.vehiclemovementcontrol.repository.LogEntryRepository;
import uz.coding.vehiclemovementcontrol.repository.OwnerRepository;
import uz.coding.vehiclemovementcontrol.repository.VehicleRepository;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class LogEntryService {

    @Autowired
    LogEntryRepository logEntryRepository;

    @Autowired
    VehicleRepository vehicleRepository;


    @Autowired
    OwnerRepository ownerRepository;


    public LogEntry editLogEntry(Integer id, LogEntry logEntry) {


        return logEntryRepository.editLogEntry(id,logEntry);

    }

    public List<LogEntry> fetchEntries(String daterange, String ownerName, String vehicleName)  {

        Date startDate = null;
        Date endDate = null;
        if (daterange != null){
            String start = daterange.substring(0,daterange.indexOf(":") );
            String end = daterange.substring(daterange.indexOf(":") + 1,daterange.length());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            try {
                startDate = (Date) formatter.parse(start);
                endDate = (Date) formatter.parse(end);
            }
            catch (ParseException ex){
                ex.printStackTrace();
            }
        }
        Integer vehicleid = null;
        if (vehicleName != null){
            Vehicle vehicle = vehicleRepository.findbyName(vehicleName);
            vehicleid = vehicle.getId();
        }
        Integer ownerid = null;
        if (ownerName != null){
            Owner owner = ownerRepository.findbyName(ownerName);
            ownerid = owner.getId();
        }


        return logEntryRepository.fetchEntries(startDate,endDate,ownerid, vehicleid);
    }

    public String deleteLogEntry(Integer id) {
        ;
        return logEntryRepository.deleteLogEntry(id);
    }

    public String addLogEntry(LogEntry logEntry) {
        boolean checkNull = logEntry.isNull();

        if (checkNull = false){
            return null;
        }

        Integer vehicleid = null;


        return logEntryRepository.saveLogEntry(logEntry);
    }

}
