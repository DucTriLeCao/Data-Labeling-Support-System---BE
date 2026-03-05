package project.dlss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.dlss.dto.DataItemDTO;
import project.dlss.entity.DataItem;
import project.dlss.entity.Dataset;
import project.dlss.repository.DataItemRepository;
import project.dlss.repository.DatasetRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DataItemService {

    private final DataItemRepository dataItemRepository;
    private final DatasetRepository datasetRepository;

    // CREATE
    public DataItem createDataItem(DataItemDTO dto) {

        Dataset dataset = datasetRepository.findById(dto.getDatasetId())
                .orElseThrow(() -> new RuntimeException("Dataset not found"));

        DataItem item = new DataItem();

        item.setDataset(dataset);
        item.setContent(dto.getContent());
        item.setStatus(dto.getStatus());
        item.setCreatedAt(LocalDateTime.now());

        return dataItemRepository.save(item);
    }

    // READ ALL
    public List<DataItem> getAll() {
        return dataItemRepository.findAll();
    }

    // READ BY DATASET
    public List<DataItem> getByDataset(Long datasetId) {
        return dataItemRepository.findByDatasetId(datasetId);
    }

    // UPDATE
    public DataItem update(Long id, DataItemDTO dto) {

        DataItem item = dataItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DataItem not found"));

        item.setContent(dto.getContent());
        item.setStatus(dto.getStatus());

        return dataItemRepository.save(item);
    }

    // DELETE
    public void delete(Long id) {
        dataItemRepository.deleteById(id);
    }

}