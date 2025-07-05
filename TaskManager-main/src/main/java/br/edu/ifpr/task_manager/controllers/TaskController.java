package br.edu.ifpr.task_manager.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import br.edu.ifpr.task_manager.models.Task;
import br.edu.ifpr.task_manager.models.Status;

@Controller
@RequestMapping({ "", "/tasks" })
public class TaskController {

    private List<Task> tasks = new ArrayList<>();

    public TaskController () {
        tasks.add(new Task(1L, "Task1", "T1 descrição", LocalDate.now()));
        tasks.add(new Task(2L, "Task2", "T2 descrição", LocalDate.now()));
        tasks.add(new Task(3L, "Task3", "T3 descrição", LocalDate.now()));
    }

    @GetMapping({ "", "/", "/tasks" })
    public String listTask(Model model) {
        model.addAttribute("tasksList", tasks);
        return "task-list";
    }

    @GetMapping("/andamento")
    public String listEmAndamento(Model model) {
        List<Task> emAndamento = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getStatus() == Status.EM_ANDAMENTO) {
                emAndamento.add(task);
            }
        }
        model.addAttribute("tasksList", emAndamento);
        return "task-list";
    }

    @GetMapping("/create")
    public String createTask() {
        return "task-create";
    }

    @PostMapping("/create")
    public String saveTask(Task task) {
        task.setId(Long.valueOf(tasks.size() + 1));
        tasks.add(task);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                model.addAttribute("task", task);
                return "task-edit";
            }
        }
        return "redirect:/tasks";
    }

    @PostMapping("/edit")
    public String editTask(Task taskForm) {
        for (Task task : tasks) {
            if (taskForm.getId().equals(task.getId())) {
                tasks.set(tasks.indexOf(task), taskForm);
                break;
            }
        }
        return "redirect:/tasks";
    }

    @GetMapping("/remove/{id}")
    public String removeTask(@PathVariable Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
        return "redirect:/tasks";
    }

    @GetMapping("/details/{id}")
    public String detailsTask(@PathVariable Long id, Model model) {
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                model.addAttribute("task", task);
                return "task-details";
            }
        }
        return "redirect:/tasks";
    }
}
