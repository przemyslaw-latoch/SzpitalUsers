package szpital.users.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "sys_user")
@Getter
@Setter
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByLogin", query = "SELECT u FROM User u WHERE u.login = :login"),
    @NamedQuery(name = "User.findWithHash",
        query = "SELECT u FROM User u WHERE u.login = :login AND u.passwordHash = :hash")
})
public class User implements Serializable {

  @Id
  @SequenceGenerator(name="sys_user_id_seq",
      sequenceName="sys_user_id_seq",
      allocationSize=1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
      generator="sys_user_id_seq")
  Long id;
  @Column(name = "login")
  String login;
  @Column(name = "password_hash")
  String passwordHash;
  @Enumerated(EnumType.STRING)
  @Column(name = "role")
  UserRole role;
  @Column(name = "email")
  String email;
  @Column(name = "name")
  String name;
  @Column(name = "last_name")
  String lastName;
  @Column(name = "birthday")
  Date birthday;
  @Column(name = "notes")
  String notes;

  public boolean isAdmin() {
    return role != null && role == UserRole.ADMINISTRATOR;
  }
}
