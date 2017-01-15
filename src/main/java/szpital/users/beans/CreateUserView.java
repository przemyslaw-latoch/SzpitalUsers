package szpital.users.beans;

import lombok.Getter;
import lombok.Setter;
import szpital.users.data.User;
import szpital.users.data.UserRole;
import szpital.users.services.AuthService;
import szpital.users.services.UserService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class CreateUserView implements Serializable {
  @Inject
  private UserService userService;
  @Inject
  private AuthService authService;

  private final List<UserRole> roles = Arrays.asList(UserRole.values());
  private final Converter roleConverter = new Converter() {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
      if (s == null) return null;
      return UserRole.valueOf(s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
      return o != null ? ((UserRole) o).name() : "";
    }
  };
  private User user = new User();
  private String pass;
  private String confirmPass;
  private String birthdayString;

  public void saveUser() {
    if (Objects.equals(pass, confirmPass)) {
      String s = authService.calculateMD5(user.getLogin(), pass);
      SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-DD");
      Date parse;
      try {
        parse = parser.parse(birthdayString);
      } catch (ParseException e) {
        parse = new Date();
      }
      user.setBirthday(new java.sql.Date(parse.getTime()));
      user.setPasswordHash(s);
      userService.save(user);
    }
    FacesContext.getCurrentInstance().addMessage("Hasła nie zgodne", new FacesMessage("Hasła nie zgodne"));
  }

}

