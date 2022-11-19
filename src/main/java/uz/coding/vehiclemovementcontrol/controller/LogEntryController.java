package uz.coding.vehiclemovementcontrol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.coding.vehiclemovementcontrol.entity.LogEntry;
import uz.coding.vehiclemovementcontrol.model.ReturnModel;
import uz.coding.vehiclemovementcontrol.service.LogEntryService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class LogEntryController {

    @Autowired
    private LogEntryService logEntryService;

    @GetMapping("/logentries")
    public ResponseEntity<ReturnModel> fetchEntries (@RequestParam(required = false,name = "dateRange") String daterange,
                                                     @RequestParam(required = false,name = "owner") String ownerName,
                                                     @RequestParam(required = false,name = "vehicle") String vehicleName){
//        log.info("Inside saveUser of UserController");
        List<LogEntry> logEntries = logEntryService.fetchEntries(daterange,ownerName,vehicleName);
        Long totaldistance = calculateTotalDistance(logEntries);

        ReturnModel returnModel = new ReturnModel();
        returnModel.setLogEntries(logEntries);
        returnModel.setTotaldistance(totaldistance);

        return Optional
                .ofNullable( returnModel )
                .map( user -> ResponseEntity.ok().body(returnModel) )          //200 OK
                .orElseGet( () -> ResponseEntity.notFound().build() );
    }

    @PostMapping("/add")
    public String addLogEntry(@RequestBody LogEntry logEntry){
//        log.info("Inside saveUser of UserController");
        return logEntryService.addLogEntry(logEntry);
    }
    @PostMapping("/edit/{id}")
    public LogEntry editLogEntry(@PathVariable Integer id, @RequestBody LogEntry logEntry){
//        log.info("Inside saveUser of UserController");
        return logEntryService.editLogEntry(id,logEntry);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteLogEntry(@PathVariable Integer id){
//        log.info("Inside saveUser of UserController");
        return logEntryService.deleteLogEntry(id);
    }

    private Long calculateTotalDistance(List<LogEntry> logEntries) {

        long sum = (long) logEntries.stream().map(x -> x.getEndingodometr() - x.getStartingodometr())
                .reduce(0L,Long :: sum);
        return sum;
    }

}
