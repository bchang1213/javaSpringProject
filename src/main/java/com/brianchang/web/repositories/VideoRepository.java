package com.brianchang.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brianchang.web.models.Video;

@Repository
public interface VideoRepository extends CrudRepository<Video,Long>{
	List<Video> findAll();
}
