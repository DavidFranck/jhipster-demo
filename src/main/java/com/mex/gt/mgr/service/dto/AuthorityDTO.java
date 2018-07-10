package com.mex.gt.mgr.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Authority entity.
 */
public class AuthorityDTO implements Serializable {

    private Long id;

    private String name;

    private Set<MenuDTO> menus = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<MenuDTO> getMenus() {
        return menus;
    }

    public void setMenus(Set<MenuDTO> menus) {
        this.menus = menus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AuthorityDTO authorityDTO = (AuthorityDTO) o;
        if(authorityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), authorityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AuthorityDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
