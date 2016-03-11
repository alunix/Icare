package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import model.AppointmentModel;
import model.BloodPressureModel;
import model.BloodSugarModel;
import model.DietModel;
import model.DoctorModel;
import model.HospitalModel;
import model.MedicalHistoryModel;
import model.MedicineModel;
import model.ProfileModel;
import model.VaccineModel;

/**
 * Created by Mobile App Develop on 7-1-16.
 */
public class DataStorage {

    private DBHelper dbHelper;
    private SQLiteDatabase database;

//------------------------------------------------------------------------------------------------//

    public DataStorage(Context context){
        dbHelper=new DBHelper(context);

    }

    private void dbOpen()
    {
        database=dbHelper.getWritableDatabase();

    }

    private void dbClose()
    {
     dbHelper.close();
    }

//---------------------------------------*Profile*------------------------------------------------//

    public boolean insertProfile (ProfileModel profileModel)
    {
        this.dbOpen();

        ContentValues cvProfileValue=new ContentValues();
        cvProfileValue.put(DBHelper.PROFILE_COL_PROFILE_NAME,profileModel.getProfileName());
        cvProfileValue.put(DBHelper.PROFILE_COL_DATE_OF_BIRTH, profileModel.getDateOfBirth());
        cvProfileValue.put(DBHelper.PROFILE_COL_GENDER,profileModel.getGender());
        cvProfileValue.put(DBHelper.PROFILE_COL_BLOOD_GROUP,profileModel.getBloodGroup());
        cvProfileValue.put(DBHelper.PROFILE_COL_HEIGHT,profileModel.getHeight());
        cvProfileValue.put(DBHelper.PROFILE_COL_WEIGHT,profileModel.getWeight());
        cvProfileValue.put(DBHelper.PROFILE_COL_EMAIL,profileModel.getEmail());
        cvProfileValue.put(DBHelper.PROFILE_COL_CONTACT_NO,profileModel.getContactNo());
        cvProfileValue.put(DBHelper.PROFILE_COL_PERSON_ID, profileModel.getPersonId());
        cvProfileValue.put(DBHelper.PROFILE_COL_ID_TYPE,profileModel.getIdType());
        cvProfileValue.put(DBHelper.PROFILE_COL_IMAGE_PATH, profileModel.getImagePath());
        cvProfileValue.put(DBHelper.PROFILE_COL_MAJOR_DISEASE, profileModel.getMajorDisease());

        cvProfileValue.put(DBHelper.PROFILE_COL_NID_BCN, profileModel.getNid_bcn());
        cvProfileValue.put(DBHelper.PROFILE_COL_RELATIONSHIP, profileModel.getRelation());
        cvProfileValue.put(DBHelper.COL_FLAG, profileModel.getProfile_flag());

        long inserted=database.insert(DBHelper.TABLE_NAME_PROFILE, null, cvProfileValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateProfile (int profileId,ProfileModel profileModel)
    {
        this.dbOpen();

        ContentValues cvProfileValue=new ContentValues();
        cvProfileValue.put(DBHelper.PROFILE_COL_PROFILE_NAME,profileModel.getProfileName());
        cvProfileValue.put(DBHelper.PROFILE_COL_DATE_OF_BIRTH, profileModel.getDateOfBirth());
        cvProfileValue.put(DBHelper.PROFILE_COL_GENDER,profileModel.getGender());
        cvProfileValue.put(DBHelper.PROFILE_COL_BLOOD_GROUP,profileModel.getBloodGroup());
        cvProfileValue.put(DBHelper.PROFILE_COL_HEIGHT,profileModel.getHeight());
        cvProfileValue.put(DBHelper.PROFILE_COL_WEIGHT,profileModel.getWeight());
        cvProfileValue.put(DBHelper.PROFILE_COL_EMAIL,profileModel.getEmail());
        cvProfileValue.put(DBHelper.PROFILE_COL_CONTACT_NO,profileModel.getContactNo());
        cvProfileValue.put(DBHelper.PROFILE_COL_PERSON_ID, profileModel.getPersonId());
        cvProfileValue.put(DBHelper.PROFILE_COL_ID_TYPE,profileModel.getIdType());
        cvProfileValue.put(DBHelper.PROFILE_COL_IMAGE_PATH, profileModel.getImagePath());
        cvProfileValue.put(DBHelper.PROFILE_COL_MAJOR_DISEASE, profileModel.getMajorDisease());

        cvProfileValue.put(DBHelper.PROFILE_COL_NID_BCN, profileModel.getNid_bcn());
        cvProfileValue.put(DBHelper.PROFILE_COL_RELATIONSHIP, profileModel.getRelation());
        //cvProfileValue.put(DBHelper.COL_FLAG, profileModel.getProfile_flag());


        //long inserted=database.insert(DBHelper.TABLE_NAME_PROFILE,null,cvProfileValue);

        long updated = database.update(DBHelper.TABLE_NAME_PROFILE, cvProfileValue, DBHelper.PROFILE_COL_ID + "= " + profileId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }



    public ArrayList<ProfileModel> getAllProfile()
    {

        ArrayList<ProfileModel> profileModels=new ArrayList<>();
        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_PROFILE + " WHERE " +
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int prid=cursor.getInt(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID));
                String profileName=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PROFILE_NAME));
                String dateOfBirth =cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_DATE_OF_BIRTH));
                String gender=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_GENDER));
                String bloodGroup=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_BLOOD_GROUP));
                String height=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_HEIGHT));
                String weight=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_WEIGHT));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_EMAIL));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_CONTACT_NO));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PERSON_ID));
                String idType=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID_TYPE));
                String imagePath=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_IMAGE_PATH));
                String majorDisease=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_MAJOR_DISEASE));

                String nationIdBcn=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_NID_BCN));
                String relationship=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_RELATIONSHIP ));

                String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));

                ProfileModel profileModel=new ProfileModel(prid,profileName,dateOfBirth,gender,bloodGroup,height,weight,email,contactNo,personId,idType,imagePath,majorDisease,nationIdBcn,relationship,profile_flag);


                profileModels.add(profileModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return profileModels;
    }


    public int  getProfileModelMaxID(String tableName) {

        this.dbOpen();
        int prid=0;
        String selectQuery = "select case when max(id) is null then 1 else max(id)+1 end maxID from "+tableName;

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                prid=cursor.getInt(cursor.getColumnIndex("maxID"));

                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return  prid;
    }



    public ArrayList<ProfileModel> getProfileModelByProfileID(int profileID) {

        ArrayList<ProfileModel> profileModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_PROFILE + " WHERE " +
                DBHelper.PROFILE_COL_ID + "=" + profileID + " AND "+
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int prid=cursor.getInt(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID));
                String profileName=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PROFILE_NAME));
                String dateOfBirth =cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_DATE_OF_BIRTH));
                String gender=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_GENDER));
                String bloodGroup=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_BLOOD_GROUP));
                String height=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_HEIGHT));
                String weight=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_WEIGHT));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_EMAIL));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_CONTACT_NO));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PERSON_ID));
                String idType=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID_TYPE));
                String imagePath=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_IMAGE_PATH));
                String majorDisease=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_MAJOR_DISEASE));

                String nationIdBcn=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_NID_BCN));
                String relationship=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_RELATIONSHIP ));


                String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));

                ProfileModel profileModel=new ProfileModel(prid,profileName,dateOfBirth,gender,bloodGroup,height,weight,email,contactNo,personId,idType,imagePath,majorDisease,nationIdBcn,relationship,profile_flag);

                profileModels.add(profileModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return profileModels;
    }



    public ArrayList<ProfileModel> getProfileModelByPersonID(String profileID)
    {

        ArrayList<ProfileModel> profileModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_PROFILE + " WHERE " +
                DBHelper.PROFILE_COL_PERSON_ID + "='" + profileID + "' AND "+
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int prid=cursor.getInt(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID));
                String profileName=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PROFILE_NAME));
                String dateOfBirth =cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_DATE_OF_BIRTH));
                String gender=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_GENDER));
                String bloodGroup=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_BLOOD_GROUP));
                String height=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_HEIGHT));
                String weight=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_WEIGHT));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_EMAIL));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_CONTACT_NO));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PERSON_ID));
                String idType=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID_TYPE));
                String imagePath=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_IMAGE_PATH));
                String majorDisease=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_MAJOR_DISEASE));

                String nationIdBcn=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_NID_BCN));
                String relationship=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_RELATIONSHIP ));

                String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));

                ProfileModel profileModel=new ProfileModel(prid,profileName,dateOfBirth,gender,bloodGroup,height,weight,email,contactNo,personId,idType,imagePath,majorDisease,nationIdBcn,relationship,profile_flag);



                profileModels.add(profileModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return profileModels;
    }


    public ArrayList<ProfileModel> getProfileModelBySearchbyName(String prName)
    {

        ArrayList<ProfileModel> profileModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_PROFILE + " WHERE " +
                DBHelper.PROFILE_COL_PROFILE_NAME + " LIKE '%" + prName + "%' AND "+
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int prid=cursor.getInt(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID));
                String profileName=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PROFILE_NAME));
                String dateOfBirth =cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_DATE_OF_BIRTH));
                String gender=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_GENDER));
                String bloodGroup=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_BLOOD_GROUP));
                String height=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_HEIGHT));
                String weight=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_WEIGHT));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_EMAIL));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_CONTACT_NO));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_PERSON_ID));
                String idType=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_ID_TYPE));
                String imagePath=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_IMAGE_PATH));
                String majorDisease=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_MAJOR_DISEASE));

                String nationIdBcn=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_NID_BCN));
                String relationship=cursor.getString(cursor.getColumnIndex(DBHelper.PROFILE_COL_RELATIONSHIP ));

                String profile_flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));

                ProfileModel profileModel=new ProfileModel(prid,profileName,dateOfBirth,gender,bloodGroup,height,weight,email,contactNo,personId,idType,imagePath,majorDisease,nationIdBcn,relationship,profile_flag);



                profileModels.add(profileModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return profileModels;
    }
    public boolean deleteProfile (int profileId, String flag )
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_PROFILE, cvDietValue, DBHelper.PROFILE_COL_ID + "= " + profileId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteAllActivitiesByPersonId (String personId, String flag )
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_PROFILE, cvDietValue, DBHelper.PROFILE_COL_PERSON_ID + "= '" + personId+"'", null);
        long updated_Diet = database.update(DBHelper.TABLE_NAME_DIET, cvDietValue, DBHelper.DIET_COL_DIET_PERSON_ID + "= '" + personId+"'", null);
        long updated_vaccine = database.update(DBHelper.TABLE_NAME_VACCINATION, cvDietValue, DBHelper.VACCINATION_COL_PERSON_ID + "= '" + personId+"'", null);
        long updated_medicalHistory = database.update(DBHelper.TABLE_NAME_MEDICAL_HISTORY, cvDietValue, DBHelper.MEDICAL_HISTORY_COL_PERSON_ID + "= '" + personId+"'", null);
        long updated_medicine = database.update(DBHelper.TABLE_NAME_MEDICINE, cvDietValue, DBHelper.MEDICINE_COL_PERSON_ID + "= '" + personId + "'", null);
        long updated_Appointment = database.update(DBHelper.TABLE_NAME_APPOINTMENT, cvDietValue, DBHelper.APPOINTMENT_COL_PERSON_ID + "= '" + personId+"'", null);

        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public boolean updateAllActivitiesByPersonId (String OldpersonId, String newPersonId )
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.PROFILE_COL_PERSON_ID, newPersonId);

        long updated = database.update(DBHelper.TABLE_NAME_PROFILE, cvDietValue, DBHelper.PROFILE_COL_PERSON_ID + "= '" + OldpersonId+"'", null);
        long updated_Diet = database.update(DBHelper.TABLE_NAME_DIET, cvDietValue, DBHelper.DIET_COL_DIET_PERSON_ID + "= '" + OldpersonId+"'", null);
        long updated_vaccine = database.update(DBHelper.TABLE_NAME_VACCINATION, cvDietValue, DBHelper.VACCINATION_COL_PERSON_ID + "= '" + OldpersonId+"'", null);
        long updated_medicalHistory = database.update(DBHelper.TABLE_NAME_MEDICAL_HISTORY, cvDietValue, DBHelper.MEDICAL_HISTORY_COL_PERSON_ID + "= '" + OldpersonId+"'", null);
        long updated_medicine = database.update(DBHelper.TABLE_NAME_MEDICINE, cvDietValue, DBHelper.MEDICINE_COL_PERSON_ID + "= '" + OldpersonId + "'", null);
        long updated_Appointment = database.update(DBHelper.TABLE_NAME_APPOINTMENT, cvDietValue, DBHelper.APPOINTMENT_COL_PERSON_ID + "= '" + OldpersonId+"'", null);

        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }



