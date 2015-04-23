package mj.todolistfx;

import mj.todolistfx.dao.PersonDao;
import mj.todolistfx.entity.Issue;
import mj.todolistfx.entity.Person;
import mj.todolistfx.service.IssueService;

import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        List<Issue> issues = IssueService.listIssues();
//        List<Person> persons = PersonDao.listPersons();

        System.out.println(issues.get(0).getResponsiblePerson());

    }

}
