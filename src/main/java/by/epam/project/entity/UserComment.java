package by.epam.project.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class for storing of user comment's data
 *
 * @author Shpakova A.
 */

public class UserComment extends Entity {
    private int commentId;
    private LocalDateTime dateOfComment;
    private LocalDate ticketDate;
    private String comment;
    private int userId;
    private User commentator;


    public UserComment() {
    }

    public UserComment(int commentId, LocalDateTime dateOfComment, LocalDate ticketDate, String comment, int userId, User commentator) {
        this.commentId = commentId;
        this.dateOfComment = dateOfComment;
        this.ticketDate = ticketDate;
        this.comment = comment;
        this.userId = userId;
        this.commentator = commentator;
    }


    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public LocalDateTime getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(LocalDateTime dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    public LocalDate getTicketDate() {
        return ticketDate;
    }

    public void setTicketDate(LocalDate ticketDate) {
        this.ticketDate = ticketDate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public User getCommentator() {
        return commentator;
    }

    public void setCommentator(User commentator) {
        this.commentator = commentator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        UserComment userComment = (UserComment) o;
        return commentId == userComment.commentId && userId == userComment.userId &&
                (dateOfComment == userComment.dateOfComment || (dateOfComment != null && dateOfComment.equals(userComment.getDateOfComment()))) &&
                (ticketDate == userComment.ticketDate || (ticketDate != null && ticketDate.equals(userComment.getTicketDate()))) &&
                (comment == userComment.comment || (comment != null && comment.equals(userComment.getComment()))) &&
                (commentator == userComment.commentator || (commentator != null && commentator.equals(userComment.getCommentator())));

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + commentId;
        result = prime * result + userId;
        result = prime * result + ((dateOfComment == null) ? 0 : dateOfComment.hashCode());
        result = prime * result + ((ticketDate == null) ? 0 : ticketDate.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((commentator == null) ? 0 : commentator.hashCode());

        return result;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("UserComments{" + "commentId=" + commentId + ", userId=" + userId + ", commentDate=" + dateOfComment +
                ", ticketDate=" + ticketDate + ", comment='" + comment + ", commentator" + commentator + '}');
        return str.toString();
    }

    public static class Builder {                    //dao
        private UserComment userComment;

        public Builder() {
            userComment = new UserComment();
        }

        public Builder setCommentId(int commentId) {
            userComment.commentId = commentId;
            return this;
        }


        public Builder setUserId(int userId) {
            userComment.userId = userId;
            return this;
        }

        public Builder setDateOfComment(LocalDateTime dateOfComment) {
            userComment.dateOfComment = dateOfComment;
            return this;
        }

        public Builder setTicketDate(LocalDate ticketDate) {
            userComment.ticketDate = ticketDate;
            return this;
        }

        public Builder setComment(String comment) {
            userComment.comment = comment;
            return this;
        }

        public Builder setCommentator(User commentator) {
            userComment.commentator = commentator;
            return this;
        }

        public UserComment build() {
            return userComment;
        }
    }
}