//-----------------------------------------*Diet*-------------------------------------------------//

    public boolean insertDiet (DietModel dietModel)
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.DIET_COL_DIET_TYPE, dietModel.getDiteType());
        cvDietValue.put(DBHelper.DIET_COL_DIET_MENU, dietModel.getDietMenu());
        cvDietValue.put(DBHelper.DIET_COL_DIET_DATE, dietModel.getDietDate());
        cvDietValue.put(DBHelper.DIET_COL_DIET_TIME, dietModel.getDietTime());
        cvDietValue.put(DBHelper.DIET_COL_DIET_REMINDER_STATE, dietModel.getReminderState());
        cvDietValue.put(DBHelper.DIET_COL_DIET_ALARM_STATE, dietModel.getAlarmState());
        cvDietValue.put(DBHelper.DIET_COL_DIET_PERSON_ID, dietModel.getPersonId());
        cvDietValue.put(DBHelper.COL_FLAG, dietModel.getFlag());
        cvDietValue.put(DBHelper.COL_ALARM, dietModel.getAlarmCode());
        cvDietValue.put(DBHelper.COL_NOTIFICATION, dietModel.getNotificationCode());

        long inserted=database.insert(DBHelper.TABLE_NAME_DIET, null, cvDietValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }



    public boolean updateDiet (int dietId, DietModel dietModel )
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.DIET_COL_DIET_TYPE, dietModel.getDiteType());
        cvDietValue.put(DBHelper.DIET_COL_DIET_MENU, dietModel.getDietMenu());
        cvDietValue.put(DBHelper.DIET_COL_DIET_DATE, dietModel.getDietDate());
        cvDietValue.put(DBHelper.DIET_COL_DIET_TIME, dietModel.getDietTime());
        cvDietValue.put(DBHelper.DIET_COL_DIET_REMINDER_STATE, dietModel.getReminderState());
        cvDietValue.put(DBHelper.DIET_COL_DIET_ALARM_STATE, dietModel.getAlarmState());
        cvDietValue.put(DBHelper.DIET_COL_DIET_PERSON_ID, dietModel.getPersonId());
        cvDietValue.put(DBHelper.COL_FLAG, dietModel.getFlag());
        cvDietValue.put(DBHelper.COL_ALARM, dietModel.getAlarmCode());
        cvDietValue.put(DBHelper.COL_NOTIFICATION, dietModel.getNotificationCode());

       // long inserted=database.insert(DBHelper.TABLE_NAME_DIET,null,cvDietValue);

        long updated = database.update(DBHelper.TABLE_NAME_DIET, cvDietValue, DBHelper.DIET_COL_ID + "= " + dietId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteDiet (int dietId, String flag )
    {
        this.dbOpen();

        ContentValues cvDietValue=new ContentValues();
        cvDietValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_DIET, cvDietValue, DBHelper.DIET_COL_ID + "= " + dietId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<DietModel> getDietModelByPersonIDandDietDate(String profileID, String dietDatePra, String dietDateOperator) {

        ArrayList<DietModel> dietModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DIET + " WHERE " +
                DBHelper.DIET_COL_DIET_PERSON_ID + "='" + profileID + "' AND dietDate "
                + dietDateOperator + "'" +dietDatePra + "'  AND "+
        DBHelper.COL_FLAG + "='A'" + " order by dietDate desc";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int dietID = cursor.getInt(cursor.getColumnIndex(DBHelper.DIET_COL_ID));
                String dietType = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_TYPE));
                String dietMenu = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_MENU));
                String dietDate = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_DATE));
                String dietTime = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_TIME));
                String dietReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_REMINDER_STATE));
                String dietAlarmState = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_ALARM_STATE));
                String dietPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_PERSON_ID));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));
                DietModel dietModel = new DietModel(dietID,dietType,dietMenu,dietDate,dietTime,dietReminderState,dietAlarmState,dietPersonID,flag,alarmCode,notificationCode);
                dietModels.add(dietModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return dietModels;
    }


    public ArrayList<DietModel> getDietModelByDietID(int DietID) {

        ArrayList<DietModel> dietModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DIET + " WHERE " +
                DBHelper.DIET_COL_ID + " = " + DietID + " AND "+
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int dietID = cursor.getInt(cursor.getColumnIndex(DBHelper.DIET_COL_ID));
                String dietType = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_TYPE));
                String dietMenu = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_MENU));
                String dietDate = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_DATE));
                String dietTime = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_TIME));
                String dietReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_REMINDER_STATE));
                String dietAlarmState = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_ALARM_STATE));
                String dietPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.DIET_COL_DIET_PERSON_ID));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                DietModel dietModel = new DietModel(dietID,dietType,dietMenu,dietDate,dietTime,dietReminderState,dietAlarmState,dietPersonID,flag,alarmCode,notificationCode);
                dietModels.add(dietModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return dietModels;
    }



