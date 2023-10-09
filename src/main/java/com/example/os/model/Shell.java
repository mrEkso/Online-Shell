package com.example.os.model;

public class Shell {
    private final int id;
    private final int current_disk_id;
    private final int current_folder_id;

    public Shell(int id, int current_disk_id, int current_folder_id) {
        this.id = id;
        this.current_disk_id = current_disk_id;
        this.current_folder_id = current_folder_id;
    }

    public int getId() {
        return id;
    }

    public int getCurrent_disk_id() {
        return current_disk_id;
    }

    public int getCurrent_folder_id() {
        return current_folder_id;
    }
}
