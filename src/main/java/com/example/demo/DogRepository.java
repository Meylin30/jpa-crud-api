package com.example.demo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dogs, Long> {
      /**
     * Get all dogs by breed.
     *
     * @param breed The breed to search for.
     * @return List of dogs with the specified breed.
     */
    List<Dogs> getDogsByBreed(String breed);

    /**
     * Get all dogs older than a certain age.
     *
     * @param age The minimum age to search for.
     * @return List of dogs older than the specified age.
     */
    @Query(value = "SELECT * FROM dogs d WHERE d.age > ?1", nativeQuery = true)
    List<Dogs> getDogsOlderThan(int age);

    /**
     * Get all dogs with names containing a given keyword.
     *
     * @param name The partial name to search for.
     * @return List of dogs whose names contain the specified keyword.
     */
    @Query(value = "SELECT * FROM dogs d WHERE d.name LIKE %?1%", nativeQuery = true)
    List<Dogs> getDogsByName(String name);
}
