package com.tadiwa.backend.features.executive_members;

/** The role of an executive member in a cell */
public enum ExecutiveRole {
    POLITICAL_COMMISAR {
        @Override
        public String toString() {
            return "Political Commisar";
        }
    },

    TREASURER {
        @Override
        public String toString() {
            return "Treasurer";
        }
    },

    REGULAR {
        @Override
        public String toString() {
            return "Regular";
        }
    },

    CHAIRMAN {
        @Override
        public String toString() {
            return "Chairman";
        }
    };

    public abstract String toString();
}
