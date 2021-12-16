package Reflection.BarracksWars.Interfaces;

import java.lang.reflect.Executable;

public interface CommandInterpreter {

    Executable interpretCommand(String[] data, String commandName);
}