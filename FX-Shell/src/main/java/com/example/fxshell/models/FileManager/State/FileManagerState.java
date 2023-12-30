package com.example.fxshell.models.FileManager.State;

import com.example.fxshell.models.FileManager.FileManager;

public abstract class FileManagerState {
    public abstract void perform(FileManager ctx);
}
