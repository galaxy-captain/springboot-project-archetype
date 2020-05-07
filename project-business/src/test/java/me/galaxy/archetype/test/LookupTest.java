package me.galaxy.archetype.test;

import me.galaxy.archetype.business.LookupService;
import me.galaxy.archetype.repo.Lookup;
import me.galaxy.archetype.repo.LookupRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LookupTest {

    @InjectMocks
    private LookupService lookupService;

    @Mock
    private LookupRepository lookupRepository;

    @Test
    public void testQueryLookupByCode() {

        Lookup lookup = new Lookup();
        lookup.setName("MockSettings");

        Mockito.when(lookupRepository.selectLookup()).thenReturn(lookup);

        String name = lookupService.queryLookupByCode();

        Assert.assertEquals("MockSettings", name);
    }

}
