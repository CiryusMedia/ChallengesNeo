package com.ciryusmedia.challengenetwork.challengespluginneo.mclogic.gui;

public enum ItemType {

    FILLER(1),
    LINE_FILLER(3),
    EXIT()
    MENU(-1);

    public final int CUSTOM_MODEL_DATA_ID;

    ItemType(int customModelDataId) {
        CUSTOM_MODEL_DATA_ID = customModelDataId;
    }
}
