package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogController {
    @Autowired
  private DogService dogService;

  /**
   * Endpoint to get all dogs.
   *
   * @return List of all dogs.
   */
  @GetMapping("/dogs")
  public Object getAllDogs() {
    return dogService.getAllDogs();
  }

  /**
   * Endpoint to get a dog by ID.
   *
   * @param id The ID of the dog to retrieve.
   * @return The dog with the specified ID.
   */
  @GetMapping("/dogs/{id}")
  public Dogs getDogById(@PathVariable long id) {
    return dogService.getDogById(id);
  }

  /**
   * Endpoint to get dogs by breed.
   *
   * @param breed The breed of the dogs to search for.
   * @return List of dogs with the specified breed.
   */
  @GetMapping("/dogs/breed/{breed}")
  public Object getDogsByBreed(@PathVariable String breed) {
    return dogService.getDogsByBreed(breed);
  }

  /**
   * Endpoint to get dogs older than a specified age.
   *
   * @param age The minimum age of the dogs to retrieve.
   * @return List of dogs older than the specified age.
   */
  @GetMapping("/dogs/olderThan")
  public Object getDogsOlderThan(@RequestParam(name = "age", defaultValue = "5") int age) {
    return new ResponseEntity<>(dogService.getDogsOlderThan(age), HttpStatus.OK);
  }

  /**
   * Endpoint to add a new dog.
   *
   * @param dog The dog to add.
   * @return The added dog.
   */
  @PostMapping("/dogs")
  public Object addDog(@RequestBody Dogs dog) {
    return dogService.addDog(dog);
  }

  /**
   * Endpoint to update an existing dog.
   *
   * @param id  The ID of the dog to update.
   * @param dog The updated dog information.
   * @return The updated dog.
   */
  @PutMapping("/dogs/{id}")
  public Dogs updateDog(@PathVariable Long id, @RequestBody Dogs dog) {
    dogService.updateDog(id, dog);
    return dogService.getDogById(id);
  }

  /**
   * Endpoint to delete a dog.
   *
   * @param id The ID of the dog to delete.
   * @return List of remaining dogs.
   */
  @DeleteMapping("/dogs/{id}")
  public Object deleteDog(@PathVariable Long id) {
    dogService.deleteDog(id);
    return dogService.getAllDogs();
  }

  /**
   * Endpoint to write dog data to a JSON file.
   *
   * @param dog The dog to write to file.
   * @return Confirmation message or file data.
   */
  @PostMapping("/dogs/writeFile")
  public Object writeJson(@RequestBody Dogs dog) {
    return dogService.writeJson(dog);
  }

  /**
   * Endpoint to read and return dog data from a JSON file.
   *
   * @return The contents of the JSON file.
   */
  @GetMapping("/dogs/readFile")
  public Object readJson() {
    return dogService.readJson();
  }

  /**
   * Endpoint to get dogs by name
   *
   * @param name The name of the dog to search for
   * @return List of students with the specified name
   */
  @GetMapping("/dogs/search")
  public Object getDogsByName (@RequestParam String name) {
    if (name != null){
      return dogService.getDogsByName(name);
    }
    else{
      return dogService.getAllDogs();
    }

  }

  
}