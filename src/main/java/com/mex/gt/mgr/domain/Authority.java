package com.mex.gt.mgr.domain;

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
 * The Authority entity.
 */
@ApiModel(description = "The Authority entity.")
@Entity
@Table(name = "sys_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "sys_authority_menu",
        joinColumns = @JoinColumn(name = "authorities_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "menus_id", referencedColumnName = "id"))
    private Set<Menu> menus = new HashSet<>();


    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

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

    public Authority name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public Authority menus(Set<Menu> menus) {
        this.menus = menus;
        return this;
    }

    public Authority addMenu(Menu menu) {
        this.menus.add(menu);
        menu.getAuthorities().add(this);
        return this;
    }

    public Authority removeMenu(Menu menu) {
        this.menus.remove(menu);
        menu.getAuthorities().remove(this);
        return this;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
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
        Authority authority = (Authority) o;
        if (authority.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Authority{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
