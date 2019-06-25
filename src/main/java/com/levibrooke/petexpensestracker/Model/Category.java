package com.levibrooke.petexpensestracker.Model;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="category_type")
public class Category {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    long id;

    @Column(unique = true)
    String name;

//    @OneToMany(mappedBy="expense")
//    public List<Expense> expenses;

//    @Autowired
//    CategoryRepository categoryRepository;

    public Category(){
//        loadData();
    }

    public Category(String name){
        this.name = name;
    }

//    public void loadData(){
//        List<String> categoryList = new ArrayList<>();
//        categoryList.add("Food");
//        categoryList.add("Vet Expense");
//        categoryList.add("Supplies/Toys");
//        categoryList.add("Grooming");
//        categoryList.add("Daycare");
//        categoryList.add("Pet Date");
//        categoryList.add("Miscellaneous");
//
//        for(int i = 0; i<categoryList.size(); i++){
//            Category newCategory = new Category(categoryList.get(i));
//            categoryRepository.save( newCategory);
//        }
//    }

    public long getId() { return id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public List<Expense> getExpenses() { return expenses; }
}
