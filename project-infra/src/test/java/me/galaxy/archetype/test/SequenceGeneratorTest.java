package me.galaxy.archetype.test;

import me.galaxy.archetype.infra.sequence.SequenceGenerator;
import me.galaxy.archetype.infra.sequence.SnowflakeSequenceGenerator;
import me.galaxy.archetype.infra.sequence.UUIDSequenceGenerator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SequenceGeneratorTest {

    @Test
    public void testSnowflakeSequenceGenerator() {
        SequenceGenerator generator = new SnowflakeSequenceGenerator();
        String id = generator.next();
        System.out.println(id);
        Assert.assertNotNull(id);
    }

    @Test
    public void testUUIDSequenceGenerator(){
        SequenceGenerator generator = new UUIDSequenceGenerator();
        String id = generator.next();
        System.out.println(id);
        Assert.assertNotNull(id);
    }

}
