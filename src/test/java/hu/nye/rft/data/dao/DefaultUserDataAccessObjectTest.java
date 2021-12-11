package hu.nye.rft.data.dao;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.assertEquals;

import hu.nye.rft.data.domain.UserEntity;
import hu.nye.rft.data.repository.UserRepository;
import hu.nye.rft.error.UserNotFoundException;
import hu.nye.rft.error.EmailAlreadyUseException;

import org.mockito.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Test class of {@link DefaultUserDataAccessObject}.
 */
public class DefaultUserDataAccessObjectTest {

    private static final Long USER_ID = 69L;
    private static final String USER_NAME = "Test User";


    @InjectMocks
    private DefaultUserDataAccessObject underTest;

    @Mock
    private UserRepository userRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        underTest = null;
    }

    @Test
    public void testGetUserByIdShouldReturnUserWhenCalledWithExistingId() {
        //given
        UserEntity expected = createUser();
        given(userRepository.findById(USER_ID)).willReturn(createOptionalUser());

        //when
        UserEntity actual = underTest.getUserById(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userRepository).should().findById(USER_ID);
    }



    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundWhenCalledWithNonExistingId() {
        //given
        given(userRepository.findById(USER_ID)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUserById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnListOfUserWhenCalled() {
        //given
        Collection<UserEntity> expected = List.of(createUser());
        given(userRepository.findAll()).willReturn(List.of(createUser()));

        //when
        Collection<UserEntity> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userRepository).should().findAll();
    }

    @Test
    public void testAddUserShouldSaveTheUserWhenCalledWithNonExistingId() {
        //given
        UserEntity user = createUser();

        //when
        underTest.addUser(user);

        //then
        then(userRepository).should().save(user);
    }

    @Test(expectedExceptions = EmailAlreadyUseException.class)
    public void testAddUserShouldThrowUserAlreadyExistExceptionWhenCalledWithExistingEmail() {
        //given
        UserEntity user = createUser();
        willThrow(DataIntegrityViolationException.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddUserShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        UserEntity user = createUser();
        willThrow(Exception.class).given(userRepository).save(user);

        //when
        underTest.addUser(user);

        //then exception thrown
    }

    @Test
    public void testDeleteUserByIdShouldDelegateToRepository() {
        //given
        //when
        underTest.deleteUserById(USER_ID);

        //then
        then(userRepository).should().deleteById(USER_ID);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testDeleteUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithNonExistingId() {
        //given
        willThrow(EmptyResultDataAccessException.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testDeleteUserByIdShouldThrowRuntimeExceptionWhenUnhandledExceptionHappens() {
        //given
        willThrow(Exception.class).given(userRepository).deleteById(USER_ID);

        //when
        underTest.deleteUserById(USER_ID);

        //then exception thrown
    }

    private Optional<UserEntity> createOptionalUser(){
        return Optional.of(createUser());
    }

    private UserEntity createUser() {
        UserEntity user = new UserEntity();
        user.setId(USER_ID);
        user.setUserName(USER_NAME);

        return user;
    }




}