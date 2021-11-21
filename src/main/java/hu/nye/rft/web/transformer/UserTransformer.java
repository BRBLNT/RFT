package hu.nye.rft.web.transformer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.data.domain.UserEntity;
import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    @Autowired
    public UserTransformer() {
    }

    public List<UserView> transform(Collection<UserEntity> collection){
        List<UserView> result = null;
        if(collection != null){
            result = collection.stream().map(this::transform).collect(Collectors.toList());
        }
        return result;
    }

    public UserView transform(UserEntity entity){
        UserView result = null;
        if(entity != null){
            result = UserView.builder()
                    .id(entity.getId())
                    .userName(entity.getUserName())
                    .password(entity.getPassword())
                    .build();
        }
        return result;
    }

    public UserEntity transform(CreateUserRequest request){
        UserEntity result = null;
        if(request != null){
            result = new UserEntity();
            result.setEmailAddress(request.getEmailAddress());
            result.setUserName(request.getUserName());
            result.setPassword(request.getPassword());
        }
        return result;
    }
}
