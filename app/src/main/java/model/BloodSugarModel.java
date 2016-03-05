package model;

/**
 * Created by Jewana on 3/4/2016.
 */
public class BloodSugarModel {

    private int bsId;
    private String bsDate;
    private String bsTime;
    private String bsLevel;
    private String bsNotes;
    private String flag;
    private String personId;

    public BloodSugarModel() {
    }

    public BloodSugarModel(String bsDate, String bsTime, String bsLevel, String bsNotes, String flag, String personId) {
        this.bsDate = bsDate;
        this.bsTime = bsTime;
        this.bsLevel = bsLevel;
        this.bsNotes = bsNotes;
        this.flag = flag;
        this.personId = personId;
    }

    public BloodSugarModel(int bsId, String bsDate, String bsTime, String bsLevel, String bsNotes, String flag, String personId) {
        this.bsId = bsId;
        this.bsDate = bsDate;
        this.bsTime = bsTime;
        this.bsLevel = bsLevel;
        this.bsNotes = bsNotes;
        this.flag = flag;
        this.personId = personId;
    }

    public int getBsId() {
        return bsId;
    }



    public String getBsDate() {
        return bsDate;
    }

    public void setBsDate(String bsDate) {
        this.bsDate = bsDate;
    }

    public String getBsTime() {
        return bsTime;
    }

    public void setBsTime(String bsTime) {
        this.bsTime = bsTime;
    }

    public String getBsLevel() {
        return bsLevel;
    }

    public void setBsLevel(String bsLevel) {
        this.bsLevel = bsLevel;
    }

    public String getBsNotes() {
        return bsNotes;
    }

    public void setBsNotes(String bsNotes) {
        this.bsNotes = bsNotes;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
