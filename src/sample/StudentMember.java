package sample;

public class StudentMember extends DefaultMember {
    private String schoolName;

    public StudentMember(int membershipNumber, String name, String schoolName) {
        super(membershipNumber, name);
        this.schoolName = schoolName;
        this.memberType = "Student";
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
