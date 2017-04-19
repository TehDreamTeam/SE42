package nl.tehdreamteam.se42.data;

/**
 * Defines certain constants for the data layer.
 *
 * @author Lesley
 */
public final class DataConstants {

    /**
     * The persistency-unit name for Hiberbante.
     */
    public static final String HIBERNATE_PERSISTENCY_UNIT_NAME = "manager";

    private DataConstants() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

}
