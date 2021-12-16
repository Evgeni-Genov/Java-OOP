package viceCity.models.neighbourhood;

import viceCity.models.guns.Gun;
import viceCity.models.players.Player;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GangNeighbourhood implements Neighbourhood {

    @Override
    public void action(Player mainPlayer, Collection<Player> civilPlayers) {
        Collection<Gun> guns = mainPlayer.getGunRepository().getModels();

        for (Gun gun : guns) {
            if (civilPlayers.stream().filter(p -> p.isAlive()).findFirst().orElse(null) == null) {
                break;
            }

            while (gun.canFire()) {
                if (civilPlayers.stream().filter(p -> p.isAlive()).findFirst().orElse(null) == null) {
                    break;
                }

                for (Player civilPlayer : civilPlayers) {

                    while (civilPlayer.isAlive()) {
                        civilPlayer.takeLifePoints(gun.fire());
                    }

                    if (!gun.canFire()) {
                        break;
                    }
                }
            }
        }

        List<Player> aliveCivilPlayers = civilPlayers.stream().filter(Player::isAlive).collect(Collectors.toList());

        for (Player aliveCivilPlayer : aliveCivilPlayers) {
            Collection<Gun> civilPlayerGuns = aliveCivilPlayer.getGunRepository().getModels();

            for (Gun civilPlayerGun : civilPlayerGuns) {

                while (civilPlayerGun.canFire()) {
                    mainPlayer.takeLifePoints(civilPlayerGun.fire());

                    if (!mainPlayer.isAlive()) {
                        return;
                    }
                }
            }
        }
    }
}
