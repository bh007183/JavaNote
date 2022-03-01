package com.diary.models;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteDao extends CrudRepository<Note, Long>{

}