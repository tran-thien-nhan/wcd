package models;

import javax.persistence.*;

@Entity(name = "todo")
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String Dated;
    private String Status;

    public Todo() {
    }

    public Todo(String title, String Dated, String Status) {
        this.title = title;
        this.Dated = Dated;
        this.Status = Status;
    }

    public Todo(Long id, String title, String Dated, String Status) {
        this.id = id;
        this.title = title;
        this.Dated = Dated;
        this.Status = Status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDated() {
        return Dated;
    }

    public void setDated(String Dated) {
        this.Dated = Dated;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    
}
