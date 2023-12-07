package com.ymsaadi.gamerecommendationapi.mappers;

import com.ymsaadi.gamerecommendationapi.models.UserReview;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserReviewMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserReviewFromDto(UserReview userReview, @MappingTarget UserReview entity);
}
