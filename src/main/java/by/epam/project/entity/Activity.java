package by.epam.project.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Class for storing of activity's data
 *
 * @author Shpakova A.
 */

public class Activity extends Entity {
    private int eventId;
    private String name;
    private String imageURL;
    private String description;
    private String address;
    private LocalDate date;
    private BigDecimal price;

    public Activity() {
    }

    public Activity(int eventId, String name, String imageURL, String description, String address, LocalDate date, BigDecimal price) {
        this.eventId = eventId;
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
        this.address = address;
        this.date = date;
        this.price = price;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Activity event = (Activity) o;
        return eventId == event.eventId &&
                (name == event.name || (name != null && name.equals(event.getName()))) &&
                (imageURL == event.imageURL || (imageURL != null && imageURL.equals(event.getImageURL()))) &&
                (description == event.description || (description != null && description.equals(event.getDescription()))) &&
                (address == event.address || (address != null && address.equals(event.getAddress()))) &&
                (date == event.date || (date != null && date.equals(event.getDate()))) &&
                (price == event.price || (price != null && price.equals(event.getPrice())));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((imageURL == null) ? 0 : imageURL.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((price == null) ? 0 : price.hashCode());
        result = prime * result + eventId;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Event {" + "eventId=" + " " + eventId + ", eventName=" + " " + name + ", eventDescription=" + " " + description
                + ", eventAddress=" + address + ", eventDate=" + " " + date + ", price=" + price + "}");  // в строковом представлении
        return str.toString();
    }

    public static class Builder {                    //dao
        private Activity event;

        public Builder() {
            event = new Activity();
        }

        public Builder setEventId(int eventId) {
            event.eventId = eventId;
            return this;
        }

        public Builder setName(String name) {
            event.name = name;
            return this;
        }

        public Builder setImageURL(String imageURL) {
            event.imageURL = imageURL;
            return this;
        }

        public Builder setDescription(String description) {
            event.description = description;
            return this;
        }

        public Builder setAddress(String address) {
            event.address = address;
            return this;
        }

        public Builder setDate(LocalDate date) {
            event.date = date;
            return this;
        }

        public Builder setPrice(BigDecimal price) {
            event.price = price;
            return this;
        }

        public Activity build() {
            return event;
        }
    }
}