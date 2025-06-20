package org.example.librarymanagement.repository;

import org.example.librarymanagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategotyRepository extends JpaRepository<Category,Integer> {
}
