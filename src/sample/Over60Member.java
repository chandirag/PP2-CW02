package sample;

public class Over60Member extends DefaultMember {
    private int age;

    public Over60Member() {};

    public Over60Member(int membershipNumber, String name, String membershipDate, double height, double weight, int age, String memberType) {
        super(membershipNumber, name, membershipDate, height, weight, memberType);
        if (age > 60)
            this.age = age;
        else
            throw new IllegalArgumentException("Age must be over 60 for member type Over60.");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 60)
            this.age = age;
        else
            throw new IllegalArgumentException("Age must be over 60 for member type Over60.");
    }
}
