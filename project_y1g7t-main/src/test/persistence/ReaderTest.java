package persistence;

import model.TradeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    TradeList tradeList;
    String path;

    @BeforeEach
    void setup() {
        tradeList = new TradeList();
        tradeList.clearFile();
    }

    @Test
    void testLoad() {
        Reader.load(tradeList, "data/savedTest.json");
        assertEquals("Lebron",tradeList.getPlayer(0).getName());
        assertEquals(7000,tradeList.getTotalSalary());
    }


}

