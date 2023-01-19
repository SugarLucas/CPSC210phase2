package model;

import java.util.Objects;

// this class creates a player with fields name, salary and team
public class Player {

    protected  String name;
    protected  int salary;

    protected  Teams team;

    // REQUIRES: salary greater than 0
    // MODIFIES:this
    // EFFECTS: constructor of player

    public Player(String name, int salary, Teams team) {
        this.name = name;
        this.salary = salary;
        this.team = team;

    }
//    // EFFECTS: returns string representation of this player
//
//    public String toString(Player player) {
//        return name + ", " + salary + "M" + "," + team;
//    }
    // EFFECTS: get the name of the player

    public String getName() {
        return name;
    }

    // EFFECTS: get the salary of the player
    public int getSalary() {
        return salary;
    }

    // EFFECTS: get the team of the player
    public Teams getTeam() {
        return team;
    }

    // MODIFIES: this
    // EFFECTS: set a new name for the player
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: set a new salary for the player
    public void setSalary(int salary) {
        this.salary = salary;
    }

    // MODIFIES: this
    // EFFECTS: set a new team for the player
    public void setTeam(Teams team) {
        this.team = team;
    }

    // EFFECTS: return true if two players have the same name,salary, team
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return salary == player.salary && name.equals(player.name) && team == player.team;
    }

    // EFFECTS: return the hashCode of the player
    @Override
    public int hashCode() {
        return Objects.hash(name, salary, team);
    }
}
