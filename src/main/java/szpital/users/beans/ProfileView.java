package szpital.users.beans;

import lombok.Getter;
import lombok.Setter;
import szpital.users.data.User;
import szpital.users.services.AuthService;
import szpital.users.services.UserService;
import szpital.users.session.UserContext;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.Objects;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class ProfileView implements Serializable {
  @Inject
  private UserContext userContext;
  @Inject
  private AuthService authService;
  @Inject
  private UserService userService;
  private String oldPass;
  private String newPass;
  private String confirmNewPass;
  private boolean showChangePass = false;

  public void showChangePass() {
    showChangePass = true;
  }

  public void executeChangePass() {
    User user = userContext.getUser();
    String s = authService.calculateMD5(user.getLogin(), oldPass);
    if (Objects.equals(s, user.getPasswordHash()) && Objects.equals(newPass, confirmNewPass)) {
      oldPass = null;
      String newHash = authService.calculateMD5(user.getLogin(), newPass);
      user.setPasswordHash(newHash);
      userService.update(user);
      return;
    }
    FacesContext.getCurrentInstance().addMessage("Coś jest źle", new FacesMessage("Coś jest źle"));
  }

}
