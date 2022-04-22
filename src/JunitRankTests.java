import org.junit.Test;

import static org.junit.Assert.*;

public class JunitRankTests {
    /******************************************************************************************
     * This tests the getValue() and isRed() getters in the Suit class.
     *****************************************************************************************/
    @Test
    public void testSuitValueAndColor() {
        assertEquals(Rank.ACE.getValue(),1);
        assertEquals(Rank.KING.getValue(),13);
    }
}
