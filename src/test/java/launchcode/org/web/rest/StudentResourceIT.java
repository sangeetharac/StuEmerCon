package launchcode.org.web.rest;

import launchcode.org.StuemerconApp;
import launchcode.org.domain.Student;
import launchcode.org.repository.StudentRepository;
import launchcode.org.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.ZoneOffset;
import java.time.ZoneId;
import java.util.List;

import static launchcode.org.web.rest.TestUtil.sameInstant;
import static launchcode.org.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import launchcode.org.domain.enumeration.Gender;
/**
 * Integration tests for the {@link StudentResource} REST controller.
 */
@SpringBootTest(classes = StuemerconApp.class)
public class StudentResourceIT {

    private static final String DEFAULT_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LAST_NAME = "BBBBBBBBBB";

    private static final Integer DEFAULT_GRADE = 5;
    private static final Integer UPDATED_GRADE = 4;

    private static final Gender DEFAULT_GENDER = Gender.MALE;
    private static final Gender UPDATED_GENDER = Gender.FEMALE;

    private static final ZonedDateTime DEFAULT_BIRTH_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_BIRTH_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_EMR_1_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMR_1_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_1_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMR_1_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_1_RELATION_SHIP = "AAAAAAAAAA";
    private static final String UPDATED_EMR_1_RELATION_SHIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_1_EMAIL = "]@{9fq.fd";
    private static final String UPDATED_EMR_1_EMAIL = "b:O=%;@&;?.C^/i&w";

    private static final Integer DEFAULT_EMR_1_PHONE_NO = 1;
    private static final Integer UPDATED_EMR_1_PHONE_NO = 2;

    private static final String DEFAULT_EMR_2_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMR_2_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_2_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_EMR_2_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_2_RELATION_SHIP = "AAAAAAAAAA";
    private static final String UPDATED_EMR_2_RELATION_SHIP = "BBBBBBBBBB";

    private static final String DEFAULT_EMR_2_EMAIL = "*@^UOC+._|.";
    private static final String UPDATED_EMR_2_EMAIL = "W<Kh1b@jY.gaT9I0";

    private static final Integer DEFAULT_EMR_2_PHONE_NO = 1;
    private static final Integer UPDATED_EMR_2_PHONE_NO = 2;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restStudentMockMvc;

