package com.cml.test.filestorage.repository;

import com.cml.test.filestorage.documents.File;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.ArrayList;
import java.util.Optional;

public interface FileRepository extends ElasticsearchRepository<File, Long> {

    public void deleteFileById(String id);

    public Optional<File> findFileById(String id);

    public ArrayList<File> findAllByNameContains(String pattern);

}
