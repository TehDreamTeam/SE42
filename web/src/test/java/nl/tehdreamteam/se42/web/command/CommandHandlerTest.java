package nl.tehdreamteam.se42.web.command;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.Mockito.*;

public class CommandHandlerTest {

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();

    private CommandHandler handler;
    private Command command;

    @Test
    public void handle_nullLine_throwsException() {
        expectedException.expect(NullPointerException.class);

        givenDefaultCommandHandler();

        handleNullCommand();
    }

    @Test
    public void handle_unregisteredCommand_throwsException() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("Command with the given identifier not registered.");

        givenDefaultCommandHandler();
        givenCommandWithDefaultIdentifier();

        handleCommandWithoutArguments();
    }

    @Test
    public void handle_lineWithoutArguments_executesCommand() {
        givenDefaultCommandHandler();
        givenCommandWithDefaultIdentifier();
        registerCommand();

        handleCommandWithoutArguments();

        verifyCommandWasCalledWithoutArguments();
    }

    @Test
    public void handle_lineWithArguments_executesCommand() {
        givenDefaultCommandHandler();
        givenCommandWithDefaultIdentifier();
        registerCommand();

        handleCommandWithArguments();

        verifyCommandWasCalledWithArguments();
    }

    @Test
    public void getCommand_nullCommand_throwsException() {
        expectedException.expect(NullPointerException.class);

        givenDefaultCommandHandler();

        getNullCommand();
    }

    @Test
    public void register_nullCommand_throwsException() {
        expectedException.expect(NullPointerException.class);

        givenDefaultCommandHandler();
        givenNullCommand();

        registerCommand();
    }

    @Test
    public void register_nullCommandIdentifiers_throwsException() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("This command has no identifiers.");

        givenDefaultCommandHandler();
        givenCommandWithNullIdentifiers();

        registerCommand();
    }

    @Test
    public void register_emptyCommandIdentifiers_throwsException() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("This command has no identifiers.");

        givenDefaultCommandHandler();
        givenCommandWithoutIdentifiers();

        registerCommand();
    }

    @Test
    public void deregister_nullCommand_throwsException() {
        expectedException.expect(NullPointerException.class);

        givenDefaultCommandHandler();
        givenNullCommand();

        deregisterCommand();
    }

    @Test
    public void deregister_nullCommandIdentifiers_throwsException() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("This command has no identifiers.");

        givenDefaultCommandHandler();
        givenCommandWithNullIdentifiers();

        deregisterCommand();
    }

    @Test
    public void deregister_emptyCommandIdentifiers_throwsException() {
        expectedException.expect(NullPointerException.class);
        expectedException.expectMessage("This command has no identifiers.");

        givenDefaultCommandHandler();
        givenCommandWithoutIdentifiers();

        deregisterCommand();
    }

    @Before
    public void reset() {
        handler = null;
        command = null;
    }

    private void handleCommandWithoutArguments() {
        handler.handle("default");
    }

    private void handleCommandWithArguments() {
        handler.handle("default and some arguments");
    }

    private void handleNullCommand() {
        handler.handle(null);
    }

    private void getNullCommand() {
        handler.getCommand(null);
    }

    private void registerCommand() {
        handler.register(command);
    }

    private void deregisterCommand() {
        handler.deregister(command);
    }

    private void verifyCommandWasCalledWithoutArguments() {
        verify(command).execute(handler, "", new String[0]);
    }

    private void verifyCommandWasCalledWithArguments() {
        verify(command).execute(handler, "and some arguments", new String[]{"and", "some", "arguments"});
    }

    private void givenDefaultCommandHandler() {
        handler = new CommandHandler();
    }

    private void givenDefaultCommand() {
        command = mock(Command.class);
    }

    private void givenCommandWithDefaultIdentifier() {
        givenDefaultCommand();
        when(command.getIdentifiers()).thenReturn(new String[]{"default"});
    }

    private void givenCommandWithNullIdentifiers() {
        givenDefaultCommand();
        when(command.getIdentifiers()).thenReturn(null);
    }

    private void givenCommandWithoutIdentifiers() {
        givenDefaultCommand();
        when(command.getIdentifiers()).thenReturn(new String[0]);
    }

    private void givenNullCommand() {
        command = null;
    }

}