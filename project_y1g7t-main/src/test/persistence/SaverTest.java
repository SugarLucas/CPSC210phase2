package persistence;

import model.Player;
import model.Teams;
import model.TradeList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persistence.Reader;
import persistence.Saver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class SaverTest {
    TradeList tradeList;

    @BeforeEach
    public void setup() {
        tradeList = new TradeList();
        tradeList.clearFile();

    }

    @Test
    public void testSaveLoad() {
        Player lebron = new Player("Lebron", 4000, Teams.LA_Lakers);
        Player steph = new Player("Steph", 4300, Teams.Golden_State_Warriors);

        tradeList.addPlayer(lebron);
        tradeList.addPlayer(steph);
        Saver.save(tradeList, "./data/savedTl.json");
        try {
            List<String> lines = Files.readAllLines(Paths.get("./data/savedTl.json"));
            String actualString = lines.get(0); // there is only 1 line in the file
            String expectedString = "[{\"name\":\"Lebron\",\"team\":\"LA_Lakers\",\"salary\":4000}," +
                    "{\"name\":\"Steph\",\"team\":\"Golden_State_Warriors\",\"salary\":4300}]";
            assertEquals(expectedString, actualString);
        } catch (IOException e) {
            fail("Unexpected IOException");
        }
        tradeList = new TradeList();
        Reader.load(tradeList, "./data/savedTl.json");
        assertEquals(tradeList.getTotalSalary(), 8300);
    }

    @Test
    public void testException() {
        Player lebron = new Player("Lebron", 4000, Teams.LA_Lakers);
        Player steph = new Player("Steph", 4300, Teams.Golden_State_Warriors);

        tradeList.addPlayer(lebron);
        tradeList.addPlayer(steph);
        Saver.save(tradeList, "./data/savedTl.json");
        try {
            List<String> lines = Files.readAllLines(Paths.get("./data/savedTl____.json"));
            fail("Unexpected IOException");
        } catch (IOException e) {
            // Excepted
        }
        tradeList = new TradeList();
        Reader.load(tradeList, "./data/savedTl.json");
        assertEquals(tradeList.getTotalSalary(), 8300);
    }
}

