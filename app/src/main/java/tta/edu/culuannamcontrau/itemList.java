package tta.edu.culuannamcontrau;

public class itemList {
    // Variable to store data corresponding
    // to keyword in database
    String busstop;
    long cost;
    String time;
    String name;
    static String rfid,mailname;
    public itemList() {
    }

    public itemList(String busstop, long cost, String time) {
        this.busstop = busstop;
        this.cost = cost;
        this.time = time;
    }

    public String getBusstop() {
        return busstop;
    }

    public void setBusstop(String busstop) {
        this.busstop = busstop;
    }

    public long getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String getMailname() {
        return mailname;
    }

    public void setMailname(String mailname) {
        this.mailname = mailname;
    }
}
