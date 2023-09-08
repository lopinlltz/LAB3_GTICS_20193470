package com.example.lab3.repository;
import com.example.lab3.entity.Employees;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employees,Integer> {
    List<Employees> findByFirstname(String texto1);
    List<Employees> findByLastname(String texto2);

    @Query(nativeQuery = true, value = "SELECT * FROM employees where first_name = ?1")
    List<Employees> buscarPorNombre(String texto1);

    @Query(nativeQuery = true, value = "SELECT * FROM employees where last_name = ?1")
    List<Employees> buscarPorApellido(String texto2);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET phone_number = ?1 WHERE employee_id = ?2")
    void actualizarTelefono(String phonenumber, int employeeid);

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE employees SET salary = ?1 WHERE employee_id = ?2")
    void actualizarSalario(String salary, int employeeid);

}
