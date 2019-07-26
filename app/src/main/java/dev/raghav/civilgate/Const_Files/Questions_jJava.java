package dev.raghav.civilgate.Const_Files;

public class Questions_jJava {
    int id ,sub_id
            ,marks,Status,type;
    float minusmark;
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

    public void setMinusmark(float minusmark) {
        this.minusmark = minusmark;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    int position;
    String Written_ans;
    int TIme_taken;

//    public Questions_jJava(String s, int id, int queposition) {
//        this.id = id;
//
//    }
    public String getWritten_ans() {
        return Written_ans;
    }

    public void setWritten_ans(String written_ans) {
        Written_ans = written_ans;
    }

    public int getTIme_taken() {
        return TIme_taken;
    }

    public void setTIme_taken(int TIme_taken) {
        this.TIme_taken = TIme_taken;
    }
    public Questions_jJava(int id , int type ,String que) {
        this.id = id;
        this.type = type;
        this.Que = que;
    }

    public Questions_jJava(String Ans, int timeTaken , int que_number) {
        this.Written_ans = Ans;
        this.TIme_taken = timeTaken;
        this.position = que_number;
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

    public Questions_jJava(int id, int sub_id, float minusmark, int marks, String solution, int status, String createdate,String video, String que, String ans_1, String ans_2, String ans_3, String ans_4, String ans, String video_url) {
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

    public float getMinusmark() {
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
