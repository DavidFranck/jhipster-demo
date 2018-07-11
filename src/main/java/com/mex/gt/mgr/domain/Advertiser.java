package com.mex.gt.mgr.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import com.mex.gt.mgr.domain.enumeration.DelFlag;

/**
 * A Advertiser.
 */
@Entity
@Table(name = "t_advertiser")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Advertiser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 名称
     */
    @NotNull
    @ApiModelProperty(value = "名称", required = true)
    @Column(name = "contacts", nullable = false)
    private String contacts;

    /**
     * 联系人
     */
    @NotNull
    @ApiModelProperty(value = "联系人", required = true)
    @Column(name = "industry_level_1", nullable = false)
    private String industryLevel1;

    /**
     * 广告主一级行业类型
     */
    @NotNull
    @ApiModelProperty(value = "广告主一级行业类型", required = true)
    @Column(name = "industry_level_2", nullable = false)
    private String industryLevel2;

    /**
     * 广告主二级行业类型
     */
    @NotNull
    @ApiModelProperty(value = "广告主二级行业类型", required = true)
    @Column(name = "website", nullable = false)
    private String website;

    /**
     * 公司网址
     */
    @ApiModelProperty(value = "公司网址")
    @Column(name = "address")
    private String address;

    /**
     * 公司地址
     */
    @ApiModelProperty(value = "公司地址")
    @Column(name = "check_status")
    private String checkStatus;

    /**
     * 审核状态
     */
    @ApiModelProperty(value = "审核状态")
    @Column(name = "wax_uid")
    private String waxUid;

    /**
     * 微博uid
     */
    @ApiModelProperty(value = "微博uid")
    @Column(name = "org_code")
    private String orgCode;

    /**
     * 组织机构代码
     */
    @ApiModelProperty(value = "组织机构代码")
    @Column(name = "org_code_certificate")
    private String orgCodeCertificate;

    /**
     * 组织机构代码证
     */
    @ApiModelProperty(value = "组织机构代码证")
    @Column(name = "business_license")
    private String businessLicense;

    /**
     * 营业执照
     */
    @ApiModelProperty(value = "营业执照")
    @Column(name = "id_card")
    private String idCard;

    /**
     * 法人身份证
     */
    @ApiModelProperty(value = "法人身份证")
    @Column(name = "icp")
    private String icp;

    /**
     * ICP备案
     */
    @ApiModelProperty(value = "ICP备案")
    @Column(name = "taxes_certificate")
    private String taxesCertificate;

    /**
     * 完税证
     */
    @ApiModelProperty(value = "完税证")
    @Column(name = "network_bussiness_certificate")
    private String networkBussinessCertificate;

    /**
     * 网络文化经营许可证
     */
    @ApiModelProperty(value = "网络文化经营许可证")
    @Column(name = "health_org_certificate")
    private String healthOrgCertificate;

    /**
     * 医疗机构执业许可证
     */
    @ApiModelProperty(value = "医疗机构执业许可证")
    @Column(name = "health_ad_file")
    private String healthAdFile;

    /**
     * 医疗广告审查证明
     */
    @ApiModelProperty(value = "医疗广告审查证明")
    @Column(name = "game_auth_certificate")
    private String gameAuthCertificate;

    /**
     * 游戏授权资质
     */
    @ApiModelProperty(value = "游戏授权资质")
    @Column(name = "game_version_file")
    private String gameVersionFile;

    /**
     * 游戏版号文件
     */
    @ApiModelProperty(value = "游戏版号文件")
    @Column(name = "celebrity_endorsement")
    private String celebrityEndorsement;

    /**
     * 明星代言资质
     */
    @ApiModelProperty(value = "明星代言资质")
    @Column(name = "tax_registration")
    private String taxRegistration;

    /**
     * 税务登记证
     */
    @ApiModelProperty(value = "税务登记证")
    @Column(name = "administrativ")
    private String administrativ;

    /**
     * 行政许可
     */
    @ApiModelProperty(value = "行政许可")
    @Column(name = "jhi_authorization")
    private String authorization;

    @Column(name = "remarks")
    private String remarks;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    @Column(name = "create_date")
    private Instant createDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "update_date")
    private Instant updateDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @Column(name = "create_by")
    private Long createBy;

    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @Column(name = "update_by")
    private Long updateBy;

    /**
     * 更新者
     */
    @ApiModelProperty(value = "更新者")
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

    public Advertiser name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContacts() {
        return contacts;
    }

    public Advertiser contacts(String contacts) {
        this.contacts = contacts;
        return this;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getIndustryLevel1() {
        return industryLevel1;
    }

    public Advertiser industryLevel1(String industryLevel1) {
        this.industryLevel1 = industryLevel1;
        return this;
    }

    public void setIndustryLevel1(String industryLevel1) {
        this.industryLevel1 = industryLevel1;
    }

    public String getIndustryLevel2() {
        return industryLevel2;
    }

    public Advertiser industryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2;
        return this;
    }

    public void setIndustryLevel2(String industryLevel2) {
        this.industryLevel2 = industryLevel2;
    }

    public String getWebsite() {
        return website;
    }

    public Advertiser website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public Advertiser address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public Advertiser checkStatus(String checkStatus) {
        this.checkStatus = checkStatus;
        return this;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getWaxUid() {
        return waxUid;
    }

    public Advertiser waxUid(String waxUid) {
        this.waxUid = waxUid;
        return this;
    }

    public void setWaxUid(String waxUid) {
        this.waxUid = waxUid;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public Advertiser orgCode(String orgCode) {
        this.orgCode = orgCode;
        return this;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgCodeCertificate() {
        return orgCodeCertificate;
    }

    public Advertiser orgCodeCertificate(String orgCodeCertificate) {
        this.orgCodeCertificate = orgCodeCertificate;
        return this;
    }

    public void setOrgCodeCertificate(String orgCodeCertificate) {
        this.orgCodeCertificate = orgCodeCertificate;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public Advertiser businessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
        return this;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getIdCard() {
        return idCard;
    }

    public Advertiser idCard(String idCard) {
        this.idCard = idCard;
        return this;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getIcp() {
        return icp;
    }

    public Advertiser icp(String icp) {
        this.icp = icp;
        return this;
    }

    public void setIcp(String icp) {
        this.icp = icp;
    }

    public String getTaxesCertificate() {
        return taxesCertificate;
    }

    public Advertiser taxesCertificate(String taxesCertificate) {
        this.taxesCertificate = taxesCertificate;
        return this;
    }

    public void setTaxesCertificate(String taxesCertificate) {
        this.taxesCertificate = taxesCertificate;
    }

    public String getNetworkBussinessCertificate() {
        return networkBussinessCertificate;
    }

    public Advertiser networkBussinessCertificate(String networkBussinessCertificate) {
        this.networkBussinessCertificate = networkBussinessCertificate;
        return this;
    }

    public void setNetworkBussinessCertificate(String networkBussinessCertificate) {
        this.networkBussinessCertificate = networkBussinessCertificate;
    }

    public String getHealthOrgCertificate() {
        return healthOrgCertificate;
    }

    public Advertiser healthOrgCertificate(String healthOrgCertificate) {
        this.healthOrgCertificate = healthOrgCertificate;
        return this;
    }

    public void setHealthOrgCertificate(String healthOrgCertificate) {
        this.healthOrgCertificate = healthOrgCertificate;
    }

    public String getHealthAdFile() {
        return healthAdFile;
    }

    public Advertiser healthAdFile(String healthAdFile) {
        this.healthAdFile = healthAdFile;
        return this;
    }

    public void setHealthAdFile(String healthAdFile) {
        this.healthAdFile = healthAdFile;
    }

    public String getGameAuthCertificate() {
        return gameAuthCertificate;
    }

    public Advertiser gameAuthCertificate(String gameAuthCertificate) {
        this.gameAuthCertificate = gameAuthCertificate;
        return this;
    }

    public void setGameAuthCertificate(String gameAuthCertificate) {
        this.gameAuthCertificate = gameAuthCertificate;
    }

    public String getGameVersionFile() {
        return gameVersionFile;
    }

    public Advertiser gameVersionFile(String gameVersionFile) {
        this.gameVersionFile = gameVersionFile;
        return this;
    }

    public void setGameVersionFile(String gameVersionFile) {
        this.gameVersionFile = gameVersionFile;
    }

    public String getCelebrityEndorsement() {
        return celebrityEndorsement;
    }

    public Advertiser celebrityEndorsement(String celebrityEndorsement) {
        this.celebrityEndorsement = celebrityEndorsement;
        return this;
    }

    public void setCelebrityEndorsement(String celebrityEndorsement) {
        this.celebrityEndorsement = celebrityEndorsement;
    }

    public String getTaxRegistration() {
        return taxRegistration;
    }

    public Advertiser taxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration;
        return this;
    }

    public void setTaxRegistration(String taxRegistration) {
        this.taxRegistration = taxRegistration;
    }

    public String getAdministrativ() {
        return administrativ;
    }

    public Advertiser administrativ(String administrativ) {
        this.administrativ = administrativ;
        return this;
    }

    public void setAdministrativ(String administrativ) {
        this.administrativ = administrativ;
    }

    public String getAuthorization() {
        return authorization;
    }

    public Advertiser authorization(String authorization) {
        this.authorization = authorization;
        return this;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getRemarks() {
        return remarks;
    }

    public Advertiser remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public Advertiser createDate(Instant createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public Advertiser updateDate(Instant updateDate) {
        this.updateDate = updateDate;
        return this;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public Advertiser createBy(Long createBy) {
        this.createBy = createBy;
        return this;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public Advertiser updateBy(Long updateBy) {
        this.updateBy = updateBy;
        return this;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public DelFlag getDelFlag() {
        return delFlag;
    }

    public Advertiser delFlag(DelFlag delFlag) {
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
        Advertiser advertiser = (Advertiser) o;
        if (advertiser.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), advertiser.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Advertiser{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", contacts='" + getContacts() + "'" +
            ", industryLevel1='" + getIndustryLevel1() + "'" +
            ", industryLevel2='" + getIndustryLevel2() + "'" +
            ", website='" + getWebsite() + "'" +
            ", address='" + getAddress() + "'" +
            ", checkStatus='" + getCheckStatus() + "'" +
            ", waxUid='" + getWaxUid() + "'" +
            ", orgCode='" + getOrgCode() + "'" +
            ", orgCodeCertificate='" + getOrgCodeCertificate() + "'" +
            ", businessLicense='" + getBusinessLicense() + "'" +
            ", idCard='" + getIdCard() + "'" +
            ", icp='" + getIcp() + "'" +
            ", taxesCertificate='" + getTaxesCertificate() + "'" +
            ", networkBussinessCertificate='" + getNetworkBussinessCertificate() + "'" +
            ", healthOrgCertificate='" + getHealthOrgCertificate() + "'" +
            ", healthAdFile='" + getHealthAdFile() + "'" +
            ", gameAuthCertificate='" + getGameAuthCertificate() + "'" +
            ", gameVersionFile='" + getGameVersionFile() + "'" +
            ", celebrityEndorsement='" + getCelebrityEndorsement() + "'" +
            ", taxRegistration='" + getTaxRegistration() + "'" +
            ", administrativ='" + getAdministrativ() + "'" +
            ", authorization='" + getAuthorization() + "'" +
            ", remarks='" + getRemarks() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", updateDate='" + getUpdateDate() + "'" +
            ", createBy='" + getCreateBy() + "'" +
            ", updateBy='" + getUpdateBy() + "'" +
            ", delFlag='" + getDelFlag() + "'" +
            "}";
    }
}
