package com.vector.megumin;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeguminRepository extends JpaRepository<Megumin,Long> {
    Page<Megumin> findAll(Pageable page);
}
