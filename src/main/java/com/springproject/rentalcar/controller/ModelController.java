package com.springproject.rentalcar.controller;

import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.entity.Model;
import com.springproject.rentalcar.service.ModelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/model")
public class ModelController {

    @Autowired
    public ModelServiceImp modelServiceImp;

    @GetMapping()
    public List<Model> fetchModels(){
        return modelServiceImp.fetchModels();
    }

    @PostMapping()
    public Model createModel(@RequestBody Model model){
        return modelServiceImp.createModel(model);
    }

    @GetMapping("/{modelId}")
    public Model getModel(@PathVariable("modelId") Long modelId){
        return modelServiceImp.getModel(modelId);
    }

    @PutMapping("/{modelId}")
    public Model updateModel(@RequestBody Model model, @PathVariable("modelId") Long modelId){
        return modelServiceImp.updateModel(model, modelId);
    }
    @GetMapping("/byModelName/{modelName}")
    public Model getModelByMakeName(@PathVariable("modelName") String modelName){
        return modelServiceImp.getModelByName(modelName);
    }

    @DeleteMapping("/{modelId}")
    public Model deleteCategory(@PathVariable("modelId") Long modelId){
        return modelServiceImp.deleteModel(modelId);
    }
}
