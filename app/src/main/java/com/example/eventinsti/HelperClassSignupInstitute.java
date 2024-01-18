package com.example.eventinsti;

public class HelperClassSignupInstitute {
    String susername="";
    String instituteName="";
    String instituteCode="";
    String instituteAddress="";
    String institutePhone="";
    String collegeType ="";
    String collegeFields = "";
    String degreeType="";
    String branchType="";

    public String getSusername() {
        return susername;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public String getInstituteAddress() {
        return instituteAddress;
    }

    public String getInstitutePhone() {
        return institutePhone;
    }

    public String getCollegeType() {
        return collegeType;
    }

    public String getCollegeFields() {
        return collegeFields;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public String getBranchType() {
        return branchType;
    }

    public HelperClassSignupInstitute(String susername, String instituteName, String instituteCode, String instituteAddress, String institutePhone, String collegeType, String collegeFields, String degreeType, String branchType) {
        this.susername = susername;
        this.instituteName = instituteName;
        this.instituteCode = instituteCode;
        this.instituteAddress = instituteAddress;
        this.institutePhone = institutePhone;
        this.collegeType = collegeType;
        this.collegeFields = collegeFields;
        this.degreeType = degreeType;
        this.branchType = branchType;
    }

    public HelperClassSignupInstitute() {
    }
}
