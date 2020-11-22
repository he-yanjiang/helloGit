package com.sst.service.model;

import com.sst.bean.ContentDO;

import java.io.Serializable;
import java.util.List;

public class ContentModel  implements Serializable {
    private Integer id;

    private String name;

    private String keys;

    private List<ContentDO> contentDOList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKeys() {
        return keys;
    }

    public void setKeys(String keys) {
        this.keys = keys;
    }

    public List<ContentDO> getContentDOList() {
        return contentDOList;
    }

    public void setContentDOList(List<ContentDO> contentDOList) {
        this.contentDOList = contentDOList;
    }
}
