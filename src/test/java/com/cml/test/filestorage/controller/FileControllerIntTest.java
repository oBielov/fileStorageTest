package com.cml.test.filestorage.controller;


import com.cml.test.filestorage.documents.File;
import com.cml.test.filestorage.service.FileService;
import com.cml.test.filestorage.service.domain.response.BadResponse;
import com.cml.test.filestorage.service.domain.response.OkResponse;
import com.cml.test.filestorage.service.domain.response.SaveResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.UUID;
import static org.mockito.Mockito.when;

@WebMvcTest(FileController.class)
class FileControllerIntTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    FileService service;


    @Test
    void ReturnOkResponseWhenSaveValidFile(){
        File file = new File(UUID.randomUUID().toString(),
                "doc.txt",
                125,
                List.of("testTag"));
        when(service.saveFile(file)).thenReturn(new SaveResponse(file.getId()));
    }

    @Test
    void ReturnBadResponseWhenSaveFileWithNegativeSize(){
        when(service.saveFile(new File(UUID.randomUUID().toString(),
                "doc.txt",
                -120,
                List.of("testTag")))).thenReturn(new BadResponse(false, "File size can not be negative"));
    }

    @Test
    void ReturnBadResponseWhenSaveFileWithBlankName(){
        when(service.saveFile(new File(UUID.randomUUID().toString(),
                "",
                120,
                List.of("testTag")))).thenReturn(new BadResponse(false, "File name can not be blank"));
    }

    @Test
    void ReturnOkResponseWhenDeleteExistingFile() {
        when(service.deleteFile("0ed676c5-3088-4810-8ecf-027faa0417cb"))
                .thenReturn(new OkResponse(true));
    }

    @Test
    void ReturnBadResponseWhenDeleteFileThatNotExists(){
        when(service.deleteFile(" ")).thenReturn(new BadResponse(false, "No such file"));
    }

}