//-----------------------------------------*Doctor*-----------------------------------------------//

    public boolean insertDoctor (DoctorModel doctorModel)
    {
        this.dbOpen();

        ContentValues cvDoctorValue=new ContentValues();
        cvDoctorValue.put(DBHelper.DOCTOR_COL_NAME, doctorModel.getDocName());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_SPECIALIST, doctorModel.getDocSpecialist());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_ADDRESS, doctorModel.getDocAddress());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_EMAIL, doctorModel.getDocEmail());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_CONTACT_NO, doctorModel.getDocContactNo());
        cvDoctorValue.put(DBHelper.COL_FLAG, doctorModel.getFlag());

        long inserted=database.insert(DBHelper.TABLE_NAME_DOCTOR, null, cvDoctorValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateDoctor (int doctorId,DoctorModel doctorModel)
    {
        this.dbOpen();

        ContentValues cvDoctorValue=new ContentValues();
        cvDoctorValue.put(DBHelper.DOCTOR_COL_NAME, doctorModel.getDocName());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_SPECIALIST, doctorModel.getDocSpecialist());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_ADDRESS, doctorModel.getDocAddress());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_EMAIL, doctorModel.getDocEmail());
        cvDoctorValue.put(DBHelper.DOCTOR_COL_CONTACT_NO, doctorModel.getDocContactNo());
        cvDoctorValue.put(DBHelper.COL_FLAG, doctorModel.getFlag());

       // long inserted=database.insert(DBHelper.TABLE_NAME_DOCTOR,null,cvDoctorValue);

        long updated = database.update(DBHelper.TABLE_NAME_DOCTOR, cvDoctorValue, DBHelper.DOCTOR_COL_ID + "= " + doctorId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteDoctor (int doctorId, String flag )
    {
        this.dbOpen();

        ContentValues cvDoctorValue=new ContentValues();
        cvDoctorValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_DOCTOR, cvDoctorValue, DBHelper.DOCTOR_COL_ID + "= " + doctorId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<DoctorModel> getAllDoctorModel(){

        ArrayList<DoctorModel> doctorModels=new ArrayList<>();
        this.dbOpen();


        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DOCTOR + " WHERE " +
                DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        //Cursor cursor=database.query(DBHelper.TABLE_NAME_DOCTOR, null, null, null, null, null, null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int doctorID=cursor.getInt(cursor.getColumnIndex(DBHelper.DOCTOR_COL_ID));
                String doctorName=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_NAME));
                String specialist =cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_SPECIALIST));
                String doctorAddress=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_ADDRESS));
                String doctorEmail=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_EMAIL));
                String doctorContactNo=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_CONTACT_NO));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                DoctorModel doctorModel = new DoctorModel(doctorID,doctorName,specialist,doctorAddress,doctorEmail,doctorContactNo,flag);
                doctorModels.add(doctorModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return doctorModels;
    }


    public ArrayList<DoctorModel> getDoctorModelByDoctorID(int doctorID) {

        ArrayList<DoctorModel> doctorModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DOCTOR + " WHERE " +
                DBHelper.DOCTOR_COL_ID + " = " + doctorID ;

        /*String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DOCTOR + " WHERE " +
                DBHelper.DOCTOR_COL_ID + " = '" + doctorID + "' AND "+DBHelper.COL_FLAG + "='A'" ;*/
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int docID=cursor.getInt(cursor.getColumnIndex(DBHelper.DOCTOR_COL_ID));
                String doctorName=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_NAME));
                String specialist =cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_SPECIALIST));
                String doctorAddress=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_ADDRESS));
                String doctorEmail=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_EMAIL));
                String doctorContactNo=cursor.getString(cursor.getColumnIndex(DBHelper.DOCTOR_COL_CONTACT_NO));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                DoctorModel doctorModel = new DoctorModel(docID,doctorName,specialist,doctorAddress,doctorEmail,doctorContactNo,flag);
                doctorModels.add(doctorModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return doctorModels;
    }


