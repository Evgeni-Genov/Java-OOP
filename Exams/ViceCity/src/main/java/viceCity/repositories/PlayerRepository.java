package viceCity.repositories;

import viceCity.models.players.BasePlayer;
import viceCity.models.players.Player;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class PlayerRepository<T extends Player> implements Repository<T> {
    private Collection<T> models;

    public PlayerRepository() {
        this.models = new ArrayList<>();
    }

    @Override
    public Collection<T> getModels() {
       return this.models;
    }


    @Override
    public void add(T model) {
        this.models.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.models.remove(model);
    }

    @Override
    public T find(String name) {
        return this.models
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
