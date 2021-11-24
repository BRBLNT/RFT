package hu.nye.rft.web.transformer;


import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.data.domain.SubjectUserEntity;
import hu.nye.rft.web.domain.CreateSubjectUserRequest;
import hu.nye.rft.web.domain.SubjectUserView;
import org.springframework.stereotype.Component;

@Component
public class SubjectUserTransformer {

    public List<SubjectUserView> transformToView(Collection<SubjectUserEntity> entries){
        List<SubjectUserView> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToView)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<SubjectUserEntity> transformToEntity(Collection<SubjectUserView> entries){
        List<SubjectUserEntity> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToEntity)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public SubjectUserView transformToView(SubjectUserEntity entity){
        SubjectUserView result = null;
        if(entity != null){
            result = SubjectUserView.builder()
                    .userId(entity.getUserId())
                    .subjectId(entity.getSubjectId())
                    .build();
        }
        return result;
    }

    public SubjectUserEntity transformToEntity(SubjectUserView view){
        SubjectUserEntity result = null;
        if(view != null){
            result = new SubjectUserEntity();
            result.setUserId(view.getUserId());
            result.setSubjectId(view.getSubjectId());
        }
        return result;
    }

    public SubjectUserEntity transform(CreateSubjectUserRequest request){
        SubjectUserEntity result = null;
        if(request != null){
            result = new SubjectUserEntity();
            result.setUserId(request.getUserId());
            result.setSubjectId(request.getSubjectId());
        }
        return result;
    }
}