//----------------------------------------*Appointment*-------------------------------------------//


    public boolean insertAppointment (AppointmentModel appointmentModel)
    {
        this.dbOpen();

        ContentValues cvAppointmentValue=new ContentValues();
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_APPT_DATE, appointmentModel.getApptDate());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_APPT_TIME, appointmentModel.getApptTime());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_REMARKS, appointmentModel.getRemarks());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_DOCTOR_ID, appointmentModel.getDoctorId());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_PERSON_ID, appointmentModel.getPersonId());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_REMINDER_STATE, appointmentModel.getReminderState());
        cvAppointmentValue.put(DBHelper.COL_FLAG, appointmentModel.getFlag());
        cvAppointmentValue.put(DBHelper.COL_ALARM, appointmentModel.getAlarmCode());
        cvAppointmentValue.put(DBHelper.COL_NOTIFICATION, appointmentModel.getNotificationCode());

        long inserted=database.insert(DBHelper.TABLE_NAME_APPOINTMENT, null, cvAppointmentValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }


    public boolean updateAppointment (int appointmentId,AppointmentModel appointmentModel)
    {
        this.dbOpen();

        ContentValues cvAppointmentValue=new ContentValues();
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_APPT_DATE, appointmentModel.getApptDate());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_APPT_TIME, appointmentModel.getApptTime());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_REMARKS, appointmentModel.getRemarks());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_DOCTOR_ID, appointmentModel.getDoctorId());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_PERSON_ID, appointmentModel.getPersonId());
        cvAppointmentValue.put(DBHelper.APPOINTMENT_COL_REMINDER_STATE, appointmentModel.getReminderState());
        cvAppointmentValue.put(DBHelper.COL_FLAG, appointmentModel.getFlag());
        cvAppointmentValue.put(DBHelper.COL_ALARM, appointmentModel.getAlarmCode());
        cvAppointmentValue.put(DBHelper.COL_NOTIFICATION, appointmentModel.getNotificationCode());

        long updated = database.update(DBHelper.TABLE_NAME_APPOINTMENT, cvAppointmentValue, DBHelper.APPOINTMENT_COL_ID + "= " + appointmentId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public boolean deleteAppointment (int appointmentId, String flag )
    {
        this.dbOpen();

        ContentValues cvAppointmentValue=new ContentValues();
        cvAppointmentValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_APPOINTMENT, cvAppointmentValue, DBHelper.APPOINTMENT_COL_ID + "= " + appointmentId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

        public ArrayList<AppointmentModel> getAppointmentModelByPersonIDandDate(String profileID,String DatePra,String DateOperator)
    {

        ArrayList<AppointmentModel> appointmentModels = new ArrayList<>();

        this.dbOpen();
        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_APPOINTMENT+ " WHERE " +
                DBHelper.APPOINTMENT_COL_PERSON_ID + "='" + profileID + "' AND apptDate "
                + DateOperator + "'" +DatePra + "' AND "+DBHelper.COL_FLAG + "='A'"+" order by apptDate desc";

        /*String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_APPOINTMENT+ " WHERE " +
                DBHelper.APPOINTMENT_COL_PERSON_ID + " LIKE '%" + profileID + "%'";*/

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int apptID = cursor.getInt(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_ID));
                String apptDate = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_APPT_DATE));
                String apptTime = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_APPT_TIME));
                String apptRemarks = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_REMARKS));
                String apptDoctorID = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_DOCTOR_ID));
                String apptPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_PERSON_ID));
                String aaptReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_REMINDER_STATE));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                AppointmentModel appointmentModel= new AppointmentModel(apptID,apptDate,apptTime,apptRemarks,apptDoctorID,apptPersonID,aaptReminderState,flag,alarmCode,notificationCode);
                appointmentModels.add(appointmentModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return appointmentModels;
    }

    public ArrayList<AppointmentModel> getAppointmentModelByAppointmentId(int appointmentId)
    {

        ArrayList<AppointmentModel> appointmentModels = new ArrayList<>();

        this.dbOpen();
        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_APPOINTMENT + " WHERE " +
                DBHelper.APPOINTMENT_COL_ID + "=" + appointmentId + " AND "+DBHelper.COL_FLAG + "='A'";


        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int apptID = cursor.getInt(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_ID));
                String apptDate = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_APPT_DATE));
                String apptTime = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_APPT_TIME));
                String apptRemarks = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_REMARKS));
                String apptDoctorID = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_DOCTOR_ID));
                String apptPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_PERSON_ID));
                String aaptReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.APPOINTMENT_COL_REMINDER_STATE));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                AppointmentModel appointmentModel= new AppointmentModel(apptID,apptDate,apptTime,apptRemarks,apptDoctorID,apptPersonID,aaptReminderState,flag,alarmCode,notificationCode);
                appointmentModels.add(appointmentModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return appointmentModels;
    }


//-------------------------------------*MedicalHistory*-------------------------------------------//
    public boolean insertMedicalHistory (MedicalHistoryModel medicalHistoryModel)
    {
        this.dbOpen();

        ContentValues cvMedicalHistoryValue=new ContentValues();
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DOC_ID, medicalHistoryModel.getDoctorId());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DATE, medicalHistoryModel.getMhDate());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_PERSON_ID, medicalHistoryModel.getPersonId());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_PRESCRIPTION_PATH, medicalHistoryModel.getPrescriptionPath());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DETAILS, medicalHistoryModel.getDetails());
        cvMedicalHistoryValue.put(DBHelper.COL_FLAG, medicalHistoryModel.getFlag());

        long inserted=database.insert(DBHelper.TABLE_NAME_MEDICAL_HISTORY, null, cvMedicalHistoryValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateMedicalHistory (int mhId,MedicalHistoryModel medicalHistoryModel)
    {
        this.dbOpen();

        ContentValues cvMedicalHistoryValue=new ContentValues();
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DOC_ID, medicalHistoryModel.getDoctorId());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DATE, medicalHistoryModel.getMhDate());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_PERSON_ID, medicalHistoryModel.getPersonId());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_PRESCRIPTION_PATH, medicalHistoryModel.getPrescriptionPath());
        cvMedicalHistoryValue.put(DBHelper.MEDICAL_HISTORY_COL_DETAILS, medicalHistoryModel.getDetails());
        cvMedicalHistoryValue.put(DBHelper.COL_FLAG, medicalHistoryModel.getFlag());

        //long inserted=database.insert(DBHelper.TABLE_NAME_MEDICAL_HISTORY,null,cvMedicalHistoryValue);

        long updated = database.update(DBHelper.TABLE_NAME_MEDICAL_HISTORY, cvMedicalHistoryValue, DBHelper.MEDICAL_HISTORY_COL_ID + "= " + mhId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteMedicalHistory (int mhId, String flag )
    {
        this.dbOpen();

        ContentValues cvMedicalHistoryValue=new ContentValues();
        cvMedicalHistoryValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_MEDICAL_HISTORY, cvMedicalHistoryValue, DBHelper.MEDICAL_HISTORY_COL_ID + "= " + mhId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<MedicalHistoryModel> getMedicalHistoryModelByID(int mhId) {

        ArrayList<MedicalHistoryModel> medicalHistoryModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICAL_HISTORY+ " WHERE " +
                DBHelper.MEDICAL_HISTORY_COL_ID + " = " + mhId + " AND "+DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int mhID = cursor.getInt(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_ID));
                String mhDocID = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DOC_ID));
                String mhDate = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DATE));
                String mhPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_PERSON_ID));
                String mhPrescriptionPath = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_PRESCRIPTION_PATH));
                String mhDetails = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DETAILS));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                MedicalHistoryModel medicalHistoryModel= new MedicalHistoryModel(mhID,mhDocID,mhDate,mhPersonID,mhPrescriptionPath,mhDetails,flag);
                medicalHistoryModels.add(medicalHistoryModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return medicalHistoryModels;
    }

    public ArrayList<MedicalHistoryModel> getMedicalHistoryModelByPersonID(String profileID) {

        ArrayList<MedicalHistoryModel> medicalHistoryModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICAL_HISTORY+ " WHERE " +
                DBHelper.MEDICAL_HISTORY_COL_PERSON_ID + " = '" + profileID + "' AND "+DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int mhID = cursor.getInt(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_ID));
                String mhDocID = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DOC_ID));
                String mhDate = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DATE));
                String mhPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_PERSON_ID));
                String mhPrescriptionPath = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_PRESCRIPTION_PATH));
                String mhDetails = cursor.getString(cursor.getColumnIndex(DBHelper.MEDICAL_HISTORY_COL_DETAILS));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                MedicalHistoryModel medicalHistoryModel= new MedicalHistoryModel(mhID,mhDocID,mhDate,mhPersonID,mhPrescriptionPath,mhDetails,flag);
                medicalHistoryModels.add(medicalHistoryModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return medicalHistoryModels;
    }

