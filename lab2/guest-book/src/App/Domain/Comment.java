package App.Domain;

import java.util.Date;

public class Comment {
    private String name;
    private String email;
    private String comment;
    private Date date;

    public Comment(String name, String email, String comment) {
        this.name = name;
        this.email = email;
        this.comment = comment;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }
}
