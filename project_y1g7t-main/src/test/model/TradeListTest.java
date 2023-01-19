package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
public class TradeListTest {
    private Player player1;
    private Player player2;
    private Player player3;
    private TradeList tradeList;
    private TradeList tradeList2;


    @BeforeEach
    void setup(){
        player1 = new Player("Lebron James",4000, Teams.LA_Lakers);
        player2 = new Player("Stephen Curry",4500, Teams.Golden_State_Warriors);
        player3 = new Player("Lucas",5000, Teams.Golden_State_Warriors);
        tradeList = new TradeList();
        tradeList2 = new TradeList();

    }

    @Test
    void testAddPlayer(){
        assertEquals(0,tradeList.size());
        tradeList.addPlayer(player1);
        assertEquals(1,tradeList.size());
        assertTrue(tradeList.contains(player1));
        tradeList.addPlayer(player2);
        assertEquals(2,tradeList.size());
        assertTrue(tradeList.contains(player2));
    }

    @Test
    void testRemovePlayer(){
        assertEquals(0,tradeList.size());
        tradeList.addPlayer(player3);
        assertEquals(1,tradeList.size());
        tradeList.removePlayer();
        assertEquals(0,tradeList.size());
    }
    @Test
    void testGetPlayers(){
        tradeList.addPlayer(player2);
        tradeList.addPlayer(player3);
        assertEquals(Arrays.asList("Stephen Curry: $4500", "Lucas: $5000"),tradeList.getPlayers());
    }
    @Test
    void testGetTotalSalary() {
        assertEquals(0,tradeList.getTotalSalary());
        tradeList.addPlayer(player2);
        assertEquals(4500,tradeList.getTotalSalary());
        tradeList.addPlayer(player3);
        assertEquals(9500,tradeList.getTotalSalary());
    }
    @Test
    void testGetPath(){
        tradeList.setPath("fds");
        assertEquals("fds",tradeList.getPath());
        tradeList2.setPath("sdfwwe");
        assertEquals("fds",tradeList.getPath());
    }

    @Test
    void testSetPath(){
        tradeList.setPath("fds");
        assertEquals("fds",tradeList.path);
        tradeList2.setPath("sdfwwe");
        assertEquals("fds",tradeList.path);
    }




}
