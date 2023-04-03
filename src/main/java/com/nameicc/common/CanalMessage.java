package com.nameicc.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CanalMessage implements Serializable {

    private String type;

    private String table;

    private List<String> data;

    private String database;

    private Long es;

    private Integer id;

    private Boolean isDdl;

    private List<String> old;

    private List<String> pkNames;

    private String sql;

    private Long ts;

}
