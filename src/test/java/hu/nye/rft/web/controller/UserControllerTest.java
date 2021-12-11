package hu.nye.rft.web.controller;

import hu.nye.rft.error.EmailAlreadyUseException;
import hu.nye.rft.error.UserNotFoundException;
import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.UserView;
import hu.nye.rft.web.service.UserServiceInterface;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.*;
import static org.testng.Assert.*;

/**
 * Test class of the {@link UserController}.
 */
public class UserControllerTest {


    private static final Long USER_ID = 458L;
    private static final Long OTHER_USER_ID = 8596L;
    private static final String USER_NAME = "Test User";
    private static final String OTHER_USER_NAME = "Other Test User";
    private static final String EMAIL_ADDRESS = "test_user@mail.com";

    @InjectMocks
    private UserController underTest;

    @Mock
    private UserServiceInterface userService;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        userService = null;
        underTest = null;
    }

    @Test
    public void testGetUserShouldReturnUserViewWhenCalledWithExistingId() {
        //given
        UserView expected = createUser(USER_ID, USER_NAME);
        given(userService.getUserById(USER_ID)).willReturn(expected);

        //when
        UserView actual = underTest.getUser(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userService).should().getUserById(USER_ID);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserShouldReturnUserUserNotFoundExceptionWhenCalledWithNonExistingId() {
        //given
        given(userService.getUserById(USER_ID)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUser(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnAListOfUser() {
        //given
        List<UserView> expected = List.of(
                createUser(USER_ID, USER_NAME),
                createUser(OTHER_USER_ID, OTHER_USER_NAME)
        );
        given(userService.getAllUser()).willReturn(expected);

        //when
        List<UserView> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userService).should().getAllUser();
    }

    @Test
    public void testAddUserShouldDelegateToUserServiceAndNotThrowExceptionWhenCalledWithValidUser() {
        //given
        CreateUserRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);

        //when
        underTest.addUser(request);

        //then
        then(userService).should().addUser(request);
    }

    @Test(expectedExceptions = EmailAlreadyUseException.class)
    public void testAddUserShouldThrowEmailAlreadyInUseExceptionWhenCalledWithExistingEmail() {
        //given
        CreateUserRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);
        willThrow(EmailAlreadyUseException.class).given(userService).addUser(request);

        //when
        underTest.addUser(request);

        //then exception thrown
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testAddUserShouldThrowUserAlreadyExistExceptionWhenCalledWithInValidUser() {
        //given
        CreateUserRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);
        willThrow(RuntimeException.class).given(userService).addUser(request);

        //when
        underTest.addUser(request);

        //then exception thrown
    }

    @Test
    public void testDeleteUserShouldDelegateToUserService() {
        //given
        //when
        underTest.deleteUser(USER_ID);

        //then
        then(userService).should().deleteUserById(USER_ID);
    }

    private CreateUserRequest createCreateUserRequest(String username, String emailAddress){
        return CreateUserRequest.builder()
                .userName(username)
                .emailAddress(emailAddress)
                .build();
    }

    private UserView createUser(Long userId, String username) {
        return UserView.builder()
                .id(userId)
                .userName(username)
                .build();
    }

}
