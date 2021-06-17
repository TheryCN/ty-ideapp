package com.github.therycn.tyideapp.repository;

import com.github.therycn.tyideapp.entity.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Collection;

/**
 * Workspace Repository (REST is available at /workspaces).
 *
 * @author TheryLeopard
 */
@CrossOrigin
public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {

    Collection<Workspace> findByUserIdOrderByName(Long userId);

}
