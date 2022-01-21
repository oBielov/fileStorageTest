package com.cml.test.filestorage.repository;

import com.cml.test.filestorage.documents.File;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import java.util.List;
import java.util.Optional;

public interface FileRepository extends ElasticsearchRepository<File, Long> {

    Optional<File> findFileById(String id);

    Page<File> findAllByNameContains(String pattern, Pageable pageable);

    Page<File> findAllByTagsIn(List<String> tags, Pageable pageable);

    Page<File> findAll(Pageable pageable);

}
