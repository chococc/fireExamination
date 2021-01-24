package com.sxbang.friday.model;

public class testidentity {
    private String studentName;
    private String testNo;
    private String testName;
    private String testPlace;
    private String testPlaceID;
    private Integer testPlaceNo;

    public String getStudentName(){return studentName;}
    public String getTestName(){return testName;}
    public void setTestName(String testName){this.testName=testName;}
    public String getTestPlace(){return testPlace;}
    public Integer getTestPlaceNo(){return testPlaceNo;}
    public void setTestPlaceNo(Integer testPlaceNo){this.testPlaceNo=testPlaceNo;}
    public String getTestNo(){return testNo;}

    public testidentity(testidentity testidentity){
        this.studentName=testidentity.studentName;
        this.testName=testidentity.testName;
        this.testPlace=testidentity.testPlace;
        this.testPlaceID=testidentity.testPlaceID;
        this.testPlaceNo=testidentity.testPlaceNo;
        this.testNo=testidentity.testNo;
    }
    public testidentity(){}
}
