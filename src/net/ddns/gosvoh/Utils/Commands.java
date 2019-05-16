/*
 * Copyright (c) 2019.
 * Программа написана gosvoh (Алексей Вохмин)
 * This program has been written by gosvoh (Alexey Vokhmin)
 */

package net.ddns.gosvoh.Utils;

import java.util.HashMap;

public class Commands {
    public static HashMap<String, CommandList> commandCodes = new HashMap<>();

    static {
        commandCodes.put("save", CommandList.SAVE);
        commandCodes.put("remove_greater_key", CommandList.REMOVE_GREATER_KEY);
        commandCodes.put("info", CommandList.INFO);
        commandCodes.put("add_if_max", CommandList.ADD_IF_MAX);
        commandCodes.put("remove_lower", CommandList.REMOVE_LOWER);
        commandCodes.put("show", CommandList.SHOW);
        commandCodes.put("insert", CommandList.INSERT);
        commandCodes.put("remove", CommandList.REMOVE);
        commandCodes.put("help", CommandList.HELP);
    }

    public enum CommandList {
        SAVE(false),
        REMOVE_GREATER_KEY(true),
        INFO(false),
        ADD_IF_MAX(true),
        REMOVE_LOWER(true),
        SHOW(false),
        INSERT(true),
        REMOVE(true),
        HELP(false);

        private boolean hasArgs;

        private CommandList(boolean hasArgs) {
            this.hasArgs = hasArgs;
        }
    }
}
