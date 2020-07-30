package sample;

public class StudentMember extends DefaultMember {
    private String schoolName;

    public StudentMember() {};

    public StudentMember(int membershipNumber, String name, String membershipDate, double height, double weight, String schoolName, String memberType) {
        super(membershipNumber, name, membershipDate, height, weight, memberType);
        this.schoolName = Database.toTitleCase(schoolName);
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
