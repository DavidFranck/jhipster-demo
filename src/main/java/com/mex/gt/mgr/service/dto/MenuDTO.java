package com.mex.gt.mgr.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Menu entity.
 */
public class MenuDTO implements Serializable {

    private Long id;

    private String name;

    private String href;

    private String icon;

    private String target;

    private String isShow;

    private String permission;

    private Integer sort;

    private Long menuId;

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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MenuDTO menuDTO = (MenuDTO) o;
        if(menuDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), menuDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MenuDTO{" +
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
