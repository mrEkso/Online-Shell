package com.example.shellfx.models.FileManager.State;

import com.example.shellfx.models.FileManager.FileManager;

public abstract class FileManagerState {
    public abstract void perform(FileManager ctx);
}
