package com.example.demo;

import java.io.IOException;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DogService {
    
@Autowired
  private DogRepository dogRepository;

  /**
   * Method to get all dogs.
   *
   * @return List of all dogs.
   */
  public Object getAllDogs() {
    return dogRepository.findAll();
  }

  /**
   * Method to get a dog by ID.
   *
   * @param dogId The ID of the dog to retrieve.
   * @return The dog with the specified ID.
   */
  public Dogs getDogById(@PathVariable long dogId) {
    return dogRepository.findById(dogId).orElse(null);
  }

  /**
   * Method to get dogs by name.
   *
   * @param name The name of the dog to search for.
   * @return List of dogs with the specified name.
   */
  public Object getDogsByName(String name) {
    return dogRepository.getDogsByName(name);
  }
  

  /**
   * Method to get dogs by breed.
   *
   * @param breed The breed to search for.
   * @return List of dogs with the specified breed.
   */
  public Object getDogsByBreed(String breed) {
    return dogRepository.getDogsByBreed(breed);
  }

  /**
   * Method to get dogs older than a specified age.
   *
   * @param age The age threshold.
   * @return List of dogs older than the specified age.
   */
  public Object getDogsOlderThan(int age) {
    return dogRepository.getDogsOlderThan(age);
  }

  /**
   * Method to add a new dog.
   *
   * @param dog The dog to add.
   * @return The saved dog.
   */
  public Dogs addDog(Dogs dog) {
    return dogRepository.save(dog);
  }

  /**
   * Method to update an existing dog.
   *
   * @param dogId The ID of the dog to update.
   * @param dog   The updated dog information.
   * @return The updated dog.
   */
  public Dogs updateDog(Long dogId, Dogs dog) {
    dog.setId(dogId);
    return dogRepository.save(dog);
  }

  /**
   * Method to delete a dog by ID.
   *
   * @param dogId The ID of the dog to delete.
   */
  public void deleteDog(Long dogId) {
    dogRepository.deleteById(dogId);
  }

  /**
   * Method to write a dog object to a JSON file.
   *
   * @param dog The dog object to write.
   * @return A success or error message.
   */
  public String writeJson(Dogs dog) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      objectMapper.writeValue(new File("dogs.json"), dog);
      return "Dog written to JSON file successfully";
    } catch (IOException e) {
      e.printStackTrace();
      return "Error writing dog to JSON file";
    }
  }

  /**
   * Method to read a dog object from a JSON file.
   *
   * @return The dog object read from the JSON file.
   */
  public Object readJson() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.readValue(new File("dogs.json"), Dogs.class);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }


}
    
