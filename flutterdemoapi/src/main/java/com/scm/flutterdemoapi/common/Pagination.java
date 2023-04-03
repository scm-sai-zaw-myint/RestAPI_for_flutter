package com.scm.flutterdemoapi.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pagination{
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String next;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String previous;
    @JsonIgnore
    private String prefix;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer limit;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer offset;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer page;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer size;
    @JsonIgnore
    private List<?> data;

    @JsonIgnore
    private static final String APP_URL = "http://localhost:9000/api";

    public Pagination(List<?> data,Integer offset, Integer limit, String prefix) {
        this.paginate(data, offset, limit, prefix);
    }
    private void paginate(List<?> data,Integer offset, Integer limit, String prefix){
        List<?> allPage = getCollection(data, limit);
        if(offset > allPage.size()) {
            return;
        }
        this.offset = offset;
        this.limit = limit;
        this.page = allPage.size();
        this.size = data.size();
        this.prefix = prefix;
        this.setNext();
        this.setPrevious();
        if(offset < allPage.size() + 1)
            this.data = (List<?>) allPage.get(offset-1);
    }

    public void setNext() {
        StringBuffer url = new StringBuffer(APP_URL);
        Integer o = this.page > this.offset ? (this.offset + 1) : this.offset;
        this.next = url.append("/").append(prefix).append("?page=").append(o).toString();
    }
    public void setPrevious() {
        StringBuffer url = new StringBuffer(APP_URL);
        Integer o = this.offset > 1 ? (this.offset - 1) : this.offset;
        this.previous = url.append("/").append(prefix).append("?page=").append(o).toString();
    }

    private <T> List<List<T>> getCollection(Collection<T> c, Integer limit) {
        if (c == null)
            return Collections.emptyList();
        List<T> list = new ArrayList<T>(c);
        if (limit == null || limit <= 0 || limit > list.size())
            limit = list.size();
        int numPages = (int) Math.ceil((double) list.size() / (double) limit);
        List<List<T>> pages = new ArrayList<List<T>>(numPages);
        for (int pageNum = 0; pageNum < numPages;) {
            pages.add(list.subList(pageNum * limit, Math.min(++pageNum * limit, list.size())));
        }
        return pages;
    }
}
