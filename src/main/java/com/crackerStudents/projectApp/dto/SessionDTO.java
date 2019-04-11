package com.crackerStudents.projectApp.dto;


public class SessionDTO {
    private int id;

    private SessionRowDTO rowId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SessionRowDTO getRowId() {
        return rowId;
    }

    public void setRowId(SessionRowDTO rowId) {
        this.rowId = rowId;
    }
}
