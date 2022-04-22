import org.junit.Test;

import static org.junit.Assert.*;

public class JunitSuitTests {
    /******************************************************************************************
     * This tests the getValue() and isRed() getters in the Suit class.
     *****************************************************************************************/
    @Test
    public void testSuitValueAndColor() {
        assertEquals(Suit.SPADES.getValue(),1);
        assertFalse(Suit.SPADES.isRed());
        assertEquals(Suit.HEARTS.getValue(),4);
        assertTrue(Suit.HEARTS.isRed());
    }
}
