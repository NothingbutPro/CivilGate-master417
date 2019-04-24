package dev.raghav.civilgate.Const_Files;

import android.provider.MediaStore;

public class Questions_jJava {
    int id ,sub_id ,minusmark,marks,Status,type;
   String video;
    String Que;
    String Ans_1;
    String Ans_2;
    String Ans_3;
    String Ans_4;
    String solution;
    String Ans;
    String video_url;
    String Createdate;



    String Written_ans;
    String TIme_taken;

    public Questions_jJava(int id ) {
        this.id = id;
    }
    public String getWritten_ans() {
        return Written_ans;
    }

    public void setWritten_ans(String written_ans) {
        Written_ans = written_ans;
    }

    public String getTIme_taken() {
        return TIme_taken;
    }

    public void setTIme_taken(String TIme_taken) {
        this.TIme_taken = TIme_taken;
    }
    public Questions_jJava(int id , int type ,String que) {
        this.id = id;
        this.type = type;
        this.Que = que;
    }

    public Questions_jJava(String Ans, String timeTaken) {
        this.Written_ans = Ans;
        this.TIme_taken = timeTaken;
    }

    public int getType() {
        return type;
    }

    public void setType(int type ) {
        this.type = type;

    }

    public Questions_jJava(int id, int sub_id, int minusmark, int marks, String solution, int status, String createdate, String video, String que, String video_url) {
        this.id = id;
        this.sub_id = sub_id;
        this.minusmark = minusmark;
        this.marks = marks;
        this.solution = solution;
        this.Status = status;
        this.Createdate = createdate;
        this.video = video;
        this.Que = que;
        this.video_url = video_url;
    }

    public Questions_jJava(int id, int sub_id, int minusmark, int marks, String solution, int status, String createdate,String video, String que, String ans_1, String ans_2, String ans_3, String ans_4, String ans, String video_url) {
        this.id = id;
        this.sub_id = sub_id;
        this.minusmark = minusmark;
        this.marks = marks;
        this.solution = solution;
        this.Status = status;
        this.Createdate = createdate;
        this.video = video;
        this.Que = que;
        this.Ans_1 = ans_1;
        this.Ans_2 = ans_2;
        this.Ans_3 = ans_3;
        this.Ans_4 = ans_4;
        this.Ans = ans;
        this.video_url = video_url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSub_id() {
        return sub_id;
    }

    public void setSub_id(int sub_id) {
        this.sub_id = sub_id;
    }

    public int getMinusmark() {
        return minusmark;
    }

    public void setMinusmark(int minusmark) {
        this.minusmark = minusmark;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public String getCreatedate() {
        return Createdate;
    }

    public void setCreatedate(String createdate) {
        Createdate = createdate;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getQue() {
        return Que;
    }

    public void setQue(String que) {
        Que = que;
    }

    public String getAns_1() {
        return Ans_1;
    }

    public void setAns_1(String ans_1) {
        Ans_1 = ans_1;
    }

    public String getAns_2() {
        return Ans_2;
    }

    public void setAns_2(String ans_2) {
        Ans_2 = ans_2;
    }

    public String getAns_3() {
        return Ans_3;
    }

    public void setAns_3(String ans_3) {
        Ans_3 = ans_3;
    }

    public String getAns_4() {
        return Ans_4;
    }

    public void setAns_4(String ans_4) {
        Ans_4 = ans_4;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String ans) {
        Ans = ans;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }
}
