package szpital.users.filter;

import szpital.users.session.UserContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationFilter implements Filter {

  public AuthorizationFilter() {
  }

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {

  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response,
                       FilterChain chain) throws IOException, ServletException {
    try {

      HttpServletRequest reqt = (HttpServletRequest) request;
      HttpServletResponse resp = (HttpServletResponse) response;
      HttpSession ses = reqt.getSession(false);

      String reqURI = reqt.getRequestURI();
      if (reqURI.contains("/login")
          || reqURI.contains("/login.xhtml")
          || reqURI.contains("/public/")
          || reqURI.contains("/bootstrap/")
          || reqURI.contains("javax.faces.resource")
          || (ses != null && ses.getAttribute("user") != null &&
          ((UserContext) ses.getAttribute("user")).isAuthenticated()))
        chain.doFilter(request, response);
      else {
        resp.sendRedirect(reqt.getContextPath() + "/login?faces-redirect=true");
        return;
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }

  @Override
  public void destroy() {

  }
}
