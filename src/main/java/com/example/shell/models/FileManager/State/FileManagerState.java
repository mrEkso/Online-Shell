package com.example.shell.models.FileManager.State;

import com.example.shell.models.FileManager.FileManager;

public abstract class FileManagerState {
    public abstract void perform(FileManager ctx);
}
