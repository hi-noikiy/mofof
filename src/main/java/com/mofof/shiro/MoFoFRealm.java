package com.mofof.shiro;

import java.util.Set;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.mofof.entity.system.MenuItem;
import com.mofof.entity.system.Perm;
import com.mofof.entity.system.Role;
import com.mofof.entity.system.User;
import com.mofof.entity.system.UserAccount;
import com.mofof.service.MenuItemService;
import com.mofof.service.PermService;
import com.mofof.service.RoleService;
import com.mofof.service.UserAccountService;
import com.mofof.service.UserService;

/**
 * 
 */
public class MoFoFRealm extends AuthorizingRealm {

  @Autowired
  private UserService userService;
  @Autowired
  private UserAccountService userAccountService;
  @Autowired
  private RoleService roleService;
  @Autowired
  private PermService permService;
  @Autowired
  private MenuItemService menuItemService;

  {
    HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
    hashMatcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
    hashMatcher.setStoredCredentialsHexEncoded(false);
    hashMatcher.setHashIterations(1024);
    this.setCredentialsMatcher(hashMatcher);
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // null usernames are invalid
    if (principals == null) {
      throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
    }

    UserAccount userAccount = (UserAccount) getAvailablePrincipal(principals);

    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
    // info.setRoles(user.getRoles());
    // info.setStringPermissions(user.getPerms());
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

    UsernamePasswordToken upToken = (UsernamePasswordToken) token;
    String username = upToken.getUsername();

    // Null username is invalid
    if (username == null) {
      throw new AccountException("Null usernames are not allowed by this realm.");
    }

    UserAccount userAccount = userAccountService.findByName(username);

    if (userAccount == null) {
      throw new UnknownAccountException("No account found for admin [" + username + "]");
    }    
   
      if (User.SUSPEND == userAccount.getUser().getStatus()) {
        throw new AccountException("[" + username + "] is suspend");     
    }
    

    User user = userAccount.getUser();
    Role role = user.getRole();
    if (role != null) {
      Set<Perm> perms = permService.getRolePerms(role);
      Set<MenuItem> menuItems = menuItemService.getRoleMenuItems(role);
      //user.getRole().addPerms(perms);
      permService.addPerms(role, perms);
      //user.getRole().addMenuItems(menuItems);
      menuItemService.addMenuItems(role, menuItems);
    }
    SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userAccount, userAccount.getPassword(),
        userAccount.getUsername());

    if (userAccount.getSalt() != null) {
      info.setCredentialsSalt(ByteSource.Util.bytes(userAccount.getSalt()));
    }

    return info;

  }

}