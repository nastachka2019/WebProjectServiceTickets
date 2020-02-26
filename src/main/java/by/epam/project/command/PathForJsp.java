package by.epam.project.command;

/**
 * Класс, сщдержащий пути к  jsp файлам
 *
 * @author Shpakova A.
 */
public enum PathForJsp {
    INDEX("/index.jsp"),
    REGISTRATION("/jsp/registration.jsp"),
    HOME("/jsp/home.jsp"),
    ERROR("/jsp/error.jsp"),
    SIGN_IN("/jsp/sign_in.jsp"),
    SETTINGS("/jsp/settings.jsp"),
    EVENT_LIST("/jsp/eventList.jsp"),
    EVENT("/jsp/event.jsp"),
    USER_LIST("/jsp/userList.jsp"),
    USER("/jsp/user.jsp"),
    ORDER("/jsp/order.jsp");


    private String url;

    PathForJsp(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
