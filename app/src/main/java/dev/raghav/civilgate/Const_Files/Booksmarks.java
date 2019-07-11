package dev.raghav.civilgate.Const_Files;

public class Booksmarks {
    private String name_of_que, rightans, ans1,ans2 ,ans3,ans4,levelids;

    public Booksmarks(String name_of_que, String rightans, String ans1, String ans2, String ans3, String ans4) {
        this.name_of_que = name_of_que;
        this.rightans = rightans;
        this.ans1 = ans1;
        this.ans2 = ans2;
        this.ans3 = ans3;
        this.ans4 = ans4;
    }

    public String getName_of_que() {
        return name_of_que;
    }

    public void setName_of_que(String name_of_que) {
        this.name_of_que = name_of_que;
    }

    public String getRightans() {
        return rightans;
    }

    public void setRightans(String rightans) {
        this.rightans = rightans;
    }

    public String getAns1() {
        return ans1;
    }

    public void setAns1(String ans1) {
        this.ans1 = ans1;
    }

    public String getAns2() {
        return ans2;
    }

    public void setAns2(String ans2) {
        this.ans2 = ans2;
    }

    public String getAns3() {
        return ans3;
    }

    public void setAns3(String ans3) {
        this.ans3 = ans3;
    }

    public String getAns4() {
        return ans4;
    }

    public void setAns4(String ans4) {
        this.ans4 = ans4;
    }
}