    private Student student;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final StudentResource studentResource = new StudentResource(studentRepository);
        this.restStudentMockMvc = MockMvcBuilders.standaloneSetup(studentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createEntity(EntityManager em) {
        Student student = new Student()
            .firstName(DEFAULT_FIRST_NAME)
            .lastName(DEFAULT_LAST_NAME)
            .grade(DEFAULT_GRADE)
            .gender(DEFAULT_GENDER)
            .birthDate(DEFAULT_BIRTH_DATE)
            .emr1FirstName(DEFAULT_EMR_1_FIRST_NAME)
            .emr1LastName(DEFAULT_EMR_1_LAST_NAME)
            .emr1RelationShip(DEFAULT_EMR_1_RELATION_SHIP)
            .emr1Email(DEFAULT_EMR_1_EMAIL)
            .emr1PhoneNo(DEFAULT_EMR_1_PHONE_NO)
            .emr2FirstName(DEFAULT_EMR_2_FIRST_NAME)
            .emr2LastName(DEFAULT_EMR_2_LAST_NAME)
            .emr2RelationShip(DEFAULT_EMR_2_RELATION_SHIP)
            .emr2Email(DEFAULT_EMR_2_EMAIL)
            .emr2PhoneNo(DEFAULT_EMR_2_PHONE_NO);
        return student;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Student createUpdatedEntity(EntityManager em) {
        Student student = new Student()
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .grade(UPDATED_GRADE)
            .gender(UPDATED_GENDER)
            .birthDate(UPDATED_BIRTH_DATE)
            .emr1FirstName(UPDATED_EMR_1_FIRST_NAME)
            .emr1LastName(UPDATED_EMR_1_LAST_NAME)
            .emr1RelationShip(UPDATED_EMR_1_RELATION_SHIP)
            .emr1Email(UPDATED_EMR_1_EMAIL)
            .emr1PhoneNo(UPDATED_EMR_1_PHONE_NO)
            .emr2FirstName(UPDATED_EMR_2_FIRST_NAME)
            .emr2LastName(UPDATED_EMR_2_LAST_NAME)
            .emr2RelationShip(UPDATED_EMR_2_RELATION_SHIP)
            .emr2Email(UPDATED_EMR_2_EMAIL)
            .emr2PhoneNo(UPDATED_EMR_2_PHONE_NO);
        return student;
    }

    @BeforeEach
    public void initTest() {
        student = createEntity(em);
    }

    @Test
    @Transactional
    public void createStudent() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isCreated());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate + 1);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstName()).isEqualTo(DEFAULT_FIRST_NAME);
        assertThat(testStudent.getLastName()).isEqualTo(DEFAULT_LAST_NAME);
        assertThat(testStudent.getGrade()).isEqualTo(DEFAULT_GRADE);
        assertThat(testStudent.getGender()).isEqualTo(DEFAULT_GENDER);
        assertThat(testStudent.getBirthDate()).isEqualTo(DEFAULT_BIRTH_DATE);
        assertThat(testStudent.getEmr1FirstName()).isEqualTo(DEFAULT_EMR_1_FIRST_NAME);
        assertThat(testStudent.getEmr1LastName()).isEqualTo(DEFAULT_EMR_1_LAST_NAME);
        assertThat(testStudent.getEmr1RelationShip()).isEqualTo(DEFAULT_EMR_1_RELATION_SHIP);
        assertThat(testStudent.getEmr1Email()).isEqualTo(DEFAULT_EMR_1_EMAIL);
        assertThat(testStudent.getEmr1PhoneNo()).isEqualTo(DEFAULT_EMR_1_PHONE_NO);
        assertThat(testStudent.getEmr2FirstName()).isEqualTo(DEFAULT_EMR_2_FIRST_NAME);
        assertThat(testStudent.getEmr2LastName()).isEqualTo(DEFAULT_EMR_2_LAST_NAME);
        assertThat(testStudent.getEmr2RelationShip()).isEqualTo(DEFAULT_EMR_2_RELATION_SHIP);
        assertThat(testStudent.getEmr2Email()).isEqualTo(DEFAULT_EMR_2_EMAIL);
        assertThat(testStudent.getEmr2PhoneNo()).isEqualTo(DEFAULT_EMR_2_PHONE_NO);
    }

    @Test
    @Transactional
    public void createStudentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = studentRepository.findAll().size();

        // Create the Student with an existing ID
        student.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setFirstName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setLastName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr1FirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr1FirstName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr1LastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr1LastName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr1EmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr1Email(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr2FirstNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr2FirstName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr2LastNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr2LastName(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEmr2EmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = studentRepository.findAll().size();
        // set the field null
        student.setEmr2Email(null);

        // Create the Student, which fails.

        restStudentMockMvc.perform(post("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllStudents() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get all the studentList
        restStudentMockMvc.perform(get("/api/students?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(student.getId().intValue())))
            .andExpect(jsonPath("$.[*].firstName").value(hasItem(DEFAULT_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].lastName").value(hasItem(DEFAULT_LAST_NAME)))
            .andExpect(jsonPath("$.[*].grade").value(hasItem(DEFAULT_GRADE)))
            .andExpect(jsonPath("$.[*].gender").value(hasItem(DEFAULT_GENDER.toString())))
            .andExpect(jsonPath("$.[*].birthDate").value(hasItem(sameInstant(DEFAULT_BIRTH_DATE))))
            .andExpect(jsonPath("$.[*].emr1FirstName").value(hasItem(DEFAULT_EMR_1_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].emr1LastName").value(hasItem(DEFAULT_EMR_1_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emr1RelationShip").value(hasItem(DEFAULT_EMR_1_RELATION_SHIP)))
            .andExpect(jsonPath("$.[*].emr1Email").value(hasItem(DEFAULT_EMR_1_EMAIL)))
            .andExpect(jsonPath("$.[*].emr1PhoneNo").value(hasItem(DEFAULT_EMR_1_PHONE_NO)))
            .andExpect(jsonPath("$.[*].emr2FirstName").value(hasItem(DEFAULT_EMR_2_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].emr2LastName").value(hasItem(DEFAULT_EMR_2_LAST_NAME)))
            .andExpect(jsonPath("$.[*].emr2RelationShip").value(hasItem(DEFAULT_EMR_2_RELATION_SHIP)))
            .andExpect(jsonPath("$.[*].emr2Email").value(hasItem(DEFAULT_EMR_2_EMAIL)))
            .andExpect(jsonPath("$.[*].emr2PhoneNo").value(hasItem(DEFAULT_EMR_2_PHONE_NO)));
    }
    
    @Test
    @Transactional
    public void getStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", student.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(student.getId().intValue()))
            .andExpect(jsonPath("$.firstName").value(DEFAULT_FIRST_NAME))
            .andExpect(jsonPath("$.lastName").value(DEFAULT_LAST_NAME))
            .andExpect(jsonPath("$.grade").value(DEFAULT_GRADE))
            .andExpect(jsonPath("$.gender").value(DEFAULT_GENDER.toString()))
            .andExpect(jsonPath("$.birthDate").value(sameInstant(DEFAULT_BIRTH_DATE)))
            .andExpect(jsonPath("$.emr1FirstName").value(DEFAULT_EMR_1_FIRST_NAME))
            .andExpect(jsonPath("$.emr1LastName").value(DEFAULT_EMR_1_LAST_NAME))
            .andExpect(jsonPath("$.emr1RelationShip").value(DEFAULT_EMR_1_RELATION_SHIP))
            .andExpect(jsonPath("$.emr1Email").value(DEFAULT_EMR_1_EMAIL))
            .andExpect(jsonPath("$.emr1PhoneNo").value(DEFAULT_EMR_1_PHONE_NO))
            .andExpect(jsonPath("$.emr2FirstName").value(DEFAULT_EMR_2_FIRST_NAME))
            .andExpect(jsonPath("$.emr2LastName").value(DEFAULT_EMR_2_LAST_NAME))
            .andExpect(jsonPath("$.emr2RelationShip").value(DEFAULT_EMR_2_RELATION_SHIP))
            .andExpect(jsonPath("$.emr2Email").value(DEFAULT_EMR_2_EMAIL))
            .andExpect(jsonPath("$.emr2PhoneNo").value(DEFAULT_EMR_2_PHONE_NO));
    }

    @Test
    @Transactional
    public void getNonExistingStudent() throws Exception {
        // Get the student
        restStudentMockMvc.perform(get("/api/students/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Update the student
        Student updatedStudent = studentRepository.findById(student.getId()).get();
        // Disconnect from session so that the updates on updatedStudent are not directly saved in db
        em.detach(updatedStudent);
        updatedStudent
            .firstName(UPDATED_FIRST_NAME)
            .lastName(UPDATED_LAST_NAME)
            .grade(UPDATED_GRADE)
            .gender(UPDATED_GENDER)
            .birthDate(UPDATED_BIRTH_DATE)
            .emr1FirstName(UPDATED_EMR_1_FIRST_NAME)
            .emr1LastName(UPDATED_EMR_1_LAST_NAME)
            .emr1RelationShip(UPDATED_EMR_1_RELATION_SHIP)
            .emr1Email(UPDATED_EMR_1_EMAIL)
            .emr1PhoneNo(UPDATED_EMR_1_PHONE_NO)
            .emr2FirstName(UPDATED_EMR_2_FIRST_NAME)
            .emr2LastName(UPDATED_EMR_2_LAST_NAME)
            .emr2RelationShip(UPDATED_EMR_2_RELATION_SHIP)
            .emr2Email(UPDATED_EMR_2_EMAIL)
            .emr2PhoneNo(UPDATED_EMR_2_PHONE_NO);

        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedStudent)))
            .andExpect(status().isOk());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
        Student testStudent = studentList.get(studentList.size() - 1);
        assertThat(testStudent.getFirstName()).isEqualTo(UPDATED_FIRST_NAME);
        assertThat(testStudent.getLastName()).isEqualTo(UPDATED_LAST_NAME);
        assertThat(testStudent.getGrade()).isEqualTo(UPDATED_GRADE);
        assertThat(testStudent.getGender()).isEqualTo(UPDATED_GENDER);
        assertThat(testStudent.getBirthDate()).isEqualTo(UPDATED_BIRTH_DATE);
        assertThat(testStudent.getEmr1FirstName()).isEqualTo(UPDATED_EMR_1_FIRST_NAME);
        assertThat(testStudent.getEmr1LastName()).isEqualTo(UPDATED_EMR_1_LAST_NAME);
        assertThat(testStudent.getEmr1RelationShip()).isEqualTo(UPDATED_EMR_1_RELATION_SHIP);
        assertThat(testStudent.getEmr1Email()).isEqualTo(UPDATED_EMR_1_EMAIL);
        assertThat(testStudent.getEmr1PhoneNo()).isEqualTo(UPDATED_EMR_1_PHONE_NO);
        assertThat(testStudent.getEmr2FirstName()).isEqualTo(UPDATED_EMR_2_FIRST_NAME);
        assertThat(testStudent.getEmr2LastName()).isEqualTo(UPDATED_EMR_2_LAST_NAME);
        assertThat(testStudent.getEmr2RelationShip()).isEqualTo(UPDATED_EMR_2_RELATION_SHIP);
        assertThat(testStudent.getEmr2Email()).isEqualTo(UPDATED_EMR_2_EMAIL);
        assertThat(testStudent.getEmr2PhoneNo()).isEqualTo(UPDATED_EMR_2_PHONE_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingStudent() throws Exception {
        int databaseSizeBeforeUpdate = studentRepository.findAll().size();

        // Create the Student

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restStudentMockMvc.perform(put("/api/students")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(student)))
            .andExpect(status().isBadRequest());

        // Validate the Student in the database
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteStudent() throws Exception {
        // Initialize the database
        studentRepository.saveAndFlush(student);

        int databaseSizeBeforeDelete = studentRepository.findAll().size();

        // Delete the student
        restStudentMockMvc.perform(delete("/api/students/{id}", student.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Student> studentList = studentRepository.findAll();
        assertThat(studentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
