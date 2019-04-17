package dev.raghav.civilgate.Const_Files;

import dev.raghav.civilgate.R;

public class Tests_Name {

 public String test_name , test_start_date,test_end_date,subject_ids ;
    public   float test_len;

    public Tests_Name(String test_name, String test_start_date, String test_end_date, float test_len , String subject_ids) {
        this.test_name = test_name;
        this.test_start_date = test_start_date;
        this.test_end_date = test_end_date;
        this.test_len = test_len;
        this.subject_ids = subject_ids;

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