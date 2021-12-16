package Reflection.BarracksWars.Core.Commands;

import Reflection.BarracksWars.Core.Command;
import Reflection.BarracksWars.Interfaces.Repository;
import Reflection.BarracksWars.Interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Retire extends Command {
    public Retire(String[] data, Repository repository,
                  UnitFactory unitFactory) {

        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        String unitType = super.getData()[1];
        super.getRepository().removeUnit(unitType);

        return unitType + " retired!";
    }

}