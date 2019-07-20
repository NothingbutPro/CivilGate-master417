package dev.raghav.civilgate.Api;

import java.io.File;

import dev.raghav.civilgate.Const_Files.BooktheMarks;
import dev.raghav.civilgate.Const_Files.Brain_Questions;
import dev.raghav.civilgate.Const_Files.CompareGraph;
import dev.raghav.civilgate.Const_Files.Detailed_Analysis_const;
import dev.raghav.civilgate.Const_Files.Forgotten_Passowrd;
import dev.raghav.civilgate.Const_Files.Full_Solutions;
import dev.raghav.civilgate.Const_Files.Myoverall;
import dev.raghav.civilgate.Const_Files.Package;
import dev.raghav.civilgate.Const_Files.Percentage;
import dev.raghav.civilgate.Const_Files.PostBookMarks;
import dev.raghav.civilgate.Other_Parsing_Files.Credit;
import dev.raghav.civilgate.Other_Parsing_Files.Dashboard_Latest_Test;
import dev.raghav.civilgate.Other_Parsing_Files.Exam_Test;
import dev.raghav.civilgate.Other_Parsing_Files.Get_About;
import dev.raghav.civilgate.Other_Parsing_Files.Get_Level;
import dev.raghav.civilgate.Other_Parsing_Files.Instant_Report;
import dev.raghav.civilgate.Other_Parsing_Files.RemoveBookmarks;
import dev.raghav.civilgate.Other_Parsing_Files.Schedule;
import dev.raghav.civilgate.Other_Parsing_Files.Score_Result;
import dev.raghav.civilgate.Other_Parsing_Files.Submit_Question;
import dev.raghav.civilgate.Other_Parsing_Files.Test_Question;
import dev.raghav.civilgate.Parsingfiles.LoginReg.Login_Responce;
import dev.raghav.civilgate.Parsingfiles.LoginReg.RegisPars_responce;
import dev.raghav.civilgate.Reports_Adapters.Parsing_files.HisHistory;
import dev.raghav.civilgate.Test_Activities.Test_Types.TestStart;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("Ragistration")
    Call<RegisPars_responce> Register_to_app(
            @Field("name") String name,
            @Field("mobile") String mobile,
            @Field("email") String email,
            @Field("password") String password,
            @Field("passout_year") String passout_year,
            @Field("collage_name") String collage_name,
            @Field("address") String address,
            @Field("profile_image") File profile_image,
            @Field("sign_image") File sign_image
    );
    @FormUrlEncoded
    @POST("GettestQuestion")
    Call<Detailed_Analysis_const> DETAILED_ANALYSIS_CONST_CALL(
            @Field("student_id") int  student_id,
            @Field("level_id") String level_id,
            @Field("level_sub_id") String level_sub_id

    );
    @FormUrlEncoded
    @POST(Retro_Urls.Gettestrecord)
    Call<Full_Solutions> FULL_SOLUTIONS_CALL(
            @Field("student_id") int  student_id,
            @Field("level_id") String level_id,
            @Field("level_sub_id") String level_sub_id

    );
    @FormUrlEncoded
    @POST(Retro_Urls.addbookmark)
    Call<PostBookMarks> PostBookMarks(
            @Field("student_id") int  student_id,
            @Field("que_id") String que_id,
              @Field("test_id") String test_id,
            @Field("solve_id") String solve_id

    );


    @FormUrlEncoded
    @POST(Retro_Urls.Getbookmark)
    Call<BooktheMarks> BOOKTHE_MARKS_CALL(
            @Field("student_id") int  student_id,
            @Field("test_id") String  test_id
    );
