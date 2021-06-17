package com.github.therycn.tyideapp;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

/**
 * Idea Save.
 */
@Getter
@Setter
public class IdeaSave {

    private Long id;

    @NotEmpty
    private String name;

    private String subTitle;

    private String description;

    private int rating;

    private boolean achieve;

    private String feasibility;

    private Long workspaceId;

    private Date createdOn;

    private Date updatedOn;

    private List<CircleSave> localizations;

}
