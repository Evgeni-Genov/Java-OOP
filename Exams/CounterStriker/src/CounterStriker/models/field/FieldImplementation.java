package CounterStriker.models.field;

import CounterStriker.models.players.CounterTerrorist;
import CounterStriker.models.players.Player;
import CounterStriker.models.players.Terrorist;

import java.util.Collection;
import java.util.stream.Collectors;

import static CounterStriker.common.OutputMessages.COUNTER_TERRORIST_WINS;
import static CounterStriker.common.OutputMessages.TERRORIST_WINS;

public class FieldImplementation implements Field {
    @Override
    public String start(Collection<Player> players) {
        Collection<Player> terrorists = players
                .stream()
                .filter(p -> p instanceof Terrorist)
                .collect(Collectors.toList());

        Collection<Player> counterTerrorists = players
                .stream()
                .filter(p -> p instanceof CounterTerrorist)
                .collect(Collectors.toList());

        while (terrorists.stream().anyMatch(Player::isAlive)
                && counterTerrorists.stream().anyMatch(Player::isAlive)) {

            for (Player terrorist : terrorists) {
                for (Player counterTerrorist : counterTerrorists) {
                    counterTerrorist.takeDamage(terrorist.getGun().fire());
                }
            }
            counterTerrorists = counterTerrorists
                    .stream()
                    .filter(Player::isAlive)
                    .collect(Collectors.toList());

            for (Player counterTerrorist : counterTerrorists) {
                for (Player terrorist : terrorists) {
                    terrorist.takeDamage(counterTerrorist.getGun().fire());
                }
            }

            terrorists = terrorists
                    .stream()
                    .filter(Player::isAlive)
                    .collect(Collectors.toList());

        }

        return terrorists
                .stream()
                .anyMatch(Player::isAlive) ? TERRORIST_WINS : COUNTER_TERRORIST_WINS;
    }
}