//----------------------------------------*Medicine*----------------------------------------------//

    public boolean insertMedicine (MedicineModel medicineModel)
    {
        this.dbOpen();

        ContentValues cvMedicineValue=new ContentValues();
        cvMedicineValue.put(DBHelper.MEDICINE_COL_MEDICINE_NAME, medicineModel.getMedicineName());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_MEDICINE_DOSE, medicineModel.getMedicineDose());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_FROM_DATE, medicineModel.getmFromDate());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_TO_DATE, medicineModel.getmToDate());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_DOC_ID, medicineModel.getDoctorId());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_PERSON_ID, medicineModel.getPersonId());
        cvMedicineValue.put(DBHelper.MEDICINE_ALARM_STATE, medicineModel.getmAlarmState());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_MORNING_STATE, medicineModel.getmMorning());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_AFTERNOON_STATE, medicineModel.getmAfterNoon());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_EVENING_STATE, medicineModel.getmEvening());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_NIGHT_STATE, medicineModel.getmNight());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_REMARKS, medicineModel.getmRemarks());
        cvMedicineValue.put(DBHelper.COL_FLAG, medicineModel.getFlag());
        cvMedicineValue.put(DBHelper.COL_ALARM, medicineModel.getAlarmCode());
        cvMedicineValue.put(DBHelper.COL_NOTIFICATION, medicineModel.getNotificationCode());

        long inserted=database.insert(DBHelper.TABLE_NAME_MEDICINE, null, cvMedicineValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }


    public boolean updateMedicine (int mId,MedicineModel medicineModel)
    {
        this.dbOpen();

        ContentValues cvMedicineValue=new ContentValues();
        cvMedicineValue.put(DBHelper.MEDICINE_COL_MEDICINE_NAME, medicineModel.getMedicineName());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_MEDICINE_DOSE, medicineModel.getMedicineDose());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_FROM_DATE, medicineModel.getmFromDate());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_TO_DATE, medicineModel.getmToDate());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_DOC_ID, medicineModel.getDoctorId());
        cvMedicineValue.put(DBHelper.MEDICINE_COL_PERSON_ID, medicineModel.getPersonId());
        cvMedicineValue.put(DBHelper.MEDICINE_ALARM_STATE, medicineModel.getmAlarmState());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_MORNING_STATE, medicineModel.getmMorning());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_AFTERNOON_STATE, medicineModel.getmAfterNoon());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_EVENING_STATE, medicineModel.getmEvening());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_NIGHT_STATE, medicineModel.getmNight());
        cvMedicineValue.put(DBHelper.MEDICINE_TAKE_REMARKS, medicineModel.getmRemarks());
        cvMedicineValue.put(DBHelper.COL_FLAG, medicineModel.getFlag());
        cvMedicineValue.put(DBHelper.COL_ALARM, medicineModel.getAlarmCode());
        cvMedicineValue.put(DBHelper.COL_NOTIFICATION, medicineModel.getNotificationCode());

       // long inserted=database.insert(DBHelper.TABLE_NAME_MEDICINE,null,cvMedicineValue);

        long updated = database.update(DBHelper.TABLE_NAME_MEDICINE, cvMedicineValue, DBHelper.MEDICINE_COL_ID + "= " + mId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean deleteMedicine (int mId, String flag )
    {
        this.dbOpen();

        ContentValues cvMedicineValue=new ContentValues();
        cvMedicineValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_MEDICINE, cvMedicineValue, DBHelper.MEDICINE_COL_ID + "= " + mId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<MedicineModel> getMedicineModelByMedicineID(int MedicineID)
    {

        ArrayList<MedicineModel> medicineModels = new ArrayList<>();


        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICINE+ " WHERE " +
                DBHelper.MEDICINE_COL_ID + "=" + MedicineID + " AND "+ DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int mID=cursor.getInt(cursor.getColumnIndex(DBHelper.MEDICINE_COL_ID));
                String medicineName=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINE_NAME));
                String medicineDose =cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINE_DOSE));
                String fromDate=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_FROM_DATE));
                String toDate=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_TO_DATE));
                String mDocID=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_DOC_ID));
                String mPersonID=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_PERSON_ID));
                String alarmState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_ALARM_STATE));
                String morningState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_MORNING_STATE));
                String afternoonState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_AFTERNOON_STATE));
                String eveningState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_EVENING_STATE));
                String nightState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_NIGHT_STATE));
                String mRemarks=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_REMARKS));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                MedicineModel medicineModel = new MedicineModel(mID,medicineName,medicineDose,fromDate,toDate,mDocID,mPersonID,
                        alarmState,morningState,afternoonState,eveningState,nightState,mRemarks,flag,alarmCode,notificationCode);
                medicineModels.add(medicineModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return medicineModels;
    }

    public ArrayList<MedicineModel> getMedicineModelByPersonID(String profileID,String DatePra,String DateOperator)
    {

        ArrayList<MedicineModel> medicineModels = new ArrayList<>();


        this.dbOpen();
        //select * from medicine where  mToDate>= '2016-02-01'

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICINE+ " WHERE " +
                DBHelper.MEDICINE_COL_PERSON_ID + "='" + profileID + "' AND "+  DBHelper.MEDICINE_COL_TO_DATE
                + DateOperator + "'" +DatePra + "' AND "+ DBHelper.COL_FLAG + "='A'" +" order by "+  DBHelper.MEDICINE_COL_TO_DATE +" ASC";

        /*String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICINE + " WHERE " +
                DBHelper.MEDICINE_COL_PERSON_ID + " LIKE '%" + profileID + "%'";*/

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int mID=cursor.getInt(cursor.getColumnIndex(DBHelper.MEDICINE_COL_ID));
                String medicineName=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINE_NAME));
                String medicineDose =cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_MEDICINE_DOSE));
                String fromDate=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_FROM_DATE));
                String toDate=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_TO_DATE));
                String mDocID=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_DOC_ID));
                String mPersonID=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_COL_PERSON_ID));
                String alarmState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_ALARM_STATE));
                String morningState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_MORNING_STATE));
                String afternoonState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_AFTERNOON_STATE));
                String eveningState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_EVENING_STATE));
                String nightState=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_NIGHT_STATE));
                String mRemarks=cursor.getString(cursor.getColumnIndex(DBHelper.MEDICINE_TAKE_REMARKS));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                MedicineModel medicineModel = new MedicineModel(mID,medicineName,medicineDose,fromDate,toDate,mDocID,mPersonID,
                        alarmState,morningState,afternoonState,eveningState,nightState,mRemarks,flag,alarmCode,notificationCode);
                medicineModels.add(medicineModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return medicineModels;
    }

