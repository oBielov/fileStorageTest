package com.cml.test.filestorage.service.domain.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

/**
 * POJO data class to add tags to file.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TagsData {
    String id; //file id
    ArrayList<String> tags;
}
