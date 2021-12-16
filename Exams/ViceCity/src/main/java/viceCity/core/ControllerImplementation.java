package viceCity.core;

import viceCity.core.interfaces.Controller;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;
import viceCity.repositories.GunRepository;
import viceCity.repositories.PlayerRepository;
import viceCity.repositories.interfaces.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static viceCity.common.ConstantMessages.*;

public class ControllerImplementation implements Controller {
    private Repository<Gun> guns;
    private Repository<Player> players;
    private Neighbourhood neighbourhood;
    private MainPlayer mainPlayer;

    public ControllerImplementation() {
        this.guns = new GunRepository<>();
        this.players = new PlayerRepository<>();
        this.neighbourhood = new GangNeighbourhood();
        mainPlayer = new MainPlayer();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        players.add(player);

        return String.format(PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        String output;

        if (type.equals("Rifle")) {
            gun = new Rifle(name);
            output = String.format(GUN_ADDED, name, type);

        } else if (type.equals("Pistol")) {
            gun = new Pistol(name);
            output = String.format(GUN_ADDED, name, type);

        } else {
            output = String.format(GUN_TYPE_INVALID);
        }

        guns.add(gun);

        return output;
    }

    @Override
    public String addGunToPlayer(String name) {
        Player player;
        Gun foundGun = guns.getModels().stream().findFirst().orElse(null);

        if (foundGun != null) {
            guns.remove(foundGun);
        }
        String output;

        if (foundGun == null) {
            output = GUN_QUEUE_IS_EMPTY;

        } else {

            if (name.equals("Vercetti")) {
                player = this.mainPlayer;
                player.getGunRepository().add(foundGun);
                output = String.format(GUN_ADDED_TO_MAIN_PLAYER, foundGun.getName(), "Tommy " + name);

            } else if (players.find(name) == null) {
                output = CIVIL_PLAYER_DOES_NOT_EXIST;

            } else {
                player = this.players.find(name);
                player.getGunRepository().add(foundGun);
                output = String.format(GUN_ADDED_TO_CIVIL_PLAYER, foundGun.getName(), name);
            }
        }

        return output;
    }

    @Override
    public String fight() {
        List<Player> civilPlayers = (List<Player>) this.players.getModels();

        this.neighbourhood.action(this.mainPlayer, civilPlayers);

        boolean isCivilDead = false;

        for (Player civilPlayer : civilPlayers) {

            if (civilPlayer.getLifePoints() != 50) {
                isCivilDead = true;

                break;

            }
        }

        if (this.mainPlayer.getLifePoints() == 100 && (!isCivilDead)) {
            return FIGHT_HOT_HAPPENED;

        } else {
            StringBuilder builder = new StringBuilder();

            List<Player> deadCivils = civilPlayers
                    .stream()
                    .filter(p -> (!p.isAlive()))
                    .collect(Collectors.toList());

            builder
                    .append(FIGHT_HAPPENED)
                    .append(System.lineSeparator());

            builder
                    .append(String.format(MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints()))
                    .append(System.lineSeparator());

            builder
                    .append(String.format(MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, deadCivils.size()))
                    .append(System.lineSeparator());

            builder
                    .append(String.format(CIVIL_PLAYERS_LEFT_MESSAGE, civilPlayers.size() - deadCivils.size()));

            return builder.toString().trim();
        }
    }
}

