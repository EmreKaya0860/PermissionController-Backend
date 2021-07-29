package com.example.permissionControl.personsRepository;
import com.example.permissionControl.Persons.persons;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface personsRepository extends PagingAndSortingRepository<persons,Integer> {

}
