package CounterStriker;

import CounterStriker.core.EngineImplementation;
import CounterStriker.core.Engine;

public class Main {
    public static void main(String[] args) {
        Engine engine = new EngineImplementation();

        engine.run();
    }
}