//    @FormUrlEncoded
//    @POST(Retro_Urls.Getbookmark)
//    Call<BooktheMarks> BOOKTHE_MARKS_CALL(
//            @Field("student_id") int  student_id
//    );
    @FormUrlEncoded
    @POST(Retro_Urls.forgetpassword)
    Call<Forgotten_Passowrd> Forgetpassword(
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST(Retro_Urls.StudentQue)
    Call<Brain_Questions> StudentQue(
            @Field("student_id") int student_id,
            @Field("question") String question,
            @Field("ans_1") String ans_1,
            @Field("ans_2") String ans_2,
            @Field("ans_3") String ans_3,
            @Field("ans_4") String ans_4,
            @Field("ans") String ans,
            @Field("solution") String solution

    );
////jackson
//    @POST("login/{email}/{password}")
//    Call<Login_Responce> Login_that_dk(
//            @Path("email") String email,
//              @Path("password") String password
//    );
//@POST("/login")
//Call<Login_Responce> basicLogin();
@FormUrlEncoded
@POST("login")
Call<Login_Responce> Login_that_dk(
        @Field("email") String email,
        @Field("password") String password
);

@FormUrlEncoded
@POST("Get_Percentage")
Call<Percentage> PERCENTAGE_CALL(
        @Field("student_id") int student_id
);
@FormUrlEncoded
@POST("endTest")
Call<Instant_Report> EndTest(
        @Field("student_id") int student_id,
        @Field("level_sub_id") String level_sub_id,
        @Field("level_id") int level_id


);
@FormUrlEncoded
@POST("GetTestScore")
Call<Score_Result> SCORE_RESULT_CALL(
        @Field("student_id") int student_id,
        @Field("level_sub_id") String level_sub_id,
        @Field("level_id") int level_id


);
@FormUrlEncoded
@POST("Gethistory")
Call<HisHistory> Gethistory(
        @Field("student_id") int student_id
);
@FormUrlEncoded
@POST("getlastest")
Call<Dashboard_Latest_Test> DASHBOARD_LATEST_TEST_CALL(
        @Field("student_id") int student_id
);
@Headers("Content-Type: application/x-www-form-urlencoded")
@GET("Get_Package")
Call<Package> Get_Package();
    @Headers("Content-Type: application/x-www-form-urlencoded")
    @GET("Get_creditPoin")
    Call<Credit> Get_Credits(@Query("student_id") int student_id);
@Headers("Content-Type: application/x-www-form-urlencoded")
@GET(Retro_Urls.About_us)
Call<Get_About> TellAbout();
@Headers("Content-Type: application/x-www-form-urlencoded")
@GET(Retro_Urls.Get_level)
Call<Get_Level> GetLevels();
@Headers("Content-Type: application/x-www-form-urlencoded")
@GET(Retro_Urls.Getschedule)
Call<Schedule> SCHEDULE_CALL();
//@Headers("Content-Type: application/x-www-form-urlencoded")
//@POST(Retro_Urls.Get_GetExam)
//@POST(Retro_Urls.Get_GetExam+"/{level_id}")
//@POST("GetExam/{level_id}")
//@Headers("Content-Type: text/json;charset=UTF-8")
@GET(Retro_Urls.Get_GetExam)
//@FormUrlEncoded
Call<Exam_Test> Get_GetExam(@Query("level_id") int level_id ,@Query("student_id") int student_id );
@Headers("Content-Type: application/x-www-form-urlencoded")
@FormUrlEncoded
@POST(Retro_Urls.GetQuestion)
Call<Test_Question> GetQuestion(@Field("subject_id") String subject_id);
    @FormUrlEncoded
    @POST(Retro_Urls.Toppers_list)
    Call<CompareGraph> Toppers_list(@Field("test_id") String test_id , @Field("user_id") String user_id);


    @FormUrlEncoded
    @POST(Retro_Urls.Removebookmark)
    Call<RemoveBookmarks> REMOVE_BOOKMARKS_CALL( @Field("bookmark_id") String bookmark_id);

    @FormUrlEncoded
    @POST(Retro_Urls.Getbasicinfo)
    Call<Myoverall> MYOVERALL_DATA_CALL(@Field("student_id") int user_id);
//@POST(Retro_Urls.Teststatus)
//Call<Submit_Question> SubmitQuery(@Field("que_id") int que_id,@Field("student_id") int student_id,@Field("time") String time,@Field("q_status") int q_status,@Field("que_ans") String que_an);
@FormUrlEncoded
@POST(Retro_Urls.Teststatus)
Call<Submit_Question> SubmitQuery(@Field("level_id") int level_id, @Field("level_sub_id") String level_sub_id, @Field("que_id") int que_id, @Field("student_id") int student_id, @Field("time") int time, @Field("q_status") int q_status, @Field("que_ans") String que_an);
@FormUrlEncoded
@POST(Retro_Urls.Srarttest)
Call<TestStart> GetTestQuestionCall(
        @Field("student_id") int student_id,
        @Field("level_id") int level_id,
        @Field("level_sub_id") String level_sub_id, @Field("subject_id") String subject_id );



}
