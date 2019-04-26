package com.graduate.jrsmain.vo;
public class ProcessVO {

    private AdvProcess advProcess;
    private Integer page;
    private Integer size;

    public AdvProcess getAdvProcess() {
        return advProcess;
    }

    public void setAdvProcess(AdvProcess advProcess) {
        this.advProcess = advProcess;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
