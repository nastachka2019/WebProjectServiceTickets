package by.epam.project.entity;
/**
 * Класс-сущность для  EventType
 *
 * @author Shpakova A.
 */

public class EventType extends Entity {
    private int eventTypeId;
  private String eventTypeValue;          // concert, theatre, sport, etc

    public EventType() {
    }

    public EventType(int eventTypeId, String eventTypeValue) {
        this.eventTypeId = eventTypeId;
   this.eventTypeValue=eventTypeValue;

    }

    public int getEventTypeId() {
        return eventTypeId;
    }

    public void setEventTypeId(int eventTypeId) {
        this.eventTypeId=eventTypeId;
    }

    public String getEventTypeValue() {
        return eventTypeValue;
    }

    public void setEventTypeValue(String eventTypeValue) {
        this.eventTypeValue = eventTypeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        EventType eventType = (EventType) o;
        return eventTypeId== eventType.eventTypeId &&
                (eventTypeValue == eventType.eventTypeValue || (eventTypeValue != null && eventTypeValue.equals(eventType.getEventTypeValue())));


    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + eventTypeId;
        result = prime * result + ((eventTypeValue == null) ? 0 : eventTypeValue.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("EventType {" + "eventTypeId=" + " " + eventTypeId + ", eventTypeValue=" + " " + eventTypeValue +"}");  // в строковом представлении
        return str.toString();
    }


    public static class Builder {                    //dao
        private EventType eventType;

        public Builder() {
            eventType = new EventType();
        }

        public Builder setEventTypeId(int eventTypeId){
            eventType.eventTypeId=eventTypeId;
            return this;
        }
        public Builder setEventTypeValue(String eventTypeValue){
            eventType.eventTypeValue=eventTypeValue;
            return this;
        }

        public EventType build() {
            return eventType;
        }
    }
}
