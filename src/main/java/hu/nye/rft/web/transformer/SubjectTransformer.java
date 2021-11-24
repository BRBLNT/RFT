package hu.nye.rft.web.transformer;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import hu.nye.rft.data.domain.SubjectEntryEntity;
import hu.nye.rft.data.domain.UserEntity;
import hu.nye.rft.web.domain.CreateSubjectRequest;
import hu.nye.rft.web.domain.CreateUserRequest;
import hu.nye.rft.web.domain.SubjectEntryView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectTransformer {

    public List<SubjectEntryView> transformToView(Collection<SubjectEntryEntity> entries){
        List<SubjectEntryView> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToView)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public List<SubjectEntryEntity> transformToEntity(Collection<SubjectEntryView> entries){
        List<SubjectEntryEntity> result = null;
        if(entries != null){
            result = entries.stream()
                    .map(this::transformToEntity)
                    .collect(Collectors.toList());
        }
        return result;
    }

    public SubjectEntryView transformToView(SubjectEntryEntity entity){
        SubjectEntryView result = null;
        if(entity != null){
            result = SubjectEntryView.builder()
                    .name(entity.getName())
                    .teacherId(entity.getTeacherId())
                    .classroom(entity.getClassroom())
                    .date(entity.getDate())
                    .build();
        }
        return result;
    }

    public SubjectEntryEntity transformToEntity(SubjectEntryView view){
        SubjectEntryEntity result = null;
        if(view != null){
            result = new SubjectEntryEntity();
            result.setName(view.getName());
            result.setTeacherId(view.getTeacherId());
            result.setClassroom(view.getClassroom());
            result.setDate(view.getDate());
        }
        return result;
    }

    public SubjectEntryEntity transform(CreateSubjectRequest request){
        SubjectEntryEntity result = null;
        if(request != null){
            result = new SubjectEntryEntity();
            result.setName(request.getName());
            result.setTeacherId(request.getTeacherId());
            result.setClassroom(request.getClassroom());
            result.setDate(request.getDate());
        }
        return result;
    }

}
