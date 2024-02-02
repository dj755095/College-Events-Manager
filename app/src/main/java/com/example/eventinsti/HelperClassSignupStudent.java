package com.example.eventinsti;

public class HelperClassSignupStudent {
    String username,name,instituteName,instituteCode,phoneNumber,gender, studyClass,juniorStudyClass,studyBranch,studyYear, degreeType;

    public HelperClassSignupStudent() {
    }

    public HelperClassSignupStudent(String username, String name, String instituteName, String instituteCode, String phoneNumber, String gender, String studyClass, String juniorStudyClass, String studyBranch, String studyYear, String degreeType) {
        this.username = username;
        this.name = name;
        this.instituteName = instituteName;
        this.instituteCode = instituteCode;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.studyClass = studyClass;
        this.juniorStudyClass = juniorStudyClass;
        this.studyBranch = studyBranch;
        this.studyYear = studyYear;
        this.degreeType = degreeType;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getInstituteName() {
        return instituteName;
    }

    public String getInstituteCode() {
        return instituteCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public String getStudyClass() {
        return studyClass;
    }

    public String getJuniorStudyClass() {
        return juniorStudyClass;
    }

    public String getStudyBranch() {
        return studyBranch;
    }

    public String getStudyYear() {
        return studyYear;
    }

    public String getDegreeType() {
        return degreeType;
    }
}
