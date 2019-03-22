import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "Lottery")
@SessionScoped
public class Lottery {
    private Integer age;
    private String name;
    private String pesel;

    public String send() {
        return (Math.random() < .2) ? "WON" : "LOST";
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
