package nl.tehdreamteam.se42.logic.user;

import com.jnape.palatable.lambda.adt.Either;
import nl.tehdreamteam.se42.data.user.UserRepository;
import nl.tehdreamteam.se42.data.user.hibernate.UserHibernateRepository;
import nl.tehdreamteam.se42.domain.Conversation;
import nl.tehdreamteam.se42.domain.User;
import nl.tehdreamteam.se42.logic.BusinessLogic;
import nl.tehdreamteam.se42.logic.controller.UserController;
import nl.tehdreamteam.se42.logic.exception.ServerError;
import nl.tehdreamteam.se42.logic.exception.impl.LoginExceptionHandler;
import nl.tehdreamteam.se42.logic.validator.input.InputValidatorChain;
import nl.tehdreamteam.se42.logic.validator.input.impl.NonNullInputValidator;
import nl.tehdreamteam.se42.logic.validator.input.impl.NotEmptyStringInputValidator;
import nl.tehdreamteam.se42.logic.validator.output.OutputValidatorChain;
import nl.tehdreamteam.se42.logic.validator.output.impl.PasswordOutputValidator;

import java.util.List;
import java.util.function.Supplier;

/**
 * Default implementation of the {@code UserController} interface.
 *
 * @author Oscar de Leeuw
 */
public class DefaultUserController implements UserController {

    private UserRepository userRepository;

    /**
     * Creates a new {@code DefaultUserController}.
     *
     * @param userRepository The {@code UserRepository} that should be for fetching {@link User} data.
     */
    public DefaultUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new {@code DefaultUserController}.
     * Will use a {@link UserHibernateRepository} as {@link UserRepository} implementation.
     */
    public DefaultUserController() {
        this(new UserHibernateRepository());
    }

    @Override
    public Either<ServerError, User> login(String username, String password) {

        InputValidatorChain inputValidation = new InputValidatorChain();
        inputValidation.add(new NonNullInputValidator(username))
                .add(new NonNullInputValidator(password))
                .add(new NotEmptyStringInputValidator(username))
                .add(new NotEmptyStringInputValidator(password));

        Supplier<User> retrieveFunction = () -> userRepository.get(username);

        OutputValidatorChain<User> outputValidation = new OutputValidatorChain<>();
        outputValidation.add(new PasswordOutputValidator(password));

        return BusinessLogic.execute(retrieveFunction, inputValidation, outputValidation, new LoginExceptionHandler());
    }

    @Override
    public Either<ServerError, User> register(String username, String password) {
        return null;
    }

    @Override
    public Either<ServerError, List<Conversation>> getConversations(long userId) {
        return null;
    }


}
