package me.galaxy.archetype.repo;

import org.springframework.stereotype.Repository;

@Repository
public class LookupRepository {

    public Lookup selectLookup(){
        Lookup lookup =  new Lookup();
        lookup.setName("Settings");
        return lookup;
    }

}
