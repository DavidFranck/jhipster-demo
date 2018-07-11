package com.mex.gt.mgr.web.rest;

import com.mex.gt.mgr.GtMgrApp;

import com.mex.gt.mgr.domain.Advertiser;
import com.mex.gt.mgr.repository.AdvertiserRepository;
import com.mex.gt.mgr.service.AdvertiserService;
import com.mex.gt.mgr.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.mex.gt.mgr.domain.enumeration.DelFlag;
/**
 * Test class for the AdvertiserResource REST controller.
 *
 * @see AdvertiserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GtMgrApp.class)
public class AdvertiserResourceIntTest {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACTS = "AAAAAAAAAA";
    private static final String UPDATED_CONTACTS = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY_LEVEL_1 = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY_LEVEL_1 = "BBBBBBBBBB";

    private static final String DEFAULT_INDUSTRY_LEVEL_2 = "AAAAAAAAAA";
    private static final String UPDATED_INDUSTRY_LEVEL_2 = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_CHECK_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_CHECK_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_WAX_UID = "AAAAAAAAAA";
    private static final String UPDATED_WAX_UID = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_CODE_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_ORG_CODE_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_BUSINESS_LICENSE = "AAAAAAAAAA";
    private static final String UPDATED_BUSINESS_LICENSE = "BBBBBBBBBB";

    private static final String DEFAULT_ID_CARD = "AAAAAAAAAA";
    private static final String UPDATED_ID_CARD = "BBBBBBBBBB";

    private static final String DEFAULT_ICP = "AAAAAAAAAA";
    private static final String UPDATED_ICP = "BBBBBBBBBB";

    private static final String DEFAULT_TAXES_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_TAXES_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_NETWORK_BUSSINESS_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_NETWORK_BUSSINESS_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_HEALTH_ORG_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_HEALTH_ORG_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_HEALTH_AD_FILE = "AAAAAAAAAA";
    private static final String UPDATED_HEALTH_AD_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_GAME_AUTH_CERTIFICATE = "AAAAAAAAAA";
    private static final String UPDATED_GAME_AUTH_CERTIFICATE = "BBBBBBBBBB";

    private static final String DEFAULT_GAME_VERSION_FILE = "AAAAAAAAAA";
    private static final String UPDATED_GAME_VERSION_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_CELEBRITY_ENDORSEMENT = "AAAAAAAAAA";
    private static final String UPDATED_CELEBRITY_ENDORSEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_REGISTRATION = "AAAAAAAAAA";
    private static final String UPDATED_TAX_REGISTRATION = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATIV = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATIV = "BBBBBBBBBB";

    private static final String DEFAULT_AUTHORIZATION = "AAAAAAAAAA";
    private static final String UPDATED_AUTHORIZATION = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    private static final Instant DEFAULT_CREATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_CREATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_UPDATE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_UPDATE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Long DEFAULT_CREATE_BY = 1L;
    private static final Long UPDATED_CREATE_BY = 2L;

    private static final Long DEFAULT_UPDATE_BY = 1L;
    private static final Long UPDATED_UPDATE_BY = 2L;

    private static final DelFlag DEFAULT_DEL_FLAG = DelFlag.ON;
    private static final DelFlag UPDATED_DEL_FLAG = DelFlag.OFF;

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Autowired
    private AdvertiserService advertiserService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAdvertiserMockMvc;

    private Advertiser advertiser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AdvertiserResource advertiserResource = new AdvertiserResource(advertiserService);
        this.restAdvertiserMockMvc = MockMvcBuilders.standaloneSetup(advertiserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Advertiser createEntity(EntityManager em) {
        Advertiser advertiser = new Advertiser()
            .name(DEFAULT_NAME)
            .contacts(DEFAULT_CONTACTS)
            .industryLevel1(DEFAULT_INDUSTRY_LEVEL_1)
            .industryLevel2(DEFAULT_INDUSTRY_LEVEL_2)
            .website(DEFAULT_WEBSITE)
            .address(DEFAULT_ADDRESS)
            .checkStatus(DEFAULT_CHECK_STATUS)
            .waxUid(DEFAULT_WAX_UID)
            .orgCode(DEFAULT_ORG_CODE)
            .orgCodeCertificate(DEFAULT_ORG_CODE_CERTIFICATE)
            .businessLicense(DEFAULT_BUSINESS_LICENSE)
            .idCard(DEFAULT_ID_CARD)
            .icp(DEFAULT_ICP)
            .taxesCertificate(DEFAULT_TAXES_CERTIFICATE)
            .networkBussinessCertificate(DEFAULT_NETWORK_BUSSINESS_CERTIFICATE)
            .healthOrgCertificate(DEFAULT_HEALTH_ORG_CERTIFICATE)
            .healthAdFile(DEFAULT_HEALTH_AD_FILE)
            .gameAuthCertificate(DEFAULT_GAME_AUTH_CERTIFICATE)
            .gameVersionFile(DEFAULT_GAME_VERSION_FILE)
            .celebrityEndorsement(DEFAULT_CELEBRITY_ENDORSEMENT)
            .taxRegistration(DEFAULT_TAX_REGISTRATION)
            .administrativ(DEFAULT_ADMINISTRATIV)
            .authorization(DEFAULT_AUTHORIZATION)
            .remarks(DEFAULT_REMARKS)
            .createDate(DEFAULT_CREATE_DATE)
            .updateDate(DEFAULT_UPDATE_DATE)
            .createBy(DEFAULT_CREATE_BY)
            .updateBy(DEFAULT_UPDATE_BY)
            .delFlag(DEFAULT_DEL_FLAG);
        return advertiser;
    }

    @Before
    public void initTest() {
        advertiser = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdvertiser() throws Exception {
        int databaseSizeBeforeCreate = advertiserRepository.findAll().size();

        // Create the Advertiser
        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isCreated());

        // Validate the Advertiser in the database
        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeCreate + 1);
        Advertiser testAdvertiser = advertiserList.get(advertiserList.size() - 1);
        assertThat(testAdvertiser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAdvertiser.getContacts()).isEqualTo(DEFAULT_CONTACTS);
        assertThat(testAdvertiser.getIndustryLevel1()).isEqualTo(DEFAULT_INDUSTRY_LEVEL_1);
        assertThat(testAdvertiser.getIndustryLevel2()).isEqualTo(DEFAULT_INDUSTRY_LEVEL_2);
        assertThat(testAdvertiser.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testAdvertiser.getAddress()).isEqualTo(DEFAULT_ADDRESS);
        assertThat(testAdvertiser.getCheckStatus()).isEqualTo(DEFAULT_CHECK_STATUS);
        assertThat(testAdvertiser.getWaxUid()).isEqualTo(DEFAULT_WAX_UID);
        assertThat(testAdvertiser.getOrgCode()).isEqualTo(DEFAULT_ORG_CODE);
        assertThat(testAdvertiser.getOrgCodeCertificate()).isEqualTo(DEFAULT_ORG_CODE_CERTIFICATE);
        assertThat(testAdvertiser.getBusinessLicense()).isEqualTo(DEFAULT_BUSINESS_LICENSE);
        assertThat(testAdvertiser.getIdCard()).isEqualTo(DEFAULT_ID_CARD);
        assertThat(testAdvertiser.getIcp()).isEqualTo(DEFAULT_ICP);
        assertThat(testAdvertiser.getTaxesCertificate()).isEqualTo(DEFAULT_TAXES_CERTIFICATE);
        assertThat(testAdvertiser.getNetworkBussinessCertificate()).isEqualTo(DEFAULT_NETWORK_BUSSINESS_CERTIFICATE);
        assertThat(testAdvertiser.getHealthOrgCertificate()).isEqualTo(DEFAULT_HEALTH_ORG_CERTIFICATE);
        assertThat(testAdvertiser.getHealthAdFile()).isEqualTo(DEFAULT_HEALTH_AD_FILE);
        assertThat(testAdvertiser.getGameAuthCertificate()).isEqualTo(DEFAULT_GAME_AUTH_CERTIFICATE);
        assertThat(testAdvertiser.getGameVersionFile()).isEqualTo(DEFAULT_GAME_VERSION_FILE);
        assertThat(testAdvertiser.getCelebrityEndorsement()).isEqualTo(DEFAULT_CELEBRITY_ENDORSEMENT);
        assertThat(testAdvertiser.getTaxRegistration()).isEqualTo(DEFAULT_TAX_REGISTRATION);
        assertThat(testAdvertiser.getAdministrativ()).isEqualTo(DEFAULT_ADMINISTRATIV);
        assertThat(testAdvertiser.getAuthorization()).isEqualTo(DEFAULT_AUTHORIZATION);
        assertThat(testAdvertiser.getRemarks()).isEqualTo(DEFAULT_REMARKS);
        assertThat(testAdvertiser.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAdvertiser.getUpdateDate()).isEqualTo(DEFAULT_UPDATE_DATE);
        assertThat(testAdvertiser.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testAdvertiser.getUpdateBy()).isEqualTo(DEFAULT_UPDATE_BY);
        assertThat(testAdvertiser.getDelFlag()).isEqualTo(DEFAULT_DEL_FLAG);
    }

    @Test
    @Transactional
    public void createAdvertiserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = advertiserRepository.findAll().size();

        // Create the Advertiser with an existing ID
        advertiser.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        // Validate the Advertiser in the database
        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertiserRepository.findAll().size();
        // set the field null
        advertiser.setName(null);

        // Create the Advertiser, which fails.

        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkContactsIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertiserRepository.findAll().size();
        // set the field null
        advertiser.setContacts(null);

        // Create the Advertiser, which fails.

        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndustryLevel1IsRequired() throws Exception {
        int databaseSizeBeforeTest = advertiserRepository.findAll().size();
        // set the field null
        advertiser.setIndustryLevel1(null);

        // Create the Advertiser, which fails.

        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIndustryLevel2IsRequired() throws Exception {
        int databaseSizeBeforeTest = advertiserRepository.findAll().size();
        // set the field null
        advertiser.setIndustryLevel2(null);

        // Create the Advertiser, which fails.

        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkWebsiteIsRequired() throws Exception {
        int databaseSizeBeforeTest = advertiserRepository.findAll().size();
        // set the field null
        advertiser.setWebsite(null);

        // Create the Advertiser, which fails.

        restAdvertiserMockMvc.perform(post("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isBadRequest());

        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAdvertisers() throws Exception {
        // Initialize the database
        advertiserRepository.saveAndFlush(advertiser);

        // Get all the advertiserList
        restAdvertiserMockMvc.perform(get("/api/advertisers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(advertiser.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].contacts").value(hasItem(DEFAULT_CONTACTS.toString())))
            .andExpect(jsonPath("$.[*].industryLevel1").value(hasItem(DEFAULT_INDUSTRY_LEVEL_1.toString())))
            .andExpect(jsonPath("$.[*].industryLevel2").value(hasItem(DEFAULT_INDUSTRY_LEVEL_2.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].address").value(hasItem(DEFAULT_ADDRESS.toString())))
            .andExpect(jsonPath("$.[*].checkStatus").value(hasItem(DEFAULT_CHECK_STATUS.toString())))
            .andExpect(jsonPath("$.[*].waxUid").value(hasItem(DEFAULT_WAX_UID.toString())))
            .andExpect(jsonPath("$.[*].orgCode").value(hasItem(DEFAULT_ORG_CODE.toString())))
            .andExpect(jsonPath("$.[*].orgCodeCertificate").value(hasItem(DEFAULT_ORG_CODE_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].businessLicense").value(hasItem(DEFAULT_BUSINESS_LICENSE.toString())))
            .andExpect(jsonPath("$.[*].idCard").value(hasItem(DEFAULT_ID_CARD.toString())))
            .andExpect(jsonPath("$.[*].icp").value(hasItem(DEFAULT_ICP.toString())))
            .andExpect(jsonPath("$.[*].taxesCertificate").value(hasItem(DEFAULT_TAXES_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].networkBussinessCertificate").value(hasItem(DEFAULT_NETWORK_BUSSINESS_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].healthOrgCertificate").value(hasItem(DEFAULT_HEALTH_ORG_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].healthAdFile").value(hasItem(DEFAULT_HEALTH_AD_FILE.toString())))
            .andExpect(jsonPath("$.[*].gameAuthCertificate").value(hasItem(DEFAULT_GAME_AUTH_CERTIFICATE.toString())))
            .andExpect(jsonPath("$.[*].gameVersionFile").value(hasItem(DEFAULT_GAME_VERSION_FILE.toString())))
            .andExpect(jsonPath("$.[*].celebrityEndorsement").value(hasItem(DEFAULT_CELEBRITY_ENDORSEMENT.toString())))
            .andExpect(jsonPath("$.[*].taxRegistration").value(hasItem(DEFAULT_TAX_REGISTRATION.toString())))
            .andExpect(jsonPath("$.[*].administrativ").value(hasItem(DEFAULT_ADMINISTRATIV.toString())))
            .andExpect(jsonPath("$.[*].authorization").value(hasItem(DEFAULT_AUTHORIZATION.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].updateDate").value(hasItem(DEFAULT_UPDATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.intValue())))
            .andExpect(jsonPath("$.[*].updateBy").value(hasItem(DEFAULT_UPDATE_BY.intValue())))
            .andExpect(jsonPath("$.[*].delFlag").value(hasItem(DEFAULT_DEL_FLAG.toString())));
    }

    @Test
    @Transactional
    public void getAdvertiser() throws Exception {
        // Initialize the database
        advertiserRepository.saveAndFlush(advertiser);

        // Get the advertiser
        restAdvertiserMockMvc.perform(get("/api/advertisers/{id}", advertiser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(advertiser.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.contacts").value(DEFAULT_CONTACTS.toString()))
            .andExpect(jsonPath("$.industryLevel1").value(DEFAULT_INDUSTRY_LEVEL_1.toString()))
            .andExpect(jsonPath("$.industryLevel2").value(DEFAULT_INDUSTRY_LEVEL_2.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.address").value(DEFAULT_ADDRESS.toString()))
            .andExpect(jsonPath("$.checkStatus").value(DEFAULT_CHECK_STATUS.toString()))
            .andExpect(jsonPath("$.waxUid").value(DEFAULT_WAX_UID.toString()))
            .andExpect(jsonPath("$.orgCode").value(DEFAULT_ORG_CODE.toString()))
            .andExpect(jsonPath("$.orgCodeCertificate").value(DEFAULT_ORG_CODE_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.businessLicense").value(DEFAULT_BUSINESS_LICENSE.toString()))
            .andExpect(jsonPath("$.idCard").value(DEFAULT_ID_CARD.toString()))
            .andExpect(jsonPath("$.icp").value(DEFAULT_ICP.toString()))
            .andExpect(jsonPath("$.taxesCertificate").value(DEFAULT_TAXES_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.networkBussinessCertificate").value(DEFAULT_NETWORK_BUSSINESS_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.healthOrgCertificate").value(DEFAULT_HEALTH_ORG_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.healthAdFile").value(DEFAULT_HEALTH_AD_FILE.toString()))
            .andExpect(jsonPath("$.gameAuthCertificate").value(DEFAULT_GAME_AUTH_CERTIFICATE.toString()))
            .andExpect(jsonPath("$.gameVersionFile").value(DEFAULT_GAME_VERSION_FILE.toString()))
            .andExpect(jsonPath("$.celebrityEndorsement").value(DEFAULT_CELEBRITY_ENDORSEMENT.toString()))
            .andExpect(jsonPath("$.taxRegistration").value(DEFAULT_TAX_REGISTRATION.toString()))
            .andExpect(jsonPath("$.administrativ").value(DEFAULT_ADMINISTRATIV.toString()))
            .andExpect(jsonPath("$.authorization").value(DEFAULT_AUTHORIZATION.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.updateDate").value(DEFAULT_UPDATE_DATE.toString()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.intValue()))
            .andExpect(jsonPath("$.updateBy").value(DEFAULT_UPDATE_BY.intValue()))
            .andExpect(jsonPath("$.delFlag").value(DEFAULT_DEL_FLAG.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAdvertiser() throws Exception {
        // Get the advertiser
        restAdvertiserMockMvc.perform(get("/api/advertisers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdvertiser() throws Exception {
        // Initialize the database
        advertiserService.save(advertiser);

        int databaseSizeBeforeUpdate = advertiserRepository.findAll().size();

        // Update the advertiser
        Advertiser updatedAdvertiser = advertiserRepository.findOne(advertiser.getId());
        updatedAdvertiser
            .name(UPDATED_NAME)
            .contacts(UPDATED_CONTACTS)
            .industryLevel1(UPDATED_INDUSTRY_LEVEL_1)
            .industryLevel2(UPDATED_INDUSTRY_LEVEL_2)
            .website(UPDATED_WEBSITE)
            .address(UPDATED_ADDRESS)
            .checkStatus(UPDATED_CHECK_STATUS)
            .waxUid(UPDATED_WAX_UID)
            .orgCode(UPDATED_ORG_CODE)
            .orgCodeCertificate(UPDATED_ORG_CODE_CERTIFICATE)
            .businessLicense(UPDATED_BUSINESS_LICENSE)
            .idCard(UPDATED_ID_CARD)
            .icp(UPDATED_ICP)
            .taxesCertificate(UPDATED_TAXES_CERTIFICATE)
            .networkBussinessCertificate(UPDATED_NETWORK_BUSSINESS_CERTIFICATE)
            .healthOrgCertificate(UPDATED_HEALTH_ORG_CERTIFICATE)
            .healthAdFile(UPDATED_HEALTH_AD_FILE)
            .gameAuthCertificate(UPDATED_GAME_AUTH_CERTIFICATE)
            .gameVersionFile(UPDATED_GAME_VERSION_FILE)
            .celebrityEndorsement(UPDATED_CELEBRITY_ENDORSEMENT)
            .taxRegistration(UPDATED_TAX_REGISTRATION)
            .administrativ(UPDATED_ADMINISTRATIV)
            .authorization(UPDATED_AUTHORIZATION)
            .remarks(UPDATED_REMARKS)
            .createDate(UPDATED_CREATE_DATE)
            .updateDate(UPDATED_UPDATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .updateBy(UPDATED_UPDATE_BY)
            .delFlag(UPDATED_DEL_FLAG);

        restAdvertiserMockMvc.perform(put("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAdvertiser)))
            .andExpect(status().isOk());

        // Validate the Advertiser in the database
        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeUpdate);
        Advertiser testAdvertiser = advertiserList.get(advertiserList.size() - 1);
        assertThat(testAdvertiser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAdvertiser.getContacts()).isEqualTo(UPDATED_CONTACTS);
        assertThat(testAdvertiser.getIndustryLevel1()).isEqualTo(UPDATED_INDUSTRY_LEVEL_1);
        assertThat(testAdvertiser.getIndustryLevel2()).isEqualTo(UPDATED_INDUSTRY_LEVEL_2);
        assertThat(testAdvertiser.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testAdvertiser.getAddress()).isEqualTo(UPDATED_ADDRESS);
        assertThat(testAdvertiser.getCheckStatus()).isEqualTo(UPDATED_CHECK_STATUS);
        assertThat(testAdvertiser.getWaxUid()).isEqualTo(UPDATED_WAX_UID);
        assertThat(testAdvertiser.getOrgCode()).isEqualTo(UPDATED_ORG_CODE);
        assertThat(testAdvertiser.getOrgCodeCertificate()).isEqualTo(UPDATED_ORG_CODE_CERTIFICATE);
        assertThat(testAdvertiser.getBusinessLicense()).isEqualTo(UPDATED_BUSINESS_LICENSE);
        assertThat(testAdvertiser.getIdCard()).isEqualTo(UPDATED_ID_CARD);
        assertThat(testAdvertiser.getIcp()).isEqualTo(UPDATED_ICP);
        assertThat(testAdvertiser.getTaxesCertificate()).isEqualTo(UPDATED_TAXES_CERTIFICATE);
        assertThat(testAdvertiser.getNetworkBussinessCertificate()).isEqualTo(UPDATED_NETWORK_BUSSINESS_CERTIFICATE);
        assertThat(testAdvertiser.getHealthOrgCertificate()).isEqualTo(UPDATED_HEALTH_ORG_CERTIFICATE);
        assertThat(testAdvertiser.getHealthAdFile()).isEqualTo(UPDATED_HEALTH_AD_FILE);
        assertThat(testAdvertiser.getGameAuthCertificate()).isEqualTo(UPDATED_GAME_AUTH_CERTIFICATE);
        assertThat(testAdvertiser.getGameVersionFile()).isEqualTo(UPDATED_GAME_VERSION_FILE);
        assertThat(testAdvertiser.getCelebrityEndorsement()).isEqualTo(UPDATED_CELEBRITY_ENDORSEMENT);
        assertThat(testAdvertiser.getTaxRegistration()).isEqualTo(UPDATED_TAX_REGISTRATION);
        assertThat(testAdvertiser.getAdministrativ()).isEqualTo(UPDATED_ADMINISTRATIV);
        assertThat(testAdvertiser.getAuthorization()).isEqualTo(UPDATED_AUTHORIZATION);
        assertThat(testAdvertiser.getRemarks()).isEqualTo(UPDATED_REMARKS);
        assertThat(testAdvertiser.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAdvertiser.getUpdateDate()).isEqualTo(UPDATED_UPDATE_DATE);
        assertThat(testAdvertiser.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testAdvertiser.getUpdateBy()).isEqualTo(UPDATED_UPDATE_BY);
        assertThat(testAdvertiser.getDelFlag()).isEqualTo(UPDATED_DEL_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingAdvertiser() throws Exception {
        int databaseSizeBeforeUpdate = advertiserRepository.findAll().size();

        // Create the Advertiser

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAdvertiserMockMvc.perform(put("/api/advertisers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(advertiser)))
            .andExpect(status().isCreated());

        // Validate the Advertiser in the database
        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAdvertiser() throws Exception {
        // Initialize the database
        advertiserService.save(advertiser);

        int databaseSizeBeforeDelete = advertiserRepository.findAll().size();

        // Get the advertiser
        restAdvertiserMockMvc.perform(delete("/api/advertisers/{id}", advertiser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Advertiser> advertiserList = advertiserRepository.findAll();
        assertThat(advertiserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Advertiser.class);
        Advertiser advertiser1 = new Advertiser();
        advertiser1.setId(1L);
        Advertiser advertiser2 = new Advertiser();
        advertiser2.setId(advertiser1.getId());
        assertThat(advertiser1).isEqualTo(advertiser2);
        advertiser2.setId(2L);
        assertThat(advertiser1).isNotEqualTo(advertiser2);
        advertiser1.setId(null);
        assertThat(advertiser1).isNotEqualTo(advertiser2);
    }
}
