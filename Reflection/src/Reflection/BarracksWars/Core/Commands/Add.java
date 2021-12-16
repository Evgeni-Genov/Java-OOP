package Reflection.BarracksWars.Core.Commands;

import Reflection.BarracksWars.Core.Command;
import Reflection.BarracksWars.Interfaces.Repository;
import Reflection.BarracksWars.Interfaces.Unit;
import Reflection.BarracksWars.Interfaces.UnitFactory;
import jdk.jshell.spi.ExecutionControl;

public class Add extends Command {
    public Add(String[] data, Repository repository,
               UnitFactory unitFactory) {

        super(data, repository, unitFactory);
    }

    @Override
    public String execute() throws ExecutionControl.NotImplementedException {
        return this.addUnitCommand(super.getData());
    }

    private String addUnitCommand(String[] data) throws ExecutionControl.NotImplementedException {
        String unitType = data[1];
        Unit unitToAdd = super.getUnitFactory().createUnit(unitType);

        super.getRepository().addUnit(unitToAdd);

        return unitType + " added!";
    }
}