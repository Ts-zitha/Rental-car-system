package com.springproject.rentalcar.service;


import com.springproject.rentalcar.entity.Category;
import com.springproject.rentalcar.entity.Model;
import com.springproject.rentalcar.exception.BusinessException;
import com.springproject.rentalcar.exception.EmptyTableException;
import com.springproject.rentalcar.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ModelServiceImp implements ModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Override
    public List<Model> fetchModels() {

        List<Model> models =  modelRepository.findAll();
        if(models.size() == 0){
            throw new EmptyTableException("There are no car models");
        }else {
            return models;
        }
    }

    @Override
    public Model createModel(Model model) {

        if(model.getModelName() == "" || model.getModelYear() == "" || model.getModelMake() ==""){
            throw new BusinessException(400, "Enter valid category type");
        }else {
            return modelRepository.save(model);
        }
    }

    @Override
    public Model getModel(Long modelId) {
        return modelRepository.findById(modelId).get();
    }

    @Override
    public Model updateModel(Model model, Long modelId) {
        Model existingModel = modelRepository.findById(modelId).get();

        if(model.getModelMake() == "" || model.getModelYear() == "" || model.getModelName() ==""){
            throw new BusinessException(400, "Enter valid model fields");
        }else {
            existingModel.setModelMake(model.getModelMake());
            existingModel.setModelName(model.getModelName());
            existingModel.setModelYear(model.getModelYear());
            return modelRepository.save(existingModel);
        }
    }

    @Override
    public Model getModelByName(String modelName) {
        if(modelName == ""){
            throw new BusinessException(400, "Enter valid model name");
        }else {
            Model model = modelRepository.findByModelName(modelName);
            if(model==null){
                throw new NoSuchElementException("Model does not exists");
            }else {
                return model;
            }
        }
    }

    @Override
    public Model deleteModel(Long modelId) {
        Model deletedModel= modelRepository.findById(modelId).get();

        modelRepository.delete(deletedModel);
        return deletedModel;
    }
}
