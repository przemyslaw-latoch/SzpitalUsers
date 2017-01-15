package szpital.users.beans;

import lombok.Getter;
import lombok.Setter;
import szpital.users.data.User;
import szpital.users.services.AuthService;
import szpital.users.services.UserService;
import szpital.users.session.UserContext;
import szpital.users.util.SessionUtils;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class LoginView implements Serializable {

  @Inject
  private UserContext userContext;
  @Inject
  private AuthService authService;
  @Inject
  private UserService userService;
  private String pass;
  private String msg;
  private String user;

  //validate login
  public String validateUsernamePassword() {
    boolean valid = authService.validate(user, pass);
    if (valid) {
      User user = userService.getUser(this.user);
      userContext.initialize(user);
      HttpSession session = SessionUtils.getSession();
      session.setAttribute("user", userContext);
      return "home?faces-redirect=true";
    } else {
      FacesContext.getCurrentInstance().addMessage(
          null,
          new FacesMessage(FacesMessage.SEVERITY_WARN,
              "Incorrect Username and Password",
              "Please enter correct username and Password"));
      return null;
    }
  }

  //logout event, invalidate session
  public String logout() {
    HttpSession session = SessionUtils.getSession();
    userContext.setAuthenticated(false);
    session.invalidate();
    return "login?faces-redirect=true";
  }
}
