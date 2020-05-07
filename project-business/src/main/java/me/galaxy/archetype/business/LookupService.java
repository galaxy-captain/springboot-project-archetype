package me.galaxy.archetype.business;

import me.galaxy.archetype.repo.LookupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LookupService implements LookupQueryService, LookupCommandService {

    @Autowired
    private LookupRepository lookupRepository;

    @Override
    public void addLookup() {

    }

    @Override
    public String queryLookupByCode() {
        return lookupRepository.selectLookup().getName();
    }

}