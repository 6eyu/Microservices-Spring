package com.learn.oauth2.constant;

public class SubscriptionConstant {
    public enum SubscriptionStatusType {

        SETTING_NEEDED(0, "Setting needed"),

        ACTIVE(1, "Active"),

        INACTIVE(2, "Inactive");

        private final int value;

        private final String namePhrase;

        SubscriptionStatusType(int value, String namePhrase) {
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
