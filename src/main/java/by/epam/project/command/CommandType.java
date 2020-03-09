package by.epam.project.command;

import by.epam.project.entity.UserRole;
import static by.epam.project.entity.UserRole.*;
import java.util.Arrays;
import java.util.List;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation;

public enum  CommandType {
    SIGN_IN (ANONYMOUS),   //возвратит только анонима
    VISIT_SIGN_IN(ANONYMOUS),
    SEARCH(ADMINISTRATOR,ANONYMOUS,USER),
    EVENT_LIST(ADMINISTRATOR,ANONYMOUS,USER),
    SETTINGS(USER, ADMINISTRATOR),
    REGISTRATION(ANONYMOUS),
    VISIT_REGISTRATION(ANONYMOUS),
    VISIT_SETTINGS(ADMINISTRATOR,USER),
    VISIT_EVENT_LIST(ADMINISTRATOR,ANONYMOUS,USER),
    UPDATE_QUANTITY_IN_TICKET(ADMINISTRATOR,USER),
    TRANSLATE(ADMINISTRATOR,ANONYMOUS,USER),
    SIGN_OUT(ADMINISTRATOR,USER),
    SHOW_EVENT_DETAILS(ADMINISTRATOR,ANONYMOUS,USER),
    SHOW_ORDER(ADMINISTRATOR,USER),
    ORDER(ADMINISTRATOR,USER),
    DELETE_TICKET(ADMINISTRATOR,USER),
    DELETE_COMMENT(ADMINISTRATOR,USER),
    COMMENT(ADMINISTRATOR,USER),
    ADD_TICKET(USER,ADMINISTRATOR),
    USER_LIST(ADMINISTRATOR),
    SHOW_USER_DETAILS(ADMINISTRATOR,USER),
    DELETE_USER(ADMINISTRATOR),
    CHANGE_USER_ROLE(ADMINISTRATOR);


    private List<UserRole> userRoleList;   //все типы пользователей, которые могут использовать соответствующую команду
    CommandType(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    CommandType(UserRole ... userRoles) {
        userRoleList = Arrays.asList(userRoles);
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }
}
