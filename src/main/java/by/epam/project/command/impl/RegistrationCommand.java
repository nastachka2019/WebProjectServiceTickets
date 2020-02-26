package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.MailSenderCommand;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
import by.epam.project.entity.UserRole;
import by.epam.project.exception.ServiceException;
import by.epam.project.service.UserService;
import by.epam.project.service.impl.UserServiceImpl;
import by.epam.project.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Этот класс используется для регистрации пользователя
 *
 * @author Shpakova A.
 */

public class RegistrationCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String USER = "User";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String PHONE= "phone";
    private static final String PASSWORD = "password";
    private static final String CONFIRMED_PASSWORD = "confirmedPassword";
    private static final String ERROR_EMAIL = "errorEmail";
    private static final String ERROR_LOGIN = "errorLogin";
    private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";
    private static final String RESPONSE = "response";
    private static final String EVENT_LIST_PATH = "/visit_event_list";

    @Override
    public String execute(HttpServletRequest request) {
        boolean fail = false;
        String page = null;

        Map<String, String> clientParameters = new HashMap<>();

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String phone = request.getParameter(PHONE);
        String password = request.getParameter(PASSWORD);
        String confirmedPassword = request.getParameter(CONFIRMED_PASSWORD);
        clientParameters.put(NAME, login);
        clientParameters.put(SURNAME, login);
        clientParameters.put(EMAIL, login);
        clientParameters.put(LOGIN, login);
        clientParameters.put(DATE_OF_BIRTH, login);
        clientParameters.put(PHONE, login);
        clientParameters.put(PASSWORD, login);
        clientParameters.put(CONFIRMED_PASSWORD, login);

        UserValidator userValidator=new UserValidator();
        Map<String, String> errorMessages = userValidator.validateData(name, surname, login, phone,  password, confirmedPassword);

        if (errorMessages.isEmpty()) {
            if (Objects.equals(password, confirmedPassword)) {
                UserService userService = new UserServiceImpl();

                try {
                    if (userService.containsEmail(email)) {
                        errorMessages.put(ERROR_EMAIL, "This email is belong to another account");
                    }
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
                try {
                    if (userService.containsLogin(login)) {
                        errorMessages.put(ERROR_LOGIN, "This username is belong to another account");
                    }
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

                if (errorMessages.isEmpty()) {

                    User.Builder newUser = new User.Builder()
                            .setUserRole(UserRole.USER)
                            .setName(name)
                            .setSurname(surname)
                            .setEmail(email)
                            .setLogin(login)
                            .setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));

                    if (!phone.isEmpty()) {
                        newUser.setPhone(phone);
                    }

                    if(!dateOfBirth.isEmpty()) {
                        newUser.setDateOfBirth(LocalDate.parse(dateOfBirth));
                    }
                    try {
                        userService.insertUser(newUser.build());
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }
                    //sending an e-mail about successful registration
                    MailSenderCommand mailSenderCommand= new MailSenderCommand("email", "password",
                            "mailTo", "Test", "MailTest");
                    mailSenderCommand.start();


                    User user = null;
                    try {
                        user = userService.findByLoginAndPass(login, password).get(0);
                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }

                    request.getSession().setAttribute(USER, user);

                    logger.info("New user. Username = " + user.getLogin());

                    request.setAttribute(RESPONSE, true);
                    page = request.getContextPath() + EVENT_LIST_PATH;

                } else {
                    fail = true;
                    page = PathForJsp.REGISTRATION.getUrl();
                }
            } else {
                fail = true;
                errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
            }
        } else {
            fail = true;
        }

        if (fail) {
            request.setAttribute(NAME, name);
            request.setAttribute(SURNAME, surname);
            request.setAttribute(EMAIL, email);
            request.setAttribute(LOGIN, login);
            request.setAttribute(DATE_OF_BIRTH, dateOfBirth);
            request.setAttribute(PHONE, phone);
            errorMessages.forEach(request::setAttribute);
            page = PathForJsp.REGISTRATION.getUrl();
        }

        return page;
    }
}
