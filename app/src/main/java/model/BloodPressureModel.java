package model;

/**
 * Created by Jewana on 3/4/2016.
 */
public class BloodPressureModel {

    private int bpId;
    private String bpDate;
    private String bpTime;
    private String bpSBP;
    private String bpDBP;
    private String bpBPM;
    private String bpNote;
    private String flag;
    private String personId;

    public BloodPressureModel() {
    }

    public BloodPressureModel(int bpId, String bpDate, String bpTime, String bpSBP, String bpDBP, String bpBPM, String bpNote, String flag, String personId) {
        this.bpId = bpId;
        this.bpDate = bpDate;
        this.bpTime = bpTime;
        this.bpSBP = bpSBP;
        this.bpDBP = bpDBP;
        this.bpBPM = bpBPM;
        this.bpNote = bpNote;
        this.flag = flag;
        this.personId = personId;

    }

    public BloodPressureModel(String bpDate, String bpTime, String bpSBP, String bpDBP, String bpBPM, String bpNote, String flag, String personId) {
        this.bpDate = bpDate;
        this.bpTime = bpTime;
        this.bpSBP = bpSBP;
        this.bpDBP = bpDBP;
        this.bpBPM = bpBPM;
        this.bpNote = bpNote;
        this.flag = flag;
        this.personId = personId;
    }

    public int getBpId() {
        return bpId;
    }


    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }


    public String getBpDate() {
        return bpDate;
    }

    public void setBpDate(String bpDate) {
        this.bpDate = bpDate;
    }

    public String getBpTime() {
        return bpTime;
    }

    public void setBpTime(String bpTime) {
        this.bpTime = bpTime;
    }

    public String getBpSBP() {
        return bpSBP;
    }

    public void setBpSBP(String bpSBP) {
        this.bpSBP = bpSBP;
    }

    public String getBpDBP() {
        return bpDBP;
    }

    public void setBpDBP(String bpDBP) {
        this.bpDBP = bpDBP;
    }

    public String getBpBPM() {
        return bpBPM;
    }

    public void setBpBPM(String bpBPM) {
        this.bpBPM = bpBPM;
    }

    public String getBpNote() {
        return bpNote;
    }

    public void setBpNote(String bpNote) {
        this.bpNote = bpNote;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
