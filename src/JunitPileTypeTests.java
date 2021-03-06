import org.junit.Test;

import static org.junit.Assert.*;

public class JunitPileTypeTests {
    private Pile pile;
    /****************************************************************************************
     * This tests the setPileType() and getPileType() setters and getters from the
     * Pile class.
     ****************************************************************************************/
    @Test
    public void testPileType() {
        pile = new Pile(PileType.TABLEAU);

        pile.setPileType(PileType.FOUNDATION);
        assertEquals(pile.getPileType(), PileType.FOUNDATION);
    }
}
