package sample;

public class DefaultMember {
    private int membershipNumber;
    private String name;
    private String membershipDate;
    String memberType;

    public DefaultMember() {};
    public DefaultMember(int membershipNumber, String name) {
        super();
        this.membershipNumber = membershipNumber;
        this.name = name;
        this.memberType = "Default";
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
}
