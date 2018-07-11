package com.mex.gt.mgr.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.mex.gt.mgr.domain.enumeration.Os;

import com.mex.gt.mgr.domain.enumeration.ProductType;

import com.mex.gt.mgr.domain.enumeration.DelFlag;

/**
 * A Product.
 */
@Entity
@Table(name = "t_product")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 产品名称
     */
    @NotNull
    @ApiModelProperty(value = "产品名称", required = true)
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 预览链接
     */
    @NotNull
    @ApiModelProperty(value = "预览链接", required = true)
    @Column(name = "preview_url", nullable = false)
    private String previewUrl;

    /**
     * 平台
     */
    @NotNull
    @ApiModelProperty(value = "平台", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "os", nullable = false)
    private Os os;

    /**
     * 产品类型
     */
    @NotNull
    @ApiModelProperty(value = "产品类型", required = true)
    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type", nullable = false)
    private ProductType type;

    /**
     * 包地址
     */
    @ApiModelProperty(value = "包地址")
    @Column(name = "packet_url")
    private String packetUrl;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "remarks")
    private String remarks;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_date")
    private Instant createDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "update_date")
    private Instant updateDate;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 删除标记
     */
    @ApiModelProperty(value = "删除标记")
    @Enumerated(EnumType.STRING)
    @Column(name = "del_flag")
    private DelFlag delFlag;

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

    public Product name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public Product previewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
        return this;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public Os getOs() {
        return os;
    }

    public Product os(Os os) {
        this.os = os;
        return this;
    }

    public void setOs(Os os) {
        this.os = os;
    }

    public ProductType getType() {
        return type;
    }

    public Product type(ProductType type) {
        this.type = type;
        return this;
    }

    public void setType(ProductType type) {
        this.type = type;
    }

    public String getPacketUrl() {
        return packetUrl;
    }

    public Product packetUrl(String packetUrl) {
        this.packetUrl = packetUrl;
        return this;
    }

    public void setPacketUrl(String packetUrl) {
        this.packetUrl = packetUrl;
    }

    public String getRemarks() {
        return remarks;
    }

    public Product remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Product createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public Product updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public Product createBy(Long createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public Product updateBy(Long updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public DelFlag getDelFlag() {
        return delFlag;
    }

    public Product delFlag(DelFlag delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public void setDelFlag(DelFlag delFlag) {
        this.delFlag = delFlag;
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
        Product product = (Product) o;
        if (product.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), product.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Product{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", previewUrl='" + getPreviewUrl() + "'" +
            ", os='" + getOs() + "'" +
            ", type='" + getType() + "'" +
            ", packetUrl='" + getPacketUrl() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", delFlag='" + getDelFlag() + "'" +
            "}";
    }
}
