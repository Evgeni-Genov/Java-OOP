package Reflection.BarracksWars.Core.Factories;

import Reflection.BarracksWars.Interfaces.Unit;
import Reflection.BarracksWars.Interfaces.UnitFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImplementation implements UnitFactory {
    private static final String UNITS_PACKAGE_NAME =
            "barracksWars.models.units.";

    @Override
    public Unit createUnit(String unitType) {
        String path = UNITS_PACKAGE_NAME + unitType;

        Unit unit;

        try {
            Class<?> clazz = Class.forName(path);
            Constructor<?> ctor = clazz.getDeclaredConstructor();
            unit = (Unit) ctor.newInstance();

        } catch (ClassNotFoundException
                | NoSuchMethodException | InstantiationException
                | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalStateException(e);
        }

        return unit;
    }
}