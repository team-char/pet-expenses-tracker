package com.levibrooke.petexpensestracker.Model;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository <Category, Long>{
    Category findById(String id);
}
