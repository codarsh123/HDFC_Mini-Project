package com.hdfc.midterm.library_management_app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hdfc.midterm.library_management_app.entities.Reports;
@Repository
public interface ReportsRepository extends JpaRepository<Reports, Long>{

}
