package viceCity.repositories;

import viceCity.models.guns.Gun;
import viceCity.repositories.interfaces.Repository;

import java.util.ArrayList;
import java.util.Collection;

public class GunRepository<T extends Gun> implements Repository<T> {
    private Collection<T> models;

    public GunRepository(){
        this.models = new ArrayList<>();
    }

    @Override
    public Collection getModels() {
        return this.models;
    }

    @Override
    public void add(T model) {
        if(models.contains(model)){
            return;
        }

        this.models.add(model);
    }

    @Override
    public boolean remove(T model) {
        return this.models.remove(model);
    }


    @Override
    public T find(String name) {
        return this.models.stream().filter(g -> g.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
