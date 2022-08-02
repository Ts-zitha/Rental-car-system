package com.springproject.rentalcar.repository;

import com.springproject.rentalcar.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    public Client findByEmail(String email);

    @Query("select s from Client s where s.email=?1")
    public Client getClientByEmail(String email);
}
