package com.crackerStudents.projectApp.mappers;

import com.crackerStudents.projectApp.domain.Tag;
import com.crackerStudents.projectApp.dto.TagDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {
    TagDTO tagToTagDto(Tag tag);

    List<TagDTO> tagsToTagsDto(List<Tag> tags);

    Tag tagDtoToTag(TagDTO tag);

    List<Tag> tagsDtoToTags(List<TagDTO> tags);

}
