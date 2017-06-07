package nl.tehdreamteam.se42.web.token.generator.id.impl;

import nl.tehdreamteam.se42.web.token.generator.id.TokenIdGenerator;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class UUIDTokenIdGeneratorTest {

    private TokenIdGenerator generator;
    private String id;

    @Before
    public void resetFields() {
        generator = null;
        id = null;
    }

    @Test
    public void generate_whenCalled_returnsValidUUIDString() {
        givenUUIDTokenIdGenerator();

        generateId();

        verifyGeneratedIdIsValidUUIDString();
    }

    private void verifyGeneratedIdIsValidUUIDString() {
        UUID.fromString(id); // Will throw an exception if it's not a valid UUID
    }

    private void generateId() {
        id = generator.generate();
    }

    private void givenUUIDTokenIdGenerator() {
        generator = new UUIDTokenIdGenerator();
    }

}