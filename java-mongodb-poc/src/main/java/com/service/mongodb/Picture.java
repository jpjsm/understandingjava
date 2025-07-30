package com.service.mongodb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Set;

public class Picture {
    private String path = "";
    private Set<UUID> thumbIds = new HashSet<UUID>();

    public Picture(String path, Collection<UUID> thumbIds) {
        this.path = path;
        this.thumbIds = new HashSet<UUID>(thumbIds);
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public void setThumbIds(Collection<UUID> thumbIds) {
        this.thumbIds = new HashSet<UUID>(thumbIds);
    }

    public Set<UUID> getThumbIds() {
        return this.thumbIds;
    }

    public List<String> getThumbIdStrs() {
        List<String> thumbIdStrs = new ArrayList<String>();
        for (UUID thumbid : this.thumbIds) {
            thumbIdStrs.add(thumbid.toString());
        }

        return thumbIdStrs;
    }
}
