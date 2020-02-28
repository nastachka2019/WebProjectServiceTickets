package by.epam.project.command.impl;

import by.epam.project.command.Command;
import by.epam.project.command.PathForJsp;
import by.epam.project.entity.User;
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
import java.util.Map;
import java.util.Objects;

/**
 * Этот класс используется для изменения параметров юзера
 *
 * @author Shpakova A.
 */

public class SettingsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String USER = "User";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String LOGIN = "login";
    private static final String DATE_OF_BIRTH = "dateOfBirth";
    private static final String PHONE = "phone";
    private static final String CURRENT_PASSWORD = "currentPassword";
    private static final String NEW_PASSWORD = "newPassword";
    private static final String CONFIRMED_PASSWORD = "confirmedNewPassword";
    private static final String ERROR_MAIL = "errorEmail";
    private static final String ERROR_LOGIN= "errorLogin";
    private static final String ERROR_PASSWORD = "errorPassword";
    private static final String ERROR_PASS_AND_CONFIRMED_PASS = "errorPassAndConfirmedPassMessage";
    private static final String ERROR_DATA = "errorData";
    private static final String OK = "ok";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)  {

        boolean fail = false;
        Map<String, String> errorMessages;

        User user = (User) request.getSession().getAttribute(USER);
        UserService userService = new UserServiceImpl();

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String login = request.getParameter(LOGIN);
        String dateOfBirth = request.getParameter(DATE_OF_BIRTH);
        String phone = request.getParameter(PHONE);
        String currentPassword = request.getParameter(CURRENT_PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        String confirmedNewPassword = request.getParameter(CONFIRMED_PASSWORD);

        UserValidator userDataValidator = new UserValidator();
        errorMessages = userDataValidator.validateData(name, surname, login, phone);

        boolean updatePass = false;

        try {
            if (!user.getEmail().equals(email) && userService.containsEmail(email)) {
                errorMessages.put(ERROR_MAIL, "This email is taken by another account");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            if (!user.getLogin().equals(login) && !errorMessages.containsKey("errorUsername")
                    && userService.containsLogin(login)) {
                errorMessages.put(ERROR_LOGIN, "This login is taken by another account");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        if (!newPassword.isEmpty()) {
            if (!confirmedNewPassword.isEmpty()) {
                if (BCrypt.checkpw(currentPassword, user.getPassword())) {
                    if (!userDataValidator.validatePass(newPassword) || !userDataValidator.validatePass(confirmedNewPassword)) {
                        errorMessages.put(ERROR_PASSWORD, "Password does not match the requirements");
                    } else {
                        if (Objects.equals(newPassword, confirmedNewPassword)) {
                            updatePass = true;
                        } else {
                            errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
                        }
                    }
                } else {
                    errorMessages.put(ERROR_PASSWORD, "Wrong password");
                }

            } else {
                errorMessages.put(ERROR_PASS_AND_CONFIRMED_PASS, "Password and confirmed password do not match");
            }
        }

        if (errorMessages.isEmpty()) {
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setLogin(login);
            if (!dateOfBirth.isEmpty()) {
                user.setDateOfBirth(LocalDate.parse(dateOfBirth));
            }else{
                user.setDateOfBirth(null);
            }
            if(!phone.isEmpty()){
                user.setPhone(phone);
            }else{
                user.setPhone(" ");
            }

            if (updatePass) {
                user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt(12)));
            }

            try {
                userService.update(user);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        } else {
            fail = true;
        }

        request.getSession().setAttribute(USER, user);

        if (fail) {
            errorMessages.forEach(request::setAttribute);
            request.setAttribute(ERROR_DATA, "Changes not saved");
        } else {
            request.setAttribute(CURRENT_PASSWORD, currentPassword);
            request.setAttribute(NEW_PASSWORD, newPassword);
            request.setAttribute(CONFIRMED_PASSWORD, confirmedNewPassword);

            request.setAttribute(OK, "Changes saved");
        }

        return PathForJsp.SETTINGS.getUrl();
    }
}
