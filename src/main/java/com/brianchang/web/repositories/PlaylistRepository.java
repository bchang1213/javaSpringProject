package com.brianchang.web.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.brianchang.web.models.Playlist;

@Repository
public interface PlaylistRepository  extends CrudRepository<Playlist,Long>{

}
