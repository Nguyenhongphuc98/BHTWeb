/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhtweb.entities;
/**
 *
 * @author NguyenHongPhuc
 */
public class BHTSemester {
    
    int semesterId;
    
    String academicYear;
    
    String semesterNo;

    public BHTSemester() {
    }

    public BHTSemester(int semesterId, String academicYear, String semesterNo) {
        this.semesterId = semesterId;
        this.academicYear = academicYear;
        this.semesterNo = semesterNo;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getSemesterNo() {
        return semesterNo;
    }

    public void setSemesterNo(String semesterNo) {
        this.semesterNo = semesterNo;
    }
}