//-------------------------------------*Vaccine*--------------------------------------------------//


    public boolean insertVaccine (VaccineModel vaccineModel)
    {
        this.dbOpen();

        ContentValues cvVaccineValue=new ContentValues();
        cvVaccineValue.put(DBHelper.VACCINATION_COL_NAME, vaccineModel.getVaccineName());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_DATE, vaccineModel.getVaccineDate());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_TIME, vaccineModel.getVaccineTime());
        cvVaccineValue.put(DBHelper.VACCINATION_REMINDER_STATE, vaccineModel.getvReminderState());
        cvVaccineValue.put(DBHelper.VACCINATION_DETAILS, vaccineModel.getvDetails());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_PERSON_ID, vaccineModel.getPersonId());
        cvVaccineValue.put(DBHelper.COL_FLAG, vaccineModel.getFlag());
        cvVaccineValue.put(DBHelper.COL_ALARM, vaccineModel.getAlarmCode());
        cvVaccineValue.put(DBHelper.COL_NOTIFICATION, vaccineModel.getNotificationCode());

        long inserted=database.insert(DBHelper.TABLE_NAME_VACCINATION, null, cvVaccineValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateVaccine (int vacId,VaccineModel vaccineModel)
    {
        this.dbOpen();

        ContentValues cvVaccineValue=new ContentValues();
        cvVaccineValue.put(DBHelper.VACCINATION_COL_NAME, vaccineModel.getVaccineName());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_DATE, vaccineModel.getVaccineDate());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_TIME, vaccineModel.getVaccineTime());
        cvVaccineValue.put(DBHelper.VACCINATION_REMINDER_STATE, vaccineModel.getvReminderState());
        cvVaccineValue.put(DBHelper.VACCINATION_DETAILS, vaccineModel.getvDetails());
        cvVaccineValue.put(DBHelper.VACCINATION_COL_PERSON_ID, vaccineModel.getPersonId());
        cvVaccineValue.put(DBHelper.COL_FLAG, vaccineModel.getFlag());
        cvVaccineValue.put(DBHelper.COL_ALARM, vaccineModel.getAlarmCode());
        cvVaccineValue.put(DBHelper.COL_NOTIFICATION, vaccineModel.getNotificationCode());

        //long inserted=database.insert(DBHelper.TABLE_NAME_VACCINATION,null,cvVaccineValue);

        long updated = database.update(DBHelper.TABLE_NAME_VACCINATION, cvVaccineValue, DBHelper.VACCINATION_COL_ID + "= " + vacId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteVaccine (int vacId, String flag )
    {
        this.dbOpen();

        ContentValues cvVaccineValue=new ContentValues();
        cvVaccineValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_VACCINATION, cvVaccineValue, DBHelper.VACCINATION_COL_ID + "= " + vacId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<VaccineModel> getVaccineModelByVaccineId(int vaccineId)
    {

        ArrayList<VaccineModel> vaccineModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_VACCINATION+ " WHERE " +
                DBHelper.VACCINATION_COL_ID + "=" + vaccineId + " AND "+ DBHelper.COL_FLAG + "='A'";
         /* String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_MEDICINE+ " WHERE " +
                DBHelper.MEDICINE_COL_ID + "=" + MedicineID + " AND "+ DBHelper.COL_FLAG + "='A'";*/

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int vacID = cursor.getInt(cursor.getColumnIndex(DBHelper.VACCINATION_COL_ID));
                String vacName = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_NAME));
                String vacDate = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_DATE));
                String vacTime = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_TIME));
                String vacReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_REMINDER_STATE));
                String vacDetails = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_DETAILS));
                String vacPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_PERSON_ID));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                VaccineModel vaccineModel = new VaccineModel(vacID,vacName,vacDate,vacTime,vacReminderState,vacDetails,vacPersonID,flag,alarmCode,notificationCode);
                vaccineModels.add(vaccineModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return vaccineModels;
    }

    public ArrayList<VaccineModel> getVaccineModelByPersonIDCompletedUpComing(String profileID, String vaccineDatePra, String vaccineDateOperator)
    {

        ArrayList<VaccineModel> vaccineModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_VACCINATION+ " WHERE " +
                DBHelper.VACCINATION_COL_PERSON_ID + "='" + profileID + "' AND vaccineDate "
                + vaccineDateOperator + "'" +vaccineDatePra + "' AND "+ DBHelper.COL_FLAG + "='A'" +" order by vaccineDate desc";

            Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int vacID = cursor.getInt(cursor.getColumnIndex(DBHelper.VACCINATION_COL_ID));
                String vacName = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_NAME));
                String vacDate = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_DATE));
                String vacTime = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_TIME));
                String vacReminderState = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_REMINDER_STATE));
                String vacDetails = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_DETAILS));
                String vacPersonID = cursor.getString(cursor.getColumnIndex(DBHelper.VACCINATION_COL_PERSON_ID));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String alarmCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_ALARM));
                String notificationCode=cursor.getString(cursor.getColumnIndex(DBHelper.COL_NOTIFICATION));

                VaccineModel vaccineModel = new VaccineModel(vacID,vacName,vacDate,vacTime,vacReminderState,vacDetails,vacPersonID,flag,alarmCode,notificationCode);
                vaccineModels.add(vaccineModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return vaccineModels;
    }
    
