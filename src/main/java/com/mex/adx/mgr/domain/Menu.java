package com.mex.adx.mgr.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * The Resource entity.
 */
@ApiModel(description = "The Menu entity.")
@Entity
@Table(name = "sys_menu")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "href")
    private String href;

    @Column(name = "icon")
    private String icon;

    @Column(name = "target")
    private String target;

    @Column(name = "is_show")
    private String isShow;

    @Column(name = "permission")
    private String permission;

    @Column(name = "sort")
    private Integer sort;

    @ManyToOne
    private Menu menu;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Menu> children = new HashSet<>();

    @ManyToMany(mappedBy = "menus")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Authority> authorities = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Menu name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHref() {
        return href;
    }

    public Menu href(String href) {
        this.href = href;
        return this;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public Menu icon(String icon) {
        this.icon = icon;
        return this;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public Menu target(String target) {
        this.target = target;
        return this;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIsShow() {
        return isShow;
    }

    public Menu isShow(String isShow) {
        this.isShow = isShow;
        return this;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public Menu permission(String permission) {
        this.permission = permission;
        return this;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSort() {
        return sort;
    }

    public Menu sort(Integer sort) {
        this.sort = sort;
        return this;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Menu getMenu() {
        return menu;
    }

    public Menu menu(Menu menu) {
        this.menu = menu;
        return this;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Set<Menu> getChildren() {
        return children;
    }

    public Menu children(Set<Menu> menus) {
        this.children = menus;
        return this;
    }

    public Menu addChildren(Menu menu) {
        this.children.add(menu);
        menu.setMenu(this);
        return this;
    }

    public Menu removeChildren(Menu menu) {
        this.children.remove(menu);
        menu.setMenu(null);
        return this;
    }

    public void setChildren(Set<Menu> menus) {
        this.children = menus;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public Menu authorities(Set<Authority> authorities) {
        this.authorities = authorities;
        return this;
    }

    public Menu addAuthority(Authority authority) {
        this.authorities.add(authority);
        authority.getMenus().add(this);
        return this;
    }

    public Menu removeAuthority(Authority authority) {
        this.authorities.remove(authority);
        authority.getMenus().remove(this);
        return this;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        if (menu.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menu.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Menu{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", href='" + getHref() + "'" +
            ", icon='" + getIcon() + "'" +
            ", target='" + getTarget() + "'" +
            ", isShow='" + getIsShow() + "'" +
            ", permission='" + getPermission() + "'" +
            ", sort='" + getSort() + "'" +
            "}";
    }
}
