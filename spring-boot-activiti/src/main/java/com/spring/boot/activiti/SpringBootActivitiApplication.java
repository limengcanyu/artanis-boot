package com.spring.boot.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.api.model.shared.model.VariableInstance;
import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.process.runtime.connector.Connector;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
//@EnableScheduling
@SpringBootApplication
public class SpringBootActivitiApplication implements CommandLineRunner {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActivitiApplication.class, args);
    }

    @Override
    public void run(String... args) {
        securityUtil.logInAs("system");

        Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
        log.info("> Available Process definitions: " + processDefinitionPage.getTotalItems());
        for (ProcessDefinition pd : processDefinitionPage.getContent()) {
            log.info("\t > Process definition: " + pd);
        }
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void processText() {

        securityUtil.logInAs("system");

        Content content = pickRandomString();

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

        log.info("> Starting process to process content: " + content + " at " + formatter.format(new Date()));

        ProcessInstance processInstance = processRuntime.start(ProcessPayloadBuilder
                .start()
                .withProcessDefinitionKey("categorizeHumanProcess")
                .withName("Processing Content: " + content)
                .withVariable("content", content)
                .build());
        log.info(">>> Created Process Instance: " + processInstance);
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 5000)
    public void checkAndWorkOnTasksWhenAvailable() {
        securityUtil.logInAs("bob");

        Page<Task> tasks = taskRuntime.tasks(Pageable.of(0, 10));
        if (tasks.getTotalItems() > 0) {
            for (Task t : tasks.getContent()) {

                log.info("> Claiming task: " + t.getId());
                taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(t.getId()).build());

                List<VariableInstance> variables = taskRuntime.variables(TaskPayloadBuilder.variables().withTaskId(t.getId()).build());
                VariableInstance variableInstance = variables.get(0);
                if (variableInstance.getName().equals("content")) {
                    Content contentToProcess = variableInstance.getValue();
                    log.info("> Content received inside the task to approve: " + contentToProcess);

                    if (contentToProcess.getBody().contains("activiti")) {
                        log.info("> User Approving content");
                        contentToProcess.setApproved(true);
                    } else {
                        log.info("> User Discarding content");
                        contentToProcess.setApproved(false);
                    }
                    taskRuntime.complete(TaskPayloadBuilder.complete()
                            .withTaskId(t.getId()).withVariable("content", contentToProcess).build());
                }
            }
        } else {
            log.info("> There are no task for me to work on.");
        }
    }

    @Bean
    public Connector tagTextConnector() {
        return integrationContext -> {
            Content contentToTag = (Content) integrationContext.getInBoundVariables().get("content");
            contentToTag.getTags().add(" :) ");
            integrationContext.addOutBoundVariable("content",
                    contentToTag);
            log.info("Final Content: " + contentToTag);
            return integrationContext;
        };
    }

    @Bean
    public Connector discardTextConnector() {
        return integrationContext -> {
            Content contentToDiscard = (Content) integrationContext.getInBoundVariables().get("content");
            contentToDiscard.getTags().add(" :( ");
            integrationContext.addOutBoundVariable("content",
                    contentToDiscard);
            log.info("Final Content: " + contentToDiscard);
            return integrationContext;
        };
    }

    private Content pickRandomString() {
        String[] texts = {"hello from london", "Hi there from activiti!", "all good news over here.", "I've tweeted about activiti today.",
                "other boring projects.", "activiti cloud - Cloud Native Java BPM"};
        return new Content(texts[new Random().nextInt(texts.length)], false, null);
    }

}
