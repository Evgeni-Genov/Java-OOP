package bakery;

import bakery.core.ControllerImplementation;
import bakery.core.EngineImplementation;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.interfaces.Table;

import bakery.io.ConsoleReader;
import bakery.io.ConsoleWriter;
import bakery.repositories.DrinkRepositoryImplementation;
import bakery.repositories.FoodRepositoryImplementation;
import bakery.repositories.TableRepositoryImplementation;
import bakery.repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {
        String a = " ";
        int a1 = a.length();

        FoodRepository<BakedFood> foodRepository = new FoodRepositoryImplementation<>();
        DrinkRepository<Drink> drinkRepository = new DrinkRepositoryImplementation<>();
        TableRepository<Table> tableRepository = new TableRepositoryImplementation<>();

        Controller controller = new ControllerImplementation(foodRepository, drinkRepository, tableRepository);

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        EngineImplementation engine = new EngineImplementation(reader, writer, controller);

        engine.run();
    }
}
