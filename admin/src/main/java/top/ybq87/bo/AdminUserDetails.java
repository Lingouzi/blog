package top.ybq87.bo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import top.ybq87.mbg.model.UmsAdmin;
import top.ybq87.mbg.model.UmsPermission;

/**
 * SpringSecurity需要的用户详情
 *
 * @author 创建人：ly 664162337@qq.com
 * @date 创建日期：2020/1/10 22:15
 */
public class AdminUserDetails implements UserDetails {
    
    private final UmsAdmin umsAdmin;
    private final List<UmsPermission> permissionList;
    
    public AdminUserDetails(UmsAdmin umsAdmin, List<UmsPermission> permissionList) {
        this.umsAdmin = umsAdmin;
        this.permissionList = permissionList;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的权限
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }
    
    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }
    
    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }
}
