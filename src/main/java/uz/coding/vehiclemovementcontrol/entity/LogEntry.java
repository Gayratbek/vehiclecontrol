package uz.coding.vehiclemovementcontrol.entity;

import java.lang.reflect.Field;
import java.util.Date;

public class LogEntry {

    private int id;

    private String  regno;

    private Date date;
    private Integer vehicle;
    private Integer owner;
    private Long startingodometr;
    private Long endingodometr;
    private String route;
    private String description;

    public boolean isNull() {
        Field fields[] = this.getClass().getDeclaredFields();
        for (Field f : fields) {
            try {
                Object value = f.get(this);
                if (f.getName() !="id" ){
                    if (value == null) {
                        return false;
                    }
                }

            }
            catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        }
        return true;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegno() {
        return regno;
    }

    public void setRegno(String regno) {
        this.regno = regno;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getVehicle() {
        return vehicle;
    }

    public void setVehicle(Integer vehicle) {
        this.vehicle = vehicle;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public Long getStartingodometr() {
        return startingodometr;
    }

    public void setStartingodometr(Long startingodometr) {
        this.startingodometr = startingodometr;
    }

    public Long getEndingodometr() {
        return endingodometr;
    }

    public void setEndingodometr(Long endingodometr) {
        this.endingodometr = endingodometr;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
