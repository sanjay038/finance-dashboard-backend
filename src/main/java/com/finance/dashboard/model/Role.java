package com.finance.dashboard.model;

public enum Role {
    ADMIN,     // Full access (Create, Read, Update, Delete)
    ANALYST,   // Read + Create + Analytics access
    VIEWER     // Read-only access
}
