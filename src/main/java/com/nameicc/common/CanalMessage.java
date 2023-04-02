package com.nameicc.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CanalMessage<T> implements Serializable {

    private String type;

    private String table;

    private List<T> data;

    private String database;

    private Long es;

    private Integer id;

    private Boolean isDdl;

    private List<T> old;

    private List<String> pkNames;

    private String sql;

    private Long ts;

}
