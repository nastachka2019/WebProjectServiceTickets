package by.epam.project.entity;

import java.time.LocalDate;

/**
 *  Class for storing of ticket's data
 *
 * @author Shpakova A.
 */


public class Ticket extends Entity {
    private int ticketId;
    private User user;
    private Activity event;
    private EventType eventType;
    private int quantity;
    private LocalDate date;


    public Ticket() {
    }

    public Ticket(int ticketId, Activity event, EventType eventType, User user, int quantity, LocalDate date) {
        this.ticketId = ticketId;
        this.user = user;
        this.event = event;
        this.eventType = eventType;
        this.quantity = quantity;
        this.date = date;

    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Activity getEvent() {
        return event;
    }

    public void setEvent(Activity event) {
        this.event = event;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Ticket ticket = (Ticket) o;
        return ticketId == ticket.ticketId && (user == ticket.user || (user != null && user.equals(ticket.getUser()))) &&
                (event == ticket.event || (event != null && event.equals(ticket.getEvent()))) &&
                (eventType == ticket.eventType || (eventType != null && eventType.equals(ticket.getEventType()))) &&
                quantity == ticket.quantity && (date == ticket.date || (date != null && date.equals(ticket.getEventType())));


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ticketId;
        result = prime * result + quantity;
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        result = prime * result + ((event == null) ? 0 : event.hashCode());
        result = prime * result + ((eventType == null) ? 0 : eventType.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Ticket {" + "ticketId=" + " " + ticketId + ", user=" + " " + user + ", event=" + " " + event
                + ", eventType=" + eventType + ", quantity=" + quantity + ", dateOfTicket=" + date + "}");  // в строковом представлении
        return str.toString();
    }

    public static class Builder {                    
        private Ticket ticket;

        public Builder() {
            ticket = new Ticket();
        }

        public Builder setTicketId(int ticketId) {
            ticket.ticketId = ticketId;
            return this;
        }

        public Builder setUser(User user) {
            ticket.user = user;
            return this;
        }

        public Builder setEvent(Activity event) {
            ticket.event = event;
            return this;
        }

        public Builder setEventType(EventType eventType) {
            ticket.eventType = eventType;
            return this;
        }

        public Builder setQuantity(int quantity) {
            ticket.quantity = quantity;
            return this;
        }
        public Builder setDate(LocalDate date) {
            ticket.date=date;
            return this;
        }

        public Ticket build() {
            return ticket;
        }
    }
}