//------------------------------------*Hospital*--------------------------------------------------//


    public boolean insertHospital (HospitalModel hospitalModel)
    {
        this.dbOpen();

        ContentValues cvHospitalValue=new ContentValues();
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_NAME, hospitalModel.getHospitalName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_ROAD_NAME, hospitalModel.getRoadName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_CITY_NAME, hospitalModel.getCityName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_COUNTRY_NAME, hospitalModel.getCountryName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_CONTACT_NO, hospitalModel.getContactNumber());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_EMAIL, hospitalModel.getEmailAddress());
        cvHospitalValue.put(DBHelper.COL_FLAG, hospitalModel.getFlag());

        long inserted=database.insert(DBHelper.TABLE_NAME_HOSPITAL,null,cvHospitalValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateHospital (int hid,HospitalModel hospitalModel)
    {
        this.dbOpen();

        ContentValues cvHospitalValue=new ContentValues();
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_NAME, hospitalModel.getHospitalName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_ROAD_NAME, hospitalModel.getRoadName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_CITY_NAME, hospitalModel.getCityName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_COUNTRY_NAME, hospitalModel.getCountryName());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_CONTACT_NO, hospitalModel.getContactNumber());
        cvHospitalValue.put(DBHelper.HOSPITAL_COL_EMAIL, hospitalModel.getEmailAddress());
        cvHospitalValue.put(DBHelper.COL_FLAG, hospitalModel.getFlag());

        // long inserted=database.insert(DBHelper.TABLE_NAME_HOSPITAL,null,cvHospitalValue);

        long updated = database.update(DBHelper.TABLE_NAME_HOSPITAL, cvHospitalValue, DBHelper.HOSPITAL_COL_ID + "= " + hid, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }



    public boolean deleteHospital (int hid, String flag )
    {
        this.dbOpen();

        ContentValues cvHospitalValue=new ContentValues();
        cvHospitalValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_HOSPITAL, cvHospitalValue, DBHelper.HOSPITAL_COL_ID + "= " + hid, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }



    public ArrayList<HospitalModel> getAllHospitalModelbyHospitalId(int hId)
    {

        ArrayList<HospitalModel> hospitalModels=new ArrayList<>();
        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_HOSPITAL+ " WHERE " +
                DBHelper.HOSPITAL_COL_ID + "=" + hId + " AND "+ DBHelper.COL_FLAG + "='A'";

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int hospitalID=cursor.getInt(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_ID));
                String hospitalName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_NAME));
                String roadName =cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_ROAD_NAME));
                String cityName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_CITY_NAME));
                String countryName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_COUNTRY_NAME));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_CONTACT_NO));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_EMAIL));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                HospitalModel hospitalModel = new HospitalModel(hospitalID,hospitalName,roadName,cityName,countryName,contactNo,email,flag);
                hospitalModels.add(hospitalModel);

                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return hospitalModels;
    }


    public ArrayList<HospitalModel> getAllHospitalModel()
    {

        ArrayList<HospitalModel> hospitalModels=new ArrayList<>();
        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_HOSPITAL + " WHERE " +
                DBHelper.COL_FLAG + "='A'";
        Cursor cursor = database.rawQuery(selectQuery, null);

        /*Cursor cursor=database.query(DBHelper.TABLE_NAME_HOSPITAL, null, null, null, null, null, null);*/

        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){

                int hospitalID=cursor.getInt(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_ID));
                String hospitalName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_NAME));
                String roadName =cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_ROAD_NAME));
                String cityName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_CITY_NAME));
                String countryName=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_COUNTRY_NAME));
                String contactNo=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_CONTACT_NO));
                String email=cursor.getString(cursor.getColumnIndex(DBHelper.HOSPITAL_COL_EMAIL));
                String flag=cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                HospitalModel hospitalModel = new HospitalModel(hospitalID,hospitalName,roadName,cityName,countryName,contactNo,email,flag);
                hospitalModels.add(hospitalModel);

                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return hospitalModels;
    }
//------------------------------------------------------------------------------------------------//


    //------------------------------------*BloodPressureModel*--------------------------------------------------//


    public boolean insertBloodPressure (BloodPressureModel bloodPressureModel)
    {
        this.dbOpen();

        ContentValues cvBloodPressureValue=new ContentValues();
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_DATE, bloodPressureModel.getBpDate());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_TIME, bloodPressureModel.getBpTime());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_SBP, bloodPressureModel.getBpSBP());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_DBP, bloodPressureModel.getBpDBP());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_BPM, bloodPressureModel.getBpBPM());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_NOTE, bloodPressureModel.getBpNote());
        cvBloodPressureValue.put(DBHelper.COL_FLAG, bloodPressureModel.getFlag());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_PERSON_ID, bloodPressureModel.getPersonId());

        long inserted=database.insert(DBHelper.TABLE_NAME_BLOOD_PRESSURE, null, cvBloodPressureValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateBloodPressure (int bpId,BloodPressureModel bloodPressureModel)
    {
        this.dbOpen();

        ContentValues cvBloodPressureValue=new ContentValues();
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_DATE, bloodPressureModel.getBpDate());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_TIME, bloodPressureModel.getBpTime());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_SBP, bloodPressureModel.getBpSBP());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_DBP, bloodPressureModel.getBpDBP());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_BPM, bloodPressureModel.getBpBPM());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_NOTE, bloodPressureModel.getBpNote());
        cvBloodPressureValue.put(DBHelper.COL_FLAG, bloodPressureModel.getFlag());
        cvBloodPressureValue.put(DBHelper.BLOOD_PRESSURE_COL_PERSON_ID, bloodPressureModel.getPersonId());

        // long inserted=database.insert(DBHelper.TABLE_NAME_HOSPITAL,null,cvHospitalValue);

        long updated = database.update(DBHelper.TABLE_NAME_BLOOD_PRESSURE, cvBloodPressureValue, DBHelper.BLOOD_PRESSURE_COL_ID + "= " + bpId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteBloodPressure (int bpId, String flag )
    {
        this.dbOpen();

        ContentValues cvBloodPressureValue=new ContentValues();
        cvBloodPressureValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_BLOOD_PRESSURE, cvBloodPressureValue, DBHelper.BLOOD_PRESSURE_COL_ID + "= " + bpId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<BloodPressureModel> getBloodPressureByProfileId(String profileID) {

        ArrayList<BloodPressureModel> bloodPressureModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_BLOOD_PRESSURE + " WHERE " +
                DBHelper.BLOOD_PRESSURE_COL_PERSON_ID + "='" + profileID + "'  AND "+
                DBHelper.COL_FLAG + "='A'" + " order by "+ DBHelper.BLOOD_PRESSURE_COL_DATE + ", "+ DBHelper.BLOOD_PRESSURE_COL_TIME + " desc";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int bpId = cursor.getInt(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_ID));
                String bpDate = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_DATE));
                String bpTime = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_TIME));
                String bpSBP = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_SBP));
                String bpDBP = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_DBP));
                String bpBPM = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_BPM));
                String bpNote = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_NOTE));
                String flag = cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_PERSON_ID));

                BloodPressureModel pressureModel = new BloodPressureModel(bpId,bpDate,bpTime,bpSBP,bpDBP,bpBPM,bpNote,flag,personId);
                bloodPressureModels.add(pressureModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return bloodPressureModels;
    }

    public ArrayList<BloodPressureModel> getBloodPressureBybpId(int bpId1) {

        ArrayList<BloodPressureModel> bloodPressureModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_BLOOD_PRESSURE + " WHERE " +
                DBHelper.BLOOD_PRESSURE_COL_ID + " = " + bpId1 ;

        /*String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DOCTOR + " WHERE " +
                DBHelper.DOCTOR_COL_ID + " = '" + doctorID + "' AND "+DBHelper.COL_FLAG + "='A'" ;*/
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int bpId = cursor.getInt(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_ID));
                String bpDate = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_DATE));
                String bpTime = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_TIME));
                String bpSBP = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_SBP));
                String bpDBP = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_DBP));
                String bpBPM = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_BPM));
                String bpNote = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_NOTE));
                String flag = cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_PRESSURE_COL_PERSON_ID));

                BloodPressureModel pressureModel = new BloodPressureModel(bpId,bpDate,bpTime,bpSBP,bpDBP,bpBPM,bpNote,flag,personId);
                bloodPressureModels.add(pressureModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return bloodPressureModels;
    }


    public ArrayList<String> queryXData(String profileID){
        ArrayList<String> xNewData = new ArrayList<String>();

        this.dbOpen();

        String selectQuery = "SELECT  bpDate FROM " + DBHelper.TABLE_NAME_BLOOD_PRESSURE + " WHERE " +
                DBHelper.BLOOD_PRESSURE_COL_PERSON_ID + "='" + profileID + "'  AND "+
                DBHelper.COL_FLAG + "='A'" + " order by "+ DBHelper.BLOOD_PRESSURE_COL_DATE + ", "+ DBHelper.BLOOD_PRESSURE_COL_TIME + " desc";

        Cursor cursor = database.rawQuery(selectQuery, null);

        //String query = "SELECT " + DAILY_DATE + " FROM " + TABLE_DAILY_FRAG;
        //Cursor cursor = mSQLiteDatabase.rawQuery(query, null);
        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            xNewData.add(cursor.getString(cursor.getColumnIndex("bpDate")));
        }
        cursor.close();
        return xNewData;
    }

    public ArrayList<Float> queryYData(String profileID){
        ArrayList<Float> yNewData = new ArrayList<Float>();



        this.dbOpen();

        String selectQuery = "SELECT  bpSBP FROM " + DBHelper.TABLE_NAME_BLOOD_PRESSURE + " WHERE " +
                DBHelper.BLOOD_PRESSURE_COL_PERSON_ID + "='" + profileID + "'  AND "+
                DBHelper.COL_FLAG + "='A'" + " order by "+ DBHelper.BLOOD_PRESSURE_COL_DATE + ", "+ DBHelper.BLOOD_PRESSURE_COL_TIME + " desc";

        Cursor cursor = database.rawQuery(selectQuery, null);

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            yNewData.add(cursor.getFloat(cursor.getColumnIndex("bpSBP")));
        }
        cursor.close();
        return yNewData;
    }



