package com.cml.test.filestorage.service;

import com.cml.test.filestorage.documents.File;
import com.cml.test.filestorage.repository.FileRepository;
import com.cml.test.filestorage.service.domain.data.TagsData;
import com.cml.test.filestorage.service.domain.exceptions.TagsNotFoundException;
import com.cml.test.filestorage.service.domain.response.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class FileService {

    private final FileRepository repository;

    @Autowired
    public FileService(FileRepository repository){
        this.repository = repository;
    }

    public Response saveFile(File file){
        file.getTags().add(getFileType(file.getName()));// see getFileType()
        file.setId(UUID.randomUUID().toString());
        repository.save(file);
        log.info("File successfully saved " + file.getName());
        log.info("tags are: " + file.getTags());
        log.info("File id: " + file.getId());
        return new SaveResponse(file.getId());
    }


    public Response deleteFile(String id){
        File file = getFileById(id);
        log.info("File " + file.getName() + " found");
        repository.delete(file);
        log.info("File successfully deleted");
        return new OkResponse(true);
    }


    public Response addTags(TagsData data){
        File file = getFileById(data.getId());
        log.info("File " + file.getName() + " found");
        file.getTags().addAll(data.getTags());
        repository.save(file);
        log.info("Tags: " + data.getTags() + " added in file " + file.getName());
        return new OkResponse(true);
    }

    public FileListResponse getList(List<String> tags, int page, int size, String q){
        if(tags != null){
            return getByTags(tags, page, size);
        }
        if(q != null){
            return getByNameContains(q, page, size);
        }
        return getAll(page, size);
    }


    private FileListResponse getByNameContains(String pattern, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<File> filePage = repository.findAllByNameContains(pattern, pageable);
        return FileListResponse.builder()
                .total(filePage.getTotalElements())
                .page(filePage.getContent())
                .build();
    }

    private FileListResponse getByTags(List<String> tags, int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<File> filePage = repository.findAllByTagsIn(tags, pageable);
        return FileListResponse.builder()
                .total(filePage.getTotalElements())
                .page(filePage.getContent())
                .build();
    }

    private FileListResponse getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<File> filePage = repository.findAll(pageable);
        return FileListResponse.builder()
                .total(filePage.getTotalElements())
                .page(filePage.getContent())
                .build();
    }

    @SneakyThrows
    public Response deleteTags(TagsData data){
        File file = getFileById(data.getId());
        log.info("File " + file.getName() + " found");
        if(file.getTags().containsAll(data.getTags())){
            file.getTags().removeAll(data.getTags());
            log.info("Tags: " + data.getTags() + " successfully deleted");
            return new OkResponse(true);
        }
        throw new TagsNotFoundException("Tags not found on a file!");
    }

   /*Get file MIME type by its extension. Returns "application/octet-stream" if file type unknown */
    private String getFileType(String name){
        Tika tika = new Tika();
        return tika.detect(name);
    }

    @SneakyThrows
    private File getFileById(String id){
        return repository.findFileById(id).orElseThrow(() -> new FileNotFoundException("No such file"));
    }

}
