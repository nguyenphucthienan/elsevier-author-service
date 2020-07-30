package com.elsevier.elsevierauthorservice.repository;

import com.elsevier.elsevierauthorservice.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AuthorRepository extends JpaRepository<Author, UUID> {}
