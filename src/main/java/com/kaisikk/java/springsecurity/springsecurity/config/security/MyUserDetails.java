package com.kaisikk.java.springsecurity.springsecurity.config.security;

import com.kaisikk.java.springsecurity.springsecurity.model.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * @author Kaisikk
 *
 * Класс пользователя чтобы логиниться.
 */
public class MyUserDetails implements UserDetails {

    private MyUser user;

    public MyUserDetails (MyUser user){
    this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(user.getRoles().split(", "))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * Получение пароля
     *
     * @return String
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Получение имя пользователя
     *
     * @return
     */
    @Override
    public String getUsername() {
        return user.getName();
    }

    /**
     * Проверка истек ли срок действия учетной записи
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Проверка заблочен ли пользователь или нет
     *
     * @return boolean
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Указывает действительны ли учетные данные пользователя
     *
     * @return boolean
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Проверка включен ли пользователь или нет
     *
     * @return boolean
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
