package dev.raghav.civilgate.Const_Files;

public class Tests_Name {

 public String test_name;
    public String test_start_date;
    public String test_end_date;
    public String subject_ids;
    public String level_id ;
    public String sub_level_cat_id ;
    public String QStatus ;
    public   float test_len;
    public String getSub_level_cat_id() {
        return sub_level_cat_id;
    }

    public void setSub_level_cat_id(String sub_level_cat_id) {
        this.sub_level_cat_id = sub_level_cat_id;
    }




    public String getTest_id() {
        return level_id;
    }

    public String getQStatus() {
        return QStatus;
    }

    public void setQStatus(String QStatus) {
        this.QStatus = QStatus;
    }



    public Tests_Name(String test_name, int test_len , String subject_ids , String level_id , String QStatus , String Sub_level_cat_id) {
        this.test_name = test_name;
        this.test_len = test_len;
        this.subject_ids = subject_ids;
        this.level_id = level_id;
        this.QStatus = QStatus;
        this.sub_level_cat_id = Sub_level_cat_id;

    }
    public void setTest_id(String test_id) {
        this.level_id = test_id;
    }
    public String getSubject_ids() {
        return subject_ids;
    }

    public void setSubject_ids(String subject_ids) {
        this.subject_ids = subject_ids;
    }

    public void setTest_len(float test_len) {
        this.test_len = test_len;
    }

    public float getTest_len() {
        return test_len;
    }

    public void setTest_len(int test_len) {
        this.test_len = test_len;
    }

    public String getTest_name() {
        return test_name;
    }

    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public String getTest_start_date() {
        return test_start_date;
    }

    public void setTest_start_date(String test_start_date) {
        this.test_start_date = test_start_date;
    }

    public String getTest_end_date() {
        return test_end_date;
    }

    public void setTest_end_date(String test_end_date) {
        this.test_end_date = test_end_date;
    }
}