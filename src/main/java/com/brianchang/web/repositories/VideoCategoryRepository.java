package com.brianchang.web.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brianchang.web.models.VideoCategory;

@Repository
public interface VideoCategoryRepository extends CrudRepository<VideoCategory, Long>{

}
