package uz.coding.vehiclemovementcontrol.model;

import uz.coding.vehiclemovementcontrol.entity.LogEntry;

import java.util.List;

public class ReturnModel {
    private List<LogEntry> logEntries;
    private long totaldistance;

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }

    public void setLogEntries(List<LogEntry> logEntries) {
        this.logEntries = logEntries;
    }

    public long getTotaldistance() {
        return totaldistance;
    }

    public void setTotaldistance(long totaldistance) {
        this.totaldistance = totaldistance;
    }
}
