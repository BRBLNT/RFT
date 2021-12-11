package hu.nye.rft.web.service;

import hu.nye.rft.data.dao.UserDataAccessObjectInterface;
import hu.nye.rft.data.domain.SubjectEntryEntity;
import hu.nye.rft.data.domain.UserEntity;
import hu.nye.rft.error.UserNotFoundException;
import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.SubjectEntryView;
import hu.nye.rft.web.domain.SubjectUserView;
import hu.nye.rft.web.domain.UserView;
import hu.nye.rft.web.transformer.UserTransformer;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.testng.Assert.assertEquals;

/**
 * Test call of {@link DefaultUserService}.
 */
public class DefaultUserServiceTest {

    private static final int QUANTITY = 15;
    private static final int OTHER_QUANTITY = 3;
    private static final Integer ITEM_QUANTITY = 36;
    private static final Long ENTRY_ID = 98L;
    private static final Long LIST_ID = 3658L;
    private static final Long OTHER_LIST_ID = 8547L;
    private static final Long USER_ID = 458L;
    private static final Long OTHER_USER_ID = 8596L;
    private static final String ITEM_NAME = "Item one";
    private static final String OTHER_ITEM_NAME = "Item two";
    private static final String USER_NAME = "Test User A";
    private static final String OTHER_USER_NAME = "Test User B";
    private static final String LIST_NAME = "List name";
    private static final String OTHER_LIST_NAME = "Other list name";
    private static final String EMAIL_ADDRESS = "test_user@mail.com";

    @Mock
    private UserDataAccessObjectInterface userDataAccessObject;
    @Mock
    private UserTransformer transformer;

    @InjectMocks
    private DefaultUserService underTest;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterMethod
    public void tearDown() {
        userDataAccessObject = null;
        transformer = null;
        underTest = null;
    }

    @Test
    public void testGetUserByIdShouldTransformAndReturnUserWhenCalledWithValidId() {
        //given
        UserEntity entity = createUserEntity(USER_ID, USER_NAME);
        UserView expected = createUserView(USER_ID, USER_NAME);
        given(userDataAccessObject.getUserById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willReturn(expected);

        //when
        UserView actual = underTest.getUserById(USER_ID);

        //then
        assertEquals(actual, expected);
        then(userDataAccessObject).should().getUserById(USER_ID);
        then(transformer).should().transform(entity);
    }

    @Test(expectedExceptions = UserNotFoundException.class)
    public void testGetUserByIdShouldThrowUserNotFoundExceptionWhenCalledWithInvalidId() {
        //given
        UserEntity entity = createUserEntity(USER_ID, USER_NAME);
        given(userDataAccessObject.getUserById(USER_ID)).willReturn(entity);
        given(transformer.transform(entity)).willThrow(UserNotFoundException.class);

        //when
        underTest.getUserById(USER_ID);

        //then exception thrown
    }

    @Test
    public void testGetAllUserShouldReturnAListOfUser() {
        //given
        List<UserView> unsortedUserList = List.of(
                createUserView(USER_ID, USER_NAME),
                createUserView(OTHER_USER_ID, OTHER_USER_NAME)
        );
        List<UserEntity> userEntities = List.of(
                createUserEntity(USER_ID, USER_NAME),
                createUserEntity(OTHER_USER_ID, OTHER_USER_NAME)
        );
        given(userDataAccessObject.getAllUser()).willReturn(userEntities);
        given(transformer.transform(userEntities)).willReturn(unsortedUserList);
        List<UserView> expected = List.of(
                createUserView(USER_ID, USER_NAME),
                createUserView(OTHER_USER_ID, OTHER_USER_NAME)
        );

        //when
        List<UserView> actual = underTest.getAllUser();

        //then
        assertEquals(actual, expected);
        then(userDataAccessObject).should().getAllUser();
        then(transformer).should().transform(userEntities);
    }

    @Test
    public void testAddUserShouldDelegateToTransformerAndUserAccessObject() {
        //given
        CreateUserRequest request = createCreateUserRequest(USER_NAME, EMAIL_ADDRESS);
        UserEntity userEntity = createUserEntity(USER_ID, USER_NAME);
        given(transformer.transform(request)).willReturn(userEntity);

        //when
        underTest.addUser(request);

        //then
        then(userDataAccessObject).should().addUser(userEntity);
    }

    private UserEntity createUserEntity(Long userId, String username) {
        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setUserName(username);
        return user;
    }


    private SubjectEntryEntity createShoppingListEntryEntity() {
        SubjectEntryEntity entry = new SubjectEntryEntity();
        entry.setId(ENTRY_ID);
        return entry;
    }

    private CreateUserRequest createCreateUserRequest(String username, String emailAddress){
        return CreateUserRequest.builder()
                .userName(username)
                .emailAddress(emailAddress)
                .build();
    }

    private UserView createUserView(Long userId, String username) {
        return UserView.builder()
                .id(userId)
                .userName(username)
                .build();
    }


}
