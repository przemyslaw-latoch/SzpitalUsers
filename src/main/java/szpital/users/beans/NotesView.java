package szpital.users.beans;

import lombok.Getter;
import lombok.Setter;
import szpital.users.services.UserService;
import szpital.users.session.UserContext;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@ViewScoped
@Getter
@Setter
public class NotesView implements Serializable {
  @Inject
  private UserContext userContext;
  @Inject
  private UserService userService;

  public void executeSaveNotes() {
    userContext.getUser().setNotes(userContext.getNotes());
    userService.update(userContext.getUser());
  }

}
