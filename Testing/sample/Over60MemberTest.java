package sample;

import org.junit.Test;

public class Over60MemberTest {

    @Test
    public void setAge() {
        Over60Member over60Member = new Over60Member();
        over60Member.setAge(61);
    }
}