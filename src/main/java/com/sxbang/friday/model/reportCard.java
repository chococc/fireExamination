package com.sxbang.friday.model;

import java.util.Date;

public class reportCard {
    private String userName;
    private String studentName;
    private String testName;
    private Date testDate;
    private String testResult;

    public String getUserName(){return userName;}
    public void setUserName(String userName){this.userName=userName;}
    public String getStudentName(){return studentName;}
    public void setStudentName(String studentName){this.studentName=studentName;}
    public String getTestName(){return testName;}
    public void setTestName(String testName){this.testName=testName;}
    public Date getTestDate(){return testDate;}
    public void setTestDate(){this.testDate=testDate;}
    public String getTestResult(){return testResult;}
    public void setTestResult(String testResult){this.testResult=testResult;}
}
