package com.github.therycn.tyideapp;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Idea Save.
 * 
 * @author TheryLeopard
 *
 */
@Getter
@Setter
public class IdeaSave {

	private Long id;

	private String name;

	private String subTitle;

	private String description;

	private int rating;

	private String feasibility;

	private Long workspaceId;

	private Date createdOn;

	private Date updatedOn;

}
