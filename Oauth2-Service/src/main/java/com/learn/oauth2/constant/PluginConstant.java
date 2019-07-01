package com.learn.oauth2.constant;

public class PluginConstant {
    public enum PluginStatusType {

        ACTIVE(1, "Active"),

        INACTIVE(2, "Inactive");

        private final int value;

        private final String namePhrase;

        PluginStatusType(int value, String namePhrase) {
            this.value = value;
            this.namePhrase = namePhrase;
        }

        public int value() {
            return this.value;
        }

        public String getNamePhrase() {
            return this.namePhrase;
        }
    }
}
