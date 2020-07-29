package sample;

public class DefaultMember {
    private int membershipNumber;
    private String name;
    private String membershipDate;
    private double weight;
    private double height;
    String memberType;

    public DefaultMember() {};
    public DefaultMember(int membershipNumber, String name, String membershipDate, double height, double weight, String memberType) {
        super();
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.membershipDate = membershipDate;
        this.weight = weight;
        this.height = height;
        this.memberType = memberType;
    }

    public int getMembershipNumber() {
        return membershipNumber;
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate) {
        this.membershipDate = membershipDate;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
