package com.example.library.repo;

import com.example.library.model.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepo extends JpaRepository<Library, Integer> {
    Optional<Library> findByUsernameAndBookId(String username, int bookId);

    int countByUsername(String username);
}