//------------------------------------------------------------------------------------------------//


    //------------------------------------*Blood SUGAR*--------------------------------------------------//

    public boolean insertBloodSugar (BloodSugarModel bloodSugarModel)
    {
        this.dbOpen();

        ContentValues cvBloodSugarValue=new ContentValues();
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_DATE, bloodSugarModel.getBsDate());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_TIME, bloodSugarModel.getBsTime());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_LEVEL, bloodSugarModel.getBsLevel());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_NOTES, bloodSugarModel.getBsNotes());
        cvBloodSugarValue.put(DBHelper.COL_FLAG, bloodSugarModel.getFlag());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_PERSON_ID, bloodSugarModel.getPersonId());

        long inserted=database.insert(DBHelper.TABLE_NAME_BLOOD_SUGAR,null,cvBloodSugarValue);

        this.dbClose();

        if(inserted>0)
        {
            return  true;
        }
        else
        {
            return  false;
        }

    }

    public boolean updateBloodSugar (int bsId,BloodSugarModel bloodSugarModel)
    {
        this.dbOpen();

        ContentValues cvBloodSugarValue=new ContentValues();
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_DATE, bloodSugarModel.getBsDate());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_TIME, bloodSugarModel.getBsTime());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_LEVEL, bloodSugarModel.getBsLevel());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_NOTES, bloodSugarModel.getBsNotes());
        cvBloodSugarValue.put(DBHelper.COL_FLAG, bloodSugarModel.getFlag());
        cvBloodSugarValue.put(DBHelper.BLOOD_SUGAR_COL_PERSON_ID, bloodSugarModel.getPersonId());


        // long inserted=database.insert(DBHelper.TABLE_NAME_HOSPITAL,null,cvHospitalValue);

        long updated = database.update(DBHelper.TABLE_NAME_BLOOD_SUGAR, cvBloodSugarValue, DBHelper.BLOOD_SUGAR_COL_ID + "= " + bsId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deleteBloodSugar (int bsId, String flag )
    {
        this.dbOpen();

        ContentValues cvBloodSugarValue=new ContentValues();
        cvBloodSugarValue.put(DBHelper.COL_FLAG, flag);

        long updated = database.update(DBHelper.TABLE_NAME_BLOOD_SUGAR, cvBloodSugarValue, DBHelper.BLOOD_SUGAR_COL_ID + "= " + bsId, null);
        this.dbClose();

        if (updated > 0) {
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<BloodSugarModel> getBloodSugarByProfileId(String profileID) {

        ArrayList<BloodSugarModel> bloodSugarModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_BLOOD_SUGAR + " WHERE " +
                DBHelper.BLOOD_SUGAR_COL_PERSON_ID + "='" + profileID + "'  AND "+
                DBHelper.COL_FLAG + "='A'" + " order by "+ DBHelper.BLOOD_SUGAR_COL_DATE + ", "+ DBHelper.BLOOD_SUGAR_COL_TIME + " desc";

        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int bsId = cursor.getInt(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_ID));
                String bsDate = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_DATE));
                String bsTime = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_TIME));
                String bsLevel = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_LEVEL));
                String bsNote = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_NOTES));
                String flag = cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_PERSON_ID));

                BloodSugarModel bloodSugarModel = new BloodSugarModel(bsId,bsDate,bsTime,bsLevel,bsNote,flag,personId);
                bloodSugarModels.add(bloodSugarModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return bloodSugarModels;
    }

    public ArrayList<BloodSugarModel> getBloodSugarBybsId(int bsId1) {

        ArrayList<BloodSugarModel> bloodSugarModels = new ArrayList<>();

        this.dbOpen();

        String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_BLOOD_SUGAR + " WHERE " +
                DBHelper.BLOOD_SUGAR_COL_ID + " = " + bsId1 ;

        /*String selectQuery = "SELECT  * FROM " + DBHelper.TABLE_NAME_DOCTOR + " WHERE " +
                DBHelper.DOCTOR_COL_ID + " = '" + doctorID + "' AND "+DBHelper.COL_FLAG + "='A'" ;*/
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null && cursor.getCount()>0){

            cursor.moveToFirst();

            for (int i=0; i<cursor.getCount(); i++){
                int bsId = cursor.getInt(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_ID));
                String bsDate = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_DATE));
                String bsTime = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_TIME));
                String bsLevel = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_LEVEL));
                String bsNote = cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_NOTES));
                String flag = cursor.getString(cursor.getColumnIndex(DBHelper.COL_FLAG));
                String personId=cursor.getString(cursor.getColumnIndex(DBHelper.BLOOD_SUGAR_COL_PERSON_ID));

                BloodSugarModel bloodSugarModel = new BloodSugarModel(bsId,bsDate,bsTime,bsLevel,bsNote,flag,personId);
                bloodSugarModels.add(bloodSugarModel);
                cursor.moveToNext();
            }
        }
        database.close();
        this.dbClose();
        return bloodSugarModels;
    }



//------------------------------------------------------------------------------------------------//






}
