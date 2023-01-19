package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class PlayerTest {
    // delete or rename this class!
    private Player player1;
    private Player player2;
    private Player player3;
    private String name = "Lebron James";
    private int salary = 4000;

    @BeforeEach
    void runBefore() {
        player1 = new Player(name,salary, Teams.LA_Lakers);
        player2 = new Player(name,salary, Teams.LA_Lakers);
        player3 = new Player(name,salary,Teams.Golden_State_Warriors);
    }
    @Test
    void testConstructor(){
        assertEquals(name,player1.getName());
        assertEquals(salary, player1.getSalary());
        assertEquals(Teams.LA_Lakers, player1.getTeam());
        player1.setName("Anthony Davis");
        assertEquals("Anthony Davis",player1.getName());
        player1.setSalary(3200);
        assertEquals(3200,player1.getSalary());
        player1.setTeam(Teams.Brooklyn_Nets);
        assertEquals(Teams.Brooklyn_Nets,player1.getTeam());
    }

    @Test
    void testSetName(){
        player1.setName("Anthony Davis");
        assertEquals("Anthony Davis",player1.getName());
    }


    @Test
    void testSetSalary(){
        player1.setSalary(3200);
        assertEquals(3200,player1.getSalary());
    }
    @Test
    void testSetTeam(){
        player1.setTeam(Teams.Brooklyn_Nets);
        assertEquals(Teams.Brooklyn_Nets,player1.getTeam());
    }
    @Test
    void testEquals(){
        assertFalse(player1.equals(name));
        assertFalse(player1.equals(player3));
        assertTrue(player1.equals(player2));
    }
    @Test
    void testHashCode(){
        assertEquals(player1.hashCode(),player1.hashCode());
    }




}