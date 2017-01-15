package szpital.users.services;

import szpital.users.data.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Stateless
public class AuthService {

  @PersistenceContext(unitName = "medsystem")
  private EntityManager em;

  public boolean validate(String user, String password) {
    String hash = calculateMD5(user, password);
    try {
      User singleResult =
          em.createNamedQuery("User.findWithHash", User.class)
              .setParameter("hash", hash)
              .setParameter("login", user)
              .getSingleResult();
      return singleResult.getLogin().equals(user);
    } catch (NoResultException ex) {
      return false;
    }
  }

  public String calculateMD5(String user, String password) {
    try {
      String plaintext = "DR" + user + "GRZEGORZ" + password + "WOJCIK";
      MessageDigest m;
      m = MessageDigest.getInstance("MD5");
      m.reset();
      m.update(plaintext.getBytes());
      byte[] digest = m.digest();
      BigInteger bigInt = new BigInteger(1, digest);
      String hashtext = bigInt.toString(16);
      while (hashtext.length() < 32) {
        hashtext = "0" + hashtext;
      }
      return hashtext;
    } catch (NoSuchAlgorithmException e) {
      return "";
    }
  }
}