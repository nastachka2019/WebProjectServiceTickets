//package by.epam.project.command;
//
//import by.epam.project.command.impl.*;
//import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.atomic.AtomicBoolean;
//import java.util.concurrent.locks.ReentrantLock;
//
//
//public class CommandManager {
//    private final Logger logger = LogManager.getLogger();
//    private enum CommandType {
//        SIGN_IN, VISIT_SIGN_IN, SEARCH,EVENT_LIST,SETTINGS,REGISTRATION, VISIT_REGISTRATION,VISIT_SETTINGS,
//        VISIT_EVENT_LIST,UPDATE_QUANTITY_IN_TICKET,TRANSLATE,SIGN_OUT,SHOW_EVENT_DETAILS, SHOW_ORDER, ORDER,
//        DELETE_TICKET,DELETE_COMMENT, COMMENT, ADD_TICKET,  USER_LIST,SHOW_USER_DETAILS, DELETE_USER, CHANGE_USER_ROLE
//    }
//    private static Map<CommandType, Command> commandMap;
//    private static CommandManager instance;
//    private static ReentrantLock lock = new ReentrantLock();
//    private static AtomicBoolean create = new AtomicBoolean(false);
//
//    static {
//        commandMap = new HashMap<>();
//        commandMap.put(CommandType.SIGN_IN, new SignInCommand());
//        commandMap.put(CommandType.VISIT_SIGN_IN, new VisitSignInCommand());
//        commandMap.put(CommandType.SEARCH, new SearchCommand());
//        commandMap.put(CommandType.EVENT_LIST, new EventListCommand());
//        commandMap.put(CommandType.SETTINGS, new SettingsCommand());
//        commandMap.put(CommandType.REGISTRATION, new RegistrationCommand());
//        commandMap.put(CommandType.VISIT_REGISTRATION, new VisitRegistrationCommand());
//        commandMap.put(CommandType.VISIT_SETTINGS, new VisitSettingsCommand());
//        commandMap.put(CommandType.VISIT_EVENT_LIST, new VisitEventListCommand());
//        commandMap.put(CommandType.UPDATE_QUANTITY_IN_TICKET, new UpdateQuantityInOrderCommand());
//        commandMap.put(CommandType.TRANSLATE, new TranslateCommand());
//        commandMap.put(CommandType.SIGN_OUT, new SignOutCommand());
//        commandMap.put(CommandType.SHOW_EVENT_DETAILS, new ShowEventDetailsCommand());
//        commandMap.put(CommandType.SHOW_ORDER, new ShowOrderCommand());
//        commandMap.put(CommandType.ORDER, new OrderCommand());
//        commandMap.put(CommandType.DELETE_TICKET, new DeleteTicketCommand());
//        commandMap.put(CommandType.DELETE_COMMENT, new DeleteCommentCommand());
//        commandMap.put(CommandType.COMMENT, new CommentCommand());
//        commandMap.put(CommandType.ADD_TICKET, new AddTicketCommand());
//        commandMap.put(CommandType.USER_LIST, new UserListCommand());
//        commandMap.put(CommandType.SHOW_USER_DETAILS, new ShowUserDetailsCommand());
//        commandMap.put(CommandType.DELETE_USER, new DeleteUserCommand());
//        commandMap.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
//
//    }
//    public static CommandManager getInstance() {
//        if (!create.get()) {
//            try {
//                lock.lock();
//                if (instance == null) {
//                    instance = new CommandManager();
//                    create.set(true);
//                }
//            } finally {
//                lock.unlock();
//            }
//        }
//        return instance;
//    }
//
//    public Command receiveCommand(String commandType) {
//        Command command = null;
//
//        try {
//            if(commandType!=null){
//                command = commandMap.get(CommandType.valueOf(commandType.toUpperCase()));
//            } else {
//               logger.error("commandType is null. Error!!!");
//            }
//        } catch (IllegalArgumentException e) {
//         logger.error(e.getMessage(), e);
//        }
//
//        return command;
//    }
//
//    public Command receiveCommand(CommandType commandType) { //factory method
//        return commandMap.get(commandType);
//    }
//
//}
