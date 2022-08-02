package com.springproject.rentalcar.repository;

import com.springproject.rentalcar.entity.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {

    public Model findByModelName(String modelMake);

}
