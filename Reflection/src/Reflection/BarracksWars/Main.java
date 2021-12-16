package Reflection.BarracksWars;


import Reflection.BarracksWars.Core.Engine;
import Reflection.BarracksWars.Core.Factories.UnitFactoryImplementation;
import Reflection.BarracksWars.Data.UnitRepository;
import Reflection.BarracksWars.Interfaces.Repository;
import Reflection.BarracksWars.Interfaces.UnitFactory;

public class Main {

    public static void main(String[] args) {
        Repository repository = new UnitRepository();
        UnitFactory unitFactory = new UnitFactoryImplementation();

        Runnable engine = new Engine(repository, unitFactory);
        engine.run();
    }
}