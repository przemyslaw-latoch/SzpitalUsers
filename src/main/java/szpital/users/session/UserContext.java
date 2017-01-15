package szpital.users.session;

import lombok.Getter;
import lombok.Setter;
import szpital.users.data.User;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

@SessionScoped
@Getter
@Setter
public class UserContext implements Serializable {
  private User user;
  private String userLogin;
  private String userName;
  private String userLastName;
  private String userCity;
  private int userAge;
  private Date userBirthDate;
  private boolean authenticated;
  private String notes;

  public void initialize(User user) {
    this.user = user;
    this.userLogin = user.getLogin();
    this.userName = user.getName();
    this.userLastName = user.getLastName();
    this.userCity = "Lublin";
    this.userBirthDate = user.getBirthday();
    this.userAge = Period.between(user.getBirthday().toLocalDate(), LocalDate.now()).getYears();;
    this.authenticated = true;
    this.notes = user.getNotes();
  }
}
