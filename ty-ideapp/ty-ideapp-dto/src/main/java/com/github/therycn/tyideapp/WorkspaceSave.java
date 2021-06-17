package com.github.therycn.tyideapp;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Workspace Save.
 */
@Getter
@Setter
public class WorkspaceSave {

    private Long id;

    @NotEmpty
    private String name;

}
