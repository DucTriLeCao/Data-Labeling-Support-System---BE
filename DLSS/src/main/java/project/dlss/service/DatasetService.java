package project.dlss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.dlss.entity.Dataset;
import project.dlss.repository.DatasetRepository;

import java.util.List;

@Service
public class DatasetService {

    @Autowired
    private DatasetRepository datasetRepository;

    // CREATE
    public Dataset createDataset(Dataset dataset) {
        return datasetRepository.save(dataset);
    }

    // READ ALL
    public List<Dataset> getAllDatasets() {
        return datasetRepository.findAll();
    }

    // READ BY ID
    public Dataset getDatasetById(Long id) {
        return datasetRepository.findById(id).orElse(null);
    }

    // READ BY PROJECT
    public List<Dataset> getDatasetsByProject(Long projectId) {
        return datasetRepository.findByProjectId(projectId);
    }

    // UPDATE
    public Dataset updateDataset(Long id, Dataset newDataset) {

        Dataset dataset = datasetRepository.findById(id).orElse(null);

        if (dataset != null) {

            dataset.setName(newDataset.getName());
            dataset.setDescription(newDataset.getDescription());
            dataset.setStatus(newDataset.getStatus());

            return datasetRepository.save(dataset);
        }

        return null;
    }

    // DELETE
    public void deleteDataset(Long id) {
        datasetRepository.deleteById(id);
    }

}