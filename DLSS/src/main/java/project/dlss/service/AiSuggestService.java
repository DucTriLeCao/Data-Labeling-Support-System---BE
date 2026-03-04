package project.dlss.service;

import java.util.List;

public interface AiSuggestService {

    List<String> suggestLabel(Long dataItemId, String content);
}
