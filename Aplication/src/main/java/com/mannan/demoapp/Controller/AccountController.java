package com.mannan.demoapp.Controller;

import com.mannan.demoapp.Interfaces.*;
import com.mannan.demoapp.Model.AccountPackage.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.List;

@Controller
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private IAccountManager accountManager;
    private IInterestManager interestManager;
    private IProjectManager projectManager;
    private IExperienceManager experienceManager;
    private ISkillManager skillManager;
    private IConnectionManager connectionManager;


    @GetMapping("/my")
    public ResponseEntity<Integer> getMyAccount(){
        String test = "j.krastanov@student.fontys.nl";  //the test account you want to log in as
        int code = test.hashCode();
        //code = 1234;  //uncomment to get this account number and ignore hash
        return ResponseEntity.ok().body(code);
    }
/*    public ResponseEntity<Long> getMyAccount(@RequestHeader("x-ms-client-principal-name") String name){
        long pcn = name.hashCode();
        return ResponseEntity.ok().body(pcn);
    }*/

    //region Account REST API Methods
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() throws SQLException {
        List<Account> accounts = accountManager.getAccounts();
        if (accounts != null) {
            return ResponseEntity.ok().body(accounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{PCN}")
    public ResponseEntity<Account> getAccount(@PathVariable(value = "PCN") Long pcn) {
        Account account = accountManager.getAccountByPcn(pcn);
        if (account != null) {
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{pcn}")
    public ResponseEntity deleteAccount(@PathVariable Long pcn) {
        accountManager.deleteAccount(pcn);
        return ResponseEntity.ok().build();
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        if (!accountManager.addAccount(account)) {
            String entity = "Account with id" + account.getPcn() + "already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "account" + "/" + account.getPcn();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping
    public ResponseEntity<Account> updateAccount(@RequestBody Account account) {
        if (accountManager.updateAccount(account)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid id.", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/picture")
    public ResponseEntity<Account> updateAccountPicture(@RequestBody Account account) {
        if (accountManager.updatePicture(account)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid id.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{pcn}/view/{myPcn}")
    public ResponseEntity<Account> viewAccount(@PathVariable(value = "pcn") Long pcn, @PathVariable(value = "myPcn") Long myPcn)
    {
        Account account = accountManager.viewAccount(pcn, myPcn);
        if (account != null) {
            return ResponseEntity.ok().body(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<Account>> searchAccount(@PathVariable String name) throws SQLException {
        List<Account> accounts = accountManager.searchAccount(name);
        if (accounts != null) {
            return ResponseEntity.ok().body(accounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //endregion

    //region Interest REST API Methods
    @GetMapping("{pcn}/interests")
    public ResponseEntity<List<Interest>> getInterests(@PathVariable(value = "pcn") Long pcn) {
        List<Interest> interests = interestManager.getInterestsByPcn(pcn);

        if (interests != null) {
            return ResponseEntity.ok().body(interests);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/interests")
    public ResponseEntity<Interest> createInterest(@RequestBody Interest interest) {
        if (!interestManager.addInterest(interest)) {
            String entity = "Interest " + interest.getInterest() + "already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "interest" + "/" + interest.getInterest();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/interests")
    public ResponseEntity<Interest> deleteInterest(@RequestBody Interest interest)
    {
        interestManager.deleteInterest(interest);
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Project REST API Methods
    @GetMapping("{pcn}/projects")
    public ResponseEntity<List<Project>> getProjects(@PathVariable(value = "pcn") Long pcn) {
        List<Project> projects = projectManager.getProjectsByPcn(pcn);

        if (projects != null) {
            return ResponseEntity.ok().body(projects);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/projects")
    public ResponseEntity<Project> getProject(@RequestBody Project prj)
    {
        Project project = projectManager.getProject(prj);
        if (project != null) {
            return ResponseEntity.ok().body(project);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/projects")
    public ResponseEntity<Project> createProject(@RequestBody Project project)
    {
        if (!projectManager.addProject(project)) {
            String entity = "Project " + project.getTitle() + "already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "project" + "/" + project.getTitle();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("/projects")
    public ResponseEntity<Project> deleteProject(@RequestBody Project project)
    {
        projectManager.deleteProject(project);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/projects")
    public ResponseEntity<Project> updateProject(@RequestBody Project project)
    {
        if (projectManager.updateProject(project)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Project does not exist.", HttpStatus.NOT_FOUND);
        }
    }
    //endregion

    //region Experience REST API Methods
    @GetMapping("{pcn}/experiences")
    public ResponseEntity<List<Experience>> getExperiences(@PathVariable(value = "pcn") Long pcn)
    {
        List<Experience> experiences = experienceManager.getExperiencesByPcn(pcn);

        if (experiences != null) {
            return ResponseEntity.ok().body(experiences);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/experiences")
    public ResponseEntity<Experience> createExperience(@RequestBody Experience experience) {
        if (!experienceManager.addExperience(experience)) {
            String entity = "Experience " + experience.getType() + "already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Experience" + "/" + experience.getType();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @PutMapping("/experiences")
    public ResponseEntity<Experience> updateExperience(@RequestBody Experience experience) {
        if (experienceManager.updateExperience(experience)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Experience does not exist.", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/experiences")
    public ResponseEntity<Experience> deleteExperience(@RequestBody Experience experience) {
        experienceManager.deleteExperience(experience);
        return ResponseEntity.ok().build();
    }
    //endregion

    //region Skill REST API Methods
    @GetMapping("{pcn}/skills")
    public ResponseEntity<List<Skill>> getSkillsByPcn(@PathVariable Long pcn) {
        List<Skill> skills = skillManager.getSkillsByPcn(pcn);
        if (skills != null) {
            return ResponseEntity.ok().body(skills);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/skills")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        if (!skillManager.addSkill(skill)) {
            String entity = "Skill " + skill.getSkill() + "already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "skill" + "/" + skill.getSkill();
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }
    @DeleteMapping("/skills")
    public ResponseEntity<Skill> deleteSkill(@RequestBody Skill skill) {
        skillManager.deleteSkill(skill);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/skills")
    public ResponseEntity<Skill> updateSkill(@RequestBody Skill skill) {
        if (skillManager.updateSkill(skill)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Skill does not exist.", HttpStatus.NOT_FOUND);
        }
    }
    //endregion

    //region Connection REST API Methods
    @GetMapping("connection/{pcn1}/{pcn2}")
    public ResponseEntity<Connection> getConnection(@PathVariable Long pcn1, @PathVariable Long pcn2) {
        Connection connection = connectionManager.getConnectionByPCNs(pcn1, pcn2);
        if (connection != null) {
            return ResponseEntity.ok().body(connection);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("connections/pending/{pcn}")
    public ResponseEntity<List<Connection>> viewPendingConnections(@PathVariable Long pcn) {
        List<Connection> connections = connectionManager.getPendingConnections(pcn);
        if (connections != null) {
            return ResponseEntity.ok().body(connections);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("connections/accepted/{pcn}")
    public ResponseEntity<List<Connection>> viewAcceptedConnections(@PathVariable Long pcn) {
        List<Connection> connections = connectionManager.getAcceptedConnections(pcn);
        if (connections != null) {
            return ResponseEntity.ok().body(connections);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("connection")
    public ResponseEntity<Connection> createConnection(@RequestBody Connection connection) {
        if (!connectionManager.addConnection(connection)) {
            String entity = "Connection already exists.";
            return new ResponseEntity(entity, HttpStatus.CONFLICT);
        } else {
            String url = "Connection" + "/";
            URI uri = URI.create(url);
            return new ResponseEntity(uri, HttpStatus.CREATED);
        }
    }

    @DeleteMapping("connection")
    public ResponseEntity<Connection> deleteConnection(@RequestBody Connection connection) {
        connectionManager.deleteConnection(connection);
        return ResponseEntity.ok().build();
    }

    @PutMapping("connection")
    public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection) {
        if (connectionManager.updateConnection(connection)) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity("Please provide a valid id.", HttpStatus.NOT_FOUND);
        }
    }
    //endregion
}
