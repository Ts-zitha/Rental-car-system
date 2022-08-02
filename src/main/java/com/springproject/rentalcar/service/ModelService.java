package com.springproject.rentalcar.service;

import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.entity.Model;

import java.util.List;

public interface ModelService {

    public List<Model> fetchModels();
    public Model createModel(Model model);
    public Model getModel(Long modelId);
    public Model updateModel(Model model, Long modelId);
    public Model getModelByName(String modelName);
    public Model deleteModel(Long modelId);
}
