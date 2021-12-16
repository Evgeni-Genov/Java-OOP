package Reflection.BarracksWars.Core.Commands;


import Reflection.BarracksWars.Core.Command;
import Reflection.BarracksWars.Interfaces.Repository;
import Reflection.BarracksWars.Interfaces.UnitFactory;

public class Report extends Command {
    public Report(String[] data, Repository repository,
                  UnitFactory unitFactory) {

        super(data, repository, unitFactory);
    }

    @Override
    public String execute() {
        return this.reportCommand();
    }

    private String reportCommand() {
        return super.getRepository().getStatistics();
    }
}