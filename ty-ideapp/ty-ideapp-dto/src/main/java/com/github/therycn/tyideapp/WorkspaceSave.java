package com.github.therycn.tyideapp;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

/**
 * Workspace Save.
 * 
 * @author tcharass
 *
 */
@Getter
@Setter
public class WorkspaceSave {

    private Long id;

    @NotEmpty
    private String name;

}
