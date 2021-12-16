package Reflection.BarracksWars.Core.Commands;


import Reflection.BarracksWars.Core.Command;
import Reflection.BarracksWars.Interfaces.Repository;
import Reflection.BarracksWars.Interfaces.UnitFactory;

public class Fight extends Command {
    public Fight(String[] data, Repository repository,
                 UnitFactory unitFactory) {

        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return this.fightCommand();
    }

    private String fightCommand() {
        return "fight";
    }
}