package by.epam.project.command;

import by.epam.project.command.impl.*;
import static by.epam.project.command.CommandType.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CommandMap {
    private static final Logger logger = LogManager.getLogger();

    private static CommandMap instance;
    private static ReentrantLock lock = new ReentrantLock();
    private static AtomicBoolean create = new AtomicBoolean(false);

    private EnumMap<CommandType, Command> commandMap = new EnumMap<CommandType, Command>(CommandType.class);

    private CommandMap() {
        commandMap.put(SIGN_IN, new SignInCommand());
        commandMap.put(VISIT_SIGN_IN, new VisitSignInCommand());
        commandMap.put(SEARCH, new SearchCommand());
        commandMap.put(EVENT_LIST, new EventListCommand());
        commandMap.put(SETTINGS, new SettingsCommand());
        commandMap.put(REGISTRATION, new RegistrationCommand());
        commandMap.put(VISIT_REGISTRATION, new VisitRegistrationCommand());
        commandMap.put(VISIT_SETTINGS, new VisitSettingsCommand());
        commandMap.put(VISIT_EVENT_LIST, new VisitEventListCommand());
        commandMap.put(UPDATE_QUANTITY_IN_TICKET, new UpdateQuantityInOrderCommand());
        commandMap.put(TRANSLATE, new TranslateCommand());
        commandMap.put(SIGN_OUT, new SignOutCommand());
        commandMap.put(SHOW_EVENT_DETAILS, new ShowEventDetailsCommand());
        commandMap.put(SHOW_ORDER, new ShowOrderCommand());
        commandMap.put(ORDER, new OrderCommand());
        commandMap.put(DELETE_TICKET, new DeleteTicketCommand());
        commandMap.put(DELETE_COMMENT, new DeleteCommentCommand());
        commandMap.put(COMMENT, new CommentCommand());
        commandMap.put(ADD_TICKET, new AddTicketCommand());
        commandMap.put(USER_LIST, new UserListCommand());
        commandMap.put(SHOW_USER_DETAILS, new ShowUserDetailsCommand());
        commandMap.put(DELETE_USER, new DeleteUserCommand());
        commandMap.put(CHANGE_USER_ROLE, new ChangeUserRoleCommand());
    }

    public static CommandMap getInstance() {
        if (!create.get()) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new CommandMap();
                    create.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Command receiveCommand(String commandType) {
        Command command = null;

        try {
            if(commandType!=null){
                command = commandMap.get(CommandType.valueOf(commandType.toUpperCase()));
            } else {
                logger.error("commandType is null. Error!!!");
            }
        } catch (IllegalArgumentException e) {
         logger.error(e.getMessage(), e);
        }

        return command;
    }

    public Command receiveCommand(CommandType commandType) { //factory method
        return commandMap.get(commandType);
    }

}
